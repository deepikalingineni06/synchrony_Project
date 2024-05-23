package com.example.imageUpDown.serviceImple;


import java.util.concurrent.ExecutorService;

import com.example.imageUpDown.dataStore.ImageUrlLists;
import com.example.imageUpDown.utility.URLToBase64;


public class MainRequestExecutor {
   // private static final Logger LOGGER = Logger.getLogger(MainRequestExecutor.class);
    URLToBase64 urlToBase64;
    UploadService uploadService;
    ImageUrlLists imageUrlLists;
    ExecutorService execService;

    public MainRequestExecutor(ExecutorService execService) {
        this.urlToBase64 = new URLToBase64();
        this.execService = execService;
    }

    public void mainExecutor(ImageUrlLists imageUrlLists) {
        this.imageUrlLists = imageUrlLists;
        this.uploadService = new UploadService(imageUrlLists);


        for (String link : imageUrlLists.getPending()){
            RequestExecutor reqExec = new RequestExecutor(link, urlToBase64, uploadService);
            execService.submit(reqExec);
        }
    }
}
