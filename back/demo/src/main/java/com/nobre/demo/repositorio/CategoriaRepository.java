package com.nobre.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nobre.demo.model.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{

}
