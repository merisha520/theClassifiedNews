package com.classified.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public final static String DBDRIVERNAME = "org.postgresql.Driver";
    public final static String DBPATH = "jdbc:postgresql://localhost:5432/classifiednewsdb";
    public final static String DBUSER = "postgres";
    public final static String DBPASSWORD = "0112358";

    public static Connection connect() {
        try {
            Class.forName(DBDRIVERNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBPATH, DBUSER, DBPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

//    public static void main(String[] args) {
//
//    }
//
//    //Passed
//    public void getArticlesTest(){
//        NewsArticleRepo re = new NewsArticleRepo();
//        ArrayList<NewsArticle> art = re.getNewsbyCategory("Sports");
//
//        for(int i=0; i<5; i++){
//            if(i <= (art.size() - 1)) {
//                System.out.println(art.get(i).toString());
//            } else {
//                break;
//            }
//        }
//    }
//
//    //Passed
//    public void insertTest(){
//        NewsWriter newsWriter = new NewsWriter(1, "Suranjan Poudel",
//                "so@gmail.com", "ABC company", "justme");
//
//        NewsWriterRepo newsWriterRepo = new NewsWriterRepo();
//        Integer id = null;
//
//        try {
//            id = newsWriterRepo.insertNewsWriter(newsWriter);
//        } catch (SQLException e) {
//            System.out.println("News Writer already exists\n"+e.getMessage());
//        }
//
//        NewsArticle newsArticle = new NewsArticle(1, "url:to:img", "this is title",
//                "Contents of the news", "Sports", "Suranjan Poudel", id, 20);
//
//        NewsArticleRepo newsArticleRepo = new NewsArticleRepo();
//
//        id = newsArticleRepo.insertNewsArticle(newsArticle);
//
//        System.out.println("NewsID: "+id);
//    }
//
//    //Passed
//    public void loginTest(){
//        NewsWriter newsWriter = new NewsWriter();
//
//        newsWriter.setEmail("so@gmail.com");
//        newsWriter.setAuthenticationKey("justme");
//
//        NewsWriterRepo newsWriterRepo = new NewsWriterRepo();
//
//        NewsWriter returnedWriter = newsWriterRepo.getWriterByEmailAndPassword(newsWriter);
//        if(returnedWriter == null)
//            System.out.println("Not Logged in");
//        else
//            System.out.println("Welcome "+returnedWriter.getFullName());
//    }
}