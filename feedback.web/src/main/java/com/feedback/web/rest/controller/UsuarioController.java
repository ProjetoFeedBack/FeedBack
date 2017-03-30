package com.feedback.web.rest.controller;

import com.feedback.core.IFachada;
import com.feedback.core.aplicacao.Resultado;
import com.feedback.core.impl.controle.Fachada;
import com.feedback.dominio.EntidadeDominio;
import com.feedback.dominio.Usuario;
import com.feedback.web.command.ICommand;
import com.feedback.web.command.impl.AlterarCommand;
import com.feedback.web.command.impl.ConsultarCommand;
import com.feedback.web.command.impl.ExcluirCommand;
import com.feedback.web.command.impl.SalvarCommand;
import com.feedback.web.vh.IViewHelper;
import com.feedback.web.vh.impl.UsuarioViewHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    protected IFachada fachada = new Fachada();
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Resultado getShopInJSON(Usuario usuario, Resultado resultado) {
        
        EntidadeDominio entidade = usuario;
        ICommand command = new ConsultarCommand();
        resultado = command.execute(entidade);
        return resultado;
    }
   

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Resultado postShopInJSON(Usuario usuario, Resultado resultado) {

        EntidadeDominio entidade = usuario;
        ICommand command = new SalvarCommand();
        resultado = command.execute(entidade);
        return resultado;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    Resultado putShopInJSON(Usuario usuario, Resultado resultado) {

        EntidadeDominio entidade = usuario;
        ICommand command = new AlterarCommand();
        resultado = command.execute(entidade);
        return resultado;

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody
    Resultado deleteShopInJSON(Usuario usuario, Resultado resultado) {

        EntidadeDominio entidade = usuario;
        ICommand command = new ExcluirCommand();
        resultado = command.execute(entidade);
        return resultado;

    }

}
