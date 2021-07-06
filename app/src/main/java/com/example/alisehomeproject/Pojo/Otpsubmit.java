package com.example.alisehomeproject.Pojo;

import java.io.Serializable;

public class Otpsubmit implements Serializable {
    String number,otp;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
