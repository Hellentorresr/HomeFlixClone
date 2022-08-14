/**
 * @autor por Hellen Torres
 * @FechaCreacion 29/07/2022
 * @Ultima_Modificacion 08//08/2022 7:pm
 * @por Hellen torres
 */
package model;

import java.time.LocalDate;


public class Video {

    private String nombreVideo;
    private String categoryVideo;
    private LocalDate fecha;
    private String description;
    private boolean califica;
    private String cover;
    private String videoPath;

    private int videoId;

    private double totalDuration;

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

    /***
     * Tercer constructor
     */
    public Video(String nombreVideo, String categoryVideo, String description, String cover, String videoPath, LocalDate fecha) {
        this.nombreVideo = nombreVideo;
        this.categoryVideo = categoryVideo;
        this.description = description;
        this.cover = cover;
        this.videoPath = videoPath;
        this.fecha = fecha;
    }

    /**
     *  constructor 4
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

    public Video(double totalDuration) {
        this.totalDuration = totalDuration;
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

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
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
                ", totalDuration=" + totalDuration +
                '}';
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video video)) return false;
        return this.videoPath.equals(video.videoPath);
    }
}
