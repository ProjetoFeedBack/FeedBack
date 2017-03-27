/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feedback.web.rest;

/**
 *
 * @author Dinax
 */
public class Shop {

	String name;
	String staffName[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStaffName() {
        return staffName;
    }

    public void setStaffName(String[] staffName) {
        this.staffName = staffName;
    }

	

}