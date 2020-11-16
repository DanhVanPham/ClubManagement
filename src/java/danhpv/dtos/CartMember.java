/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.dtos;

import danhpv.entities.TblUser;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class CartMember implements Serializable {

    private String groupID;
    private HashMap<String, HashMap<String, TblUser>> cart;

    public CartMember() {
        this.groupID = "Guest";
        this.cart = new HashMap<>();
    }

    public CartMember(String customer) {
        this.groupID = customer;
        this.cart = new HashMap<>();
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public HashMap<String, HashMap<String, TblUser>> getCart() {
        return cart;
    }

//    public void setCart(HashMap<String, HashMap<String, TblUser>> cart) {
//        this.cart = cart;
//    }

    public boolean addCart(TblUser dto, String groupID) throws Exception {
        if (this.cart.containsKey(groupID)) {
            if (this.cart.get(groupID).containsKey(dto.getUserID())) {
                return false;
            } else {
                this.cart.get(groupID).put(dto.getUserID(), dto);
                return true;
            }
        } else {
            HashMap<String, TblUser> user = new HashMap<>();
            user.put(dto.getUserID(), dto);
            this.cart.put(groupID, user);
            return true;
        }
    }

    public boolean remove(String userid, String groupID) throws Exception {
        if (this.cart.containsKey(groupID)) {
            if (this.cart.get(groupID).containsKey(userid)) {
                this.cart.get(groupID).remove(userid);
                if(this.cart.get(groupID).isEmpty()) {
                    this.cart.remove(groupID);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean removeGroupID(String groupID) throws Exception {
        if (this.cart.containsKey(groupID)) {
            this.cart.remove(groupID);
            return true;
        }
        return false;
    }
}
