package com.example.imageUpDown.entity;

import java.util.List;

public class UrlObject {
    public UrlObject(List<String> urls) {
		super();
		this.urls = urls;
	}

	public UrlObject() {
		super();
	}

	private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

	@Override
	public String toString() {
		return "UrlObject [urls=" + urls + "]";
	}
}
