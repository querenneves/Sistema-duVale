package com.carrinho.compras.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.carrinho.compras.model.Lanches;
import com.carrinho.compras.repository.CategoriaRepository;
import com.carrinho.compras.repository.LanchesRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LanchesController {
	
	@Autowired
	private LanchesRepository lanchesRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Lanches>> getAll() {

		return ResponseEntity.ok(lanchesRepository.findAll());

		// Select * from tb_produto;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lanches> getById(@PathVariable Long id) {

		return lanchesRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		// Select * from tb_produto;
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Lanches>> getByNome(@PathVariable String nome) {

		return ResponseEntity.ok(lanchesRepository.findAllByNomeContainingIgnoreCase(nome));

	}
	
	@PostMapping
	public ResponseEntity<Lanches> post(@Valid @RequestBody Lanches lanches) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lanchesRepository.save(lanches));
	}

	@PutMapping
	public ResponseEntity<Lanches> put(@Valid @RequestBody Lanches lanches) {
		if (lanchesRepository.existsById(lanches.getId())) {

			if (categoriaRepository.existsById(lanches.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(lanchesRepository.save(lanches));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Lanches> postagem = lanchesRepository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		lanchesRepository.deleteById(id);

		/* DELETE FROM tb_produto WHERE id = id */
	}
}
