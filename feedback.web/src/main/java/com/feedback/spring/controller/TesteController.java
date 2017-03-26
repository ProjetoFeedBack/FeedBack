/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.spring.controller;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.core.impl.dao.GrupoDAO;
import com.feedback.dominio.EntidadeDominio;
import com.feedback.dominio.Grupo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Dinax
 */
@Controller
public class TesteController {

    @RequestMapping(value = "/ola2", method = RequestMethod.GET)
    public String index2(ModelMap map) {
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

        TesteController test = new TesteController();
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

        map.put("msg", "Hello Spring teste 3!");
        return "index";

    }

    public Resultado Salvar(Resultado resultado, EntidadeDominio entidade) {
        GrupoDAO dao = new GrupoDAO();
        dao.salvar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;
    }

    public Resultado Alterar(Resultado resultado, EntidadeDominio entidade) {
        GrupoDAO dao = new GrupoDAO();
        dao.alterar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;
    }

    public Resultado Consultar(Resultado resultado, EntidadeDominio entidade) {
        GrupoDAO dao = new GrupoDAO();
        dao.consultar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;
    }

    public Resultado Excluir(Resultado resultado, EntidadeDominio entidade) {
        GrupoDAO dao = new GrupoDAO();
        dao.excluir(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);
        return resultado;

    }

}

