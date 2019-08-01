package com.example.demomultipledatasources.comercio.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demomultipledatasources.comercio.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
