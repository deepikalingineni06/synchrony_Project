package com.example.imageUpDown.serviceImple;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.example.imageUpDown.dataStore.ImageUrlLists;
import com.example.imageUpDown.exception.UploadException;
import com.example.imageUpDown.utility.CustomResponseHandler;
import com.example.imageUpDown.utility.ResponseObject;
import com.example.imageUpDown.utility.TimeZone;




public class UploadService {
    private static final Logger LOGGER = Logger.getLogger(UploadService.class);
    public static final String CLIENT_ID = "713c07570942395";
    public static final String IMGUR_URL = "https://api.imgur.com/3/image";
    ImageUrlLists imageUrlLists;

    public UploadService(ImageUrlLists imageUrlLists) {
        this.imageUrlLists = imageUrlLists;
    }

    public ImageUrlLists getImageUrlLists() {
        return imageUrlLists;
    }

    public void uploadImage(String base64String, String imageLink) throws UploadException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPostRequest = new HttpPost(IMGUR_URL);
        httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("image", base64String));
        CustomResponseHandler customResponseHandler = new CustomResponseHandler();
        int status = -1;
        try {
            httpPostRequest.setEntity(new UrlEncodedFormEntity(params));
            ResponseObject responseBody = (ResponseObject) httpClient.execute(httpPostRequest, customResponseHandler);

            LOGGER.info(responseBody);

            status = responseBody.getStatusCode();
            if(status>=200 && status<300){
            	imageUrlLists.getPending().remove(imageLink);
                imageUrlLists.getCompleted().add(responseBody.getLink());
                if(imageUrlLists.getPending().isEmpty()) {
                    TimeZone dateTime = new TimeZone();
                    imageUrlLists.setFinished(dateTime.getTimeNow());
                    imageUrlLists.setStatus("processed");
                }
                LOGGER.info("Adding the imgur link for " + imageLink + " to completed list.");
            } else {
            	imageUrlLists.getPending().remove(imageLink);
                imageUrlLists.getFailed().add(imageLink);
                LOGGER.info("Adding the link " + imageLink + " to failed list");
            }

            httpClient.close();
        } catch (UnsupportedEncodingException e) {
            throw new UploadException(e, status);
        } catch (ClientProtocolException e) {
            throw new UploadException(e, status);
        } catch (IOException e) {
            throw new UploadException(e, status);
        }

    }
}
