package com.example.alisehomeproject.ProfilePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userdata {
    @SerializedName("invites")
    @Expose
    private Invites invites;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

}
