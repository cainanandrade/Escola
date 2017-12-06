package br.unincor.model;

public class Notas {
	private Integer mat_aluno;
	private Integer id_disc;
	private Double nota;
	public Notas(Integer mat_aluno, Integer id_disc, Double nota) {
		super();
		this.mat_aluno = mat_aluno;
		this.id_disc = id_disc;
		this.nota = nota;
	}
	public Integer getMat_aluno() {
		return mat_aluno;
	}
	public void setMat_aluno(Integer mat_aluno) {
		this.mat_aluno = mat_aluno;
	}
	public Integer getId_disc() {
		return id_disc;
	}
	public void setId_disc(Integer id_disc) {
		this.id_disc = id_disc;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
}
