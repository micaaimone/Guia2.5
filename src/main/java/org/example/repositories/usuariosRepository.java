package org.example.repositories;

import org.example.interfaces.IRepository;
import org.example.model.entities.usuariosEntity;

import java.sql.SQLException;
import java.util.List;

public class usuariosRepository implements IRepository<usuariosEntity> {

    /*se crea una unica instancia de la clase*/
    private static usuariosRepository instance;

    /*constructor private para evitar que se creen instancias fuera de la clase*/
    private usuariosRepository() {
    }

    /* metodo clave del patrón Singleton, es static asi que lo puedo llamar sin necesidad de instanciar la clase*/
    public static usuariosRepository getInstance() {
        /*si instance es null, crea una nueva instancia, sino retorna la instancia ya creada*/
        if(instance == null){
            instance = new usuariosRepository();
        }
        return instance;
    }

    //tengo q hacer los metodos

    @Override
    public List<usuariosEntity> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(usuariosEntity entity) throws SQLException {

    }

    @Override
    public void update(usuariosEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
