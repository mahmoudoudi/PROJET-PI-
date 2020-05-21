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
public class RateCity {
    int id;
    String City;
    float Rate;
    
  public RateCity() {
    }
  
    public RateCity(int id, String City, float Rate) {
        this.id = id;
        this.City = City;
        this.Rate = Rate;
    }

    public RateCity(int id) {
        this.id = id;
    }

    public RateCity(String City) {
        this.City = City;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float Rate) {
        this.Rate = Rate;
    }

    @Override
    public String toString() {
        return "RateCity{" + "id=" + id + ", City=" + City + ", Rate=" + Rate + '}';
    }

  
    
    
    
}
