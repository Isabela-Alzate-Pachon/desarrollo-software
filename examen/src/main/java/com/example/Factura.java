package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Factura implements FacturaOperacion {
    private int id;
    private String numero;
    private String concepto;
    private double subtotal;
    private double porcentajeImpuesto;
    private String tipo;
    private int usuarioId;

    public Factura(String numero, String concepto, double subtotal, double porcentajeImpuesto, String tipo, int usuarioId) {
        this.numero = numero;
        this.concepto = concepto;
        this.subtotal = subtotal;
        this.porcentajeImpuesto = porcentajeImpuesto;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    @Override
    public double calcularImpuesto() {
        return subtotal * porcentajeImpuesto / 100;
    }

    @Override
    public double calcularTotal() {
        return subtotal + calcularImpuesto();
    }

    
    public boolean datosValidos() {
        return numero != null && !numero.trim().isEmpty()
            && concepto != null && !concepto.trim().isEmpty()
            && tipo != null && !tipo.trim().isEmpty()
            && subtotal >= 0
            && porcentajeImpuesto >= 0;
    }

    public void registrar(Connection conn) throws SQLException {
        String sql = "INSERT INTO facturas (numero, concepto, subtotal, porcentaje_impuesto, tipo, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, numero);
            ps.setString(2, concepto);
            ps.setDouble(3, subtotal);
            ps.setDouble(4, porcentajeImpuesto);
            ps.setString(5, tipo);
            ps.setInt(6, usuarioId);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            }
        }
    }

    
    public static List<Factura> listarTodas(Connection conn) throws SQLException {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT id, numero, concepto, subtotal, porcentaje_impuesto, tipo, usuario_id FROM facturas";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Factura f = new Factura(
                        rs.getString("numero"),
                        rs.getString("concepto"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("porcentaje_impuesto"),
                        rs.getString("tipo"),
                        rs.getInt("usuario_id")
                    );
                    f.setId(rs.getInt("id"));
                    lista.add(f);
                }
            }
        }
        return lista;
    }
        
    public static Factura buscarPorId(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, numero, concepto, subtotal, porcentaje_impuesto, tipo, usuario_id FROM facturas WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Factura f = new Factura(
                        rs.getString("numero"),
                        rs.getString("concepto"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("porcentaje_impuesto"),
                        rs.getString("tipo"),
                        rs.getInt("usuario_id")
                    );
                    f.setId(rs.getInt("id"));
                    return f;
                }
            }
        }
        return null;
    }
}
