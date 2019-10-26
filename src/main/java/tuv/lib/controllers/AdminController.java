package tuv.lib.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tuv.lib.models.UserServiceImpl;
import tuv.lib.models.interfaces.UserService;

public class AdminController implements Initializable {
	private UserService userService;
	

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		userService = new UserServiceImpl();
	}
	
}
