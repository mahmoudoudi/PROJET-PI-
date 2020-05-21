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
public class Product {
    int idproduct,quantityproduct;
    String namecategory,nameproduct,descriptionproduct;
    float priceproduct,promotion;

    public Product(int idproduct, int quantityproduct, String namecategory, String nameproduct, String descriptionproduct, float priceproduct, float promotion) {
        this.idproduct = idproduct;
        this.quantityproduct = quantityproduct;
        this.namecategory = namecategory;
        this.nameproduct = nameproduct;
        this.descriptionproduct = descriptionproduct;
        this.priceproduct = priceproduct;
        this.promotion = promotion;
    }

    public Product(int quantityproduct, String namecategory, String nameproduct, String descriptionproduct, float priceproduct, float promotion) {
        this.quantityproduct = quantityproduct;
        this.namecategory = namecategory;
        this.nameproduct = nameproduct;
        this.descriptionproduct = descriptionproduct;
        this.priceproduct = priceproduct;
        this.promotion = promotion;
    }

    public Product(int idproduct) {
        this.idproduct = idproduct;
    }

    public Product() {
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }
    

   

    public int getQuantityproduct() {
        return quantityproduct;
    }

    public void setQuantityproduct(int quantityproduct) {
        this.quantityproduct = quantityproduct;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getDescriptionproduct() {
        return descriptionproduct;
    }

    public void setDescriptionproduct(String descriptionproduct) {
        this.descriptionproduct = descriptionproduct;
    }

    public float getPriceproduct() {
        return priceproduct;
    }

    public void setPriceproduct(float priceproduct) {
        this.priceproduct = priceproduct;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Product{" + "idproduct=" + idproduct + ", quantityproduct=" + quantityproduct + ", namecategory=" + namecategory + ", nameproduct=" + nameproduct + ", descriptionproduct=" + descriptionproduct + ", priceproduct=" + priceproduct + ", promotion=" + promotion + '}';
    }
    
}
