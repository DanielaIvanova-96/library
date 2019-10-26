package tuv.lib.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;





public class AdminController implements Initializable {
	//private UserService userService;
	

	@FXML
	private Button btn_addUser;
	@FXML
	private Button btn_removeUser;


	@FXML
	private TextField tb_addUsername;
	@FXML
	private TextField tb_addPassword;
	@FXML
	private TextField tb_removeUsername;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	//	userService = new UserServiceImpl();
	}
	
	@FXML
	private void removeUser(ActionEvent event) {

		System.out.println("some text  2 ");
		
	}
	
	@FXML
	private void createUser(ActionEvent event) {

		System.out.println("some text");
		
	}
}
