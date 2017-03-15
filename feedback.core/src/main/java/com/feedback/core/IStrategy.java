package com.feedback.core;

import com.feedback.dominio.EntidadeDominio;
import org.springframework.beans.factory.annotation.Autowired;



public interface IStrategy 
{
        @Autowired
	public String processar(EntidadeDominio entidade);
	
}
