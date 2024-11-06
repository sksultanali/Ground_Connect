package com.developerali.groundconnect.Models;

import java.util.ArrayList;

public class PostModel {
    String id, caption;
    ArrayList<String> imgUrls;
    int like;
    long uploadTime;

    public PostModel() {
    }

    public PostModel(long uploadTime, int like, ArrayList<String> imgUrls, String caption) {
        this.uploadTime = uploadTime;
        this.like = like;
        this.imgUrls = imgUrls;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
