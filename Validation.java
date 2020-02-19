/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

/**
 *
 * @author MPSHOP
 */
public class Validation {
   public static boolean textValidation(TextField tf){
    
    boolean b=false;
    if (tf.getText().length()!=0 || !tf.getText().isEmpty()){
        b=true;

    }
    return b;
    
    }
    public static boolean textValidation(TextField tf ,Label lb ){
    
    boolean b=true;
    
    if (!textValidation(tf)){
                            b=false;
                            }
   lb.setText("Veuillez remplir ce champ");
    return b;
    
    }
    
    public static boolean textalphabet(TextField tf ,Label lb,String errorMessage)
    {
    boolean isAlphabet =true;
    
    String validationString=null;
    if(!tf.getText().matches("[a-z A-Z]+")){
    isAlphabet=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[a-z A-Z]+"));
        return isAlphabet;
    
}
    
    
        public static boolean texNum(TextField tf ,Label lb,String errorMessage)
    {
    boolean isNum =true;
    
    String validationString=null;
    if(!tf.getText().matches("[0-9]+")){
    isNum=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[0-9]+"));
        return isNum;
    
}
    //[a-z0-9]
    
      public static boolean texAlphNum(TextField tf ,Label lb,String errorMessage)
    {
    boolean isAlphNum =true;
    
    String validationString=null;
    if(!tf.getText().matches("[a-z0-9]+")){
    isAlphNum=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[a-z0-9]+"));
        return isAlphNum;
    
}
      
            public static boolean texMail(TextField tf ,Label lb,String errorMessage)
    {
    boolean isMail =true;
    
    String validationString=null;
    if(!tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")){
    isMail=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$"));
        return isMail;
    
} 






  

  
}
