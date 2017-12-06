package br.unincor.view;

import javax.swing.JOptionPane;

public class ViewGUI {

	public void exibeMsg(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	public String recebeString(String texto) {
		String resposta = JOptionPane.showInputDialog(texto);
		return resposta;
	}
	
	public Boolean recebeBoolean(String texto) {
		try {
			int resposta = JOptionPane.showConfirmDialog(null, texto);
			if(resposta == JOptionPane.YES_OPTION)
				return Boolean.TRUE;
			return Boolean.FALSE;
		} catch (Exception e) {
			exibeMsg("Entrada inválida!");
		}
		return null;
	}

	public Double recebeDouble(String texto) {
		try {
			String resposta = JOptionPane.showInputDialog(texto);
			Double respostaConvertida = Double.parseDouble(resposta);
			return respostaConvertida;
		} catch (Exception e) {
			exibeMsg("Entrada inválida!");
		}
		return null;
	}

	public Integer recebeInt(String texto) {
		try {
			String resposta = JOptionPane.showInputDialog(texto);
			Integer respostaConvertida = Integer.parseInt(resposta);
			return respostaConvertida;
		} catch (Exception e) {
			exibeMsg("Entrada inválida!");
		}
		return null;
	}
	
	public  static Integer recebeOp(String texto, Object[] options){
		Integer resposta = JOptionPane.showOptionDialog(null, texto, "", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		
		return resposta;
		
	}
}



















