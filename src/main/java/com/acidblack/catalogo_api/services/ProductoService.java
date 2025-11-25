package com.acidblack.catalogo_api.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acidblack.catalogo_api.entities.Producto;
import com.acidblack.catalogo_api.respositories.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Constructor para inyección de dependencias

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto actualizar(Integer id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setCategorias(productoActualizado.getCategorias());
                    producto.setImagenes(productoActualizado.getImagenes());
                    producto.setGenero(productoActualizado.getGenero());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Transactional
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorPrecio(BigDecimal min, BigDecimal max) {
        return productoRepository.findByPrecioRange(min, max);
    }

    public List<Producto> buscarPorTexto(String texto) {
        return productoRepository.buscarPorTexto(texto);
    }

    public List<Producto> buscarPorGenero(String genero) {
        return productoRepository.findByGenero(genero);
    }
}