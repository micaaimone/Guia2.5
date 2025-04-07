package org.example.model.repositories.services;
import org.example.model.ENUMS.Permiso;
import org.example.model.ENUMS.TipoCuenta;
import org.example.model.entities.CredencialesEntity;
import org.example.model.entities.CuentasEntity;
import org.example.model.entities.UsuariosEntity;
import org.example.model.repositories.impl.CredencialesRepository;
import org.example.model.repositories.impl.UsuariosRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class UsuariosServices {
    //los services son la logica, mandan a llamar a los metodos de los repositorios y trabajan con los datos que obtienen de ahi
    private static UsuariosServices instance;
    private final UsuariosRepository usuarioRepository;
    private final CredencialesServices credencialesServices;
    private final CuentasServices cuentasServices;

    private UsuariosServices(){
        this.credencialesServices = CredencialesServices.getInstance();
        this.cuentasServices = CuentasServices.getInstance();
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

            crearCredencial(entity);

            crearCajaAhorro(entity);

         } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void crearCajaAhorro(UsuariosEntity entity) throws SQLException{
        int id_Usuario = entity.getId_usuario();
        TipoCuenta tipoCuenta = TipoCuenta.CAJA_AHORRO;

        CuentasEntity cuentaNueva = new CuentasEntity(id_Usuario, tipoCuenta);

        cuentasServices.save(cuentaNueva);

    }

    public void crearCuentaCorriente(UsuariosEntity entity) throws SQLException{
        int id_Usuario = entity.getId_usuario();
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;

        CuentasEntity cuentaNueva = new CuentasEntity(id_Usuario, tipoCuenta);

        cuentasServices.save(cuentaNueva);
    }

    private void crearCredencial(UsuariosEntity entity) throws SQLException {
        //designo los atributos de mi nueva credencial
        String usernameAutomatica = entity.getNombre().concat(entity.getApellido()).toLowerCase();
        String contraseñaAutomatica = "1234"; //deberia avisar al usuario la contraseña y el username
        Permiso permisoAutomatico = Permiso.CLIENTE;
        int id_Usuario = entity.getId_usuario();

        CredencialesEntity credencialNueva = new CredencialesEntity(id_Usuario, usernameAutomatica,contraseñaAutomatica,permisoAutomatico);
        credencialesServices.save(credencialNueva);

        //no se como hacerlo pero me gustaria avisar al usuario que se le envia todo por gmail
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
