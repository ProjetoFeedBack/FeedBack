
package com.feedback.dominio;
import org.springframework.stereotype.Component;
        
@Component
public class Usuario extends Pessoa {

	private String cpf;
        private String rg;
        private String email;
        private String senha;
        private Telefone telefone;

	/**
	 * M�todo de recupera��o do campo cpf
	 *
	 * @return valor do campo cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Valor de cpf atribu�do a cpf
	 *
	 * @param cpf Atributo da Classe
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
        
        /**
	 * Metodo de recuperacao do campo rg
	 *
	 * @param rg Atributo da Classe
	 */
        public String getRg() {
            return rg;
        }

        /**
	 * Valor de rg atribuido a rg
	 *
	 * @param rg Atributo da Classe
	 */
        public void setRg(String rg) {
            this.rg = rg;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public Telefone getTelefone() {
            return telefone;
        }

        public void setTelefone(Telefone telefone) {
            this.telefone = telefone;
        }

	
	
	
}
