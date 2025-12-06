package com.pagina.pagina4.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "opciones")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String imagen;

    @Column(name = "precio_pequeno", nullable = false)
    private int precioPequeno;

    @Column(name = "precio_normal", nullable = false)
    private int precioNormal;

    @Column(name = "precio_grande", nullable = false)
    private int precioGrande;

    // Constructor vacío (requerido por JPA)
    public Opcion() {}

    // Constructor con parámetros
    public Opcion(Long id, String nombre, String imagen, int precioPequeno, int precioNormal, int precioGrande) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precioPequeno = precioPequeno;
        this.precioNormal = precioNormal;
        this.precioGrande = precioGrande;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPrecioPequeno() {
        return precioPequeno;
    }

    public void setPrecioPequeno(int precioPequeno) {
        this.precioPequeno = precioPequeno;
    }

    public int getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(int precioNormal) {
        this.precioNormal = precioNormal;
    }

    public int getPrecioGrande() {
        return precioGrande;
    }

    public void setPrecioGrande(int precioGrande) {
        this.precioGrande = precioGrande;
    }
}