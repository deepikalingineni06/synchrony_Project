package com.example.imageUpDown.model;

public class LinkStatusObject {

    private String url;
    private String status;

    public LinkStatusObject() {
    }

    public LinkStatusObject(String url, String status) {
        this.url = url;
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
