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
    private int code;
    private ArrayList<Integer>videos;

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

    /**
     * Segundo metodo constructor
     */
    public PlaylistVideos() {
    }

    /**
     * Tercer constructor
     */
    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int code, ArrayList<Integer> videos) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
        this.code = code;
        this.videos = videos;
    }

    /**
     * Getters y setters
     */

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public float getTotalPlayListDurationTime() {
        return totalPlayListDurationTime;
    }

    public void setTotalPlayListDurationTime(Video video) {
        this.totalPlayListDurationTime+=video.getTotalDuration();
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Integer> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Integer> videos) {
        this.videos = videos;
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
                ", code=" + code +
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
        return Float.compare(that.getTotalPlayListDurationTime(), getTotalPlayListDurationTime()) == 0 && getCode() == that.getCode() && Objects.equals(getNamePlaylist(), that.getNamePlaylist()) && Objects.equals(getTema(), that.getTema()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getVideos(), that.getVideos());
    }
}
