package br.unincor.model;

public class Disciplinas {
	private Integer id;
	private String nome;
	public Disciplinas(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	@Override
	public String toString() {
		return "Disciplinas [id=" + id + ", nome=" + nome + "]";
	}
}
