package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.*;



public class ValidadorDadosObrigatoriosUsuario implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario)entidade;
			
			String nome = usuario.getNome();			
			String cpf = usuario.getCpf();
			String rg = usuario.getRg();
			
			if(nome == null || rg == null || cpf==null ){
				return "Nome,rg, cpf e cidade sao de preenchimento obrigatorio!";
			}
			
			if(nome.trim().equals("") || rg.trim().equals("") || 
					cpf.trim().equals("")){
				return "Nome,rg, cpf e cidade sao de preenchimento obrigatorio!";
			}
			
		}else{
			return "Deve ser registrado um Usuario!";
		}
		
		
		return null;
	}

}
