package org.example.model.entities;

import java.time.LocalDateTime;

public class UsuariosEntity {

    private int id_usuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDateTime fechaCreacion;


    //constructor
    public UsuariosEntity(int id_usuario, String nombre, String apellido, String dni, String email, LocalDateTime fechaCreacion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuariosEntity(String nombre, String apellido, String dni, String email, LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
    }

    //constructor vacio
    public UsuariosEntity() {
    }

    //getters, setters
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    //toString


    @Override
    public String toString() {
        return "usuariosEntity{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
