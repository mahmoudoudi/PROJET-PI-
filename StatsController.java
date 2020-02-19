/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static java.lang.ProcessBuilder.Redirect.from;
import static java.lang.management.LockInfo.from;
import static java.lang.management.MemoryNotificationInfo.from;
import static java.lang.management.MemoryUsage.from;
import java.net.URL;
import static java.sql.Timestamp.from;
import static java.time.Period.from;
import static java.util.Date.from;
import static java.util.GregorianCalendar.from;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

import Entities.Category;
import Entities.Product;
import java.sql.*;
import javafx.collections.ObservableList;
import Service.ServiceCategory;
import Service.ServiceProduct;
import java.net.URL;
import Utilis.DataBase;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author MPSHOP
 */
public class StatsController implements Initializable {
    
    @FXML 
    private PieChart statsp;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ServiceProduct serP = new ServiceProduct();
       
        ObservableList<PieChart.Data> pieChartData;
        DecimalFormat df = new DecimalFormat("#.##");
        double x = 0;
        double y = 0;
        double a = 0;
        try {
            x=(double) serP.stats1();
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            y=(double) serP.stats2();
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            a=(double) serP.statsA();
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        x=(x/a)*100.00;
        y=(y/a)*100.00;
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Product Without Promotion "+df.format(x), x),
                new PieChart.Data("Product Without Promotion "+df.format(y), y));
       statsp.setData(pieChartData);
    }    

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
}
