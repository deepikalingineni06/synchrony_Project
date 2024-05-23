package com.example.imageUpDown.dataStore;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.imageUpDown.entity.ImageStatusObject;
import com.example.imageUpDown.entity.LinkStatusObject;
import com.example.imageUpDown.entity.UrlObject;
import com.example.imageUpDown.utility.IdGenerator;
import com.example.imageUpDown.utility.TimeZone;


@Repository
public class DatabaseMaper {
    private static final Logger LOGGER = Logger.getLogger(DatabaseMaper.class);

    @Autowired
    private IdGenerator idGenerator;

    public static final Map<Long, ImageUrlLists> images = new ConcurrentHashMap<Long, ImageUrlLists>();
    private List<String> vExtensions;

    public Map<Long, ImageUrlLists> getImages() {
        return images;
    }

    public DatabaseMaper() {
    	vExtensions = new ArrayList<>();
        vExtensions.add("png");
        vExtensions.add("gif");
        vExtensions.add("tif");
        vExtensions.add("jpg");
        vExtensions.add("bmp");
        vExtensions.add("jpeg");
    }

    public ImageUrlLists create(UrlObject urlObject){
    	ImageUrlLists imageUrlLists = new ImageUrlLists();
        Long id = idGenerator.getNextId();
        imageUrlLists.setId(String.valueOf(id));
        imageUrlLists.setStatus("in-progress");


        Set<String> hashSet = new LinkedHashSet<>(urlObject.getUrls());


        Iterator<String> setIterator = hashSet.iterator();
        while(setIterator.hasNext()) {
            String linkToBeChecked = setIterator.next();
            String ext = FilenameUtils.getExtension(linkToBeChecked);
            if(!vExtensions.contains(ext.toLowerCase())) {
                setIterator.remove();
            }
        }

        imageUrlLists.getPending().addAll(hashSet);

        TimeZone dateTime = new TimeZone();
        imageUrlLists.setCreated(dateTime.getTimeNow());

        images.put(id, imageUrlLists);
        return imageUrlLists;
    }

    public Optional<ImageStatusObject> getImageStatusById (Long id) {
    	ImageStatusObject imageStatusObject = null;
        if(images.containsKey(id)) {
        	ImageUrlLists imageListObject = images.get(id);

        	imageStatusObject = new ImageStatusObject();
            imageStatusObject.setId(imageListObject.getId());
            imageStatusObject.setCreated(imageListObject.getCreated());
            imageStatusObject.setStatus(imageListObject.getStatus());

            LinkStatusObject linkStatusObject = new LinkStatusObject();
            if (!imageListObject.getPending().isEmpty()) {
                linkStatusObject.setPending(imageListObject.getPending());
            } else {
            	imageStatusObject.setFinished(imageListObject.getFinished());
            }
            if (!imageListObject.getCompleted().isEmpty()) {
                linkStatusObject.setComplete(imageListObject.getCompleted());
            }
            if (!imageListObject.getFailed().isEmpty()) {
                linkStatusObject.setFailed(imageListObject.getFailed());
            }
            imageStatusObject.setUploaded(linkStatusObject);
        }
        return Optional.ofNullable(imageStatusObject);
    }

    public List<String> getAllUploadedLinks () {
        List<String> uploadedUrlsList = new ArrayList<String>();
        for (Map.Entry<Long, ImageUrlLists> entry : images.entrySet()){
            uploadedUrlsList.addAll(entry.getValue().getCompleted());
        }
        return uploadedUrlsList;

    }
}
