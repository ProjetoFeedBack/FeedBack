package com.feedback.core;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;
import org.springframework.beans.factory.annotation.Autowired;

public interface IFachada {

    @Autowired
    public Resultado salvar(EntidadeDominio entidade);

    @Autowired
    public Resultado alterar(EntidadeDominio entidade);

    @Autowired
    public Resultado excluir(EntidadeDominio entidade);

    @Autowired
    public Resultado consultar(EntidadeDominio entidade);

    @Autowired
    public Resultado visualizar(EntidadeDominio entidade);

}
