/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author jelas
 */
public class Category {
    int idcategory;
    String namecategory,descriptioncategory;

    public Category() {
    }

    public Category(int idcategory) {
        this.idcategory = idcategory;
    }

    public Category(int idcategory, String namecategory, String descriptioncategory) {
        this.idcategory = idcategory;
        this.namecategory = namecategory;
        this.descriptioncategory = descriptioncategory;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public String getDescriptioncategory() {
        return descriptioncategory;
    }

    public void setDescriptioncategory(String descriptioncategory) {
        this.descriptioncategory = descriptioncategory;
    }

    @Override
    public String toString() {
        return "Category{" + "idcategory=" + idcategory + ", namecategory=" + namecategory + ", descriptioncategory=" + descriptioncategory + '}';
    }
    
}
