package tuv.lib.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import tuv.lib.models.UserServiceImpl;
import tuv.lib.models.interfaces.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class OperatorController implements Initializable {
    private UserService userService;

    @FXML
    private Button btn_addBook, btn_removeBook, btn_addClient, btn_makeRent, btn_findBook, btn_findClient, btn_closeRent;

    @FXML
    private Pane pln_addBook, pln_removeBook, pln_addClient, pln_makeRent, pln_findBook, pln_findClient, pln_closeRent;

    @FXML
    private void buttonAction(ActionEvent event){
        if(event.getSource() == btn_addBook)
            pln_addBook.toFront();

        else if(event.getSource() == btn_removeBook)
            pln_removeBook.toFront();

        else if(event.getSource() == btn_addClient)
            pln_addClient.toFront();

        else if(event.getSource() == btn_makeRent)
            pln_makeRent.toFront();

        else if(event.getSource() == btn_findBook)
            pln_findBook.toFront();

        else if(event.getSource() == btn_findClient)
            pln_findClient.toFront();

        else if(event.getSource() == btn_closeRent)
            pln_closeRent.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UserServiceImpl();
    }
}
