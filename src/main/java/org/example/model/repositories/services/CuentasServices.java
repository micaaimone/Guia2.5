package org.example.model.repositories.services;

import org.example.model.entities.CuentasEntity;
import org.example.model.repositories.impl.CuentasRepository;
import org.example.model.repositories.impl.UsuariosRepository;
import org.example.repositories.interfaces.IRepository;

import java.sql.SQLException;
import java.util.List;

public class CuentasServices implements IRepository<CuentasEntity>{
    private static CuentasServices instance;
    private static CuentasRepository cuentasRepository;

    private CuentasServices(){
        this.cuentasRepository = CuentasRepository.getInstance();
    }

    public static CuentasServices getInstance() {
        if(instance == null) instance = new CuentasServices();
        return instance;
    }

    @Override
    public List<CuentasEntity> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(CuentasEntity entity) throws SQLException {

    }

    @Override
    public void update(CuentasEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
