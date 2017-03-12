/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.*;
import java.sql.SQLException;

/**
 *
 * @author AmbienteDesen
 */
public class ValidadorGrupoPai  implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade){
                                      
            
            if(entidade instanceof Grupo){
			Grupo grupo = (Grupo)entidade;
			
			String nome = grupo.getNome();                        
                        String nivel = grupo.getNivel();
			String nivel_grupopai = grupo.getNivel_grupopai();
			
			if((nome == null ||  nivel==null || nivel_grupopai == null) || 
                           (nome.trim().equals("") || nivel.trim().equals("")|| nivel_grupopai.trim().equals(""))){
				return "Nome, descricao, nivel do grupo e nivel do Grupo do qual ele pertence sao de preenchimento obrigatorio!";
			}else{
                            
                                                   
                        }
			
			
                                              
			
		}else{
			return "Deve ser registrado um Grupo!";
		}
            
            return null;
            
        }
}
