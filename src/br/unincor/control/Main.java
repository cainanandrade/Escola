package br.unincor.control;

import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.unincor.dao.GenericDAO;
import br.unincor.model.Notas;
import br.unincor.view.ViewGUI;

public class Main {

	public static void main(String[] args){
		
		ViewGUI view = new ViewGUI();
		
		try {
			GenericDAO.getConnection();				
			
			Object[] opcoes = {"Boletim", "Aproveitamento por Disciplina", "Média por Disciplina", "Cálculo de Mensalidades"};
			Object[] opBoletim = {"Imprimir", "Voltar ao Menu"};
			
			Integer op = view.recebeOp("Escolha sua tarefa: ", opcoes);
			
			while(op != -1){
				switch (op){
				case 0:
					String nomeAluno = view.recebeString("Digite o nome do aluno: ");
					view.exibeMsg(GenericDAO.boletim(nomeAluno));
					Integer op2 = view.recebeOp("Deseja imprimir o boletim do aluno?", opBoletim);
					if (op2 == 0){						
						File boletimImp = new File(nomeAluno + ".txt");
						FileWriter fw = new FileWriter(boletimImp, false);
						BufferedWriter bw = new BufferedWriter(fw);
							
						bw.write(GenericDAO.boletim(nomeAluno));
						bw.close();
							
						view.exibeMsg("Arquivo impresso!");
						 
						
						op = view.recebeOp("Escolha sua tarefa: ", opcoes);
					}
					
					else
						op = view.recebeOp("Escolha sua tarefa: ", opcoes);
					
					break;
				case 1:
					String nDisc = view.recebeString("Digite o nome da disciplina: ");
					List<String> notas = GenericDAO.aproveitamento(nDisc);
					File discImp = new File("disciplina.txt");
					FileWriter fw = new FileWriter(discImp, false);
					BufferedWriter bw = new BufferedWriter(fw);
						bw.write(nDisc + "\n--NOTAS: \n");
					for (int i = 0; i < notas.size(); i++) {
						bw.write(notas.get(i));
					}
					
					
					bw.close();
						
					view.exibeMsg("Arquivo impresso!");
					op = view.recebeOp("Escolha sua tarefa: ", opcoes);
					break;
				case 2:
					String nomeDisc = view.recebeString("Digite o nome da disciplina: ");
					view.exibeMsg("Média da disciplina " + nomeDisc + ": " + GenericDAO.mediaDisciplina(nomeDisc));
					op = view.recebeOp("Escolha sua tarefa: ", opcoes);
					break;
				case 3:
					view.exibeMsg("Receita arrecadada: R$" + GenericDAO.calcMensalidades());
					op = view.recebeOp("Escolha sua tarefa: ", opcoes);
					break;					
				}
			}
			
		}catch (SQLException e) {
			view.exibeMsg(e.getMessage());
		} catch (ClassNotFoundException e) {
			view.exibeMsg(e.getMessage());
		}catch (IOException e) {
			view.exibeMsg(e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
