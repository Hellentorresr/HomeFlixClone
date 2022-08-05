package controller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administracion {
    public static ArrayList<Video> videos = new ArrayList<>();

    public static void setVideos(String nombreVideo, String categoryVideo, LocalDate fecha, String description, int califica, String cover, String videoPath) {
        Video video = new Video(nombreVideo,categoryVideo,fecha,description,califica,cover,videoPath);
        videos.add(video);
    }

    @Override
    public String toString() {
        return "Administracion{}";
    }
}
