/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smp;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author 62812
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Hyperlink forgot_password;

    @FXML
    private Button login_button;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextArea login_password;

    @FXML
    private TextArea login_username;

    @FXML
    private AnchorPane main_form;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private AlertMessage alert = new AlertMessage();
    private Data data;
    
    public void loginAccount(){
        
        Data data = new Data();
        
        if (login_username.getText().isEmpty()
            ||login_password.getText().isEmpty()){
            alert.errorMessage("Username atau Password Salah!");
        }else{
            
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            
            connect = Database.connectDB();
            
            try {
                
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();
                
                if (result.next()){
                    alert.successMessage("Login Berhasil");
                    
                    data.setUser_id(result.getInt("user_id"));
                    System.out.println(data.getUser_id());
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                    Parent root = loader.load();
                    HomeController controller = loader.getController();
                    controller.data = data;
                    Stage stage = new Stage();
                    
                    stage.setTitle("Sistem manajemen Proyek");
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                    login_button.getScene().getWindow().hide();
                }else {
                    alert.errorMessage("Username atau Password Salah!");
                }
            }catch(Exception e){e.printStackTrace();}
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
