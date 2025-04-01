package org.example.model.repositories.services;

import org.example.model.entities.CredencialesEntity;
import org.example.model.repositories.impl.CredencialesRepository;
import org.example.model.repositories.impl.CuentasRepository;
import org.example.repositories.interfaces.IRepository;

import java.sql.SQLException;
import java.util.List;

public class CredencialesServices implements  IRepository<CredencialesEntity> {
    private static CredencialesServices instance;
    private static CredencialesRepository credencialesRepository;

    private CredencialesServices(){
        this.credencialesRepository = CredencialesRepository.getInstance();
    }

    public static CredencialesServices getInstance() {
        if(instance == null) instance = new CredencialesServices();
        return instance;
    }

    @Override
    public List<CredencialesEntity> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(CredencialesEntity entity) throws SQLException {

    }

    @Override
    public void update(CredencialesEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
