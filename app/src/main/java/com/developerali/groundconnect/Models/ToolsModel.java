package com.developerali.groundconnect.Models;

import android.graphics.drawable.Drawable;

public class ToolsModel {

    String title;
    Drawable img;
    String link;

    public ToolsModel() {
    }

    public ToolsModel(String title, Drawable img, String link) {
        this.title = title;
        this.img = img;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
