package tuv.lib.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LogInController implements Initializable {

	@FXML
	private Button btn_logIn;
	@FXML
	private Button btn_cancel;

	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	private void login(ActionEvent event) {

		System.out.println("some text");

	}

}
