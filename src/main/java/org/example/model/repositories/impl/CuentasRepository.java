package org.example.model.repositories.impl;

import org.example.ConexionSQLite;
import org.example.model.ENUMS.TipoCuenta;
import org.example.repositories.interfaces.IRepository;
import org.example.model.entities.CuentasEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CuentasEntity> findAll() throws SQLException {
        List<CuentasEntity> listaCuentas = new ArrayList<>();

        try(Connection connection = ConexionSQLite.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM cuentas");
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next())
                    {   listaCuentas.add(new CuentasEntity(
                            resultSet.getInt("id_cuenta"),
                            resultSet.getInt("id_usuario"),
                            TipoCuenta.valueOf(resultSet.getString("tipo")),
                            resultSet.getDouble("saldo"),
                            resultSet.getTimestamp("fecha_creacion").toLocalDateTime())
                    );
                    }
                }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return listaCuentas;
    }

    @Override
    public void save(CuentasEntity entity) throws SQLException {
        if(entity.getTipo() == TipoCuenta.CUENTA_CORRIENTE || !clienteTieneCajaAhorro(entity.getId_usuario())){
            try (Connection connection = ConexionSQLite.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO cuentas (id_usuario, tipo) VALUES (?,?)",
                                PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, entity.getId_usuario());
                preparedStatement.setString(2, entity.getTipo().toString());

                preparedStatement.executeUpdate();
                //guardo mi ID
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        entity.setId_cuenta(idGenerado);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al guardar la cuenta", e);
            }
        }
    }

    @Override
    public void update(CuentasEntity entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    public boolean clienteTieneCajaAhorro(int idUsuario)
    {
        boolean tiene = false;

        try(Connection connection = ConexionSQLite.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT tipo FROM cuentas WHERE id_usuario = ? AND tipo = 'CAJA_AHORRO'");
                preparedStatement.setInt(1, idUsuario);
                try(ResultSet resultSet = preparedStatement.executeQuery())
                {
                    if (resultSet.next())
                    {
                        tiene = true;
                    }
                }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tiene;
    }
}
