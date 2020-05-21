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
import com.mycompany.myapp.entities.Delivery;
import com.mycompany.myapp.entities.RateCity;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jelas
 */
public class ServiceDelivery {
     public ArrayList<Delivery> delivery;
    public  String  result="";
    public static ServiceDelivery instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceDelivery() {
         req = new ConnectionRequest();
    }

    public static ServiceDelivery getInstance() {
        if (instance == null) {
            instance = new ServiceDelivery();
        }
        return instance;
    }
    
    public ArrayList<Delivery> parsedelivery(String jsonText){
        try {
            delivery=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Delivery d = new Delivery();
                d.setIddelivery((int)Float.parseFloat(obj.get("iddelivery").toString()));
                d.setDestination((obj.get("destination").toString()));
                d.setCity(obj.get("cityd").toString());
                d.setCost(Float.parseFloat(obj.get("costdelivery").toString()));
                d.setEtat(((int)Float.parseFloat(obj.get("etatd").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
                delivery.add(d);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return delivery;
    }
   
    
       public ArrayList<Delivery> getAllDelivery(){
        String url = Statics.BASE_URL+"ListDeliveryMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                delivery = parsedelivery(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return delivery;
       }
        
       
    
    public boolean addDelivery(Delivery t) {
        String url = Statics.BASE_URL2 + "/delivery/new?destination=" + t.getDestination()+ "&cityd=" + t.getCity()+ "&costdelivery=" + t.getCost()
                + "&etatd=" + t.getEtat() ; //création de l'URL
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
    
    
    public String DeleteDelivery(Delivery d){
          String url = Statics.BASE_URL + "deleteDeliveryMobile/?iddelivery=" + d.getIddelivery();
          
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
    
    public boolean EditDelivery(Delivery d) {
        String url = Statics.BASE_URL + "EditDeliveryMobile/?iddelivery="+d.getIddelivery()
                +"&destination=" + d.getDestination()
                + "&cityd=" + d.getCity()
                + "&costdelivery=" + d.getCost()
                + "&etatd=" + d.getEtat() ;
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
