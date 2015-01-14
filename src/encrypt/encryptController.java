/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author muskan
 */
public class encryptController implements Initializable {
   
    @FXML TextArea plaintxt;
   @FXML TextField txtfld;
   @FXML Button enc, dec;
   
    @FXML public void onpress(ActionEvent evt){
   
        if(evt.getSource()==enc)
        {
        Blowfish cipher = new Blowfish(txtfld.getText());
	String d_passwd = cipher.encrypt(plaintxt.getText());
        plaintxt.setText(d_passwd);
        }
        else if(evt.getSource()==dec)
        {
         Blowfish cipher = new Blowfish(txtfld.getText());
	String d_passwd = cipher.decrypt(plaintxt.getText());
        plaintxt.setText(d_passwd);
        }
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
