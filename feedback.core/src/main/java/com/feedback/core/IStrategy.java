package com.feedback.core;

import com.feedback.dominio.EntidadeDominio;



public interface IStrategy 
{

	public String processar(EntidadeDominio entidade);
	
}
