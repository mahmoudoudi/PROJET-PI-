/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Utilis.DataBase;
import Entities.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MPSHOP
 */
public class ServiceCategory {
    
    private final Connection con;
    private Statement ste;

    public ServiceCategory() {
        con = DataBase.getInstance().getConnection();

}
  public void ajouter1(Category c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `hooks`.`category` (`IdCategory`, `NameCategory`, `DescriptionCategory`) VALUES ( NULL, ?, ?);");
    
    pre.setString(1, c.getNameCategory());
    pre.setString(2, c.getDescriptionCategory());
    pre.executeUpdate();
    }
            
     
    public boolean delete(int i) throws SQLException {
          PreparedStatement pre=con.prepareStatement("delete from category WHERE IdCategory='"+i+"';");
            pre.executeUpdate();
            return true;
    }

    public boolean updateN(String a, int i) throws SQLException {
      
        PreparedStatement pre=con.prepareStatement("UPDATE category SET NameCategory= '" + a + "'  WHERE IdCategory='"+i+"';");
            pre.executeUpdate();
       
        return true;
    }
      public boolean updateD(String a, int i) throws SQLException {
      
        PreparedStatement pre=con.prepareStatement("UPDATE category SET DescriptionCategory= '" + a + "'  WHERE IdCategory='"+i+"';");
            pre.executeUpdate();
       
        return true;
    }


    public List<Category> readAll() throws SQLException {
    List<Category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from category");
     while (rs.next()) {  

         int IdCategory=rs.getInt(1);  
         String NameCategory=rs.getString("NameCategory");
               String DescriptionCategory=rs.getString("DescriptionCategory");
       Category c=new Category(IdCategory, NameCategory, DescriptionCategory);
     arr.add(c);
     }
    return arr;
    }

    
    public void ajouter(Category t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean update(Category t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Category> recherche(String aa) throws SQLException{
         List<Category> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from category where NameCategory like '%" + aa + "%' ;");
           while (rs.next()) {                
            
               String NameCategory=rs.getString("NameCategory");
               String DescriptionCategory=rs.getString("DescriptionCategory");
             
               
               
               Category c=new Category(NameCategory, DescriptionCategory);
     fish.add(c);
     }
        return fish;
    }
    
    public List<Category> trieln() throws SQLException{
         List<Category> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from category order by length(NameCategory) desc;");
           while (rs.next()) {                
            
               String NameCategory=rs.getString("NameCategory");
               String DescriptionCategory=rs.getString("DescriptionCategory");
               
              
               
               
               Category x=new Category(NameCategory, DescriptionCategory);
     fish.add(x);
     }
        return fish;
    }

    public void like() {
    } 
}
