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
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jelas
 */
public class ServiceProduct {
      public ArrayList<Product> product;
    public  String  result="";
    public static ServiceProduct instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProduct() {
         req = new ConnectionRequest();
    }

    public static ServiceProduct getInstance() {
        if (instance == null) {
            instance = new ServiceProduct();
        }
        return instance;
    }
    
    public ArrayList<Product> parseproduct(String jsonText){
        try {
            product=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Product p = new Product();
                p.setIdproduct((int)Float.parseFloat(obj.get("idproduct").toString()));
                p.setNamecategory((obj.get("namecategory").toString()));
                p.setNameproduct(obj.get("nameproduct").toString());
                p.setDescriptionproduct(obj.get("descriptionproduct").toString());
                p.setQuantityproduct((int)Float.parseFloat(obj.get("quantityproduct").toString()));
                p.setPriceproduct(Float.parseFloat(obj.get("priceproduct").toString()));
                p.setPromotion(Float.parseFloat(obj.get("promotion").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                product.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return product;
    }
    
       public ArrayList<Product> getAllProduct(){
        String url = Statics.BASE_URL+"ListProductMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                product = parseproduct(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return product;
       }
    
    public boolean addProduct(Product t) {
        String url = Statics.BASE_URL3 + "/product/new?namecategory=" + t.getNamecategory()+
                "&nameproduct=" + t.getNameproduct()+
                "&descriptionproduct="+t.getDescriptionproduct()+
                "&quantityproduct=" + t.getQuantityproduct()
                +"&priceproduct=" + t.getPriceproduct()+ 
                "&promotion=" + t.getPriceproduct(); //création de l'URL
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
    
    
    public String DeleteProduct(Product d) {
          String url = Statics.BASE_URL + "deleteProductMobile/?idproduct=" + d.getIdproduct();
          
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
    
    public boolean EditProduct(Product d) {
        String url = Statics.BASE_URL + "EditProductMobile/?idproduct="+d.getIdproduct()
                +"&namecategory=" + d.getNamecategory()
                +"&nameproduct=" + d.getNameproduct()
                +"&descriptionproduct="+d.getDescriptionproduct()
                +"&quantityproduct="+d.getQuantityproduct()
                +"&priceproduct="+d.getPriceproduct()
                +"&promotion="+d.getPromotion()
               ;
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
