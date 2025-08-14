package com.ikon.technical.model;

import com.ikon.technical.common.CommonModel;
import jakarta.persistence.Entity;

@Entity
public class Data extends CommonModel {
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
