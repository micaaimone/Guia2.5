package org.example.model.repositories.services;

import org.example.model.entities.CuentasEntity;
import org.example.model.repositories.impl.CuentasRepository;
import org.example.model.repositories.impl.UsuariosRepository;
import org.example.repositories.interfaces.IRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentasServices {
    private static CuentasServices instance;
    private static CuentasRepository cuentasRepository;

    private CuentasServices(){
        this.cuentasRepository = CuentasRepository.getInstance();
    }

    public static CuentasServices getInstance() {
        if(instance == null) instance = new CuentasServices();
        return instance;
    }


    public List<CuentasEntity> mostrarTodos() {
        List<CuentasEntity> cuentas = new ArrayList<>();
        try
        {
            cuentas = cuentasRepository.findAll();
        } catch (SQLException e )
        {
            e.printStackTrace();
        }
        return cuentas;
    }


    public void save(CuentasEntity entity) {
        try{
            cuentasRepository.save(entity);
        } catch (SQLException e )
        {
            e.printStackTrace();
        }
    }


    public void update(CuentasEntity entity) throws SQLException {

    }


    public void delete(int id) throws SQLException {

    }
}
