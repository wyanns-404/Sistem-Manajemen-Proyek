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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 62812
 */
public class HomeController implements Initializable {
    public Data data;
    @FXML
    private TextField anggaranProyek_form;

    @FXML
    private Button batalBuatProyek_button;

    @FXML
    private AnchorPane buatProyek_anggota_page;

    @FXML
    private AnchorPane buatProyek_page;

    @FXML
    private AnchorPane buatProyek_proyek_page;

    @FXML
    private AnchorPane buatProyek_tugas_page;

    @FXML
    private Hyperlink button_aboutUs;

    @FXML
    private ImageView button_buatProyek;

    @FXML
    private Hyperlink button_contact;

    @FXML
    private Hyperlink button_home;

    @FXML
    private Hyperlink button_myProyek;

    @FXML
    private AnchorPane home;

    @FXML
    private AnchorPane home_page;

    @FXML
    private Button lanjutBuatProyek_button;

    @FXML
    private TextField namaProyek_form;

    @FXML
    private TextField tanggalTenggat_form;

    @FXML
    private TextField tujuanProyek_form;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private AlertMessage alert = new AlertMessage();
    
    
    public void buatProyek(){
        home.setVisible(false);
        home.setVisible(false);
        buatProyek_page.setVisible(true);
        buatProyek_proyek_page.setVisible(true);
        buatProyek_anggota_page.setVisible(false);
        buatProyek_tugas_page.setVisible(false);
    }
    
    public void tambahProyek(){
        try{
            connect = Database.connectDB();
            
            String insertData = "INSERT INTO proyek (nama_proyek, tujuan_proyek, tanggal_tenggat, anggaran_proyek, user_id) VALUE(?,?,?,?,?)";
            prepare = connect.prepareStatement(insertData);
            prepare.setString(1, namaProyek_form.getText());
            prepare.setString(2, tujuanProyek_form.getText());
            prepare.setString(3, tanggalTenggat_form.getText());
            prepare.setString(4, anggaranProyek_form.getText());
            prepare.setInt(5, data.getUser_id());

            prepare.executeUpdate();
           
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
