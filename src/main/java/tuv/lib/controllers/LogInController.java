package tuv.lib.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

		try {
			btn_logIn.getScene().getWindow().hide();
			Stage admin = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../../../views/AdminPannel.fxml"));
			Scene scene = new Scene(root);
			admin.setScene(scene);
			admin.show();
			admin.setResizable(false);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
