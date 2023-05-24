package com.carrinho.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.carrinho.compras.model.Lanches;

public interface LanchesRepository extends JpaRepository<Lanches, Long> {

	List<Lanches> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
