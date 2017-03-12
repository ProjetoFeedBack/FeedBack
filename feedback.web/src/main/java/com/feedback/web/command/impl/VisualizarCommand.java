
package com.feedback.web.command.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;

public class VisualizarCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.visualizar(entidade);
	}

}
