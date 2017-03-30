package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.*;



public class ValidadorDadosObrigatoriosUsuario implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario)entidade;
			
			String emai = usuario.getEmail();			
			String senha = usuario.getSenha();
                        
			if(emai == null || senha == null ){
				return "Email e senha sao de preenchimento obrigatorio!";
			}
			
			if(emai.trim().equals("") || senha.trim().equals("")){
				return "Email e senha sao de preenchimento obrigatorio!";
			}
			
		}else{
			return "Deve ser registrado um Usuario!";
		}
		
		
		return null;
	}

}
