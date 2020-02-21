package com.classified.database.models;


import com.google.gson.annotations.Expose;

/**
 * create table newswriter (
 * writerid integer primary key,
 * fullname varchar(254) not null,
 * email varchar(254) not null,
 * authentication_key varchar(254) not null,
 * companyName varchar(254) not null
 * );
 */

public class NewsWriter {
    @Expose
    private Integer id;
    @Expose
    private String fullName;
    @Expose
    private String email;

    private String authenticationKey;
    @Expose
    private String companyName;

    @Expose
    private boolean loginStatus;

    public NewsWriter() {
    }

    public NewsWriter(Integer id, String fullName, String email, String companyName, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.companyName = companyName;
        setAuthenticationKey(password);
    }

    public NewsWriter(String fullName, String email, String companyName, String password) {
        this.fullName = fullName;
        this.email = email;
        this.companyName = companyName;
        setAuthenticationKey(password);
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    @Override
    public String toString() {
        return "NewsWriter{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", authenticationKey='" + authenticationKey + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
