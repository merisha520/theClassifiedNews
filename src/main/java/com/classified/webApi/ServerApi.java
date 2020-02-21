package com.classified.webApi;

import com.classified.algorithm.classifier.NaiveBayes;
import com.classified.algorithm.constants.Constants;
import com.classified.algorithm.datamodel.KnowledgeBase;
import com.classified.algorithm.features.PorterStemmer;
import com.classified.algorithm.features.Tokenize;
import com.classified.algorithm.predict.Predictor;
import com.classified.database.dao.NewsArticleRepo;
import com.classified.database.dao.NewsWriterRepo;
import com.classified.database.models.NewsArticle;
import com.classified.database.models.NewsWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import spark.Request;
import spark.Response;
import spark.servlet.SparkApplication;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

public class ServerApi implements SparkApplication {

    @Override
    public void init() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        /**
         * Returns current date
         */
        get("/currentdate", (req, res) -> createJson(res, getDate()));

        /**
         * @param writerid or category or none
         */
        get("/news", (req, res) -> {

            NewsArticleRepo repo = new NewsArticleRepo();
            ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();
            if (req.queryParams().iterator().hasNext()) {
                String queryParam = req.queryParams().iterator().next();
                if (queryParam.equals("category")) {
                    String category = req.queryParams("category");
                    articles = repo.getNewsbyCategory(category);
                } else if (queryParam.equals("writerid")) {
                    Integer id = Integer.parseInt(req.queryParams("writerid"));
                    articles = repo.getNewsbyWriterId(id);
                } else if (queryParam.equals("search")) {
                    String title = req.queryParams("search");
                    articles = repo.getNewsbyTitle(title);
                } else if (queryParam.equals("newsid")) {
                    Integer id = Integer.parseInt(req.queryParams("newsid"));
                    articles.add(repo.getArticleByNewsId(id));
                }
            } else {
                articles = repo.getLatestNews();
            }

            return new Gson().toJson(articles);
        });

        /**
         * @params email, password
         */
        post("/login", (req, res) -> {
            NewsWriterRepo newsWriterRepo = new NewsWriterRepo();
            NewsWriter newsWriter = new Gson().fromJson(req.body(), NewsWriter.class);

            newsWriter.setEmail(newsWriter.getEmail());
            newsWriter.setAuthenticationKey(newsWriter.getAuthenticationKey());

            newsWriter = newsWriterRepo.getWriterByEmailAndPassword(newsWriter);

            return createJson(res, newsWriter);
        });

        /**
         * @params fullname, email, password, companyname
         */
        post("/signup", (req, res) -> {
            NewsWriterRepo repo = new NewsWriterRepo();
            NewsWriter writer = new Gson().fromJson(req.body(), NewsWriter.class);

            writer.setAuthenticationKey(writer.getAuthenticationKey());

            Integer id = repo.insertNewsWriter(writer);
            writer.setId(id);
            writer.setLoginStatus(true);

            return createJson(res, writer);
        });

        /**
         * @param json file containing NewsArticle's all fields
         */
        post("/postnews", (req, res) -> {
            String StringDIR = "C:\\xampp\\htdocs\\classifiednews\\hostedimages\\";
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(StringDIR));
            Part filePart = req.raw().getPart("image");
            String imageName = UUID.randomUUID().toString() + filePart.getSubmittedFileName();
            String imageUrl = StringDIR + imageName;
            try (InputStream inputStream = filePart.getInputStream()) {
                OutputStream outputStream = new FileOutputStream(imageUrl);
                IOUtils.copy(inputStream, outputStream);
                outputStream.close();
            }

            NewsArticle article = new NewsArticle();

            article.setWriterId(Integer.parseInt(req.queryParams("writerId")));
            article.setTitle(req.queryParams("title"));
            article.setcontents(req.queryParams("contents"));

            Predictor predictor = new Predictor();

            article.setCategory(predictor.predict(htmlRemover(req.queryParams("contents"))));

            try {
                if (article.getImageUrl() == null)
                    article.setImageUrl("null");
            } catch (Exception e) {
                article.setImageUrl("null");
            }

            article = postNews(article);
            if (article != null) {
                return new Gson().toJson(article);
            } else {
                return "{'status' : 'false'}";
            }
        });

        post("/demo", (req, res) -> {
            DemoModelDTO demoModelDTO = new Gson().fromJson(req.body(), DemoModelDTO.class);
            return testJson(req, demoModelDTO.getDemoText());
        });
    }


    private DateModel getDate() {
        return new DateModel();
    }

    private String createJson(Response res, Object o) {
        res.type("application/json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(o);
    }

    private String testJson(Request req, String demoStrig) throws Exception {
        Tokenize tokenize = new Tokenize();
        String demoString = htmlRemover(demoStrig);

        PorterStemmer porterStemmer = new PorterStemmer();

        FileInputStream fileInputStream = new FileInputStream(Constants.MODEL_DIR);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        KnowledgeBase knowledgeBase = (KnowledgeBase) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        NaiveBayes nb = new NaiveBayes(knowledgeBase, tokenize);

        DemoModel demoModel = new DemoModel();

        //Text Cleaning
        String[] cleanTextArray = tokenize.getTokens(demoString);
        demoModel.setCleanedText(cleanTextArray);

        //Stop word removal
        String[] removeStopWordsArray = tokenize.removeStopWords(cleanTextArray);
        demoModel.setStopWordsRemoved(removeStopWordsArray);

        //Porter Stemming
        ArrayList<String> porterStemmedArrayList = new ArrayList<>();
        for(String i : removeStopWordsArray){
            porterStemmedArrayList.add(porterStemmer.stemWord(i));
        }
        demoModel.setPorterStemmed(porterStemmedArrayList.toArray(new String[porterStemmedArrayList.size()]));

        //Keyword Count
        Map<String, Integer> keywordCount = tokenize.getKeywordCounts(porterStemmedArrayList.toArray(new String[porterStemmedArrayList.size()]));
        demoModel.setKeyWordCounts(keywordCount);

        //Predictions
        String category = nb.predict(demoString);
        Map<String, Double> probabilities = nb.getPredictedProbabilities();
        demoModel.setPredictedCategory(category);
        demoModel.setPredictedProbabilities(probabilities);

        return new Gson().toJson(demoModel);
    }

    private NewsArticle postNews(NewsArticle article) {
        NewsArticleRepo repo = new NewsArticleRepo();

        String category;
        Predictor predictor = new Predictor();
        try {
            category = predictor.predict(article.getcontents());
        } catch (Exception e) {
            category = "uncategorized";
            e.printStackTrace();
        }

        article.setCategory(category);
        Integer id = repo.insertNewsArticle(article);

        if (id != 0)
            article.setNewsId(id);
        else
            article = null;

        return article;
    }

    private String htmlRemover(String content) {
        StringBuilder sb = new StringBuilder();
        String cleaned = Jsoup.parse(content).text();
        return cleaned;
    }

    public static void main(String[] args) {
        new ServerApi().init();
    }
}