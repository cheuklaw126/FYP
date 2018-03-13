package com.mynetgear.cheuklaw126.hiit;

/**
 * A POJO representing a YouTube video
 */
public class YouTubeVideo {
    public String id;
    public String title;

    public YouTubeVideo(String id, String content) {
        this.id = id;
        this.title = content;
    }

    @Override
    public String toString() {
        return title;
    }
}
