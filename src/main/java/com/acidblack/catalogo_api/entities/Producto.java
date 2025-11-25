package com.acidblack.catalogo_api.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(name = "categorias", columnDefinition = "text[]", nullable = false)
    private String[] categorias;

    @Column(name = "imagenes", columnDefinition = "text[]", nullable = false)
    private String[] imagenes;

    @Column(length = 50)
    private String genero;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    // ==========================================
    // CONSTRUCTORES
    // ==========================================

    public Producto() {
    }

    public Producto(Integer id, String nombre, String descripcion, BigDecimal precio,
            String[] categorias, String[] imagenes, String genero,
            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categorias = categorias;
        this.imagenes = imagenes;
        this.genero = genero;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Producto(String nombre, String descripcion, BigDecimal precio,
            String[] categorias, String[] imagenes, String genero) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categorias = categorias;
        this.imagenes = imagenes;
        this.genero = genero;
    }

    // ==========================================
    // GETTERS
    // ==========================================

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public String getGenero() {
        return genero;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ==========================================
    // SETTERS
    // ==========================================

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ==========================================
    // MÉTODOS DEL CICLO DE VIDA JPA
    // ==========================================

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    // ==========================================
    // MÉTODOS EQUALS, HASHCODE Y TOSTRING
    // ==========================================

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Producto producto = (Producto) o;

        return id != null ? id.equals(producto.id) : producto.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categorias=" + Arrays.toString(categorias) +
                ", imagenes=" + Arrays.toString(imagenes) +
                ", genero='" + genero + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}