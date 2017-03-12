package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.*;


public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Usuario){
			Usuario u = (Usuario)entidade;
			
			if((u.getCpf() == null) || (u.getCpf().length() < 9)){
				return "CPF deve conter 14 digitos!";
			}
			
		}else{
			return "CPF nao pode ser validado, pois entidade nao e um usuario!";
		}
		
		
		return null;
	}

}
