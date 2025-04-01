package org.example.view;


import org.example.ConexionSQLite;
import org.example.model.entities.UsuariosEntity;
import org.example.model.repositories.services.UsuariosServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    public UI() {
        this.usuariosServices = UsuariosServices.getInstance();
    }

    public void menu()
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



        do {
            System.out.println("1- Permitir la creación de un nuevo usuario");
            System.out.println("2- Permitir el inicio de sesión");
            System.out.println("3- Obtener todos los usuarios registrados");
            System.out.println("4- Buscar un usuario por DNI o email");
            System.out.println("5- Modificar los datos de un usuario");
            System.out.println("6- Eliminar un usuario");
            System.out.println("7- Listar todas las cuentas de un usuario");
            System.out.println("8- Obtener el saldo total de un usuario");
            System.out.println("9- Realizar un depósito en una cuenta");
            System.out.println("10- Realizar una transferencia entre cuentas");
            System.out.println("11- Obtener la cantidad de usuarios por tipo de permiso");
            System.out.println("12- Obtener la cantidad total de cuentas por tipo y mostrarlas");
            System.out.println("13-  Obtener el usuario con mayor saldo total");
            System.out.println("14- Listar los usuarios ordenados por su saldo total (de mayor a menor)");

            System.out.println("Que desea hacer?");
            opcion = scanner.nextInt();
            scanner.nextLine(); //salto de linea

            switch (opcion){
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

                    break;

                case 3:
                    List<UsuariosEntity> usuarios = usuariosServices.mostrarTodos();
                    for(UsuariosEntity u: usuarios){
                        System.out.println(u);
                    }
                    break;

            }

        } while (seguir == 1 );
    }



}
