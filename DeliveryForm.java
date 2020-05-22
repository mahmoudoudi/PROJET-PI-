/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;



import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.components.SignatureComponent;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.io.Preferences;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.BrowserNavigationCallback;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Delivery;
import com.mycompany.myapp.entities.Deliveryman;
import com.mycompany.myapp.entities.RateCity;
import com.mycompany.myapp.entities.Session;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.ServiceDelivery;
import com.mycompany.myapp.services.ServiceDeliveryman;
import com.mycompany.myapp.services.ServiceRateCity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author jelas
 */
public class DeliveryForm extends SideMenuBaseForm{
    
    protected Resources res;
    static Form  currentForm;
    user User = Session.getCurrentSession();
    float rate;
    static ArrayList<RateCity> ratecity;
   

    public DeliveryForm(Form previous, Resources resourceObjectInstance) {

        currentForm = this;
        currentForm.setTitle("Delivery");
        currentForm.setLayout(BoxLayout.y());
         Container cn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ComboBox c=new ComboBox();
        
        for (RateCity r : ServiceRateCity.getInstance().getAllRatecity()) {
            c.addItem(r.getCity());
        }
        Button search=new Button("Search by city");
        
        cn.addAll(c,search);
        currentForm.add(cn);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form SearchForm=new Form();
                
     
        SearchForm.setLayout(BoxLayout.y());
              for (Delivery d : ServiceDelivery.getInstance().getAllDelivery()) {
           if(c.getSelectedItem().toString().equals(d.getCity())){       
           
    Style s = UIManager.getInstance().getComponentStyle("Button");
FontImage pp = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
EncodedImage placeholder = EncodedImage.createFromImage(pp.scaled(pp.getWidth() * 3, pp.getHeight() * 4), false);



ArrayList<Map<String, Object>> data = new ArrayList<>();

data.add(createListEntry(d.getDestination()+" , "+ d.getCity(),String.valueOf(d.getCost()), "https://image.freepik.com/vecteurs-libre/icone-service-livraison-camion_24877-24828.jpg"));


DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);
ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");
ml.getUnselectedButton().setIcon(placeholder);
ml.getSelectedButton().setIcon(placeholder);
        
            SearchForm.add(ml);
            SearchForm.setTitle(d.getCity());
            SearchForm.show();
               ml.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     DeliverysDetail(d, resourceObjectInstance).show();                     
                 }
           });
              SearchForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
           new DeliveryForm(currentForm, resourceObjectInstance).show();
        });
        }}
         SearchForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
           new DeliveryForm(currentForm, resourceObjectInstance).show();
        });
            }});

        for (Delivery d : ServiceDelivery.getInstance().getAllDelivery()) {
            
        
     
            Container InfoContainer = new Container(BoxLayout.y());
            Label destination = new Label(d.getDestination());
            Label cityd =       new Label(d.getCity());
            Label costdelivery =new Label(String.valueOf(d.getCost()));
            Label etatd =       new Label(String.valueOf(d.getEtat()));
     
            destination.getAllStyles().setFgColor(ColorUtil.CYAN);
            cityd.getAllStyles().setFgColor(ColorUtil.CYAN);
            costdelivery.getAllStyles().setFgColor(ColorUtil.CYAN);
            etatd.getAllStyles().setFgColor(ColorUtil.CYAN);
     
            Container tl = TableLayout.encloseIn(2,
               new Label("Destination :"),
               destination,
               new Label("City :"),
               cityd,
               new Label("Coast :"),
               costdelivery,
               new Label("Etat :"),
                etatd);
            
       Style s = UIManager.getInstance().getComponentStyle("Button");
FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 4), false);

ArrayList<Map<String, Object>> data = new ArrayList<>();

data.add(createListEntry(destination.getText()+" , "+ cityd.getText(),costdelivery.getText(), "https://image.freepik.com/vecteurs-libre/icone-service-livraison-camion_24877-24828.jpg"));


DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);
ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");
ml.getUnselectedButton().setIcon(placeholder);
ml.getSelectedButton().setIcon(placeholder);

            
            add(ml);
            ml.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    DeliverysDetail(d, resourceObjectInstance).show();
                }
            });
            
            
            Container Container = new Container(BoxLayout.x());

          
            Container.add(InfoContainer);
            currentForm.add(Container);
             getTextSelection().setEnabled(true);
            destination.setTextSelectionEnabled(true);
            cityd.setTextSelectionEnabled(true);
            costdelivery.setTextSelectionEnabled(true);
            etatd.setTextSelectionEnabled(true);
            
            
            Container.addPointerReleasedListener(ev -> {
                DeliverysDetail(d, resourceObjectInstance).show();
            });
 
    }
     currentForm.getToolbar().addCommandToOverflowMenu("Add Delivery", null, ev -> {
           AddDelivery(resourceObjectInstance).show();
        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
           new InboxForm().show();
        });}
    

    
    
    
    
    
  
  public Form AddDelivery(Resources theme) {

        Form AddDelivery = new Form("Add a delivery", BoxLayout.y());

        Label destination = new Label("Destination");
        Label cityd = new Label("City");
        
        Button web=new Button("Chart of rate city");
        
        web.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f1=new Form();
                 
       Container cnt = new Container(new BorderLayout());
        final WebBrowser wb = new WebBrowser();
        if(wb.getInternal() instanceof BrowserComponent) {
            Button btn = new Button("Add");
            final TextArea content = new TextArea();
            Container north = new Container(new BorderLayout());
            north.addComponent(BorderLayout.CENTER, content);
            north.addComponent(BorderLayout.EAST, btn);
            cnt.addComponent(BorderLayout.NORTH, north);
            content.setHint("Add to web document");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ((BrowserComponent)wb.getInternal()).execute("fnc('" + content.getText() + "');");
                }
            });
            ((BrowserComponent)wb.getInternal()).setBrowserNavigationCallback(new BrowserNavigationCallback() {
                public boolean shouldNavigate(String url) {
                    if(url.startsWith("http://sayhello")) {
                        // warning!!! This is not on the EDT and this method MUST return immediately!
                        Display.getInstance().callSerially(new Runnable() {
                            public void run() {
                                ((BrowserComponent)wb.getInternal()).execute("fnc('this was written by Java code!')");
                            }
                        });
                        return false;
                    }
                    return true;
                }
            });
        }
        
        cnt.addComponent(BorderLayout.CENTER, wb);
        wb.setURL("jar:///Page.html");
        f1.add(cnt);
        
        f1.show();
            }
        });
       
        


        
        TextField des = new TextField(null, "");
       
       
       
        ComboBox c=new ComboBox();
        
        for (RateCity r : ServiceRateCity.getInstance().getAllRatecity()) {
            c.addItem(r.getCity());
        }
      
        
        Validator val = new Validator();
        val.addConstraint(des, new LengthConstraint(10),new NumericConstraint(false));
      
       
        
        Container cn1=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label lb=new Label("Enter Your Name To Continue:");
        TextField tf=new TextField();
        Label lb1=new Label("Signature:");
        SignatureComponent sig = new SignatureComponent();
        sig.addActionListener((evt)-> {
            System.out.println("The signature was changed");
            Image img = sig.getSignatureImage();
            // Now we can do whatever we want with the image of this signature.
        });
        cn1.addAll(lb,tf,lb1);
        cn1.addComponent(sig);
        
        Button Save = new Button("Continue");

        AddDelivery.addAll(destination, des, cityd, c,web);
AddDelivery.add(cn1);
AddDelivery.add(Save);


        

       
        Save.addActionListener(ev -> {
            
            if ((des.getText().length() == 0) ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                System.out.print(ratecity);
            } else {
          ratecity=ServiceRateCity.getInstance().getRate(c.getSelectedItem().toString());
                   
                    Delivery d = new Delivery();
                    d.setDestination(des.getText());
                    d.setCity(c.getSelectedItem().toString());
                    d.setCost(ratecity.get(0).getRate());
                    d.setEtat(0);
                    
                   
                       
                   

                    if (ServiceDelivery.getInstance().addDelivery(d)) {
                        Dialog.show("Success", "Delivery Added", new Command("OK"));
                        new DeliveryForm(DeliveryForm.currentForm, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                

            }

        });

        AddDelivery.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new DeliveryForm(DeliveryForm.currentForm, theme).show();
        });

        return AddDelivery;
    }
  
  public Form DeliverysDetail(Delivery d, Resources theme) {

        Form DeliveryDetail = new Form(BoxLayout.y());

       
        
        Label destination = new Label("Destination");
        Label cityd = new Label("City");
        Label costdelivery = new Label("Cost");
        Label etatd = new Label("Etat");
    


    
        TextField dest = new TextField(null, "Destination");
        dest.setText(d.getDestination());
        
        TextField cit = new TextField(null, "City");
        cit.setText(d.getCity());
        
        TextField cos = new TextField(null, "Cost");
        cos.setText(String.valueOf(d.getCost()));
        
        TextField et = new TextField(null, "Etat");
        et.setText(String.valueOf(d.getEtat()));
      
        

        Container Container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container.addAll(destination, dest, cityd, cit, costdelivery, cos,etatd ,et);
       
        DeliveryDetail.add(Container);

        Container ButtonsContainer = new Container(new BoxLayout(CENTER));

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
          Button Affecter = new Button("Affecter");
        ButtonsContainer.addAll(Edit, Delete,Affecter);

        DeliveryDetail.add(ButtonsContainer);
        DeliveryDetail.revalidate();
        
        Affecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form ListDeliverymanAvailable=new Form();
                ListDeliverymanAvailable.setTitle("Available Deliveryman");
                ListDeliverymanAvailable.setLayout(BoxLayout.y());

        for (Deliveryman d : ServiceDeliveryman.getInstance().getAllDeliveryman()) {
             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
         if(d.getAvailable().equals("yes")){
            Container InfoContainer = new Container(BoxLayout.y());
            
            Label tfname = new Label(d.getNamed());
            Label tflastname = new Label (d.getLastnamed());
            Label tfemail = new Label(d.getEmaildeliveryman());
            Label tfphone = new Label(String.valueOf(d.getPhonenumberd()));
            Label tfavailable = new Label(d.getAvailable());
            
            Label name = new Label("First Name : "+tfname.getText());
            Label lastname = new Label ("Last Name : "+tflastname.getText());
            Label email = new Label("Email : "+tfemail.getText());
            Label phone = new Label("Phone Number : "+tfphone.getText());
            Label available = new Label("Available : "+tfavailable.getText());
          
            
            available.getAllStyles().setFgColor(ColorUtil.CYAN);
            
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
      
            ListDeliverymanAvailable.add(ml);
             ListDeliverymanAvailable.show(); 
            ml.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    d.setNamed(tfname.getText());
            d.setLastnamed(tflastname.getText()); 
            d.setEmaildeliveryman(tfemail.getText());
            d.setPhonenumberd(Integer.parseInt(tfphone.getText()));
            d.setAvailable("no");
           
            
            if (ServiceDeliveryman.getInstance().EditDeliveryman(d)) {
                Dialog.show("Success", "Affected", new Command("OK"));
                new DeliveryForm(DeliveryForm.currentForm, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
                }
            });
           
          
      
            
         }
    }
        
   

       ListDeliverymanAvailable.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new DeliveryForm(DeliveryForm.currentForm, theme).show();
    

            });
            }});
        Delete.addActionListener(ev -> {
            Preferences.set("dontShowDialog", false);
         boolean b = Preferences.get("dontShowDialog", false);
    if(!b) {
        CheckBox areYouSure = new CheckBox("Don't ask again");
        areYouSure.setUIID("DialogBody");
        SpanLabel body = new SpanLabel("Are you sure you want to delete delivery?", "DialogBody");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("Are you Sure?", BoxLayout.encloseY(body, areYouSure),
                new Command[] {cancel, ok});
        if(result == ok) {
            ServiceDelivery.getInstance().DeleteDelivery(d);
            new DeliveryForm(DeliveryForm.currentForm, theme).show();
            ToastBar.showMessage("Delivery deleted...", FontImage.MATERIAL_INFO);
            Preferences.set("dontShowDialog", areYouSure.isSelected());
            
        }
    } else {
        ToastBar.showMessage("Skipped dialog", FontImage.MATERIAL_INFO);
    }
});
            
      

        Edit.addActionListener(ev -> {
            d.setDestination(dest.getText());
            d.setCity(cit.getText());
            
            d.setCost(Float.parseFloat(cos.getText()));
            d.setEtat(Integer.parseInt(et.getText()));
           
             Preferences.set("dontShowDialog", false);
         boolean b = Preferences.get("dontShowDialog", false);
    if(!b) {
        CheckBox areYouSure = new CheckBox("Don't ask again");
        areYouSure.setUIID("DialogBody");
        SpanLabel body = new SpanLabel("Are you sure you want to edit delivery?", "DialogBody");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("Are you Sure?", BoxLayout.encloseY(body, areYouSure),
                new Command[] {cancel, ok});
        if(result == ok) {
            ServiceDelivery.getInstance().EditDelivery(d);
            new DeliveryForm(DeliveryForm.currentForm, theme).show();
            ToastBar.showMessage("Delivery edited...", FontImage.MATERIAL_INFO);
            Preferences.set("dontShowDialog", areYouSure.isSelected());
            
        }
    } else {
        ToastBar.showMessage("Skipped dialog", FontImage.MATERIAL_INFO);
    }
         
        });

       DeliveryDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new DeliveryForm(DeliveryForm.currentForm, theme).show();
        });

        return DeliveryDetail;
}
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
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
   public String getDisplayName() {
        return "Web";
    }

    public Image getDemoIcon() {
        return getResources().getImage("applications-internet.png");
    }
        
    protected Resources getResources() {
        return res;
    }
    
}
