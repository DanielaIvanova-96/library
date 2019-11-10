package tuv.lib.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import tuv.lib.models.Client;
import tuv.lib.models.DBConnector;
import tuv.lib.models.User;
import tuv.lib.models.UserServiceImpl;
import tuv.lib.models.interfaces.BookService;
import tuv.lib.models.interfaces.UserService;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class OperatorController implements Initializable {
    private UserService userService;
    private BookService bookService;

    @FXML
    private Button btn_addBook, btn_removeBook, btn_addClient, btn_makeRent, btn_findBook, btn_findClient, btn_Classification;

    @FXML
    private Pane pln_addBook, pln_removeBook, pln_addClient, pln_makeRent, pln_findBook, pln_findClient, pln_Classification;

    @FXML
    private void buttonAction(ActionEvent event){
        if(event.getSource() == btn_addBook) {
            pln_addBook.setVisible(true) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_removeBook) {
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(true);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_addClient){
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(true);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_makeRent){
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(true);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_Classification){
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(true);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_findBook){
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(true);
            pln_findClient.setVisible(false);
        }

        else if(event.getSource() == btn_findClient){
            pln_addBook.setVisible(false) ;
            pln_removeBook.setVisible(false);
            pln_addClient.setVisible(false);
            pln_makeRent.setVisible(false);
            pln_Classification.setVisible(false);
            pln_findBook.setVisible(false);
            pln_findClient.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UserServiceImpl();
        pln_removeBook.setVisible(false);
        pln_addBook.setVisible(false);
        pln_Classification.setVisible(false);
        pln_findClient.setVisible(false);
        pln_makeRent.setVisible(false);
        pln_findBook.setVisible(false);
        pln_addClient.setVisible(false);
    }


    @FXML
    private TextField tb_addBook_name, tb_addBook_author, tb_addBook_genre, tb_addBook_number;
    @FXML
    private void addBook(ActionEvent event) throws SQLException{
        String name = tb_addBook_name.getText();
        String author = tb_addBook_author.getText();
        String genre = tb_addBook_genre.getText();
        String number = tb_addBook_number.getText();

        Connection con = DBConnector.getConnection();
        Statement st = con.createStatement();

        String query = "INSERT INTO libr.books_info VALUES (default, '" + name + "', '" + number + "', " +
                "(SELECT genre_id FROM libr.genres WHERE genre_name = '" + genre + "'));";

        st.executeUpdate(query);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Records are successfully inserted! ");
        // alert.setContentText("Please enter");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            public void accept(ButtonType rs) {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });

        query = "INSERT INTO libr.authors VALUES (default, '" + author +"') " +
                "ON DUPLICATE KEY UPDATE author_name = '" + author + "';";
        st.executeUpdate(query);


        //TODO: connection authors_books
        /*query = "INSERT INTO libr.";
        st.executeUpdate(query);*/

        tb_addBook_name.clear();
        tb_addBook_author.clear();
        tb_addBook_genre.clear();
        tb_addBook_number.clear();


    }

    @FXML
    private TextField tb_removeBook_name, tb_removeBook_number;
    @FXML
    private void removeBook(ActionEvent event) throws SQLException{
        String name = tb_removeBook_name.getText();
        String number = tb_removeBook_number.getText();

        Connection con = DBConnector.getConnection();
        Statement st = con.createStatement();

        String query = "DELETE FROM books WHERE EXISTS (" +
                "SELECT * FROM books_info WHERE books_info.BOOK_INFO_ID = books.BOOK_INFO_ID" +
                " AND books_info.BOOK_INFO_NAME = '" + name + "' AND books_info.BOOK_INFO_INV_NUM = '" + number +"' )" +
                " AND books.BOOK_CONDITION > 100";

        st.executeUpdate(query);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Records are successfully deleted! ");
        // alert.setContentText("Please enter");
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            public void accept(ButtonType rs) {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });
    }

    @FXML
    private TextField tb_findClient_name, tb_findClient_phone;
    @FXML
    private TableView tw_findClient;
    @FXML
    public javafx.scene.control.TableColumn tc_findClient_name, tc_findClient_phone, tc_findClient_recDate, tc_findClient_loyalty;
    @FXML
    private void findClient(ActionEvent event) throws SQLException {
        String name = tb_findClient_name.getText();
        String phone = tb_findClient_phone.getText();

        // SELECT USER_NAME, USER_PH_NUM, USER_REC_DATE, USER_LOYALTY FROM USERS WHERE USER_NAME = name AND USER_PHONE = phone;

        Connection con = DBConnector.getConnection();
        Statement st = con.createStatement();

        String query = "SELECT USER_NAME, USER_PH_NUM, USER_REC_DATE, USER_LOYALTY\r" + "FROM libr.users\r\n" + "WHERE USER_NAME = \"" + name
                + "\" AND USER_PH_NUM = \"" + phone + "\"; ";


        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String res_name = rs.getString("USER_NAME");
            String res_phone = rs.getString("USER_PH_NUM");
            String res_date = rs.getString("USER_REC_DATE");
            int res_loyalty = rs.getInt("USER_LOYALTY");


            tc_findClient_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            tc_findClient_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
            tc_findClient_loyalty.setCellValueFactory(new PropertyValueFactory<>("loyalty"));
            tc_findClient_recDate.setCellValueFactory(new PropertyValueFactory<>("recordDate"));

           // tw_findClient.getItems().addAll(tc_findClient_name, tc_findClient_phone, tc_findClient_loyalty, tc_findClient_recDate);

            tw_findClient.getItems().add(new Client(res_name, res_phone, res_date, res_loyalty));
        }
        rs.close();

    }


    @FXML
    private TextField tb_findBook_name, tb_findBook_author, tb_findBook_genre, tb_findBook_condition;
    @FXML
    private void findBook(ActionEvent event) throws SQLException{
        String name = tb_findBook_name.getText();
        String author = tb_findBook_author.getText();
        String genre = tb_findBook_genre.getText();
        String condition = tb_findBook_condition.getText();

        /*SELECT books_info.BOOK_INFO_NAME, authors.AUTHOR_NAME, genres.GENRE_NAME,books_info.BOOK_INFO_INV_NUM,
        books.BOOK_CONDITION from books_info
        join authors_books on authors_books.BOOK_NFO_ID = books_info.BOOK_INFO_ID
        join authors on authors.AUTHOR_ID = authors_books.AUTHOR_ID
        join genres on genres.GENRE_ID = books_info.GENRE_ID
        join books on books.BOOK_INFO_ID = books_info.BOOK_INFO_ID
        WHERE books_info.BOOK_INFO_NAME = "The notebook"*/
    }


    @FXML
    private TextField tb_addClient_name, tb_addClient_pass, tb_addClient_phone;
    @FXML
    private void addClient (ActionEvent event) throws SQLException{
        String name = tb_addClient_name.getText();
        String pass = tb_addClient_pass.getText();
        String phone = tb_addClient_phone.getText();

        try{
            Connection con = DBConnector.getConnection();
            Statement st = con.createStatement();

            //INSERT INTO libr.users VALUES(USER_NAME, USER_PASSWORD, USER_POSS, USER_LOYALTY, USER_PH-NUM, USER_REC_DATE);

            /*String query = "INSERT INTO libr.users\n" +
                    "VALUES ('"+name+"', '"+ pass +"', '2' , '0', '"+ phone + "', 'CURDATE()' );";*/

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal2 = Calendar.getInstance();
            String date = dateFormat.format(cal2.getTime());

            PreparedStatement stmt = con.prepareStatement("INSERT INTO libr.users VALUES (default,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2,pass);
            stmt.setInt(3,2);
            stmt.setInt(4,0);
            stmt.setString(5,phone);
            stmt.setString(6,date);

            stmt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Records are successfully inserted! ");
            // alert.setContentText("Please enter");
            alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                public void accept(ButtonType rs) {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                }
            });

            tb_addClient_name.clear();
            tb_addClient_pass.clear();
            tb_addClient_phone.clear();


        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @FXML
    private TextField tb_makeRent_cname, tb_makeRent_bname, tb_makeRent_takeDate;
    @FXML
    private void makeRent(ActionEvent event) throws SQLException{

        String book_name = tb_makeRent_bname.getText();
        String client_name = tb_makeRent_cname.getText();

        try {
            Connection con = DBConnector.getConnection();
            Statement st = con.createStatement();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());

            String query = "INSERT INTO libr.rents (default, take_date, realise_date, book_id, user_id, rent_status)" +
                    "SELECT ' "+ date + "', (SELECT DATE_ADD(" + date + ", INTERVAL 20 DAY)), b.book_id, u.user_id, '0'" +
                    "FROM libr.books_info b, libr.users u \n" +
                    "WHERE b.book_name = " + book_name + "\rAND u.user_name = "+ client_name + ";";

            st.executeUpdate(query);





        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
