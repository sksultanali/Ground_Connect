package com.developerali.groundconnect.Models;

public class Member {

    String description, VideoUrl, uid, serach, userName;
    int videoViews;

    public Member(String description, String videoUrl, String userName, int videoViews) {
        this.description = description;
        VideoUrl = videoUrl;
        this.userName = userName;
        this.videoViews = videoViews;
    }

    public Member() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSerach() {
        return serach;
    }

    public void setSerach(String serach) {
        this.serach = serach;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVideoViews() {
        return videoViews;
    }

    public void setVideoViews(int videoViews) {
        this.videoViews = videoViews;
    }
}
