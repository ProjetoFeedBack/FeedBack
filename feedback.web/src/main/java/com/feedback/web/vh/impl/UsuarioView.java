
package com.feedback.web.vh.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedback.web.vh.IViewHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
*/

public class UsuarioView implements IViewHelper{
 

	public EntidadeDominio getEntidade(HttpServletResponse response) {
		                
		List<Usuario> usuarioList = new ArrayList<Usuario>();
                
                return (EntidadeDominio) usuarioList;
        }       

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(EntidadeDominio entidade, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		
}
