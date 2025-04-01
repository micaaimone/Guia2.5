package org.example.model.repositories.services;
import org.example.model.entities.UsuariosEntity;
import org.example.model.repositories.impl.UsuariosRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuariosServices {
    //los services son la logica, mandan a llamar a los metodos de los repositorios y trabajan con los datos que obtienen de ahi
    private static UsuariosServices instance;
    private final UsuariosRepository usuarioRepository;

    private UsuariosServices(){
        this.usuarioRepository = UsuariosRepository.getInstance();
    }

    public static UsuariosServices getInstance() {
        if(instance == null) instance = new UsuariosServices();
        return instance;
    }

    public void save (UsuariosEntity entity)
    {
        try {
            usuarioRepository.save(entity);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<UsuariosEntity> mostrarTodos(){
        List<UsuariosEntity> todosLosUsuarios = new ArrayList<>();
        try{
            todosLosUsuarios = usuarioRepository.findAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return todosLosUsuarios;
    }


}
