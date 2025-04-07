package org.example.model.repositories.impl;

import org.example.ConexionSQLite;
import org.example.model.ENUMS.Permiso;
import org.example.model.entities.UsuariosEntity;
import org.example.repositories.interfaces.IRepository;
import org.example.model.entities.CredencialesEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<CredencialesEntity> findAll() throws SQLException {
        List<CredencialesEntity> listaCredenciales = new ArrayList<>();

        try(Connection connection = ConexionSQLite.getConnection())
        {
          PreparedStatement preparedStatement = connection.prepareStatement
                  ("SELECT * FROM credenciales");{
                    try(ResultSet resultSet = preparedStatement.executeQuery())
                    {
                        while(resultSet.next()){
                            listaCredenciales.add(new CredencialesEntity(
                                    resultSet.getInt("id_credencial"),
                                    resultSet.getInt("id_usuario"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    Permiso.valueOf(resultSet.getString("permiso"))
                            ));
                        }

                    }

                  }
        }
        return listaCredenciales;
    }

    @Override
    public void save(CredencialesEntity entity) throws SQLException {
        try(Connection connection = ConexionSQLite.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO credenciales (id_usuario, username, password, permiso) VALUES (?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
                        preparedStatement.setInt(1,entity.getId_usuario());
                        preparedStatement.setString(2, entity.getUsername());
                        preparedStatement.setString(3, entity.getPassword());
                        preparedStatement.setString(4,entity.getPermiso().toString());

                        preparedStatement.executeUpdate();

            //obtengo el ID generado y lo asigno a mi objeto
            try(ResultSet generateKeys = preparedStatement.getGeneratedKeys()){
                if(generateKeys.next()) {
                    int idGenerado = generateKeys.getInt(1);
                    entity.setId_credencial(idGenerado); //asigno mi ID
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void update(CredencialesEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    //Yo decidi que devuelva credencial ya que es lo que contiene a mi permiso, que es lo que voy a precisar constantemente en mi menu
    public Optional<CredencialesEntity> inicioSesion(String username, String password) throws SQLException {
        try(Connection connection = ConexionSQLite.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM credenciales WHERE username = ? AND password = ?");{
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2,password);
                    try (ResultSet resultSet = preparedStatement.executeQuery()){
                        if(resultSet.next())
                        {
                            return Optional.of(new CredencialesEntity(
                                    resultSet.getInt("id_credencial"),
                                    resultSet.getInt("id_usuario"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    Permiso.valueOf(resultSet.getString("permiso"))
                            ));
                        } else {
                            return Optional.empty();
                        }
                    }
            }
        }
    }

}
