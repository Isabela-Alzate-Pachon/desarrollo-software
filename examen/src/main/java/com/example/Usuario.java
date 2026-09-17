package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;

    public Usuario(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    
    public boolean datosValidos() {
        return nombre != null && !nombre.trim().isEmpty()
            && email != null && !email.trim().isEmpty()
            && telefono != null && !telefono.trim().isEmpty();
    }

    
    public static boolean emailExiste(Connection conn, String email) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE email = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    
    public void registrar(Connection conn) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, telefono);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            }
        }
    }

    
    public static List<Usuario> listarTodos(Connection conn) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, email, telefono FROM usuarios";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"));
                    u.setId(rs.getInt("id"));
                    lista.add(u);
                }
            }
        }
        return lista;
    }

    
    public static Usuario buscarPorId(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, nombre, email, telefono FROM usuarios WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"));
                    u.setId(rs.getInt("id"));
                    return u;
                }
            }
        }
        return null;
    }
}
