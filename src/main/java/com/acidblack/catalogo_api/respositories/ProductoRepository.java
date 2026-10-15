package com.acidblack.catalogo_api.respositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.acidblack.catalogo_api.entities.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    Optional<Producto> findByProductoId(Integer productoId);

    void deleteByProductoId(Integer productoId);

    Optional<Producto> findFirstByOrderByProductoIdDesc();

    // Coincide si la categoría está dentro del arreglo "categorias"
    @Query("{ 'categorias': ?0 }")
    List<Producto> findByCategoria(String categoria);

    @Query("{ 'precio': { $gte: ?0, $lte: ?1 } }")
    List<Producto> findByPrecioRange(BigDecimal min, BigDecimal max);

    // "regex" debe venir ya escapado desde el servicio
    @Query("{ $or: [ { 'nombre': { $regex: ?0, $options: 'i' } }, { 'descripcion': { $regex: ?0, $options: 'i' } } ] }")
    List<Producto> buscarPorTexto(String regex);

    List<Producto> findByGeneroIgnoreCase(String genero);
}