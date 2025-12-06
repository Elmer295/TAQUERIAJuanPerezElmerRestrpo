package com.pagina.pagina4.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "items_carrito")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación N:1 con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación N:1 con Opcion (tipo de taco)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opcion_id", nullable = false)
    private Opcion opcion;

    @Column(nullable = false, length = 20)
    private String tamano; // Pequeño, Normal, Extragrande

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int precio; // precio según tamaño

    // Constructor vacío
    public ItemCarrito() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Opcion getOpcion() { return opcion; }
    public void setOpcion(Opcion opcion) { this.opcion = opcion; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
}