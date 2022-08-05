package model;

import java.util.Objects;

public class Usuario {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String userName;
    private String userPassword;
    private int userId;
    private String img;

    public Usuario(String nombre, String apellido1, String apellido2, String userName, String userPassword, int userId, String img) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userId = userId;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return userId == usuario.userId && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido1, usuario.apellido1) && Objects.equals(apellido2, usuario.apellido2) && Objects.equals(userName, usuario.userName) && Objects.equals(userPassword, usuario.userPassword) && Objects.equals(img, usuario.img);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userId=" + userId +
                ", img='" + img + '\'' +
                '}';
    }
}
