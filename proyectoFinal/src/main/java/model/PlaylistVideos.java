/**
 * @autor por Hellen Torres
 * @FechaCreacion 12/08/2022
 * @Ultima_Modificacion 12//08/2022
 * @por Hellen torres
 */
package model;

import java.time.LocalDate;

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

    /**
     * Metodo constructor
     */
    public PlaylistVideos(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate) {
        this.namePlaylist = namePlaylist;
        this.totalPlayListDurationTime = totalPlayListDurationTime;
        this.tema = tema;
        this.creationDate = creationDate;
    }

    /**
     * Segundo metodo constructor
     */
    public PlaylistVideos() {
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
}
