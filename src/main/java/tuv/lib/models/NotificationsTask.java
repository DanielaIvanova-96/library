package tuv.lib.models;

import java.io.IOException;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tuv.lib.controllers.NotificationController;

public class NotificationsTask extends TimerTask {

	@Override
	public void run() {		
		Platform.runLater(new Runnable() {
            public void run() {
            	FXMLLoader loader = new FXMLLoader();
        		NotificationController nc = new NotificationController();
        		loader.setController(nc);
        		String address = "../../../views/NotificationPannel.fxml";
        		Stage sys = new Stage();
        		try {
        		loader.setLocation(getClass().getResource(address));
        			loader.load();
        		} catch (IOException e) {       			
        			e.printStackTrace();
        		}
        		Scene scene = new Scene((Parent) loader.getRoot());
        		sys.setScene(scene);
        		sys.show();
        		sys.setResizable(false);
            }
        });					
	}	
}
