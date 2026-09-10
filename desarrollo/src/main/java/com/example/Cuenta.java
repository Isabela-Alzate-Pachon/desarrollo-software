package com.example;

public class Cuenta implements CuentaOperacion {

    private double saldo;

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void consignar(double valor) {
        saldo = saldo + valor;
    }

    @Override
    public void retirar(double valor) {
        saldo = saldo - valor;
    }
}
