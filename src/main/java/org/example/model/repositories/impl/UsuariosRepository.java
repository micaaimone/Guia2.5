package org.example.model.repositories.impl;

import org.example.ConexionSQLite;
import org.example.repositories.interfaces.IRepository;
import org.example.model.entities.UsuariosEntity;

import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosRepository implements IRepository<UsuariosEntity> {

    //los repositories de cada entidad me sirven para poder conectar con la base de datos,
    //aca hago las quarys, obtengo la info de la bdd

    /*se crea una unica instancia de la clase*/
    private static UsuariosRepository instance;

    /*constructor private para evitar que se creen instancias fuera de la clase*/
    private UsuariosRepository() {
    }

    /* metodo clave del patrón Singleton, es static asi que lo puedo llamar sin necesidad de instanciar la clase*/
    public static UsuariosRepository getInstance() {
        /*si instance es null, crea una nueva instancia, sino retorna la instancia ya creada*/
        if(instance == null){
            instance = new UsuariosRepository();
        }
        return instance;
    }

    //tengo q hacer los metodos

    @Override
    public List<UsuariosEntity> findAll() throws SQLException {
        List<UsuariosEntity> usuariosActuales = new ArrayList<>();
        try(Connection connection = ConexionSQLite.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM usuarios");{
                        try(ResultSet resultSet = preparedStatement.executeQuery()){
                            while(resultSet.next()){
                                usuariosActuales.add( new UsuariosEntity(
                                    resultSet.getInt("id_usuario"),
                                        resultSet.getString("nombre"),
                                        resultSet.getString("apellido"),
                                        resultSet.getString("dni"),
                                        resultSet.getString("email"),
                                        resultSet.getTimestamp("fecha_creacion").toLocalDateTime())
                                );
                            }
                        }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuariosActuales;
    }

    @Override
    public void save(UsuariosEntity entity) throws SQLException {
        try(Connection connection = ConexionSQLite.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO usuarios (nombre, apellido, dni, email) VALUES (?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, entity.getNombre());
                preparedStatement.setString(2, entity.getApellido());
                preparedStatement.setString(3, entity.getDni());
                preparedStatement.setString(4,entity.getEmail());
                //lo guardo
                preparedStatement.executeUpdate();

                //obtengo el ID generado y lo asigno a mi objeto
                try(ResultSet generateKeys = preparedStatement.getGeneratedKeys()){
                    if(generateKeys.next()) {
                        int idGenerado = generateKeys.getInt(1);
                        entity.setId_usuario(idGenerado); //asigno mi ID
                    }
                }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UsuariosEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }


}


