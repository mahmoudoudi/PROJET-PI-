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
public class Deliveryman {
    int iddeliveryman,phonenumberd;
    String named,lastnamed,addressd,emaildeliveryman,password,available ;
    float score,numberscore;

    public Deliveryman() {
    }

    public Deliveryman(int iddeliveryman) {
        this.iddeliveryman = iddeliveryman;
    }

    public Deliveryman(int iddeliveryman, int phonenumberd, String named, String lastnamed, String addressd, String emaildeliveryman, String password, String available, float score, float numberscore) {
        this.iddeliveryman = iddeliveryman;
        this.phonenumberd = phonenumberd;
        this.named = named;
        this.lastnamed = lastnamed;
        this.addressd = addressd;
        this.emaildeliveryman = emaildeliveryman;
        this.password = password;
   
        this.available = available;
        this.score = score;
        this.numberscore = numberscore;
    }

    public int getIddeliveryman() {
        return iddeliveryman;
    }

    public void setIddeliveryman(int iddeliveryman) {
        this.iddeliveryman = iddeliveryman;
    }

    public int getPhonenumberd() {
        return phonenumberd;
    }

    public void setPhonenumberd(int phonenumberd) {
        this.phonenumberd = phonenumberd;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public String getLastnamed() {
        return lastnamed;
    }

    public void setLastnamed(String lastnamed) {
        this.lastnamed = lastnamed;
    }

    public String getAddressd() {
        return addressd;
    }

    public void setAddressd(String addressd) {
        this.addressd = addressd;
    }

    public String getEmaildeliveryman() {
        return emaildeliveryman;
    }

    public void setEmaildeliveryman(String emaildeliveryman) {
        this.emaildeliveryman = emaildeliveryman;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getNumberscore() {
        return numberscore;
    }

    public void setNumberscore(float numberscore) {
        this.numberscore = numberscore;
    }

    @Override
    public String toString() {
        return "Deliveryman{" + "iddeliveryman=" + iddeliveryman + ", phonenumberd=" + phonenumberd + ", named=" + named + ", lastnamed=" + lastnamed + ", addressd=" + addressd + ", emaildeliveryman=" + emaildeliveryman + ", password=" + password + ", available=" + available + ", score=" + score + ", numberscore=" + numberscore + '}';
    }
    
    
    
}
