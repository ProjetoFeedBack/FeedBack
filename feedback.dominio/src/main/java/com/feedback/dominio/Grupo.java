
package com.feedback.dominio;

import java.util.List;

public class Grupo extends EntidadeDominio{
    
    private String nome;
    private String descricao;
    private String nivel;
    private String nivel_grupopai;    //Herda sempre da raiz (0 = Grupo Intituição)
    private List<Usuario>  usuarios;   //Todos os usuários que fazem parte do grupo

    
    /**
	 * Metodo de recuperacao do campo nome
	 *
	 * @return valor do campo nome
	 */	
    public String getNome() {
        return nome;
    }

    /**
	 * Valor de nome atribuido a nome
	 *
	 * @param nome Atributo da Classe
	 */
    public void setNome(String nome) {
        this.nome = nome;
    }

     /**
	 * Metodo de recuperacao do campo descricao
	 *
	 * @return valor do campo descricao
	 */
    public String getDescricao() {
        return descricao;
    }

    /**
	 * Valor de descricao atribuido a descricao
	 *
	 * @param descricao Atributo da Classe
	 */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     /**
	 * Metodo de recuperacao do campo nivel
	 *
	 * @return valor do campo nivel
	 */
    public String getNivel() {
        return nivel;
    }

    /**
	 * Valor de nivel atribuido a nivel
	 *
	 * @param nivel Atributo da Classe
	 */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

     /**
	 * Metodo de recuperacao do campo nivel_grupopai
	 *
	 * @return valor do campo nivel_grupopai
	 */
    public String getNivel_grupopai() {
        return nivel_grupopai;
    }

    /**
	 * Valor de nivel_grupopai atribuido a nivel_grupopai
	 *
	 * @param nivel_grupopai Atributo da Classe
	 */
    public void setNivel_grupopai(String nivel_grupopai) {
        this.nivel_grupopai = nivel_grupopai;
    }
    
    
    
    
    
}
