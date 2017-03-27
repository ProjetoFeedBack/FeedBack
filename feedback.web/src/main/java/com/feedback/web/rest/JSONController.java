/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/grupo/add")
public class JSONController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Shop getShopInJSON() {

		Shop shop = new Shop();
		shop.setName("GET");
		shop.setStaffName(new String[]{"testeGet", "testeGet2"});

		return shop;

	}
        
        @RequestMapping(value="{name}", method = RequestMethod.POST)
	public @ResponseBody Shop postShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName(new String[]{"testePost", "testePost2"});

		return shop;

	}
        
        @RequestMapping(value="{id}", method = RequestMethod.PUT)
	public @ResponseBody Shop putShopInJSON(@PathVariable String id) {

		Shop shop = new Shop();
		shop.setName(id);
		shop.setStaffName(new String[]{"testePut", "testePut2"});
 
		return shop;

	}

}