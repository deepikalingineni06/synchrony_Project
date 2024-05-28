package com.example.imageUpDown.serviceImple;

import com.example.imageUpDown.dataStore.ImageUrlLists;
import com.example.imageUpDown.exception.UploadException;
import com.example.imageUpDown.utility.URLToBase64;

public class RequestExecutor implements Runnable{
  //  private static final Logger LOGGER = Logger.getLogger(RequestExecutor.class);
    private String imageLink;
    URLToBase64 urlToBase64;
    UploadService uploadService;

    public RequestExecutor(String imageLink, URLToBase64 urlToBase64, UploadService uploadService) {
        this.imageLink = imageLink;
        this.uploadService = uploadService;
        this.urlToBase64 = urlToBase64;
    }

    @Override
    public void run() {

        try {
            //download the image from provided URL and convert to Base64 string
            String base64String = urlToBase64.getBase64String(imageLink);

            //upload the image to imgur using the provided base 64 string
            uploadService.uploadImage(base64String, imageLink);
        } catch (UploadException e) {
            //move the pending urls to failed list
            ImageUrlLists imageUrlLists = uploadService.getImageUrlLists();
            imageUrlLists.getPending().remove(imageLink);
            imageUrlLists.getFailed().add(imageLink);
//            LOGGER.error("Adding the link " + imageLink +" to failed list");
//            LOGGER.error("**[ERROR]** encountered following error: " + e.getStatus());
        }

    }
}
