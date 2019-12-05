package tuv.lib.models;

import java.io.IOException;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tuv.lib.controllers.NotificationController;

public class NotificationsTask extends TimerTask {

	private Button btn_notifications;
	
	
	public NotificationsTask(Button btn_getNotf) {
		this.btn_notifications = btn_getNotf;
	}
	
	
	@Override
	public void run() {		
		Platform.runLater(new Runnable() {
            public void run() {
            	btn_notifications.setBackground(new Background(new BackgroundFill(Color.web("#FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });					
	}	
}
