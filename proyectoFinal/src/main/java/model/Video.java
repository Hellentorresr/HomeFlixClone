package model;

import java.time.LocalDate;
import java.util.Objects;

public class Video {

    private String nombreVideo;
    private String categoryVideo;
    private LocalDate fecha;
    private String description;
    private boolean califica;
    private String cover;
    private String videoPath;

    private int videoId;

    /**
     * Metodo constructor
     */

    public Video(String nombreVideo, String categoryVideo, LocalDate fecha, String description, boolean califica, String cover, String videoPath, int videoId) {
        this.nombreVideo = nombreVideo;
        this.categoryVideo = categoryVideo;
        this.fecha = fecha;
        this.description = description;
        this.califica = califica;
        this.cover = cover;
        this.videoPath = videoPath;
        this.videoId = videoId;
    }

    /***
     * Segundo constructor
     */
    public Video() {
    }

    public Video(String nombreVideo, String categoryVideo, String description, String cover, String videoPath) {
        this.nombreVideo = nombreVideo;
        this.categoryVideo = categoryVideo;
        this.description = description;
        this.cover = cover;
        this.videoPath = videoPath;
    }

    /**
     * tercer constructor
     */
    public Video(String nombreVideo, String categoryVideo, LocalDate fecha, String description, boolean califica, String cover, String videoPath) {
        this.nombreVideo = nombreVideo;
        this.categoryVideo = categoryVideo;
        this.fecha = fecha;
        this.description = description;
        this.califica = califica;
        this.cover = cover;
        this.videoPath = videoPath;
    }

    /**
     * Metodos setters y getters
     */
    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getCategoryVideo() {
        return categoryVideo;
    }

    public void setCategoryVideo(String categoryVideo) {
        this.categoryVideo = categoryVideo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCalifica() {
        return califica;
    }

    public void setCalifica(boolean califica) {
        this.califica = califica;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "Video{" +
                "nombreVideo='" + nombreVideo + '\'' +
                ", categoryVideo='" + categoryVideo + '\'' +
                ", fecha=" + fecha +
                ", description='" + description + '\'' +
                ", califica=" + califica +
                ", cover='" + cover + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoId=" + videoId +
                '}';
    }

    /**
     * Metodo toString
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video video)) return false;
        return this.videoPath.equals(video.videoPath);
    }
}
