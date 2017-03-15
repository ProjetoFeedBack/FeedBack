package com.feedback.core;

import java.sql.SQLException;
import java.util.List;

import com.feedback.*;
import com.feedback.dominio.EntidadeDominio;
import org.springframework.beans.factory.annotation.Autowired;


public interface IDAO {

    @Autowired
    public void salvar(EntidadeDominio entidade) throws SQLException;

    @Autowired
    public void alterar(EntidadeDominio entidade) throws SQLException;

    @Autowired
    public void excluir(EntidadeDominio entidade) throws SQLException;

    @Autowired
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;

    @Autowired
    public List<EntidadeDominio> listar() throws SQLException;

}
