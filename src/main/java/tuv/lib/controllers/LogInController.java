package tuv.lib.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tuv.lib.models.DBConnector;
import tuv.lib.models.UserServiceImpl;
import tuv.lib.models.interfaces.UserService;

public class LogInController implements Initializable {

	private UserService userService;

	@FXML
	private Button btn_logIn;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField tb_username;

	@FXML
	private TextField tb_password;

	public void initialize(URL arg0, ResourceBundle arg1) {
		userService = new UserServiceImpl();
	}

	@FXML
	private void login(ActionEvent event) {

		// System.out.println("some text");
		String address = "";

		try {
			
			int pos = userService.getUserPos(tb_username.getText(), tb_password.getText());

			if (pos == 0) {
				address = "../../../views/AdminPannel.fxml";
			} else if (pos == 1) {
				address = "../../../views/OperatorPannel.fxml";
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Incorrect username or password! ");
				// alert.setContentText("Please enter");
				alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
					public void accept(ButtonType rs) {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					}
				});
				tb_username.setText("");
				tb_password.setText("");
				
				return;
			}

			btn_logIn.getScene().getWindow().hide();
			Stage sys = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(address));
			Scene scene = new Scene(root);
			sys.setScene(scene);
			sys.show();
			sys.setResizable(false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
