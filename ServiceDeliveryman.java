/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Deliveryman;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jelas
 */
public class ServiceDeliveryman {
     public ArrayList<Deliveryman> deliveryman;
    public  String  result="";
    public static ServiceDeliveryman instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceDeliveryman() {
         req = new ConnectionRequest();
    }

    public static ServiceDeliveryman getInstance() {
        if (instance == null) {
            instance = new ServiceDeliveryman();
        }
        return instance;
    }
    
    public ArrayList<Deliveryman> parsedeliveryman(String jsonText){
        try {
            deliveryman=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Deliveryman d = new Deliveryman();
                d.setIddeliveryman((int)Float.parseFloat(obj.get("iddeliveryman").toString()));
                d.setNamed((obj.get("named").toString()));
                d.setLastnamed((obj.get("lastnamed").toString()));
                d.setAddressd((obj.get("addressd").toString()));
                d.setEmaildeliveryman((obj.get("emaildeliveryman").toString()));
                d.setPhonenumberd((int)Float.parseFloat(obj.get("phonenumberd").toString()));
                d.setAvailable((obj.get("available").toString()));
                d.setScore(Float.parseFloat(obj.get("score").toString()));
                d.setNumberscore(Float.parseFloat(obj.get("numberscore").toString()));
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                deliveryman.add(d);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return deliveryman;
    }
   
    
       public ArrayList<Deliveryman> getAllDeliveryman(){
        String url = Statics.BASE_URL+"ListDeliverymanMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                deliveryman = parsedeliveryman(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return deliveryman;
       }
        
       
    
    public boolean addDeliveryman(Deliveryman t) {
        String url = Statics.BASE_URL2 + "/deliveryman/new?named=" + t.getNamed()+ "&lastnamed=" + t.getLastnamed()+ "&addressd=" + t.getAddressd()
                + "&emaildeliveryman=" + t.getEmaildeliveryman() + "&phonenumberd=" + t.getPhonenumberd(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    public String DeleteDeliveryman(Deliveryman d){
          String url = Statics.BASE_URL + "deleteDeliverymanMobile/?iddeliveryman=" + d.getIddeliveryman();
          
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
                   

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                   result=(String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public boolean EditDeliveryman(Deliveryman d) {
        String url = Statics.BASE_URL + "EditDeliverymanMobile/?iddeliveryman="+d.getIddeliveryman()
                +"&named=" + d.getNamed()
                + "&lastnamed=" + d.getLastnamed()
                + "&addressd=" + d.getAddressd()
                + "&emaildeliveryman=" + d.getEmaildeliveryman()
                + "&phonenumberd=" + d.getPhonenumberd()
                + "&available=" + d.getAvailable();
               
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
