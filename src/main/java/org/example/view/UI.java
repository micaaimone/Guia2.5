package org.example.view;


import org.example.ConexionSQLite;
import org.example.model.ENUMS.Permiso;
import org.example.model.entities.CredencialesEntity;
import org.example.model.entities.CuentasEntity;
import org.example.model.entities.UsuariosEntity;
import org.example.model.exceptions.NoAutorizadoException;
import org.example.model.repositories.services.CredencialesServices;
import org.example.model.repositories.services.CuentasServices;
import org.example.model.repositories.services.UsuariosServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/*
* CLIENTE: Puede gestionar sus propias cuentas y ver su saldo.
GESTOR: Puede gestionar usuarios y cuentas, pero no modificar credenciales ni eliminar
administradores.
ADMINISTRADOR: Tiene acceso total a la base de datos y a la gestión de usuarios y
cuentas.
*/
public class UI {

    private final UsuariosServices usuariosServices;
    private final CredencialesServices credencialesServices;
    private final CuentasServices cuentasServices;

    public UI() {
        this.usuariosServices = UsuariosServices.getInstance();
        this.credencialesServices = CredencialesServices.getInstance();
        this.cuentasServices = CuentasServices.getInstance();
    }

    public void menu() throws NoAutorizadoException
    {

        //CONEXION A LA BDD
        System.out.println("Conectando base de datos...\n");
        try(Connection connection = ConexionSQLite.getConnection())
        {
            System.out.println("Conexion establecida \n ");
        } catch (SQLException e){
            System.out.println("No se pudo establecer conexion con la bdd\n");
        }

        Scanner scanner = new Scanner(System.in);

        int seguir = 1;
        int opcion;
        int seguirMenu = 1;
        CredencialesEntity credencial = null;

        do {
            System.out.println("1- Permitir la creación de un nuevo usuario");
            System.out.println("2- Permitir el inicio de sesión");

            System.out.println("Que desea hacer?");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                    System.out.println("------creacion de un nuevo usuario------");

                    System.out.println("Ingrese el nombre del usuario");
                    String nombre = scanner.nextLine();

                    System.out.println("Ingrese el apellido del usuario");
                    String apellido = scanner.nextLine();

                    System.out.println("Ingrese el DNI del usuario");
                    String dni = scanner.nextLine();

                    System.out.println("Ingrese el email del usuario");
                    String email = scanner.nextLine();

                    UsuariosEntity usuarioNuevo = new UsuariosEntity();
                    usuarioNuevo.setNombre(nombre);
                    usuarioNuevo.setApellido(apellido);
                    usuarioNuevo.setDni(dni);
                    usuarioNuevo.setEmail(email);

                    usuariosServices.save(usuarioNuevo);
                    System.out.println("usuario creado correctamente, id: " + usuarioNuevo.getId_usuario());
                    System.out.println("Su username es su nombre y apellido todo junto y en minusculas");
                    System.out.println("Su password es: 1234");
                    System.out.println("Desea crear una nueva cuenta corriente para el usuario nuevo? 0-si, 1-no");
                    int opcionCC = scanner.nextInt();
                    scanner.nextLine(); //salto linea

                    if(opcionCC == 0)
                    {
                        try{
                            usuariosServices.crearCuentaCorriente(usuarioNuevo);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:

                    while (credencial == null) {
                        System.out.println("------ Inicio de sesión ------");

                        System.out.print("Ingrese el username: ");
                        String user = scanner.nextLine();

                        System.out.print("Ingrese su contraseña: ");
                        String contra = scanner.nextLine();

                        Optional<CredencialesEntity> credencialOpt = credencialesServices.inicioSesion(user, contra);

                        if (credencialOpt.isPresent()) {
                            credencial = credencialOpt.get();
                            System.out.println("usuario logueado correctamente. bienvenido " + credencial.getUsername() +
                                    " (" + credencial.getPermiso() + ")");
                        } else {
                            System.out.println("username o contraseña incorrecta. intente nuevamente.");
                        }
                    }
                    break;
            }
            System.out.println("Desea seguir en el menu de logueo/creacion? 1-si 0-no");
            seguirMenu = scanner.nextInt();
            scanner.nextLine(); //salto linea
        } while (seguirMenu == 1);


        if(credencial != null)
        {
            do {
                System.out.println("1- Obtener todos los usuarios registrados");
                System.out.println("2- Buscar un usuario por DNI o email");
                System.out.println("3- Modificar los datos de un usuario");
                System.out.println("4- Eliminar un usuario");
                System.out.println("5- Listar todas las cuentas de un usuario");
                System.out.println("6- Obtener el saldo total de un usuario");
                System.out.println("7- Realizar un depósito en una cuenta");
                System.out.println("8- Realizar una transferencia entre cuentas");
                System.out.println("9- Obtener la cantidad de usuarios por tipo de permiso");
                System.out.println("10- Obtener la cantidad total de cuentas por tipo y mostrarlas");
                System.out.println("11- Obtener el usuario con mayor saldo total");
                System.out.println("12- Listar los usuarios ordenados por su saldo total (de mayor a menor)");

                System.out.println("Que desea hacer?");
                opcion = scanner.nextInt();
                scanner.nextLine(); //salto de linea

                switch (opcion){
                    case 1:
                        if(credencial.getPermiso() == Permiso.ADMINISTRADOR || credencial.getPermiso() == Permiso.GESTOR)
                        {
                            System.out.println("MOSTRANDO LISTA DE USUARIOS, CREDENCIALES Y CUENTAS.");
                            System.out.println("-----LISTA USUARIOS-----");
                            List<UsuariosEntity> usuarios = usuariosServices.mostrarTodos();
                            for(UsuariosEntity u: usuarios){
                                System.out.println(u);
                            }


                            System.out.println("------LISTA CREDENCIALES-----");
                            List<CredencialesEntity> credenciales = credencialesServices.mostrarTodos();
                            for (CredencialesEntity c: credenciales)
                            {
                                System.out.println(c);
                            }


                            System.out.println("------LISTA CUENTAS-----");
                            List<CuentasEntity> cuentas = cuentasServices.mostrarTodos();
                            for (CuentasEntity cuenta: cuentas)
                            {
                                System.out.println(cuenta);
                            }
                        } else{
                            throw new NoAutorizadoException("No contas con los permisos necesarios.");
                        }

                        break;
                    case 2:

                        break;
                    case 3:

                        break;

                }

                System.out.println("Desea seguir en el menu 1-si 0-no");
                seguir = scanner.nextInt();
                scanner.nextLine(); //salto linea

            } while (seguir == 1 );
        }

    }



}
