package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lastKey")
public class largestKey {
    @Id
    private int keyId;
    private int value;
    private int commentValue;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int id) {
        keyId = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(int commentValue) {
        this.commentValue = commentValue;
    }
}
