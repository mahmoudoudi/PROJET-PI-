/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Deliveryman;
import com.mycompany.myapp.entities.RateCity;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.ServiceDeliveryman;
import com.mycompany.myapp.services.ServiceRateCity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jelas
 */
public class DeliverymanForm extends SideMenuBaseForm {
     static Form  currentForm;
    user User = Session.getCurrentSession();
    public int i;

    
  
   

    public DeliverymanForm(Form previous, Resources resourceObjectInstance) {

        currentForm = this;
        currentForm.setTitle("Deliveryman");
        currentForm.setLayout(BoxLayout.y());

        for (Deliveryman d : ServiceDeliveryman.getInstance().getAllDeliveryman()) {
             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
     
            Container InfoContainer = new Container(BoxLayout.y());
            
            Label name = new Label(d.getNamed());
            Label lastname = new Label (d.getLastnamed());
            Label email = new Label(d.getEmaildeliveryman());
            Label phone = new Label(String.valueOf(d.getPhonenumberd()));
            Label available = new Label (d.getAvailable());
            
           name.getAllStyles().setFgColor(ColorUtil.GREEN);
           lastname.getAllStyles().setFgColor(ColorUtil.GREEN);
           email.getAllStyles().setFgColor(ColorUtil.GREEN);
           phone.getAllStyles().setFgColor(ColorUtil.GREEN);
           available.getAllStyles().setFgColor(ColorUtil.GREEN);
           
             Container tl = TableLayout.encloseIn(2,
               new Label("First Name : "),
               name,
               new Label("Last Name : "),
               lastname,
               new Label("Email : "),
               email,
               new Label("Phone Number : "),
               phone,
               new Label("Available : "),
               available);
                Style s = UIManager.getInstance().getComponentStyle("Button");
FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 4), false);



ArrayList<Map<String, Object>> data = new ArrayList<>();

data.add(createListEntry(d.getNamed()+"  "+ d.getLastnamed(),"Phone Number : "+String.valueOf(d.getPhonenumberd()), "https://www.liberaldictionary.com/wp-content/uploads/2019/01/deliveryman-7485.jpg"));


DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);
ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");
ml.getUnselectedButton().setIcon(placeholder);
ml.getSelectedButton().setIcon(placeholder);
        
            currentForm.add(ml);
            
            
           ml.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     DeliverymanDetail(d, resourceObjectInstance).show();
                 }
           });
 
    }
     currentForm.getToolbar().addCommandToOverflowMenu("Add Deliveryman", null, ev -> {
           AddDeliveryman(resourceObjectInstance).show();
        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
           new InboxForm().show();
        });}
    

    
    
    
    
  
  public Form AddDeliveryman(Resources theme) {

        Form AddDeliveryman = new Form("Add a deliveryman", BoxLayout.y());

        Label name = new Label("First Name");
        Label lastname = new Label("Last Name");
        Label address = new Label("Address");
        Label ville=new Label("City");
        Label email = new Label("Email");
        Label phone = new Label("Phone Number");

        
        TextField tfname = new TextField(null, "");
       
        TextField tflastname = new TextField(null, "");
        TextField tfaddress = new TextField(null, "");
         TextField tfemail = new TextField(null, "");
         TextField tfphone = new TextField(null, "");
        
       ComboBox c=new ComboBox();
        
        for (RateCity r : ServiceRateCity.getInstance().getAllRatecity()) {
            c.addItem(r.getCity());
        }
        

     Container tl = TableLayout.encloseIn(2, new Label("Address"),
                ville,
                tfaddress,
                c);
       
        Button Save = new Button("Continue");

        AddDeliveryman.addAll(name, tfname, lastname, tflastname, tl, email, tfemail,phone,tfphone);

AddDeliveryman.add(Save);


        
          

       
        Save.addActionListener(ev -> {
            


            if ((tfname.getText().length() == 0) || tflastname.getText().length() == 0
                    || tfaddress.getText().length() == 0 ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
               
            }
            else if (!( Integer.parseInt(tfphone.getText())<=99999999 && Integer.parseInt(tfphone.getText())>=10000000)){
                Dialog.show("Alert", "Phone Number must contain 8 numbers", new Command("OK"));
            }
           
           
            else {
                try {
                  
                    Deliveryman d = new Deliveryman();
                    d.setNamed(tfname.getText());
                    d.setLastnamed(tflastname.getText());
                    d.setAddressd(tfaddress.getText()+"  "+c.getSelectedItem().toString());
                    d.setEmaildeliveryman(tfemail.getText());
                    d.setPhonenumberd(Integer.parseInt(tfphone.getText()));
                    
                   
 Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
 m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Hooks And Horns", tfemail.getText(),"Jelassi", "Success",
                            "Check out Codename One at https://www.codenameone.com/");
                    System.out.println(success);          

                    if (ServiceDeliveryman.getInstance().addDeliveryman(d)) {
                        Dialog.show("Success", "Deliveryman Added", new Command("OK"));
                        new DeliverymanForm(DeliverymanForm.currentForm, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Number places  must be a number", new Command("OK"));
                }

            }

        });

        AddDeliveryman.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new DeliverymanForm(DeliverymanForm.currentForm, theme).show();
        });

        return AddDeliveryman;
    }
  
  public Form DeliverymanDetail(Deliveryman d, Resources theme) {

        Form DeliverymanDetail = new Form(BoxLayout.y());

       
        
        Label name = new Label("First Name");
        Label lastname = new Label("Last Name");
        Label address = new Label("Address");
        Label email = new Label("Email");
        Label phone = new Label("Phone Number");
        //Label image = new Label("image");

        //SpanLabel Message = new SpanLabel("Descrption: \n" + c.getDescription() + "\n" + "Created AT: " + c.getDate() + "\n" + "Members: " + c.getNombreplace());
    
        TextField tfname = new TextField(null, "First Name");
        tfname.setText(d.getNamed());
        
        TextField tflastname = new TextField(null, "Last Name");
        tflastname.setText(d.getLastnamed());
        
        TextField tfaddress = new TextField(null, "Address");
        tfaddress.setText(d.getAddressd());
        
        TextField tfemail = new TextField(null, "Email");
        tfemail.setText(d.getEmaildeliveryman());
        
        TextField tfphone = new TextField(null, "Phone Number");
        tfphone.setText(String.valueOf(d.getPhonenumberd()));
      

        Container Container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container.addAll(name, tfname, lastname, tflastname, address, tfaddress,email ,tfemail,phone,tfphone);
       
        DeliverymanDetail.add(Container);

        Container ButtonsContainer = new Container(new BoxLayout(CENTER));

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        Button PDF = new Button("PDF");
        ButtonsContainer.addAll(Edit, Delete,PDF);

        DeliverymanDetail.add(ButtonsContainer);
        DeliverymanDetail.revalidate();
        Delete.addActionListener(ev -> {
         Preferences.set("dontShowDialog", false);
         boolean b = Preferences.get("dontShowDialog", false);
    if(!b) {
        CheckBox areYouSure = new CheckBox("Don't ask again");
        areYouSure.setUIID("DialogBody");
        SpanLabel body = new SpanLabel("Are you sure you want to delete deliveryman?", "DialogBody");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("Are you Sure?", BoxLayout.encloseY(body, areYouSure),
                new Command[] {cancel, ok});
        if(result == ok) {
            ServiceDeliveryman.getInstance().DeleteDeliveryman(d);
            new DeliverymanForm(DeliverymanForm.currentForm, theme).show();
            ToastBar.showMessage("Deliveryman deleted...", FontImage.MATERIAL_INFO);
            Preferences.set("dontShowDialog", areYouSure.isSelected());
            
        }
    } else {
        ToastBar.showMessage("Skipped dialog", FontImage.MATERIAL_INFO);
    }
        });

        Edit.addActionListener(ev -> {
            d.setNamed(tfname.getText());
            d.setLastnamed(tflastname.getText()); 
            d.setAddressd(tfaddress.getText());
            d.setEmaildeliveryman(tfemail.getText());
            d.setAvailable("yes");
            Preferences.set("dontShowDialog", false);
         boolean b = Preferences.get("dontShowDialog", false);
    if(!b) {
        CheckBox areYouSure = new CheckBox("Don't ask again");
        areYouSure.setUIID("DialogBody");
        SpanLabel body = new SpanLabel("Are you sure you want to edit deliveryman?", "DialogBody");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("Are you Sure?", BoxLayout.encloseY(body, areYouSure),
                new Command[] {cancel, ok});
        if(result == ok) {
            ServiceDeliveryman.getInstance().EditDeliveryman(d);
            new DeliverymanForm(DeliverymanForm.currentForm, theme).show();
            ToastBar.showMessage("Deliveryman edited...", FontImage.MATERIAL_INFO);
            Preferences.set("dontShowDialog", areYouSure.isSelected());
            
        }
    } else {
        ToastBar.showMessage("Skipped dialog", FontImage.MATERIAL_INFO);
    }
            
         
        });
PDF.addActionListener(ev -> {
   
    
});
        DeliverymanDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new DeliverymanForm(currentForm, theme);
        });

        return DeliverymanDetail;
}
   


    @Override
    protected void showOtherForm(Resources res) {
       
    }
        private Map<String, Object> createListEntry(String name, String coast, String coverURL) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", coast);
    entry.put("icon_URLImage", coverURL);
    entry.put("icon_URLImageName", name);
    return entry;
}
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
}
