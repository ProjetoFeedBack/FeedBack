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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dinax
 */
public class GrupoDaoSalvarTestSpring {

    public static void main(String[] args) {
      //  Grupo grupo = (Grupo) SpringUtil.getInstance().getBean(Grupo.class);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("../springConfiguration.xml");
        Grupo grupo = (Grupo) applicationContext.getBean("grupo");
        
        grupo.setNome("Teste");
        grupo.setNivel("1");
        grupo.setNivel_grupopai("0");
        grupo.setDescricao("Teste do grupo");
        EntidadeDominio entidade = grupo;
        Calendar cal = Calendar.getInstance();
        entidade.setDtCadastro(cal.getTime());

        Resultado resultado = new Resultado();

        GrupoDAO dao = new GrupoDAO();
        dao.salvar(entidade);
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        System.out.print(resultado);

    }

}
