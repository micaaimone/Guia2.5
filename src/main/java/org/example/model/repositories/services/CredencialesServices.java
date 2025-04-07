package org.example.model.repositories.services;

import org.example.model.entities.CredencialesEntity;
import org.example.model.entities.UsuariosEntity;
import org.example.model.repositories.impl.CredencialesRepository;
import org.example.model.repositories.impl.CuentasRepository;
import org.example.repositories.interfaces.IRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CredencialesServices {
    private static CredencialesServices instance;
    private static CredencialesRepository credencialesRepository;

    private CredencialesServices(){
        this.credencialesRepository = CredencialesRepository.getInstance();
    }

    public static CredencialesServices getInstance() {
        if(instance == null) instance = new CredencialesServices();
        return instance;
    }


    public List<CredencialesEntity> mostrarTodos() {
        List<CredencialesEntity> credenciales = new ArrayList<>();

        try{
            credenciales = credencialesRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credenciales;
    }


    public void save(CredencialesEntity entity) {
        try{
            credencialesRepository.save(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(CredencialesEntity entity) throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }


    public Optional<CredencialesEntity> inicioSesion(String username, String password){
        try{
            return credencialesRepository.inicioSesion(username,password);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //deberia de hacer un metodo de validacion de USERNAME para que no se repita
}
