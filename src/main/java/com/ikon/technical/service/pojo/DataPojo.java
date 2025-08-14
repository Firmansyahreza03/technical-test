package com.ikon.technical.service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.technical.common.CommonPojo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPojo {
    private Long id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
