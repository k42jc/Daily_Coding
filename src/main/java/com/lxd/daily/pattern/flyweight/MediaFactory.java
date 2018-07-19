package com.lxd.daily.pattern.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liaoxudong
 * Date:2018/7/19
 */

public class MediaFactory {

    private static final MediaFactory factory = new MediaFactory();

    private static final Map<String,Media> medias = new ConcurrentHashMap<>();

    public static MediaFactory getInstance(){
        return factory;
    }

    public Media getMedia(String type) {
        Media media = medias.get(type);
        if (media == null) {
            if ("text".equals(type)) {
                media = new Text();
                medias.put("text", media);
            } else if ("video".equals(type)) {
                media = new Video();
                medias.put("video", media);
            }
        }
        return media;
    }
}
