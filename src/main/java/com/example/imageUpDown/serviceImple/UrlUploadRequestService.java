package com.example.imageUpDown.serviceImple;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.imageUpDown.dataStore.DatabaseMaper;
import com.example.imageUpDown.dataStore.ImageUrlLists;
import com.example.imageUpDown.entity.ImageIdObject;
import com.example.imageUpDown.entity.UrlObject;

import jakarta.annotation.PreDestroy;

@Service
public class UrlUploadRequestService {
    private static final Logger LOGGER = Logger.getLogger(UrlUploadRequestService.class);
    public static final int NUMBER_OF_THREADS = 4;
    ExecutorService execService;

    @Autowired
    private DatabaseMaper databaseMaper;

    public UrlUploadRequestService() {
       LOGGER.info("Starting the executor service for link download and upload to imgur with " + NUMBER_OF_THREADS + " threads ...");
        execService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    @Async
    public CompletableFuture<ImageIdObject> getImageIdForUrl(UrlObject urlObject) {
        ImageUrlLists imageUrlLists = databaseMaper.create(urlObject);
        ImageIdObject imageIdObject = new ImageIdObject();
        imageIdObject.setImageId(imageUrlLists.getId());
        LOGGER.info("current imageId: " + imageIdObject.getImageId());
        MainRequestExecutor mainRequestExecutor = new MainRequestExecutor(execService);
        mainRequestExecutor.mainExecutor(imageUrlLists);

        return CompletableFuture.completedFuture(imageIdObject);
    }

    @PreDestroy
    public void closeExecService() {
        execService.shutdown();
       LOGGER.info("Shutting down upload executor service ...");
        try {
            if (!execService.awaitTermination(3000L, TimeUnit.MILLISECONDS)) {
                LOGGER.warn("ExecutorService didn't terminate in the specified time.");
                List<Runnable> droppedTasks = execService.shutdownNow();
                LOGGER.warn("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
