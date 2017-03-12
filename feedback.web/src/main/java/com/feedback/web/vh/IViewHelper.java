
package com.feedback.web.vh;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request);
        
        public EntidadeDominio getEntidade(HttpServletRequest request,HttpServletResponse response);
	
	public void setView(Resultado resultado, 
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
        
        public void setView(EntidadeDominio entidade, 
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
	
}

