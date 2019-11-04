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
import tuv.lib.App;
import tuv.lib.models.DBConnector;
import tuv.lib.models.interfaces.UserService;
import tuv.lib.models.UserServiceImpl;


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

	/**
	 * 
	 * Listener on login button
	 * @param event
	 */
	@FXML
	private void login(ActionEvent event) {

		// System.out.println("some text");
		String address = "";
		//Parent root = FXMLLoader.load(getClass().getResource(address));
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			int pos = userService.getUserPos(tb_username.getText(), tb_password.getText());

			if (pos == 0) {
				AdminController adc = new AdminController();
				loader.setController(adc);
				address = "../../../views/AdminPannel.fxml";
			} else if (pos == 1) {
				OperatorController oc = new OperatorController();
				loader.setController(oc);
				address = "../../../views/OperatorPannel.fxml";
				//address = "../../../views/test.fxml";
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
			
//			LogInController logInController = new LogInController();
//			FXMLLoader loader = new FXMLLoader();
//
//			URL location = App.class.getResource("/views/LogIn.fxml");
//			loader.setLocation(location);
//			loader.setController(logInController);
//			loader.load();
//			stage.setTitle("Library Log In");
//			stage.setScene(new Scene((Parent) loader.getRoot()));
//			stage.show();
//			
			
			Stage sys = new Stage();
			//FXMLLoader loader = new FXMLLoader();
			//Parent root = loader.load();
			//loader.setController(logInController);
			loader.setLocation(getClass().getResource(address));
			loader.load();
			Scene scene = new Scene((Parent) loader.getRoot());
			sys.setScene(scene);
			sys.show();
			sys.setResizable(false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
