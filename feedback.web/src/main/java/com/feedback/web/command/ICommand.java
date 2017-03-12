
package com.feedback.web.command;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;


public interface ICommand {

	public Resultado execute(EntidadeDominio entidade);
	
}
