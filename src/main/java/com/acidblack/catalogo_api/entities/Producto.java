package com.acidblack.catalogo_api.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "AB_Catalog")
public class Producto {

    // _id real de MongoDB (ObjectId). No se expone en el JSON.
    @Id
    @JsonIgnore
    private String mongoId;

    // Campo numérico "id" que ya existe en tus documentos; es el que usa la API.
    @Field("id")
    @JsonProperty("id")
    private Integer productoId;

    private String nombre;

    private String descripcion;

    @Field("precio")
    private BigDecimal precio;

    private String[] categorias;

    private String[] imagenes;

    private String genero;

    private Instant createdAt;

    private Instant updatedAt;

    public Producto() {
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
    // GETTERS Y SETTERS
    // ==========================================

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    @JsonProperty("id")
    public Integer getProductoId() {
        return productoId;
    }

    @JsonProperty("id")
    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ==========================================
    // EQUALS, HASHCODE Y TOSTRING
    // ==========================================

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Producto producto = (Producto) o;
        return mongoId != null ? mongoId.equals(producto.mongoId) : producto.mongoId == null;
    }

    @Override
    public int hashCode() {
        return mongoId != null ? mongoId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "mongoId=" + mongoId +
                ", id=" + productoId +
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