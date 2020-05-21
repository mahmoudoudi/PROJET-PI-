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
public class ServiceRateCity {
      public ArrayList<RateCity> ratecity;
    public  String  result="";
    public static ServiceRateCity instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRateCity() {
         req = new ConnectionRequest();
    }

    public static ServiceRateCity getInstance() {
        if (instance == null) {
            instance = new ServiceRateCity();
        }
        return instance;
    }
     public ArrayList<RateCity> parseratecity(String jsonText){
        try {
            ratecity=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                RateCity r = new RateCity();
                r.setId((int)Float.parseFloat(obj.get("id").toString()));
                r.setCity((obj.get("city").toString()));
                r.setRate(Float.parseFloat(obj.get("rate").toString()));
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                ratecity.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return ratecity;
    }
      public ArrayList<RateCity> getAllRatecity(){
        String url = Statics.BASE_URL+"ListRatecityMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ratecity = parseratecity(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return ratecity;
       }
      
          public ArrayList<RateCity> getRate(String city){
        String url = Statics.BASE_URL+"findrate/"+city;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ratecity = parseratecity(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return ratecity;
       }
  
}
