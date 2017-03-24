/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.core.impl.dao;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.dominio.EntidadeDominio;
import com.feedback.dominio.Grupo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Dinax
 */
public class GrupoDaoTest {

    public static void main(String[] args) {
        Grupo grupo = new Grupo();
        grupo.setNome("Teste");
        grupo.setNivel("3");
        grupo.setNivel_grupopai("0");
        grupo.setDescricao("Teste do grupo");
        grupo.setId(3);
        EntidadeDominio entidade = grupo;
        Calendar cal = Calendar.getInstance();
        entidade.setDtCadastro(cal.getTime());

        Resultado resultado = new Resultado();
        
        GrupoDaoTest test = new GrupoDaoTest();
        test.Salvar(resultado, entidade);
        System.out.print(resultado);
        test.Consultar(resultado, entidade);
        System.out.print(resultado);
        test.Alterar(resultado, entidade);
        System.out.print(resultado);
        test.Consultar(resultado, entidade); 
        System.out.print(resultado);
        test.Excluir(resultado, entidade);
        System.out.print(resultado);
        test.Consultar(resultado, entidade); 
        System.out.print(resultado);

    }
    
    public Resultado Salvar(Resultado resultado, EntidadeDominio entidade){        
        GrupoDAO dao = new GrupoDAO();
        dao.salvar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;    
    }
    
    public Resultado Alterar(Resultado resultado, EntidadeDominio entidade){        
        GrupoDAO dao = new GrupoDAO();
        dao.alterar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;    
    }
    
    public Resultado Consultar(Resultado resultado, EntidadeDominio entidade){        
        GrupoDAO dao = new GrupoDAO();
        dao.consultar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;    
    }
    
    public Resultado Excluir(Resultado resultado, EntidadeDominio entidade){        
        GrupoDAO dao = new GrupoDAO();
        dao.excluir(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;
    
    }

}
