
package com.feedback.dominio;
import org.springframework.stereotype.Component;
        
@Component
public class Estado extends EntidadeDominio{

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}