/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Category;
import Entities.Product;
import java.sql.*;
import javafx.collections.ObservableList;
import Service.ServiceCategory;
import Service.ServiceProduct;
import java.net.URL;
import Utilis.DataBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static jdk.nashorn.internal.objects.Global.print;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperReport;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static sun.misc.Version.print;
import static sun.misc.Version.print;


/**
 * FXML Controller class
 *
 * @author MPSHOP
 */
public class AddProductController implements Initializable {
    @FXML
    private TextField namecategorytf;
    @FXML
    private TextField nameproducttf;
    @FXML
    private TextField pricetf;
    @FXML
    private TextField promotiontf;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TextField desctf;
    @FXML
    private TextField quantitytf;
    @FXML
    private TableView<Product> tablep;
    @FXML
    private TableColumn<Product, Integer> idt;
    @FXML
    private TableColumn<Product, String> namect;
    @FXML
    private TableColumn<Product, String> namept;
    @FXML
    private TableColumn<Product, String> desct;
    @FXML
    private TableColumn<Product, Integer> quantityt;
    @FXML
    private TableColumn<Product, Float> pricet;
    @FXML
    private TableColumn<Product, Float> promotiont;
     public ObservableList<Product> data = FXCollections.observableArrayList();
    @FXML
    private Button TriH;
    @FXML
    private Button TriL;
    @FXML
    private Button Reset;
    @FXML
    private Button ShowPromotion;
    @FXML
    private javafx.scene.control.Label namecategorylabe;
    @FXML
    private Label nameproductlabel;
    @FXML
    private Label quantitelabel;
    @FXML
    private Label pricelabel;
    @FXML
    private Label promotionlabel;
    @FXML
    private Button Stats;
    @FXML
    private ComboBox listecat;
    @FXML
    private Button BUploadPic;
    @FXML
    private TextField pictf;
    @FXML
    private Button pdf;
   


    public void setTablep(TableView<Product> tablep) {
        this.tablep = tablep;
    }

    public void setIdt(TableColumn<Product, Integer> idt) {
        this.idt = idt;
    }

    public void setNamect(TableColumn<Product, String> namect) {
        this.namect = namect;
    }

    public void setNamept(TableColumn<Product, String> namept) {
        this.namept = namept;
    }

    public void setDesct(TableColumn<Product, String> desct) {
        this.desct = desct;
    }

    public void setQuantityt(TableColumn<Product, Integer> quantityt) {
        this.quantityt = quantityt;
    }

    public void setPricet(TableColumn<Product, Float> pricet) {
        this.pricet = pricet;
    }

    public void setPromotiont(TableColumn<Product, Float> promotiont) {
        this.promotiont = promotiont;
    }

    public void setData(ObservableList<Product> data) {
        this.data = data;
    }

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduct serP = new ServiceProduct();
        try {
            data.addAll(serP.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);
        try {
            listecat.setItems(FXCollections.observableArrayList(serP.combo()));
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablep.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

@Override
public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
if(tablep.getSelectionModel().getSelectedItem() != null) 
{    
TableViewSelectionModel selectionModel = tablep.getSelectionModel();
ObservableList selectedCells = selectionModel.getSelectedCells();
TablePosition tablePosition = (TablePosition) selectedCells.get(0);
Object val = tablePosition.getTableColumn().getCellData(newValue);
int column = tablePosition.getColumn();
int row = tablePosition.getRow();
int x=tablep.getItems().get(row).toStringid();
String NameCategory=tablep.getItems().get(row).toStringNameCategory();
/*try {
            String NameCategory=listecat.getValue();
           
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
String NameProduct=tablep.getItems().get(row).toStringNameProduct();
String DescriptionProduct=tablep.getItems().get(row).toStringDescriptionProduct();
int QuantityProduct=tablep.getItems().get(row).toStringQuantityProduct();
float PriceProduct=tablep.getItems().get(row).toStringPriceProduct();
float Promotion=tablep.getItems().get(row).toStringPromotion();
//namecategorytf.setText(NameCategory);
//listecat.setItems(FXCollections.observableArrayList(NameCategory));
//listecat.addItem(NameCategory);
nameproducttf.setText(NameProduct);
desctf.setText(DescriptionProduct);
quantitytf.setText(String.valueOf(QuantityProduct));
pricetf.setText(String.valueOf(PriceProduct));
promotiontf.setText(String.valueOf(Promotion));        
}

        
}
});
    }  
    @FXML
    private void addaction(ActionEvent event) throws IOException, SQLException {
      if (controleSaisie()==true){
        try {
            System.out.println(controleSaisie());
            //String NameCategory = namecategorytf.getText();
            String NameProduct = nameproducttf.getText();
            String DescriptionProduct = desctf.getText();
           int QuantityProduct = Integer.parseInt(quantitytf.getText());
            float PriceProduct = Float.parseFloat(pricetf.getText());
            float Promotion = Float.parseFloat(promotiontf.getText());
            String NameCategory = listecat.getSelectionModel().getSelectedItem().toString();


                Product p=new Product (NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
            ServiceProduct serP = new ServiceProduct();
            serP.ajouter1(p);
             for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.readAll());
           idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        //namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        listecat.getSelectionModel().select(-1);
        tablep.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
   public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;

       
        if  (namecategorytf.getText().equals("")) {
            namecategorylabe.setText("Empty Text Field");
            saisie = false;
        } else {
            namecategorylabe.setText("");
        }
        if (nameproducttf.getText().equals("")) {
            nameproductlabel.setText("Empty Text Field");
            saisie = false;
        } else {
            nameproductlabel.setText("");
        }
        if (quantitytf.getText().equals("")) {
            quantitelabel.setText("Empty Text Field");
            saisie = false;
        } else {
            quantitelabel.setText("");
        }
if (pricetf.getText().equals("")) {
    pricelabel.setText("Empty Text Field");        
    saisie = false;
        } else {
            pricelabel.setText("");
        }
if (promotiontf.getText().equals("")) {
    namecategorylabe.setText("Empty Text Field");       
    saisie = false;
        } else {
            promotionlabel.setText("");
        }
if (listecat == null)
{
    namecategorylabe.setText("Empty Text Field");       
    saisie = false;
}
       
        return saisie;
    }
    @FXML
    private void deleteaction(ActionEvent event) {
         ServiceProduct serP = new ServiceProduct();
            try {
               
                TableViewSelectionModel selectionModel = tablep.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int row = tablePosition.getRow();
                  int x=tablep.getItems().get(row).toStringid();
                serP.delete(x);
                 for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
             data.addAll(serP.readAll());
           idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);   
                 
                
            } catch (SQLException ex) {
                Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }

    @FXML
    private void updateaction(ActionEvent event) throws SQLException {
        try {
            //String NameCategory = namecategorytf.getText();
            String NameCategory = listecat.getSelectionModel().getSelectedItem().toString();
            String NameProduct = nameproducttf.getText();
            String DescriptionProduct = desctf.getText();
           int QuantityProduct = Integer.parseInt(quantitytf.getText());
            float PriceProduct = Float.parseFloat(pricetf.getText());
            float Promotion = Float.parseFloat(promotiontf.getText());

                Product p=new Product (NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
            ServiceProduct serP = new ServiceProduct();
           TableViewSelectionModel selectionModel = tablep.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        int row = tablePosition.getRow();
        int x=tablep.getItems().get(row).toStringid();
        serP.update1(p,x);
        
             for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.readAll());
           idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
         listecat.getSelectionModel().select(-1);
        tablep.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void TriH(ActionEvent event) {
         ServiceProduct serP = new ServiceProduct();
        try {
            for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.triH());
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);
    }

    @FXML
    private void TriL(ActionEvent event) {
         ServiceProduct serP = new ServiceProduct();
        try {
            for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.triL());
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);
    }

    @FXML
    private void Reset(ActionEvent event) {
         ServiceProduct serP = new ServiceProduct();
        try {
            for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);
    }

    @FXML
    private void ShowPromotion(ActionEvent event) throws SQLException {
         ServiceProduct serP = new ServiceProduct();
        
        try {
            for ( int i = 0; i<tablep.getItems().size(); i++) {
    tablep.getItems().clear();
}
            data.addAll(serP.promo());
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("IdProduct"));
        namect.setCellValueFactory(new PropertyValueFactory<Product,String>("NameCategory"));
        namept.setCellValueFactory(new PropertyValueFactory<Product,String>("NameProduct"));
        desct.setCellValueFactory(new PropertyValueFactory<Product,String>("DescriptionProduct"));
        quantityt.setCellValueFactory(new PropertyValueFactory<Product,Integer>("QuantityProduct"));
        pricet.setCellValueFactory(new PropertyValueFactory<Product,Float>("PriceProduct"));
        promotiont.setCellValueFactory(new PropertyValueFactory<Product,Float>("Promotion"));
        tablep.setItems(data);
    }

    @FXML
    private void LocationStats(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stats.fxml"));
Parent root1 = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(root1));  
stage.show();
    }

    @FXML
    private void listecat(ActionEvent event) throws SQLException {
        ServiceProduct ser = new ServiceProduct();
//         List<String> arr=new ArrayList<>();
//         arr=ser.combo();
//         System.out.println(arr);
        
    }

    @FXML
    private void UploadPic(ActionEvent event) {
        JFileChooser fc = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter(
    "Image files", ImageIO.getReaderFileSuffixes());
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fc.setFileFilter(imageFilter);
  System.out.println("getCurrentDirectory(): " + fc.getCurrentDirectory());
  System.out.println("getSelectedFile() : " + fc.getSelectedFile().getName());
  pictf.setText(fc.getSelectedFile().getName());
  
} else {
  System.out.println("No Selection ");
}
    }

//    @FXML
//    private void pdfaction(ActionEvent event) {
//    }
//     
    private final Connection con;
    private Statement ste;

    public AddProductController() {
        con = DataBase.getInstance().getConnection();

}
    @FXML
    public void pdfaction (ActionEvent e) {
            ResultSet rs;
            try {
                
                String query = "Select * from product";
                
                rs = con.createStatement().executeQuery(query);
                
                
               
                
                XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF
                
                XSSFSheet sheet = wb.createSheet("Product Details");
                
                XSSFRow header = sheet.createRow(0);
                
                header.createCell(0).setCellValue("ID");
                
                header.createCell(1).setCellValue("Name");
                
                header.createCell(2).setCellValue("Description");
                
                header.createCell(3).setCellValue("Quantity");
                
                
                
                sheet.autoSizeColumn(1);
                
                sheet.autoSizeColumn(2);
                
                sheet.setColumnWidth(3, 256*25);//256-character width
                
                
                
                sheet.setZoom(150);//scale-150%
                
                
                
                
                
                int index = 1;
                
                while(rs.next()){
                    
                    XSSFRow row = sheet.createRow(index);
                    
                    row.createCell(0).setCellValue(rs.getInt("IdProduct"));
                    
                    row.createCell(1).setCellValue(rs.getString("NameProduct"));
                    
                    row.createCell(2).setCellValue(rs.getString("DescriptionProduct"));
                    
                    row.createCell(3).setCellValue(rs.getInt("QuantityProduct"));
                    
                    index++;
                    
                }
                
                
                
                try (FileOutputStream fileOut = new FileOutputStream("ProductDetails1.xlsx") // before 2007 version xls
                        ) {
                    wb.write(fileOut);
                }
                
                
                
                Alert alert = new Alert(AlertType.INFORMATION);
                
                alert.setTitle("Information Dialog");
                
                alert.setHeaderText(null);
                
                alert.setContentText("User Details Exported in Excel Sheet.");
                
                alert.showAndWait();
                
                
                
                rs.close();
                
                
                
            } catch (SQLException | FileNotFoundException ex) {
                
                System.out.println("Error " + ex.getMessage());
                
            } catch (IOException ex) {
                
                System.out.println("Error " + ex.getMessage());
                
            }
        };

  
}
