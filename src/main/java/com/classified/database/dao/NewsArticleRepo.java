package com.classified.database.dao;

import com.classified.database.DatabaseConnection;
import com.classified.database.models.NewsArticle;

import java.sql.*;
import java.util.ArrayList;

public class NewsArticleRepo {
    private static final String INSERT_SQL = "insert into " +
            "newsarticle(imageurl, title, contents, category, publisheddate, writername,writerid, viewcount) " +
            "values(?, ?, ?, ?, CURRENT_DATE, ?, ?, ?)";

    private static final String NEWSBYCATEGORY_SQL = "select * from newsarticle where category=? " +
            "order by publisheddate desc";

    private static final String NEWSBYNEWSID_SQL = "select * from newsarticle where newsid=?";

    private static final String NEWSBYWRITER_SQL = "select * from newsarticle where writerid=? " +
            "order by publisheddate desc";

    private static final String LATESTNEWS_SQL = "select * from newsarticle order by publisheddate desc";

    private String SEARCH_BY_TITLE;

    //For news posting
    public Integer insertNewsArticle(NewsArticle article) {
        Integer id = 0;

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, article.getImageUrl());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getcontents());
            pstmt.setString(4, article.getCategory());
            pstmt.setString(5, article.getWritername());
            pstmt.setInt(6, article.getWriterId());
            pstmt.setInt(7, 0);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public NewsArticle getArticleByNewsId(Integer id) {
        NewsArticle article = new NewsArticle();

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(NEWSBYNEWSID_SQL);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                article.setCategory(rs.getString(5));
                article.setcontents(rs.getString(4));
                article.setTitle(rs.getString(3));
                article.setPublishedDate(rs.getString(7));
                article.setImageUrl(rs.getString(2));
                article.setViewCount(rs.getInt(9));
                article.setWritername(rs.getString(6));
                article.setNewsId(rs.getInt(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return article;
    }

    //news article by the writer
    public ArrayList<NewsArticle> getNewsbyWriterId(Integer id) {
        ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(NEWSBYWRITER_SQL);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                NewsArticle article = new NewsArticle();
                article.setCategory(rs.getString(5));
                article.setcontents(rs.getString(4));
                article.setTitle(rs.getString(3));
                article.setPublishedDate(rs.getString(7));
                article.setImageUrl(rs.getString(2));
                article.setViewCount(rs.getInt(9));
                article.setWritername(rs.getString(6));
                article.setNewsId(rs.getInt(1));

                articles.add(article);
                article = null;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //article of the category
    public ArrayList<NewsArticle> getNewsbyCategory(String category) {
        ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(NEWSBYCATEGORY_SQL);

            pstmt.setString(1, category);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsArticle article = new NewsArticle();
                article.setCategory(rs.getString(5));
                article.setcontents(rs.getString(4));
                article.setTitle(rs.getString(3));
                article.setPublishedDate(rs.getString(7));
                article.setImageUrl(rs.getString(2));
                article.setViewCount(rs.getInt(9));
                article.setWritername(rs.getString(6));
                article.setNewsId(rs.getInt(1));

                articles.add(article);
                article = null;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //article by news title
    public ArrayList<NewsArticle> getNewsbyTitle(String title) {
        ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();

        try {
            Connection con = DatabaseConnection.connect();
            SEARCH_BY_TITLE = "select * from newsarticle where Lower(title::text) like '%" + title.toLowerCase() + "%'";
            PreparedStatement pstmt = con.prepareStatement(SEARCH_BY_TITLE);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsArticle article = new NewsArticle();
                article.setCategory(rs.getString(5));
                article.setcontents(rs.getString(4));
                article.setTitle(rs.getString(3));
                article.setPublishedDate(rs.getString(7));
                article.setImageUrl(rs.getString(2));
                article.setViewCount(rs.getInt(9));
                article.setWritername(rs.getString(6));
                article.setNewsId(rs.getInt(1));

                articles.add(article);
                article = null;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //latest articles
    public ArrayList<NewsArticle> getLatestNews() {
        ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(LATESTNEWS_SQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                NewsArticle article = new NewsArticle();
                article.setCategory(rs.getString(5));
                article.setcontents(rs.getString(4));
                article.setTitle(rs.getString(3));
                article.setPublishedDate(rs.getString(7));
                article.setImageUrl(rs.getString(2));
                article.setViewCount(rs.getInt(9));
                article.setWritername(rs.getString(6));
                article.setNewsId(rs.getInt(1));

                articles.add(article);
                article = null;
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}