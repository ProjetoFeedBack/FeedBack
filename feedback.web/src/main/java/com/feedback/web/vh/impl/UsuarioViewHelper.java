
package com.feedback.web.vh.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;
import com.feedback.dominio.Usuario;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UsuarioViewHelper implements IViewHelper{
        private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	public EntidadeDominio getEntidade(HttpServletRequest request,HttpServletResponse response ) {                    
		
		String operacao = request.getParameter("operacao");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
                
		if (operacao != null) {
			try {
				if (operacao.equals("VISUALIZAR")){
                                    
                                    //Criar a lista de Usuarios
                                    List<Usuario> usuarioList = new ArrayList<Usuario>();
                                    
                                    return (EntidadeDominio) usuarioList;
                                
                                } else if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
                                    Usuario usuario = null; 
                                   
					if (request.getParameter("studentId") != null) {
						int studentId = Integer.parseInt(request.getParameter("studentId"));
						usuario.setId(studentId);
					}

					if (request.getParameter("name") != null) {
						String name = request.getParameter("name");
						usuario.setNome(name);
					}

					if (request.getParameter("department") != null) {
						String department = request.getParameter("department");
						usuario.setCpf(department);
					}

					if (request.getParameter("emailId") != null) {
						String emailId = request.getParameter("emailId");
						usuario.setCpf(emailId);
					}					
					return (EntidadeDominio) usuario;
                                }
                        }catch (Exception ex) {
                            JSONROOT.put("Result", "ERROR");
                            JSONROOT.put("Message", ex.getMessage());
                            String error = gson.toJson(JSONROOT);
                            //response.getWriter().print(error);
                        }
					
		
	}  
                return null;
    }
    
    @Override
    public void setView(EntidadeDominio entidade, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
           
        String operacao = request.getParameter("operacao");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        if (operacao != null) {
                    try {
                        // Retornar no formato exigido pelo plug-in jTable
                        JSONROOT.put("Result", "OK");
                        JSONROOT.put("Records", entidade);

                        // Converter Objetos Java para JSON
                        String jsonArray = gson.toJson(JSONROOT);
                        response.getWriter().print(jsonArray);


                    }catch(Exception ex) {

                        JSONROOT.put("Result", "ERROR");
                        JSONROOT.put("Message", ex.getMessage());
                        String error = gson.toJson(JSONROOT);
                        response.getWriter().print(error);
                    }
                }                            
                        
        
        
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
