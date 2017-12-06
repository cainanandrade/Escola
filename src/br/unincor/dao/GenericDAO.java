package br.unincor.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unincor.model.Aluno;
import br.unincor.model.Disciplinas;
import br.unincor.model.Notas;
import br.unincor.view.ViewGUI;



public class GenericDAO {
	 
	public static Statement statement;
	public static ResultSet resultSet;	
	public static String status = null;	
	public static Connection conexao = null;
	
	
	
	
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		
		//try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String url = "jdbc:mysql://192.168.56.101:3306/escola?user=diretor&password=diretor2017";
			
			conexao = DriverManager.getConnection(url);
			
			//status = "CONECTADO!!";			
			
		//} catch (SQLException e) {
			//status = e.getMessage();
		//} catch (ClassNotFoundException e) {
			//status = e.getMessage();
		//} catch (Exception e) {
			//status = e.getMessage();
		//}
		
		return conexao;
	}
	
	public static String boletim(String nome) throws SQLException{
		String rtrnBoletim = null;
		
		//try {
			//ViewGUI view = new ViewGUI();
			statement = conexao.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM aluno WHERE nome = '" + nome + "'");
			resultSet.next();
			//é criado um objeto da classe Aluno para manipular os dados da tabela aluno
			Aluno aluno = new Aluno(resultSet.getInt("matricula"), resultSet.getString("nome"), resultSet.getString("dt_nasc"), resultSet.getString("cpf"), resultSet.getDouble("mensalidade"));
			
			List<Disciplinas> disciplinas = new ArrayList<Disciplinas>();
		
			rtrnBoletim = "NOME: " + aluno.getNome() + "\nNASCIMENTO: " + aluno.getDt_nasc() + "\nCPF: " + aluno.getCpf() + "\n";
			
			resultSet = statement.executeQuery("SELECT * FROM disciplinas");			
			
			while(resultSet.next()){
				Disciplinas disciplina = new Disciplinas(resultSet.getInt("id"), resultSet.getString("nome"));
				disciplinas.add(disciplina);
			}
			
			rtrnBoletim += "BOLETIM\n";
			
			for (int i = 0; i < disciplinas.size(); i++) {
				resultSet = statement.executeQuery("SELECT d.nome, n.nota FROM disciplinas d INNER JOIN notas n ON d.id = n.id_disc INNER JOIN aluno a ON a.matricula = n.mat_aluno WHERE a.matricula = " + aluno.getMatricula() + " AND d.id = " + disciplinas.get(i).getId());
				resultSet.next();
				
				if(resultSet.getInt("n.nota") < 60)
					rtrnBoletim += resultSet.getString("d.nome") + ": " + resultSet.getString("n.nota") + "*\n";
				else
					rtrnBoletim += resultSet.getString("d.nome") + ": " + resultSet.getString("n.nota") + "\n";
				
			}
			
		//} catch (SQLException e) {
			//rtrnBoletim = e.getMessage();
		//}
		
		
		return rtrnBoletim;
		
	}
	
	public static Double mediaDisciplina(String nDisciplina) throws SQLException{
		Double somaNotas = 0.0;
		Integer divisor = 0;
		
		
		//try {
			statement = conexao.createStatement();
			
			resultSet = statement.executeQuery("SELECT id FROM disciplinas WHERE nome = '" + nDisciplina + "'");
			resultSet.next();
			
			Integer id_disc = resultSet.getInt("id");
			
			List<Notas> listaNotas = new ArrayList<Notas>();
			
			resultSet = statement.executeQuery("SELECT * FROM notas");
			
			while(resultSet.next()){
				Notas notas = new Notas(resultSet.getInt("mat_aluno"), resultSet.getInt("id_disc"), resultSet.getDouble("nota"));
				listaNotas.add(notas);
			}
			
			for (int i = 0; i < listaNotas.size(); i++) {
				if(listaNotas.get(i).getId_disc() == id_disc){
					somaNotas += listaNotas.get(i).getNota();
					divisor += 1;
				}
				else{
					
				}
			}
			
			
		//} catch (SQLException e) {
		//	e.getMessage();
		//}
		
		Double media = somaNotas/divisor;
		
		return media;
	}
	
	public static Double calcMensalidades() throws SQLException{
		Double somaMensalidades = 0.0;
		statement = conexao.createStatement();
		
		resultSet = statement.executeQuery("SELECT mensalidade FROM aluno");
		
		while(resultSet.next()){
			somaMensalidades += resultSet.getDouble("mensalidade");
		}
		
		return somaMensalidades;
		
	}
	
	public static List aproveitamento(String nomeDisciplina) throws SQLException{
		statement = conexao.createStatement();
		
		resultSet = statement.executeQuery("SELECT * FROM disciplinas WHERE nome = '" + nomeDisciplina + "'");
		resultSet.next();
		
		Disciplinas disciplina = new Disciplinas(resultSet.getInt("id"), resultSet.getString("nome"));
		
		resultSet = statement.executeQuery("SELECT a.nome, n.nota FROM aluno a INNER JOIN notas n ON a.matricula = n.mat_aluno INNER JOIN disciplinas d ON d.id = n.id_disc WHERE d.id = " + disciplina.getId());
		
		List<String> nomesNotas = new ArrayList<String>();
		
		String nomeMaisNota = null;
		
		while(resultSet.next()){
			if (resultSet.getDouble("n.nota") < 60)
				nomeMaisNota = resultSet.getString("a.nome") + ": " + resultSet.getDouble("n.nota") + "*\n";
			else 
				nomeMaisNota = resultSet.getString("a.nome") + ": " + resultSet.getDouble("n.nota") + "\n";
			
			nomesNotas.add(nomeMaisNota);
		}
		
		return nomesNotas;
		
	}
	
}
