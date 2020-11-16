/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.dtos;

import danhpv.entities.TblEvent;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class CartObj implements Serializable {

    private String customerName;
    private HashMap<String, TblEvent> cart;

    public CartObj() {
        this.customerName = "Guest";
        this.cart = new HashMap<>();
    }

    public CartObj(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, TblEvent> getCart() {
        return cart;
    }

    public boolean addCart(TblEvent dto) throws Exception {
        if (this.cart.containsKey(dto.getEventID())) {
            return false;
        }
        this.cart.put(dto.getEventID(), dto);
        return true;
    }

    public boolean remove(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
            return true;
        }
        return false;
    }

    public boolean checkExistedCart(TblEvent dto) throws Exception {
        if (this.cart.containsKey(dto.getEventID())) {
            return true;
        }
        return false;
    }
}
