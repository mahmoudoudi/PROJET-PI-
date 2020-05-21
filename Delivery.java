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
public class Delivery {
      private int iddelivery;
     private int idorder;
    private String destination;
    private float cost;
    private String City;
    private int etat;

    public Delivery() {
    }

    public Delivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }

    public Delivery(int iddelivery, String destination, float cost, String City, int etat) {
        this.iddelivery = iddelivery;
        this.destination = destination;
        this.cost = cost;
        this.City = City;
        this.etat = etat;
    }

    public Delivery(int iddelivery, int idorder, String destination, float cost, String City, int etat) {
        this.iddelivery = iddelivery;
        this.idorder = idorder;
        this.destination = destination;
        this.cost = cost;
        this.City = City;
        this.etat = etat;
    }

    public int getIddelivery() {
        return iddelivery;
    }

    public void setIddelivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    

    @Override
    public String toString() {
        return "Delivery{" + "iddelivery=" + iddelivery + ", idorder=" + idorder + ", destination=" + destination + ", cost=" + cost + ", City=" + City + ", etat=" + etat + '}';
    }
}
