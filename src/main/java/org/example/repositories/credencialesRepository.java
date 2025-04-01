package org.example.repositories;

import org.example.interfaces.IRepository;

import java.sql.SQLException;
import java.util.List;

public class credencialesRepository implements IRepository {

    private static credencialesRepository instance;

    public credencialesRepository() {
    }

    public static credencialesRepository getInstance() {
        if(instance == null)
        {
            instance = new credencialesRepository();
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
