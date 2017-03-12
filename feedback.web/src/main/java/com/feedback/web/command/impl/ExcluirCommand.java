
package com.feedback.web.command.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;


public class ExcluirCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.excluir(entidade);
	}

}
