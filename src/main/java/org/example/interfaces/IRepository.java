package org.example.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    //con mi interfaz repository asumo q siempre voy a tener q hacer un CRUD + alguna cosa q hagan todos

    List<T> findAll() throws SQLException;

    void save(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(int id) throws SQLException;

}
