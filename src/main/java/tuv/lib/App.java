package tuv.lib;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tuv.lib.controllers.LogInController;


/**
 * Hello world!
 *
 */
public class App extends Application {
	@Override
	public void start(Stage stage) {
		try {
			LogInController logInController = new LogInController();
			FXMLLoader loader = new FXMLLoader();

			URL location = App.class.getResource("/views/LogIn.fxml");
			loader.setLocation(location);   
			loader.setController(logInController);
			loader.load();
			stage.setTitle("Library Log In");
			stage.setScene(new Scene((Parent) loader.getRoot()));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
