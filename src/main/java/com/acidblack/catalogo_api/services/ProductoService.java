package com.acidblack.catalogo_api.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.acidblack.catalogo_api.entities.Producto;
import com.acidblack.catalogo_api.respositories.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findByProductoId(id);
    }

    public Producto crear(Producto producto) {
        // Asigna el siguiente id numérico (max + 1)
        int siguienteId = productoRepository.findFirstByOrderByProductoIdDesc()
                .map(p -> p.getProductoId() == null ? 0 : p.getProductoId())
                .orElse(0) + 1;
        producto.setMongoId(null);
        producto.setProductoId(siguienteId);
        Instant ahora = Instant.now();
        producto.setCreatedAt(ahora);
        producto.setUpdatedAt(ahora);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Integer id, Producto productoActualizado) {
        return productoRepository.findByProductoId(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setCategorias(productoActualizado.getCategorias());
                    producto.setImagenes(productoActualizado.getImagenes());
                    producto.setGenero(productoActualizado.getGenero());
                    producto.setUpdatedAt(Instant.now());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public void eliminar(Integer id) {
        productoRepository.deleteByProductoId(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

  

    public List<Producto> buscarPorTexto(String texto) {
        return productoRepository.buscarPorTexto(Pattern.quote(texto));
    }

    public List<Producto> buscarPorGenero(String genero) {
        return productoRepository.findByGeneroIgnoreCase(genero);
    }
}