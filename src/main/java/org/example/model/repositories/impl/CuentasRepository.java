package org.example.model.repositories.impl;

import org.example.repositories.interfaces.IRepository;
import org.example.model.entities.CuentasEntity;


import java.sql.SQLException;
import java.util.List;

public class CuentasRepository implements IRepository<CuentasEntity> {
    //los repositories de cada entidad me sirven para poder conectar con la base de datos,
    //aca hago las quarys, obtengo la info de la bdd


    /*se crea una unica instancia de la clase*/
    private static CuentasRepository instance;

    /*constructor private para evitar que se creen instancias fuera de la clase*/
    private CuentasRepository() {
    }

    /* metodo clave del patrón Singleton, es static asi que lo puedo llamar sin necesidad de instanciar la clase*/
    public static CuentasRepository getInstance(){
        if(instance == null){
            instance = new CuentasRepository();
        }
        return instance;
    }

    @Override
    public List findAll() throws SQLException {
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
