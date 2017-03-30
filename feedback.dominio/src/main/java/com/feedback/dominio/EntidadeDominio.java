package com.feedback.dominio;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntidadeDominio implements IEntidade{
	
	private Integer id;
	private Date dtCadastro;
	
        @Autowired
	public Integer getId() {
		return id;
	}
        
        @Autowired
	public void setId(Integer id) {
		this.id = id;
	}
        
        @Autowired
	public Date getDtCadastro() {
		return dtCadastro;
	}
        
        @Autowired
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
}
