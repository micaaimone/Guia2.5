package org.example.repositories;

import org.example.interfaces.IRepository;

import java.sql.SQLException;
import java.util.List;

public class cuentasRepository implements IRepository {

    private static cuentasRepository instance;

    public cuentasRepository() {
    }

    public static cuentasRepository getInstance(){
        if(instance == null){
            instance = new cuentasRepository();
        }
        return instance;
    }

    @Override
    public List findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(Object entity) throws SQLException {

    }

    @Override
    public void update(Object entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
