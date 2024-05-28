package com.example.imageUpDown.controller;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.imageUpDown.dataStore.DatabaseMaper;
import com.example.imageUpDown.entity.ImageIdObject;
import com.example.imageUpDown.entity.ImageStatusObject;
import com.example.imageUpDown.entity.UrlObject;
import com.example.imageUpDown.serviceImple.UrlUploadRequestService;


@RestController
@RequestMapping(value = "/v1/images", produces = "application/json")
public class ImageController {

    @Autowired
    private DatabaseMaper databaseMaper;

    @Autowired
    private UrlUploadRequestService urlUploadRequestService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ImageIdObject> uploadImage(@RequestBody UrlObject urlObject){

        CompletableFuture<ImageIdObject> responseImageId = urlUploadRequestService.getImageIdForUrl(urlObject);
        try {
            return new ResponseEntity<>(responseImageId.get(), HttpStatus.CREATED);
        } catch (InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/upload/{imageId}", method = RequestMethod.GET)
    public ResponseEntity<ImageStatusObject> findByImageId(@PathVariable Long imageId){
        Optional<ImageStatusObject> imageStatusObject = databaseMaper.getImageStatusById(imageId);

        if(imageStatusObject.isPresent()) {
            return new ResponseEntity<>(imageStatusObject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UrlObject> findAllImageDetails(){
        UrlObject urlObject = new UrlObject();
        urlObject.setUrls(databaseMaper.getAllUploadedLinks());

        return new ResponseEntity<>(urlObject, HttpStatus.OK);
    }

}
