package tuv.lib.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tuv.lib.models.DBConnector;

public class LogInController implements Initializable {

	@FXML
	private Button btn_logIn;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField userNameTextBox;

	@FXML
	private TextField passwordTextBox;

	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	private void login(ActionEvent event) {

		// System.out.println("some text");

		DBConnector.setUpConncetion();

		String query = "SELECT USER_POSS\r\n" + "FROM libr.users\r\n" + "WHERE USER_NAME = \""
				+ userNameTextBox.getText() + "\" AND USER_PASSWORD = \"" + passwordTextBox.getText() + "\"; ";

		String address = "";

		try {

			ResultSet rs = DBConnector.executeQuery(query);

			btn_logIn.getScene().getWindow().hide();
			Stage admin = new Stage();

			if (rs.next()) {

				if (rs.getInt(1) == 0) {
					address = "../../../views/AdminPannel.fxml";
				} else if (rs.getInt(1) == 1) {
					address = "../../../views/OperatorPannel.fxml";
				}
			}

			Parent root = FXMLLoader.load(getClass().getResource(address));
			Scene scene = new Scene(root);
			admin.setScene(scene);
			admin.show();
			admin.setResizable(false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
