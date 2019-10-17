package tuv.lib;

import java.io.IOException;
import java.net.URL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tuv.lib.controllers.LogInController;
import tuv.lib.models.HibernateUtil;
import tuv.lib.models.User;
import tuv.lib.models.dao.UserDAOImpl;


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
		//UserDAOImpl daoImpl = new UserDAOImpl();
	
		Session s = HibernateUtil.getSessionFactory().openSession();

		String sql = "select version()";
  
		String res = (String) s.createNativeQuery(sql).getSingleResult();
		System.out.println(res);
		s.beginTransaction();
		User u = s.get(User.class,2);
		System.out.println(u.getName());
		s.close();
		//User zheni = daoImpl.getUserById(2);
		
		launch(args);
	}
}
