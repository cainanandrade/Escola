package br.unincor.model;

public class Aluno {
	private Integer matricula;
	private String nome;
	private String dt_nasc;
	private String cpf;
	private Double mensalidade;
	
	public Aluno(Integer matricula, String nome, String dt_nasc, String cpf, Double mensalidade) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.dt_nasc = dt_nasc;
		this.cpf = cpf;
		this.mensalidade = mensalidade;
	}
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(String dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Double getMensalidade() {
		return mensalidade;
	}
	public void setMensalidade(Double mensalidade) {
		this.mensalidade = mensalidade;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", dt_nasc=" + dt_nasc + ", cpf=" + cpf
				+ ", mensalidade=" + mensalidade + "]";
	}
	
	
}
