package tuv.lib.models;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


/**Notification task class that makes changes in interval of time
 * @author Zheni
 *
 */
public class NotificationsTask extends TimerTask {

	private Button btn_notifications;
		
	/**Constructor for the notification task 
	 * @param btn_getNotf button for the notifications which color is changed by the task
	 */
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
