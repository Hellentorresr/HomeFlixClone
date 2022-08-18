/**
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase PlaylistVideos
 */
public class PlaylistVideos {
    /**
     * Atributos de la clase Playlist
     */
    private String namePlaylist;
    private float totalPlayListDurationTime;
    private String tema;
    private LocalDate creationDate;
    private int id;

    private int idVideo;
    private ArrayList<Video>videos = new ArrayList<>();

    /**
     * Metodo constructor
     */
    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.videos = new ArrayList<>();
    }

    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int id, int idVideo, ArrayList<Video> videos) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.id = id;
        this.idVideo = idVideo;
        this.videos = videos;
    }

    /**
     * Segundo metodo constructor
     */
    public PlaylistVideos() {
    }

    /**
     * Tercer constructor
     */
    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int code, ArrayList<Video> videos) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.id = code;
        this.videos = videos;
    }

    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int id) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.id = id;
    }

    public PlaylistVideos(int id,String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate,  int idVideo) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.id = id;
        this.idVideo = idVideo;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    /**
     * Getters y setters
     */

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public float getTotalPlayListDurationTime() {
        return totalPlayListDurationTime;
    }

    public void setTotalPlayListDurationTime(float totalPlayListDurationTime) {
        this.totalPlayListDurationTime = totalPlayListDurationTime;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public void setTotalPlayListDurationTime(Video video) {
        this.totalPlayListDurationTime+=video.getTotalDuration();
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public void agregarVideo(Video video){
        this.videos.add(video);
    }
    /**
     * Metodo toString
     */
    @Override
    public String toString() {
        return "PlaylistVideos{" +
                "namePlaylist='" + namePlaylist + '\'' +
                ", totalPlayListDurationTime=" + totalPlayListDurationTime +
                ", tema='" + tema + '\'' +
                ", creationDate=" + creationDate +
                ", code=" + id +
                ", videos=" + videos +
                '}';
    }

    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistVideos that)) return false;
        return Float.compare(that.getTotalPlayListDurationTime(), getTotalPlayListDurationTime()) == 0 && getId() == that.getId() && Objects.equals(getNamePlaylist(), that.getNamePlaylist()) && Objects.equals(getTema(), that.getTema()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getVideos(), that.getVideos());
    }
}
