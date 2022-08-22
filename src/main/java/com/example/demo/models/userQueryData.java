package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.Generated;
import java.util.List;

@Document(collection = "queryGiven")
public class userQueryData {

    @Id
    private int id;
    private String problemLink;
    private String userDesc;
    private String givenCode;
    private List<Integer> comments;


    public void setId(int id) {
        this.id = id;
    }
    public List<Integer> getComments() {
        return comments;
    }

    public void setComments(List<Integer> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }


    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getProblemLink() {
        return problemLink;
    }

    public void setProblemLink(String problemLink) {
        this.problemLink = problemLink;
    }

    public String getGivenCode() {
        return givenCode;
    }

    public void setGivenCode(String givenCode) {
        this.givenCode = givenCode;
    }
}
