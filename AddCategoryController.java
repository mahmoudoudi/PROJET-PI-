/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.sql.*;
import javafx.collections.ObservableList;
import Entities.Category;
import Service.ServiceCategory;
import java.net.URL;
import Utilis.DataBase;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MPSHOP
 */
public class AddCategoryController implements Initializable {
     private Statement ste;
    @FXML
    private TextField namecategorytf;
    @FXML
    private TextField descriptioncategorytf;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private TableView<Category> tabc;
    @FXML
    private TableColumn<Category, Integer> idc;
    @FXML
    private TableColumn<Category, String> namec;
    @FXML
    private TableColumn<Category, String> desc;
    public ObservableList<Category> data = FXCollections.observableArrayList();
    @FXML
    private Button reset;

    
    public void setTabc(TableView<Category> tabc) {
            this.tabc = tabc;
            
    }    

    public void setIdc(TableColumn<Category, Integer> idc) {
        this.idc = idc;
    }

    public void setNamec(TableColumn<Category, String> namec) {
        this.namec = namec;
    }

    public void setDesc(TableColumn<Category, String> desc) {
        this.desc = desc;
    }

    /**
     * Initializes the controller class.
     *
     * 
     */
    public void setData(ObservableList<Category> data) {    
        this.data = data;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceCategory serC = new ServiceCategory();
            data.addAll(serC.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<Category,Integer>("IdCategory"));
            namec.setCellValueFactory(new PropertyValueFactory<Category,String>("NameCategory"));
            desc.setCellValueFactory(new PropertyValueFactory<Category,String>("DescriptionCategory"));
            tabc.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     tabc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

@Override
public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//Check whether item is selected and set value of selected item to Label
if(tabc.getSelectionModel().getSelectedItem() != null) 
{    
TableViewSelectionModel selectionModel = tabc.getSelectionModel();
ObservableList selectedCells = selectionModel.getSelectedCells();
TablePosition tablePosition = (TablePosition) selectedCells.get(0);
Object val = tablePosition.getTableColumn().getCellData(newValue);
int column = tablePosition.getColumn();


System.out.println("Selected Value" + val);
if (column==1)
                     {
                         namecategorytf.setText(val.toString());
                     descriptioncategorytf.setText("");
                     }
else if (column==2)
                     {
                         
                         descriptioncategorytf.setText(val.toString());
                         namecategorytf.setText("");
                     }
                         }


}
                     });
                             
    }    

    @FXML
    private void updateaction(ActionEvent event) {
        ServiceCategory serC = new ServiceCategory();
        String catname;
         if (((namecategorytf.getText().equals(""))&&(descriptioncategorytf.getText().equals(""))))
        {
            
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Category");
        alert.setHeaderText("The fields are empty");
        //alert.setContentText("The fields are empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            alert.close();
        } 
        }
         else if (descriptioncategorytf.getText().equals(""))
         {
            try {
                Connection con = DataBase.getInstance().getConnection();
                
                try {
                    ste=con.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                catname=namecategorytf.getText();
                TableViewSelectionModel selectionModel = tabc.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int row = tablePosition.getRow();
                int x=tabc.getItems().get(row).toString1();
                //System.out.println(x);
                serC.updateN(catname,x);
                 for ( int i = 0; i<tabc.getItems().size(); i++) {
    tabc.getItems().clear();
}
            data.addAll(serC.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<Category,Integer>("IdCategory"));
            namec.setCellValueFactory(new PropertyValueFactory<Category,String>("NameCategory"));
             desc.setCellValueFactory(new PropertyValueFactory<Category,String>("DescriptionCategory"));
              
             tabc.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
          else if (namecategorytf.getText().equals(""))
         {
            try {
                Connection con = DataBase.getInstance().getConnection();
                
                try {
                    ste=con.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                catname=descriptioncategorytf.getText();
                TableViewSelectionModel selectionModel = tabc.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int row = tablePosition.getRow();
                int x=tabc.getItems().get(row).toString1();
                //System.out.println(x);
                serC.updateD(catname,x);
                 for ( int i = 0; i<tabc.getItems().size(); i++) {
    tabc.getItems().clear();
}
            data.addAll(serC.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<Category,Integer>("IdCategory"));
            namec.setCellValueFactory(new PropertyValueFactory<Category,String>("NameCategory"));
             desc.setCellValueFactory(new PropertyValueFactory<Category,String>("DescriptionCategory"));
              
             tabc.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    @FXML
    private void deleteaction(ActionEvent event) {
         ServiceCategory serC = new ServiceCategory();
        String catname;
         if (((namecategorytf.getText().equals(""))&&(descriptioncategorytf.getText().equals(""))))
        {
            
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category");
        alert.setHeaderText("Select Category");
        //alert.setContentText("The fields are empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            alert.close();
        } 
        }
         else 
         {
            try {
               
                TableViewSelectionModel selectionModel = tabc.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int row = tablePosition.getRow();
                int x=tabc.getItems().get(row).toString1();
                serC.delete(x);
                 for ( int i = 0; i<tabc.getItems().size(); i++) {
    tabc.getItems().clear();
}
            data.addAll(serC.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<Category,Integer>("IdCategory"));
            namec.setCellValueFactory(new PropertyValueFactory<Category,String>("NameCategory"));
             desc.setCellValueFactory(new PropertyValueFactory<Category,String>("DescriptionCategory"));
              
             tabc.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         
    }

    @FXML
    private void addaction(ActionEvent event) {
        try {
            String CategoryName = namecategorytf.getText();
            String Desc = descriptioncategorytf.getText();
            Category c = new Category(CategoryName,Desc);
            ServiceCategory serC = new ServiceCategory();
            serC.ajouter1(c);
             for ( int i = 0; i<tabc.getItems().size(); i++) {
    tabc.getItems().clear();
}
            data.addAll(serC.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<Category,Integer>("IdCategory"));
            namec.setCellValueFactory(new PropertyValueFactory<Category,String>("NameCategory"));
             desc.setCellValueFactory(new PropertyValueFactory<Category,String>("DescriptionCategory"));
              
             tabc.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resaction(ActionEvent event) {
        namecategorytf.setText("");
        descriptioncategorytf.setText("");
    }
   
    
}
