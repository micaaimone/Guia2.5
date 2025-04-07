package org.example.model.entities;

import org.example.model.ENUMS.TipoCuenta;

import java.time.LocalDateTime;

public class CuentasEntity {

    private int id_cuenta;
    private int id_usuario;
    private TipoCuenta tipo;
    private double saldo;
    private LocalDateTime fecha_creacion;

    //constructores


    public CuentasEntity(int id_cuenta, int id_usuario, TipoCuenta tipo, double saldo, LocalDateTime fecha_creacion) {
        this.id_cuenta = id_cuenta;
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
    }

    public CuentasEntity(int id_usuario, TipoCuenta tipo, double saldo, LocalDateTime fecha_creacion) {
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
    }

    public CuentasEntity(int id_usuario, TipoCuenta tipo) {
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.saldo = 0;
        this.fecha_creacion = LocalDateTime.now();
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

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //toString


    @Override
    public String toString() {
        return "CuentasEntity{" +
                "id_cuenta=" + id_cuenta +
                ", id_usuario=" + id_usuario +
                ", tipo=" + tipo +
                ", saldo=" + saldo +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}
