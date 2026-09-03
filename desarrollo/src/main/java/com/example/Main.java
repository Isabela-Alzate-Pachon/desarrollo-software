package com.example;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Isabela Alzate", "isalzatep2@gmail.com", "3052234623");
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getTelefono());
        System.out.println(usuario.getEmail());
    }
}