package com.example.imageUpDown.utility;


import org.apache.commons.io.IOUtils;
import com.example.imageUpDown.exception.UploadException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class URLToBase64Encoder {

    public static String encodeImageURLToBase64(String imageUrl) throws Exception {
        InputStream in = new URL(imageUrl).openStream();
        byte[] imageBytes = in.readAllBytes();
        return Base64.encodeBase64String(imageBytes);
    }

    @Override
    public String toString() {
        return "URLToBase64Encoder []";
    }
}