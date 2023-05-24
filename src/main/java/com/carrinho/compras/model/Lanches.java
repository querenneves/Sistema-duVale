package com.carrinho.compras.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity /* gerar tabela banco de dados */
@Table(name = "tb_carrinho")
public class Lanches {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo nome é obrigatório!")
	@Size(max = 100, message = "O atributo nome deve terno máximo 100 caracteres")
	private String nome;

	@Size(max = 500)
	private String tipo;
	
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "O preço deve ser maior do que zero!")
	private BigDecimal preco;

	private String foto;
	
	@Column(columnDefinition = "integer default 0")
	private int curtir;
	
	@ManyToOne
	@JsonIgnoreProperties("carrinho")
	private Categoria categoria;
}
