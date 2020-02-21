package com.classified.database.dao;

import com.classified.database.DatabaseConnection;
import com.classified.database.models.NewsWriter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class NewsWriterRepo {

    private static final String INSERT_SQL = "insert into " +
            "newswriter(fullname, email, authentication_key, companyName) " +
            "values(?, ?, ?, ?)";
    private static final String LOGIN_SQL = "select writerid, fullname, authentication_key from " +
            "newswriter where email=?";

    //For Signup
    public Integer insertNewsWriter(NewsWriter writer) throws SQLException {
        Integer id = null;
        Connection con = DatabaseConnection.connect();

        PreparedStatement pstmt = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, writer.getFullName());
        pstmt.setString(2, writer.getEmail());
        pstmt.setString(3, encryptIt(writer.getAuthenticationKey()));
        pstmt.setString(4, writer.getCompanyName());

        int affectedRows = pstmt.executeUpdate();

        if (affectedRows > 0) {
            System.out.println(affectedRows + " Rows affected.");

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        con.close();
        return id;
    }

    //For login
    public NewsWriter getWriterByEmailAndPassword(NewsWriter writer) {
        NewsWriter result = new NewsWriter();
        result.setLoginStatus(false);
        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(LOGIN_SQL);

            pstmt.setString(1, writer.getEmail());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt(1));
                result.setFullName(rs.getString(2));
                if (rs.getString(3).equals(encryptIt(writer.getAuthenticationKey()))) {
                    result.setLoginStatus(true);
                } else {
                    result.setLoginStatus(false);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String encryptIt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            str = hashtext;

        } catch (NoSuchAlgorithmException e) {

            return str;

        }
        return str;
    }

}
