package com.feedback.core;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;

public interface IFachada {

    
    public Resultado salvar(EntidadeDominio entidade);

    
    public Resultado alterar(EntidadeDominio entidade);

    
    public Resultado excluir(EntidadeDominio entidade);

    
    public Resultado consultar(EntidadeDominio entidade);

    
    public Resultado visualizar(EntidadeDominio entidade);

}
