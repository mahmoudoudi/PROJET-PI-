/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.user;
import static com.mycompany.myapp.gui.ProductForm.currentForm;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MED
 */
public class PanierForm  extends SideMenuBaseForm {
     static Form  currentForm;
   
    static ArrayList<Product> currentPanier;
    Container cntAll = new Container(BoxLayout.y());
    static public HashMap<Product, Integer> panierMap = new HashMap<Product, Integer>();
    float prixTotal = 0;    
    user User = Session.getCurrentSession();
        
        
       
    public PanierForm(){
        
           currentForm = this;
        currentForm.setTitle("Panier");
        currentForm.setLayout(BoxLayout.y());
 
    Form previous=Display.getInstance().getCurrent();
    currentForm.getToolbar().setBackCommand("hello",e->{previous.showBack();});

        for (Product product : panierMap.keySet()) {
            Integer key = product.getIdproduct();
            Integer qte = panierMap.get(product);
            System.out.println(key + " " + qte);

           
            prixTotal += product.getPriceproduct()* qte;
            Container c =new  Container(BoxLayout.x());
           Button btnRemove=new Button("Remove");
                   btnRemove.addActionListener(e -> {
            if (Dialog.show("Confirm", "do you want to delete item?", "OK", "CANCEL")) {
                panierMap.remove(product);
                System.out.println("deleted");
                new PanierForm().show();
            }

        });


            c.addAll(new Label( product.getNameproduct() ),new Label("Qte:"+qte),new Label("price:"+ product.getPriceproduct()* qte));
             
            cntAll.add(c);
            cntAll.add(btnRemove);
        }
        Label totalLb = new Label("Total Price : " + prixTotal);
     
     
        add(cntAll);
        add(totalLb);
      

      Button applyBtn=new  Button("Apply");
      
        ServiceCommande lcs = ServiceCommande.getInstance();
        
        applyBtn.addActionListener(l->{
        
        if (panierMap.isEmpty()) {
                    Dialog.show("Produit", "Votre panier est vide ! Ajouter des article pour continuer l achat!", "OK", null);

                } else {
                    System.out.println("BUYING ARTICLES ");
                    boolean result = lcs.ConfirmAchat();
                    if (result) {
                   
                        System.out.println("SUCCESS");
                        panierMap.clear();
                        new PanierForm().show();

                    } else {
                        System.out.println("ERROR");
                    }

        
        
        
        }});
        
        add(applyBtn);
    }
       


  
    
    
 
    
    
    
    
    
        
    
 protected void showOtherForm(Resources res) {
        
    }
    
    
    

    
}
