/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MPSHOP
 */
public class Category {
    int IdCategory;
    private String NameCategory, DescriptionCategory;

    public Category(String NameCategory, String DescriptionCategory) {
        this.NameCategory = NameCategory;
        this.DescriptionCategory = DescriptionCategory;
    }

    public Category(String NameCategory) {
        this.NameCategory = NameCategory;
    }

    public Category (int IdCategory, String NameCategory, String DescriptionCategory) {
        this.IdCategory = IdCategory;
        this.NameCategory = NameCategory;
        this.DescriptionCategory = DescriptionCategory;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public String getDescriptionCategory() {
        return DescriptionCategory;
    }

    public void setIdCategory(int IdCategory) {
        this.IdCategory = IdCategory;
    }

    public void setNameCategory(String NameCategory) {
        this.NameCategory = NameCategory;
    }

    public void setDescriptionCategory(String DescriptionCategory) {
        this.DescriptionCategory = DescriptionCategory;
    }



    
    public int toString1() {
        return  IdCategory;
    }
    
    
    
}
