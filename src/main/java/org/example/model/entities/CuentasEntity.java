package org.example.model.entities;

import org.example.model.ENUMS.tipoCuenta;

import java.time.LocalDateTime;

public class CuentasEntity {

    private int id_cuenta;
    private int id_usuario;
    private tipoCuenta tipo;
    private LocalDateTime fecha_creacion;

    //constructores

    public CuentasEntity(int id_cuenta, int id_usuario, tipoCuenta tipo, LocalDateTime fecha_creacion) {
        this.id_cuenta = id_cuenta;
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.fecha_creacion = fecha_creacion;
    }

    public CuentasEntity() {
    }

    //getters, setters

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public tipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(tipoCuenta tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    //toString

    @Override
    public String toString() {
        return "cuentasEntity{" +
                "id_cuenta=" + id_cuenta +
                ", id_usuario=" + id_usuario +
                ", tipo=" + tipo +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}
