package tuv.lib.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tuv.lib.models.Rent;
import tuv.lib.models.RentServiceImpl;
import tuv.lib.models.interfaces.RentService;

public class NotificationController implements Initializable {

	private RentService rentServices;
	@FXML
	private TableView tv_notifications;
	@FXML
	public javafx.scene.control.TableColumn tc_notfUserName, tc_notfBookName, tc_notfDate;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.rentServices = new RentServiceImpl(); 
		
		List<Rent> res = rentServices.getNotifications();
		
		tc_notfUserName.setCellValueFactory(new PropertyValueFactory<Object, Object>("client"));
		tc_notfBookName.setCellValueFactory(new PropertyValueFactory<Object, Object>("book"));
		tc_notfDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("returnDate"));
	
		for (int i = 0; i < res.size(); i++) {
			tv_notifications.getItems().add(res.get(i));
		}		
	}		
}
