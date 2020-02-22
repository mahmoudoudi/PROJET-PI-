/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Category;
import Utilis.DataBase;
import Entities.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MPSHOP
 */
public class ServiceProduct {
    private final Connection con;
    private Statement ste;

    public ServiceProduct() {
        con = DataBase.getInstance().getConnection();
          

}
      
    public void ajouter1(Product p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `hooks`.`product` ( `IdProduct`, `NameCategory`, `NameProduct`, `DescriptionProduct`, `QuantityProduct`, `PriceProduct`, `Promotion`) VALUES ( NULL, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, p.getNameCategory());
    pre.setString(2, p.getNameProduct());
    pre.setString(3, p.getDescriptionProduct());
    pre.setInt(4, p.getQuantityProduct());
    pre.setFloat(5, p.getPriceProduct());
    pre.setFloat(6, 0);
    pre.executeUpdate();
    }
        
     public boolean delete(int i) throws SQLException {
          PreparedStatement pre=con.prepareStatement("delete from product WHERE IdProduct='"+i+"';");
            pre.executeUpdate();
            return true;
    }

    public boolean update(String a) throws SQLException {
      
        PreparedStatement pre=con.prepareStatement("UPDATE product SET NameProduct= '" + a + "'  WHERE IdProduct=13 ;");
            pre.executeUpdate();
       
        return true;
    }
    

    public boolean update1(Product p, int i) throws SQLException {
            boolean test=false;
            PreparedStatement pre= con.prepareStatement(  "UPDATE product SET NameCategory= ?,  NameProduct= ?, DescriptionProduct= ?, QuantityProduct= ?, PriceProduct= ? ,Promotion= ? WHERE IdProduct='"+i+"';");
            pre.setString(1, p.getNameCategory());
    pre.setString(2, p.getNameProduct());
    pre.setString(3, p.getDescriptionProduct());
    pre.setInt(4, p.getQuantityProduct());
    pre.setFloat(5, p.getPriceProduct());
    pre.setFloat(6, p.getPromotion());
    pre.executeUpdate();
            return true;
    }
    public int stats1() throws SQLException {
   
    ste=con.createStatement();
  
    ResultSet rs=ste.executeQuery("select count(*) AS total from product WHERE Promotion >0");


      while (rs.next())
      {
          int count=rs.getInt("total");
             return count;
      } 
        return 0;

    }
    public int stats2() throws SQLException {
   
    ste=con.createStatement();
  
    ResultSet rs=ste.executeQuery("select count(*) AS total from product WHERE Promotion =0");


      while (rs.next())
      {
          int count=rs.getInt("total");
             return count;
      } 
        return 0;

    }
    
    public int statsA() throws SQLException {
   
    ste=con.createStatement();
  
    ResultSet rs=ste.executeQuery("select count(*) AS total from product");


      while (rs.next())
      {
          int count=rs.getInt("total");
             return count;
      } 
        return 0;

    }
    public List<String> combo() throws SQLException {
    List<String> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select NameCategory from category");
     while (rs.next()) {                
               String NameCategory=rs.getString(1);
 
              
               arr.add(NameCategory);
     }
    return arr;
    }
    public List<Product> readAll() throws SQLException {
    List<Product> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from product");
     while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
             
               
               
               Product p=new Product (IdProduct, NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     arr.add(p);
     }
    return arr;
    }
    
public List<Product> selectitem(int i) throws SQLException {
    List<Product> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from productWHERE IdProduct='"+i+"';");
     while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
             
               
               
               Product p=new Product (IdProduct, NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     arr.add(p);
     }
    return arr;
    }
    
    public void ajouter(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean update(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Product> recherche(String aa) throws SQLException{
         List<Product> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from product where NameProduct like '%" + aa + "%' ;");
           while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
               
               
               Product p=new Product(NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     fish.add(p);
     }
        return fish;
    }
    
    public List<Product> promo() throws SQLException{
         List<Product> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from product where Promotion>0.00;");
           while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
              
               
               
               Product x=new Product(IdProduct, NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     fish.add(x);
     }
        return fish;
    }
    
     public List<Product> triL() throws SQLException{
         List<Product> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from product order by PriceProduct;");
           while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
              
               
               
               Product x=new Product(IdProduct, NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     fish.add(x);
     }
        return fish;
    }
     
     
     
    public List<Product> triH() throws SQLException{
         List<Product> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from product order by PriceProduct desc;");
           while (rs.next()) {                
               int IdProduct=rs.getInt(1);
               String NameCategory=rs.getString("NameCategory");
               String NameProduct=rs.getString("NameProduct");
               String DescriptionProduct=rs.getString("DescriptionProduct");
               int QuantityProduct=rs.getInt("QuantityProduct");
               float PriceProduct=rs.getFloat("PriceProduct");
               float Promotion=rs.getFloat("Promotion");
              
               
               
               Product x=new Product(IdProduct, NameCategory, NameProduct, DescriptionProduct, QuantityProduct, PriceProduct, Promotion);
     fish.add(x);
     }
        return fish;
    }

    public void like() {
    }
   


}
