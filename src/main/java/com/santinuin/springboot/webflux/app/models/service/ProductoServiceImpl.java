package com.santinuin.springboot.webflux.app.models.service;

import com.santinuin.springboot.webflux.app.models.dao.CategoriaDao;
import com.santinuin.springboot.webflux.app.models.dao.ProductoDao;
import com.santinuin.springboot.webflux.app.models.documents.Categoria;
import com.santinuin.springboot.webflux.app.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoDao dao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public Flux<Producto> findAll() {
        return dao.findAll();
    }

    @Override
    public Flux<Producto> findAllConNombreUpperCase() {
        Flux<Producto> productos = dao.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        });
        return productos;
    }

    @Override
    public Flux<Producto> findAllConNombreUpperCaseRepeat() {
        return findAllConNombreUpperCase().repeat(5000);
    }

    @Override
    public Mono<Producto> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return dao.delete(producto);
    }

    @Override
    public Flux<Categoria> findAllCategoria() {
        return categoriaDao.findAll();
    }

    @Override
    public Mono<Categoria> findCategoriaById(String id) {
        return categoriaDao.findById(id);
    }

    @Override
    public Mono<Categoria> saveCategoria(Categoria categoria) {
        return categoriaDao.save(categoria);
    }
}
