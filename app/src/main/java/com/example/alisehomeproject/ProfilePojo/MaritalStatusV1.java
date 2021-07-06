package com.example.alisehomeproject.ProfilePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaritalStatusV1 {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("preference_only")
    @Expose
    private Boolean preferenceOnly;
    @SerializedName("is_selected")
    @Expose
    private Boolean isSelected;
    @SerializedName("is_open_to_all")
    @Expose
    private Boolean isOpenToAll;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPreferenceOnly() {
        return preferenceOnly;
    }

    public void setPreferenceOnly(Boolean preferenceOnly) {
        this.preferenceOnly = preferenceOnly;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getIsOpenToAll() {
        return isOpenToAll;
    }

    public void setIsOpenToAll(Boolean isOpenToAll) {
        this.isOpenToAll = isOpenToAll;
    }

}
