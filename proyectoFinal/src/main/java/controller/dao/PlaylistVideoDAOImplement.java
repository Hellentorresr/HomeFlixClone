package controller.dao;

import controller.baseDatos.Connexion;
import model.PlaylistVideos;
import model.Video;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PlaylistVideoDAOImplement implements DAOPlayListVideos {

    public ArrayList<PlaylistVideos> allPlaylist;

    public PlaylistVideoDAOImplement() {
        this.allPlaylist = new ArrayList<>();
    }

    @Override
    public PlaylistVideos get(int id) throws SQLException {
        PlaylistVideos playlistVideos = null;
        Connection daoConnection = Connexion.getConnection();
        //aqui se especifican el nombre de cada columna de la tablavideo de mi base de datos
        String sql = "SELECT id, namePlaylist, totalPlayListDurationTime, tema, creationDate, videoId FROM playList WHERE id = ?";

        PreparedStatement ps = daoConnection.prepareStatement(sql);
        ps.setInt(1, id);//primer parametro
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {//verificamos si tengo un record
            //aqui obteniendo el contenido de las columnas de la base de datos del video encontrado
            int idPlayList = rs.getInt("id");
            int idVideo = rs.getInt("videoId");// guardando en oid el índice o conteo del video si lo encontró
            String nombreVideo = rs.getString("namePlaylist");
            float duration = rs.getFloat("totalPlayListDurationTime");
            String tema = rs.getString("tema");

            String fechaString = rs.getString("creationDate");
            DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate creationDate = LocalDate.parse(fechaString, JEFormatter);

            ArrayList<Video> videos = new ArrayList<>();
            playlistVideos = new PlaylistVideos(idPlayList,nombreVideo,duration,tema,creationDate,idVideo);
        }
        return playlistVideos;

    }
    public ArrayList<Integer> videosPlayList(int id) throws SQLException {
        ArrayList<Integer> videos = new ArrayList<Integer>();
        PlaylistVideos playlistVideos = null;
        Connection daoConnection = Connexion.getConnection();
        String sql = "select videoId from playList where id = " + "'" + id + "'";
        PreparedStatement ps = daoConnection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idVideo = rs.getInt("id");
            videos.add(idVideo);
        }
        return videos;
    }
    @Override
    public ArrayList<PlaylistVideos> getALL() throws SQLException {
        Connection connection = Connexion.getConnection();
        String sql = "SELECT * from playList";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PlaylistVideos playlistVideos;
            int id = rs.getInt("id");
            String nombre = rs.getString("namePlaylist");
            float totalTime = rs.getFloat("totalPlayListDurationTime");
            String tema = rs.getString("tema");
            int idVideo = rs. getInt("videoId");
            String fechaString = rs.getString("creationDate");
            DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate creationDate = LocalDate.parse(fechaString, JEFormatter);
            playlistVideos = new PlaylistVideos(id, nombre, totalTime, tema,creationDate,idVideo);
            allPlaylist.add(playlistVideos);
        }
        //cerrando la connexion
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return allPlaylist;
    }

    @Override
    public int save(PlaylistVideos playlistVideos) throws SQLException {
        return 0;
    }

    @Override
    public void insert(PlaylistVideos playlistVideos) throws SQLException {

    }


    @Override
    public void insert(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate) throws SQLException {

    }

    @Override
    public void insert(int id, String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int idVideo) throws SQLException {
        PlaylistVideos playlistVideos1 = new PlaylistVideos(id, namePlaylist, totalPlayListDurationTime, tema, creationDate, idVideo);
        Connection connection = Connexion.getConnection();
        String sql = "INSERT INTO playList (id ,namePlaylist, totalPlayListDurationTime, tema, creationDate, videoId) VAlUES (?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, playlistVideos1.getId());
        ps.setString(2, playlistVideos1.getNamePlaylist());
        ps.setFloat(3, playlistVideos1.getTotalPlayListDurationTime());
        ps.setString(4, playlistVideos1.getTema());
        ps.setString(5, String.valueOf(playlistVideos1.getCreationDate()));
        ps.setInt(6, playlistVideos1.getIdVideo());
        ps.executeUpdate();
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
    }

    /*@Override
        public void insert(String namePlaylist, float totalPlayListDurationTime, String tema, LocalDate creationDate, int idVideo) throws SQLException {
            PlaylistVideos playlistVideos1 = new PlaylistVideos(namePlaylist, totalPlayListDurationTime, tema, creationDate, idVideo);
            Connection connection = Connexion.getConnection();
            String sql = "INSERT INTO playList (namePlaylist, totalPlayListDurationTime, tema, creationDate, videoId) VAlUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, playlistVideos1.getNamePlaylist());
            ps.setFloat(2, playlistVideos1.getTotalPlayListDurationTime());
            ps.setString(3, playlistVideos1.getTema());
            ps.setString(4, String.valueOf(playlistVideos1.getCreationDate()));
            ps.setInt(5, playlistVideos1.getIdVideo());
            ps.executeUpdate();
            Connexion.closePreparedStatement(ps);
            Connexion.closeConnection(connection);
        }*/

    @Override
    public int update(PlaylistVideos playlistVideos) throws SQLException {
        Connection connection = Connexion.getConnection();
        String sql = "UPDATE playList set namePlaylist = ?, totalPlayListDurationTime = ?, tema = ?, creationDate = ?  WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, playlistVideos.getNamePlaylist());
        ps.setFloat(2, playlistVideos.getTotalPlayListDurationTime());
        ps.setString(3, playlistVideos.getTema());
        ps.setString(4, String.valueOf(playlistVideos.getCreationDate()));
        ps.setInt(5,playlistVideos.getId());

        int result = ps.executeUpdate();
        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;
    }

    @Override
    public int delete(PlaylistVideos playlistVideos) throws SQLException {
        Connection connection = Connexion.getConnection();

        String sql = "DELETE FROM playList WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, playlistVideos.getId());
        int result = ps.executeUpdate();

        Connexion.closePreparedStatement(ps);
        Connexion.closeConnection(connection);
        return result;
    }

    public String playListName(int id) throws SQLException {
        Connection connectDB = Connexion.getConnection();
        String name = "select namePlayList from playList where id = " + "'" + id + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(name);
        queryResult.next();
        String pLName = queryResult.getString(1);
        Connexion.closeConnection(connectDB);
        return pLName;
    }

    public float playListDuration(int id) throws SQLException {
        Connection connectDB = Connexion.getConnection();
        String time = "select totalPlayListDurationTime from playList where id = " + "'" + id + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(time);
        queryResult.next();
        String pLDuration = queryResult.getString(1);
        Connexion.closeConnection(connectDB);
        return Float.parseFloat(pLDuration);
    }

    public String playListTema(int id) throws SQLException {
        Connection connectDB = Connexion.getConnection();
        String tema = "select tema from playList where id = " + "'" + id + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(tema);
        queryResult.next();
        String pLTema = queryResult.getString(1);
        Connexion.closeConnection(connectDB);
        return pLTema;
    }

    public LocalDate playListDate(int id) throws SQLException {
        Connection connectDB = Connexion.getConnection();
        String date = "select creationDate from playList where id = " + "'" + id + "'";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(date);
        queryResult.next();
        String pLTema = queryResult.getString(1);
        Connexion.closeConnection(connectDB);
        return LocalDate.parse(pLTema);
    }
}
