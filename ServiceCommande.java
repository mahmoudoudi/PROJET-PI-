/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

/**
 *
 * @author MED
 */

    


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.gui.PanierForm;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;




public class ServiceCommande {

    public static ServiceCommande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    user User = Session.getCurrentSession();


    public ServiceCommande() {
        req = new ConnectionRequest();

    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public boolean ConfirmAchat() {

        String idArticles = "";
        String qtn = "";

        int sizeMap = PanierForm.panierMap.size();
        System.out.println("   MAP SIZE  " + sizeMap);
        int counter = 0;
        for (Entry<Product, Integer> entry : PanierForm.panierMap.entrySet()) {
                idArticles += entry.getKey().getIdproduct()+ ",";
                qtn += entry.getValue() + ",";
        }

        System.out.println("idArticles=" + idArticles);
        System.out.println("qtn=" + qtn);

        String url = Statics.BASE_URL + "confirmeAchat";

        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        r.setUrl(url);

        r.addArgument("size", "" + PanierForm.panierMap.size());
        r.addArgument("idArticles", "" + idArticles);
        r.addArgument("qtn", "" + qtn);

       
        r.addArgument("idUtilisateur", "" + User.getId());

 
        r.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = r.getResponseCode() == 200; //Code HTTP 200 OK
                r.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return resultOK;
    }

    
    
}
