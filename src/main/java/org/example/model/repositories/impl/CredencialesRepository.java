package org.example.model.repositories.impl;

import org.example.repositories.interfaces.IRepository;
import org.example.model.entities.CredencialesEntity;

import java.sql.SQLException;
import java.util.List;

public class CredencialesRepository implements IRepository<CredencialesEntity> {

    //los repositories de cada entidad me sirven para poder conectar con la base de datos,
    //aca hago las quarys, obtengo la info de la bdd

    /*se crea una unica instancia de la clase*/
    private static CredencialesRepository instance;

    /*constructor private para evitar que se creen instancias fuera de la clase*/
    private CredencialesRepository() {
    }

    /* metodo clave del patrón Singleton, es static asi que lo puedo llamar sin necesidad de instanciar la clase*/
    public static CredencialesRepository getInstance() {
        if(instance == null)
        {
            instance = new CredencialesRepository();
        }
        return instance;
    }

    @Override
    public List findAll() throws SQLException {
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
