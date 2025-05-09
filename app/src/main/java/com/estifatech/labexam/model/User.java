package com.estifatech.labexam.model;

public class User {
    private String id;
    private final String fillName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String portfolioWebsite;
    private final String university;
    private final String campusName;
    private final String year;
    private final String password;

    public User(String uid,String fullName, String email, String phoneNumber, String address, String portfolioWebsite, String university, String campusName, String year, String password) {
        this.id = uid;
        this.fillName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.portfolioWebsite = portfolioWebsite;
        this.university = university;
        this.campusName = campusName;
        this.year = year;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFillName() {
        return fillName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPortfolioWebsite() {
        return portfolioWebsite;
    }

    public String getUniversity() {
        return university;
    }

    public String getCampusName() {
        return campusName;
    }

    public String getYear() {
        return year;
    }


    public String getPassword() {
        return password;
    }
}
