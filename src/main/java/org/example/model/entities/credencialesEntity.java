package org.example.model.entities;

import org.example.model.ENUMS.Permiso;

public class credencialesEntity {

    private int id_credencial;
    private int id_usuario;
    private String username;
    private String password;
    private Permiso permiso;

    //constructores
    public credencialesEntity(int id_credencial, int id_usuario, String username, String password, Permiso permiso) {
        this.id_credencial = id_credencial;
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.permiso = permiso;
    }

    public credencialesEntity() {
    }

    //getters, setters

    public int getId_credencial() {
        return id_credencial;
    }

    public void setId_credencial(int id_credencial) {
        this.id_credencial = id_credencial;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    //toString

    @Override
    public String toString() {
        return "credencialesEntity{" +
                "id_credencial=" + id_credencial +
                ", id_usuario=" + id_usuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permiso=" + permiso +
                '}';
    }
}
