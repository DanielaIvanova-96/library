package tuv.lib.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

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
import tuv.lib.App;
import tuv.lib.models.DBConnector;
import tuv.lib.models.Log4;
import tuv.lib.models.User;
import tuv.lib.models.User.Possition;
import tuv.lib.models.interfaces.UserService;
import tuv.lib.models.UserServiceImpl;
import tuv.lib.models.Validator;

/**
 * Controller for the login form
 * 
 * @author Zheni
 *
 */
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

	/** Listener on login button
	 * 
	 * @param event
	 */
	@FXML
	private void login(ActionEvent event) {
		String address = "";
		try {

			FXMLLoader loader = new FXMLLoader();
			String name = tb_username.getText();
			String pass = tb_password.getText();

			boolean status = true;
			status &= Validator.hasCharsOnly(name);
			status &= !Validator.isNullOrEmpty(pass);

			if (!status) {
				Validator.showWrongInputAllert();

				tb_username.clear();
				tb_password.clear();
				return;
			}

			User u = userService.getUserByName(name);

			if (u == null || u.getPosstion() == Possition.CLIENT) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Incorrect username or password! ");
				alert.showAndWait();
				tb_username.setText("");
				tb_password.setText("");
				return;
			}

			if (u.getPassword().equals(pass)) {
				Log4.logLogIn(u);
				if (u.getPosstion() == Possition.ADMIN) {
					AdminController adc = new AdminController(u);
					loader.setController(adc);
					address = "../../../views/AdminPannel.fxml";
				} else if (u.getPosstion() == Possition.OPERATOR) {
					OperatorController oc = new OperatorController(u);
					loader.setController(oc);
					address = "../../../views/final_view.fxml";
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Incorrect password! ");
				alert.showAndWait();
				tb_password.setText("");
				return;
			}

			btn_logIn.getScene().getWindow().hide();

			Stage sys = new Stage();
			loader.setLocation(getClass().getResource(address));
			loader.load();
			Scene scene = new Scene((Parent) loader.getRoot());
			sys.setScene(scene);
			sys.show();
			sys.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Listener on the close button
	 * 
	 * @param event
	 */
	@FXML
	private void cancel(ActionEvent event) {
		Stage stage = (Stage) btn_cancel.getScene().getWindow();
		stage.close();
	}

}
