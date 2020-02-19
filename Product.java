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
public class Product {
    private int IdProduct;
    private String NameCategory, NameProduct, DescriptionProduct;
    private int QuantityProduct;
    private float PriceProduct, Promotion;

    public Product(String NameCategory, String NameProduct, String DescriptionProduct, int QuantityProduct, float PriceProduct, float Promotion) {
        this.NameCategory = NameCategory;
        this.NameProduct = NameProduct;
        this.DescriptionProduct = DescriptionProduct;
        this.QuantityProduct = QuantityProduct;
        this.PriceProduct = PriceProduct;
        this.Promotion = Promotion;
    }

    public Product(int IdProduct, String NameCategory, String NameProduct, String DescriptionProduct, int QuantityProduct, float PriceProduct, float Promotion) {
        this.IdProduct = IdProduct;
        this.NameCategory = NameCategory;
        this.NameProduct = NameProduct;
        this.DescriptionProduct = DescriptionProduct;
        this.QuantityProduct = QuantityProduct;
        this.PriceProduct = PriceProduct;
        this.Promotion = Promotion;
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }
    
    

    public String getNameCategory() {
        return NameCategory;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public String getDescriptionProduct() {
        return DescriptionProduct;
    }

    public int getQuantityProduct() {
        return QuantityProduct;
    }

    public float getPriceProduct() {
        return PriceProduct;
    }

    public float getPromotion() {
        return Promotion;
    }

    public void setNameCategory(String NameCategory) {
        this.NameCategory = NameCategory;
    }

    public void setNameProduct(String NameProduct) {
        this.NameProduct = NameProduct;
    }

    public void setDescriptionProduct(String DescriptionProduct) {
        this.DescriptionProduct = DescriptionProduct;
    }

    public void setQuantityProduct(int QuantityProduct) {
        this.QuantityProduct = QuantityProduct;
    }

    public void setPriceProduct(float PriceProduct) {
        this.PriceProduct = PriceProduct;
    }

    public void setPromotion(float Promotion) {
        this.Promotion = Promotion;
    }

    @Override
    public String toString() {
        return "Product{" + "NameCategory=" + NameCategory + ", NameProduct=" + NameProduct + ", DescriptionProduct=" + DescriptionProduct + ", QuantityProduct=" + QuantityProduct + ", PriceProduct=" + PriceProduct + ", Promotion=" + Promotion + '}';
    }
     public int toStringid() {
       return IdProduct;
     }
     public String toStringNameCategory() {
       return NameCategory;
     }
     public String toStringNameProduct() {
       return NameProduct;
     }
     public String toStringDescriptionProduct() {
       return DescriptionProduct;
     }
     public int toStringQuantityProduct() {
       return QuantityProduct;
     }
     public float toStringPriceProduct() {
       return PriceProduct;
     }
     public float toStringPromotion() {
       return Promotion;
     }
    
}
