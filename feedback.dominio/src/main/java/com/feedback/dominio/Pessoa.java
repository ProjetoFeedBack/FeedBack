package com.feedback.dominio;

public class Pessoa extends EntidadeDominio {

    private String nome;
    private String cpf;
    private String rg;
    private Telefone telefone;
    private Endereco endereco;

    
    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public Endereco getEndereco() {
        return endereco;
    }

    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
    public String getCpf() {
        return cpf;
    }

    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    public String getRg() {
        return rg;
    }

    
    public void setRg(String rg) {
        this.rg = rg;
    }

    
    public Telefone getTelefone() {
        return telefone;
    }

    
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

}
