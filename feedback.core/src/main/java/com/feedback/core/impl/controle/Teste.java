/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.core.impl.controle;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.*;

/**
 *
 * @author AmbienteDesen
 */
public class Teste {
    
    
    public static void main(String[] main){
        Fachada fachada = new Fachada();
        
        Usuario usuario = new Usuario();
        
        Resultado r = fachada.consultar(usuario);
        
        
        for(EntidadeDominio e: r.getEntidades()){
            usuario = (Usuario)e;
            
            System.out.println(usuario.getCpf());
        }
        
        
        r = fachada.consultar(new Grupo());
        
        
        for(EntidadeDominio e: r.getEntidades()){
            Grupo grupo = (Grupo)e;
            
            System.out.println(grupo.getNome());
        }
        
    }
    
}
