package com.feedback.core.aplicacao;

import com.feedback.dominio.EntidadeDominio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Resultado extends EntidadeAplicacao {

    private String msg;
    private List<EntidadeDominio> entidades;

    /**
     * M�todo de recupera��o do campo msg
     *
     * @return valor do campo msg
     */
    @Autowired
    public String getMsg() {
        return msg;
    }

    /**
     * Valor de msg atribu�do a msg
     *
     * @param msg Atributo da Classe
     */
    @Autowired
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * M�todo de recupera��o do campo entidades
     *
     * @return valor do campo entidades
     */
    @Autowired
    public List<EntidadeDominio> getEntidades() {
        return entidades;
    }

    /**
     * Valor de entidades atribu�do a entidades
     *
     * @param entidades Atributo da Classe
     */
    @Autowired
    public void setEntidades(List<EntidadeDominio> entidades) {
        this.entidades = entidades;
    }

}
