package com.example.alisehomeproject.ProfilePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferenceQuestion{
    @SerializedName("first_choice")
    @Expose
    private String firstChoice;
    @SerializedName("second_choice")
    @Expose
    private String secondChoice;

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }
}
