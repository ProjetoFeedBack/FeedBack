/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Dinax
 */
@Controller
public class TesteController {
    
    @RequestMapping(value = "/ola2", method = RequestMethod.GET)
   public String index2(ModelMap map) {
       map.put("msg", "Hello Spring teste 3!");
       return "index";
   }
}
