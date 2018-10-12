package com.example.android.armyapp;

import java.net.MalformedURLException;
import java.net.URL;

public class YoutubeImgHelper {

    public static String getImgUrlFromVideo(String videoUrl) {
        try {
            return makeYoutubeImgUrl(extractYoutubeId(videoUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String makeYoutubeImgUrl(String videoId) {
        return "http://img.youtube.com/vi/" + videoId + "/0.jpg"; // this is link which will give u thumbnail image of that video
    }

    private static String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

}
