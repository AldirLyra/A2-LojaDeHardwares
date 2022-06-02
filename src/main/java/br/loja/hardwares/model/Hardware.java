package br.loja.hardwares.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Hardware implements Cloneable {
	
	private Integer id;
	
	@NotNull(message = "O nome não pode ser nulo.")
	private String nome;
	
	@NotNull(message = "O valor não pode ser nulo.")
	private Double valor;
	
	@NotNull(message = "A quantidade não pode ser nulo.")
	private int qtdEstoque;
	
	@NotNull(message = "O fornecedor não pode ser nulo.")
	private Fornecedor fornecedor;
	
	private Especificacoes especificacoes;
	
	public Usuario getClone() {
		try {
			return (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hardware other = (Hardware) obj;
		return Objects.equals(id, other.id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Especificacoes getEspecificacoes() {
		return especificacoes;
	}
	public void setEspecificacoes(Especificacoes especificacoes) {
		this.especificacoes = especificacoes;
	}
	
	
}
