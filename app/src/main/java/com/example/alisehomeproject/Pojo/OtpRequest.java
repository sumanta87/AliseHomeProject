package com.example.alisehomeproject.Pojo;

import java.io.Serializable;
import java.io.SerializablePermission;

public class OtpRequest implements Serializable {
    String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
