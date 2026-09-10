package com.example;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



public class Main {
    public static void main(String[] args) throws SQLException{
        Scanner scanner = new Scanner (System.in);
        //try (Scanner scanner = new Scanner(System.in)) {
          //  System.out.println("Ingrese los datos del cliente:");

            //System.out.println("Ingrese el nombre: ");
            //String nombre = scanner.nextLine();

            //System.out.println("Ingrese el email: ");
            //String email = scanner.nextLine();

            //System.out.println("Ingrese el teléfono: ");
            //String telefono = scanner.nextLine();

            //Usuario usuario = new Usuario(nombre, email, telefono);

            //System.out.println(usuario.getNombre());
            //System.out.println(usuario.getTelefono());
            //System.out.println(usuario.getEmail());
           
String sql = "select * from cuentas";
try (Connection conn = BaseDatos.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            String identificador = rs.getString("identificador");
            double saldo = rs.getDouble("saldo");
            System.out.println("La cuenta tiene identificador: " + identificador);
            System.out.println("La cuenta tiene un saldo de: " + saldo);
        }
    }
}
 



        }
    }
