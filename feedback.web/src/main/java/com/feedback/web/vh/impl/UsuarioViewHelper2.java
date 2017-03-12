
package com.feedback.web.vh.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.core.util.ConverteDate;
import com.feedback.dominio.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedback.web.vh.IViewHelper;


public class UsuarioViewHelper2 implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		Usuario usuario = null; 
		
		if(!operacao.equals("VISUALIZAR")){
                                                
                        String id = request.getParameter("txtId");
			String nome = request.getParameter("txtNome");
			String cpf = request.getParameter("txtCpf");
                        String rg = request.getParameter("txtRg");
                        String logradouro = request.getParameter("txtLogradouro");
                        String numero = request.getParameter("txtNumero");
                        String cep = request.getParameter("txtCep");
                        String complemento = request.getParameter("txtComplemento");
                        String bairro = request.getParameter("txtBairro");
                        String cidade = request.getParameter("txtCidade");
                        String estado = request.getParameter("txtEstado");
                            
			String dtCadastro = request.getParameter("txtDtCadastro");
                        
			usuario = new Usuario();
			
                        		
			if(nome != null && !nome.trim().equals("")){
				usuario.setNome(nome);
			}
			
			if(id != null && !id.trim().equals("")){
				usuario.setId(Integer.parseInt(id));
			}
			
			if(cpf != null && !cpf.trim().equals("")){
				usuario.setCpf(cpf);
			}
                        
                        if(rg != null && !rg.trim().equals("")){
				usuario.setRg(rg);
			}
			
			if(dtCadastro != null && !dtCadastro.trim().equals("")){
				usuario.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
			}
                        
                        usuario.setEndereco(new Endereco());
                        usuario.getEndereco().setCidade(new Cidade());
                        usuario.getEndereco().setLogradouro(logradouro);
                        usuario.getEndereco().getCidade().setNome(cidade);
                        usuario.getEndereco().getCidade().setEstado(new Estado());
                        usuario.getEndereco().getCidade().getEstado().setNome(estado);
                        usuario.getEndereco().setComplemento(complemento);
                        usuario.getEndereco().setBairro(bairro);
                        usuario.getEndereco().setNumero(numero);
                        usuario.getEndereco().setCep(cep);
                       
                        
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					usuario = (Usuario)e;
				}
			}
		}
		
		return usuario;
	}

	
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response)  throws IOException, ServletException {
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
               		
		if(resultado.getMsg() == null){
			if(operacao.equals("SALVAR")){
				resultado.setMsg("Usuario cadastrado com sucesso!");
			}
			
                        if(operacao.equals("CONSULTAR")){
				
                                request.setAttribute("resultado",resultado);
 				d= request.getRequestDispatcher("FormListaUsuario.jsp"); 
                                d.forward(request,response);
			}
                        
                        request.setAttribute("resultado", resultado);
                        d= request.getRequestDispatcher("FormListaUsuario.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("ALTERAR")){
			
			d= request.getRequestDispatcher("FormUsuario.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")){
			
			request.setAttribute("usuario", resultado.getEntidades().get(0));
			d= request.getRequestDispatcher("FormUsuario.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("EXCLUIR")){
			
			request.getSession().setAttribute("resultado", null);
			d= request.getRequestDispatcher("FormListaUsuario.jsp");  
		}
		
		if(resultado.getMsg() != null){
			if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
				request.getSession().setAttribute("resultado", resultado);
 				d= request.getRequestDispatcher("FormUsuario.jsp");  	
			}
                        if(operacao.equals("CONSULTAR")){
				request.getSession().setAttribute("resultado", resultado);
 				d= request.getRequestDispatcher("FormListaUsuario.jsp");  	
			}
		}
		
		d.forward(request,response); 
		
	}

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(EntidadeDominio entidade, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        

}
