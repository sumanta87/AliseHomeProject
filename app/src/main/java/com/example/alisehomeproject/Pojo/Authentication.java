package com.example.alisehomeproject.Pojo;

import java.io.Serializable;

public class Authentication implements Serializable {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
