package com.feedback.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Usuario extends Pessoa {

    private String email;
    private String senha;

    @Autowired
    public String getEmail() {
        return email;
    }

    @Autowired
    public void setEmail(String email) {
        this.email = email;
    }

    @Autowired
    public String getSenha() {
        return senha;
    }

    @Autowired
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
