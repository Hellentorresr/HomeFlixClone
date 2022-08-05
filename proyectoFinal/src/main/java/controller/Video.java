package controller;

import java.time.LocalDate;

public class Video {
    private String nombreVideo;
    private String categoryVideo;
    private LocalDate fecha;
    private String description;
    private int califica;
    private String cover;

    private String videoPath;


    /**
     * Metodo constructor
     */
    public Video(String nombreVideo, String categoryVideo, LocalDate fecha, String description, int califica, String cover, String videoPath) {
        this.nombreVideo = nombreVideo;
        this.categoryVideo = categoryVideo;
        this.fecha = fecha;
        this.description = description;
        this.califica = califica;
        this.cover = cover;
        this.videoPath = videoPath;
    }

    /***
     * Segundo constructor
     */
    public Video() {
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

    public int getCalifica() {
        return califica;
    }

    public void setCalifica(int califica) {
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
                '}';
    }
}
