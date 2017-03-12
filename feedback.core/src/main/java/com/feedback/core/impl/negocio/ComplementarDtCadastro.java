package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.EntidadeDominio;
import java.util.Date;


public class ComplementarDtCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {		
		
		
		if(entidade !=null){
			Date data = new Date();		
			entidade.setDtCadastro(data);
		}else{
			return "Entidade: "+entidade.getClass().getCanonicalName()+" nula!";
		}
		
		
		
		return null;
	}

}
