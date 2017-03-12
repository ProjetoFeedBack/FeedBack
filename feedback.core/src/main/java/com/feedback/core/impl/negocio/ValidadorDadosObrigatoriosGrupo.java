package com.feedback.core.impl.negocio;

import com.feedback.core.IStrategy;
import com.feedback.dominio.*;

public class ValidadorDadosObrigatoriosGrupo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Grupo){
			Grupo grupo = (Grupo)entidade;
			
			String nome = grupo.getNome();
                        String descricao = grupo.getDescricao();
                        String nivel = grupo.getNivel();
			String nivel_grupopai = grupo.getNivel_grupopai();
			
			if(nome == null || descricao == null || nivel==null || nivel_grupopai == null){
				return "Nome, descricao, nivel do grupo e nivel do Grupo do qual ele pertence sao de preenchimento obrigatorio!";
			}
			
			if(nome.trim().equals("") || descricao.trim().equals("") || 
					nivel.trim().equals("")|| nivel_grupopai.trim().equals("")){
				return "Nome, descricao, nivel do grupo e nivel do Grupo do qual ele pertence sao de preenchimento obrigatorio!";
			}
                        
                        
                                              
			
		}else{
			return "Deve ser registrado um Grupo!";
		}
		
		
		return null;
	}

}
