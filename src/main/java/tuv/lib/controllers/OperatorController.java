package tuv.lib.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import tuv.lib.models.*;
import tuv.lib.models.interfaces.BookService;
import tuv.lib.models.interfaces.UserService;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class OperatorController implements Initializable {
	private UserService userService;
	private BookService bookService;
	private final int panesCount = 7;
	private Map<Button, Pane> panes;
	private Operator operator;

	@FXML
	private Button btn_addBook, btn_removeBook, btn_addClient, btn_makeRent, btn_findBook, btn_findClient,
			btn_Classification;
	@FXML
	private Pane pln_addBook, pln_removeBook, pln_addClient, pln_makeRent, pln_findBook, pln_findClient,
			pln_Classification;

	@FXML
	private void buttonAction(ActionEvent event) {
		for (Map.Entry<Button, Pane> entry : panes.entrySet()) {
			if (event.getSource() == entry.getKey()) {
				entry.getValue().setVisible(true);
			} else {
				entry.getValue().setVisible(false);
			}
		}
	}

	// @Override
	public void initialize(URL location, ResourceBundle resources) {
		initializePanes();
		userService = new UserServiceImpl();
		operator = new Operator();
		for (Map.Entry<Button, Pane> entry : panes.entrySet())
			entry.getValue().setVisible(false);

		cb_findBook.setValue("Book Name");
		cb_findBook.setItems(find_by);

		tb_findBook_name.setEditable(true);
		tb_findBook_name.setDisable(false);

		tb_findBook_author.setEditable(false);
		tb_findBook_author.setDisable(true);

		tb_findBook_genre.setEditable(false);
		tb_findBook_genre.setDisable(true);

		tb_findBook_condition.setEditable(false);
		tb_findBook_condition.setDisable(true);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (cb_findBook.getValue() == "Book Name") {
					tb_findBook_name.setEditable(true);
					tb_findBook_name.setDisable(false);

					tb_findBook_author.setEditable(false);
					tb_findBook_author.setDisable(true);

					tb_findBook_genre.setEditable(false);
					tb_findBook_genre.setDisable(true);

					tb_findBook_condition.setEditable(false);
					tb_findBook_condition.setDisable(true);
				}

				else if (cb_findBook.getValue() == "Book Author") {
					tb_findBook_name.setEditable(false);
					tb_findBook_name.setDisable(true);

					tb_findBook_author.setEditable(true);
					tb_findBook_author.setDisable(false);

					tb_findBook_genre.setEditable(false);
					tb_findBook_genre.setDisable(true);

					tb_findBook_condition.setEditable(false);
					tb_findBook_condition.setDisable(true);
				}

				else if (cb_findBook.getValue() == "Book Genre") {
					tb_findBook_name.setEditable(false);
					tb_findBook_name.setDisable(true);

					tb_findBook_author.setEditable(false);
					tb_findBook_author.setDisable(true);

					tb_findBook_genre.setEditable(true);
					tb_findBook_genre.setDisable(false);

					tb_findBook_condition.setEditable(false);
					tb_findBook_condition.setDisable(true);

				}

				else if (cb_findBook.getValue() == "Condition") {
					tb_findBook_name.setEditable(false);
					tb_findBook_name.setDisable(true);

					tb_findBook_author.setEditable(false);
					tb_findBook_author.setDisable(true);

					tb_findBook_genre.setEditable(false);
					tb_findBook_genre.setDisable(true);

					tb_findBook_condition.setEditable(true);
					tb_findBook_condition.setDisable(false);
				}

			}
		};

		// Set on action
		cb_findBook.setOnAction(event);
	}

	/**
	 * Initialize the connection between the buttons and the panes
	 */
	private void initializePanes() {
		panes = new HashMap<Button, Pane>();
		panes.put(btn_addBook, pln_addBook);
		panes.put(btn_removeBook, pln_removeBook);
		panes.put(btn_addClient, pln_addClient);
		panes.put(btn_makeRent, pln_makeRent);
		panes.put(btn_findBook, pln_findBook);
		panes.put(btn_findClient, pln_findClient);
		panes.put(btn_Classification, pln_Classification);
	}

	// add book block
	@FXML
	private TextField tb_addBook_name, tb_addBook_author, tb_addBook_genre, tb_addBook_number;

	@FXML
	private void addBook(ActionEvent event) throws SQLException {
		String name = tb_addBook_name.getText();
		String author = tb_addBook_author.getText();
		String genre = tb_addBook_genre.getText();
		String number = tb_addBook_number.getText();

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();

		String query = "INSERT INTO libr.genres VALUES (default, '" + genre + "') "
				+ "ON DUPLICATE KEY UPDATE genre_name = '" + genre + "';";
		st.executeUpdate(query);

		query = "INSERT INTO libr.books_info VALUES (default, '" + name + "', '" + number + "', "
				+ "(SELECT genre_id FROM libr.genres WHERE genre_name = '" + genre + "'))"
				+ " ON DUPLICATE KEY UPDATE book_info_inv_num = '" + number + "';";

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

		query = "INSERT INTO libr.authors VALUES (default, '" + author + "') "
				+ "ON DUPLICATE KEY UPDATE author_name = '" + author + "';";
		st.executeUpdate(query);

		query = "INSERT INTO libr.authors_books VALUES ((SELECT authors.AUTHOR_ID from authors WHERE AUTHOR_NAME = '"
				+ author + "'), " + "(SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '" + name
				+ "'));";
		st.executeUpdate(query);

		query = "INSERT INTO libr.books VALUES (default , 0, (SELECT books_info.BOOK_INFO_ID FROM books_info WHERE BOOK_INFO_NAME = '"
				+ name + "'));";
		st.executeUpdate(query);

		tb_addBook_name.clear();
		tb_addBook_author.clear();
		tb_addBook_genre.clear();
		tb_addBook_number.clear();

	}

	// remove book block
	@FXML
	private TextField tb_removeBook_name, tb_removeBook_number;

	@FXML
	private void removeBook(ActionEvent event) throws SQLException {
		String name = tb_removeBook_name.getText();
		String number = tb_removeBook_number.getText();

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();

		String query = "DELETE FROM books WHERE EXISTS ("
				+ "SELECT * FROM books_info WHERE books_info.BOOK_INFO_ID = books.BOOK_INFO_ID"
				+ " AND books_info.BOOK_INFO_NAME = '" + name + "' AND books_info.BOOK_INFO_INV_NUM = '" + number
				+ "' )" + " AND books.BOOK_CONDITION > 100";

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

	// find client block
	@FXML
	private TextField tb_findClient_name, tb_findClient_phone;
	@FXML
	private TableView tw_findClient;
	@FXML
	public javafx.scene.control.TableColumn tc_findClient_name, tc_findClient_phone, tc_findClient_recDate,
			tc_findClient_loyalty;

	@FXML
	private void findClient(ActionEvent event) throws SQLException {
		String name = tb_findClient_name.getText();
		String phone = tb_findClient_phone.getText();

		List<Client> res = userService.findClients(name);

		// ZM not sure how this works
		for (int i = 0; i < res.size(); i++) {
			tc_findClient_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("name"));
			tc_findClient_phone.setCellValueFactory(new PropertyValueFactory<Object, Object>("phoneNum"));
			tc_findClient_loyalty.setCellValueFactory(new PropertyValueFactory<Object, Object>("loyalty"));
			tc_findClient_recDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("recordDate"));

			tw_findClient.getItems().add(res.get(i));
		}

	}

	ObservableList<String> find_by = FXCollections.observableArrayList("Book Name", "Book Author", "Book Genre",
			"Condition");
	@FXML
	private TextField tb_findBook_name, tb_findBook_author, tb_findBook_genre, tb_findBook_condition;
	@FXML
	private ComboBox cb_findBook;
	@FXML
	private TableView tw_findBook;
	@FXML
	public javafx.scene.control.TableColumn tc_findBook_name, tc_findBook_author, tc_findBook_genre, tc_findBook_invNum,
			tc_findBook_condition;

	@FXML
	private void findBook(ActionEvent event) throws SQLException {
		String name = tb_findBook_name.getText();
		String author = tb_findBook_author.getText();
		String genre = tb_findBook_genre.getText();
		String condition = tb_findBook_condition.getText();

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();
		String query;
		ResultSet rs;

		String res_name, res_author, res_genre, res_inv_num;
		// List<String> res_author = new ArrayList<String>();

		int res_condition;

		if (cb_findBook.getValue() == "Book Name") {
			query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE BOOK_INFO_NAME = '" + name
					+ "'" + " ORDER BY BOOK_ID, AUTHOR_NAME ;";

			rs = st.executeQuery(query);

			while (rs.next()) {
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				tc_findBook_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("Name"));
				tc_findBook_author.setCellValueFactory(new PropertyValueFactory<Object, Object>("Authors"));
				tc_findBook_genre.setCellValueFactory(new PropertyValueFactory<Object, Object>("Genre"));
				tc_findBook_invNum.setCellValueFactory(new PropertyValueFactory<Object, Object>("invNumber"));
				tc_findBook_condition.setCellValueFactory(new PropertyValueFactory<Object, Object>("Condition"));

				tw_findBook.getItems().add(new Book(res_name, res_author, res_genre, res_inv_num, res_condition));
			}
		}

		if (cb_findBook.getValue() == "Book Author") {
			query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE AUTHOR_NAME = '" + author
					+ "'" + " ORDER BY BOOK_ID, AUTHOR_NAME ;";

			rs = st.executeQuery(query);

			while (rs.next()) {
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				tc_findBook_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("Name"));
				tc_findBook_author.setCellValueFactory(new PropertyValueFactory<Object, Object>("Authors"));
				tc_findBook_genre.setCellValueFactory(new PropertyValueFactory<Object, Object>("Genre"));
				tc_findBook_invNum.setCellValueFactory(new PropertyValueFactory<Object, Object>("invNumber"));
				tc_findBook_condition.setCellValueFactory(new PropertyValueFactory<Object, Object>("Condition"));

				tw_findBook.getItems().add(new Book(res_name, res_author, res_genre, res_inv_num, res_condition));
			}
		}

		if (cb_findBook.getValue() == "Book Genre") {
			query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE GENRE_NAME = '" + genre
					+ "'" + " ORDER BY BOOK_ID, AUTHOR_NAME ;";

			rs = st.executeQuery(query);

			while (rs.next()) {
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				tc_findBook_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("Name"));
				tc_findBook_author.setCellValueFactory(new PropertyValueFactory<Object, Object>("Authors"));
				tc_findBook_genre.setCellValueFactory(new PropertyValueFactory<Object, Object>("Genre"));
				tc_findBook_invNum.setCellValueFactory(new PropertyValueFactory<Object, Object>("invNumber"));
				tc_findBook_condition.setCellValueFactory(new PropertyValueFactory<Object, Object>("Condition"));

				tw_findBook.getItems().add(new Book(res_name, res_author, res_genre, res_inv_num, res_condition));
			}
		}

		if (cb_findBook.getValue() == "Condition") {
			query = "SELECT BOOK_ID, BOOK_INFO_NAME, AUTHOR_NAME, GENRE_NAME, BOOK_INFO_INV_NUM, BOOK_CONDITION"
					+ " FROM libr.books_info"
					+ " JOIN  authors_books ON books_info.BOOK_INFO_ID = authors_books.BOOK_INFO_ID"
					+ " JOIN authors ON authors.AUTHOR_ID = authors_books.AUTHOR_ID"
					+ " JOIN genres ON genres.GENRE_ID = books_info.GENRE_ID"
					+ " JOIN books ON books.BOOK_INFO_ID = books_info.BOOK_INFO_ID" + " WHERE BOOK_CONDITION = '"
					+ condition + "'" + " ORDER BY BOOK_ID, AUTHOR_NAME ;";

			rs = st.executeQuery(query);

			while (rs.next()) {
				res_name = rs.getString("BOOK_INFO_NAME");
				res_author = rs.getString("AUTHOR_NAME");
				res_genre = rs.getString("GENRE_NAME");
				res_inv_num = rs.getString("BOOK_INFO_INV_NUM");
				res_condition = rs.getInt("BOOK_CONDITION");

				tc_findBook_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("Name"));
				tc_findBook_author.setCellValueFactory(new PropertyValueFactory<Object, Object>("Authors"));
				tc_findBook_genre.setCellValueFactory(new PropertyValueFactory<Object, Object>("Genre"));
				tc_findBook_invNum.setCellValueFactory(new PropertyValueFactory<Object, Object>("invNumber"));
				tc_findBook_condition.setCellValueFactory(new PropertyValueFactory<Object, Object>("Condition"));

				tw_findBook.getItems().add(new Book(res_name, res_author, res_genre, res_inv_num, res_condition));
			}
		}

	}

	// add client block
	@FXML
	private TextField tb_addClient_name, tb_addClient_pass, tb_addClient_phone;

	@FXML
	private void addClient(ActionEvent event) {
		String name = tb_addClient_name.getText();
		String pass = tb_addClient_pass.getText();
		String phone = tb_addClient_phone.getText();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal2 = Calendar.getInstance();
		String date = dateFormat.format(cal2.getTime());

		Client cl = operator.createClient(name, pass, date, phone);

		userService.addClient(cl);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("CLient is inserted successfully ! ");
		alert.showAndWait();

		tb_addClient_name.clear();
		tb_addClient_pass.clear();
		tb_addClient_phone.clear();

	}

	// make rent block
	@FXML
	private TextField tb_makeRent_cname, tb_makeRent_bname, tb_makeRent_takeDate;

	@FXML
	private void makeRent(ActionEvent event) throws SQLException {


		String book_name = tb_makeRent_bname.getText();
		String client_name = tb_makeRent_cname.getText();

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();
		ResultSet rs;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		String id_book = "SELECT books.BOOK_ID" +
				" FROM (" +
				"SELECT books.BOOK_ID" +
				" from books" +
				" join books_info on books_info.BOOK_INFO_ID = books.BOOK_INFO_ID" +
				" WHERE books_info.BOOK_INFO_NAME = '" + book_name +"'" + ") books" +
				" left JOIN rents on rents.BOOK_ID = books.BOOK_ID" +
				" where rents.RENT_STATUS is null or rents.RENT_STATUS = 1 limit 1;";

		rs = st.executeQuery(id_book);

		if(rs.next()){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Found records!! ");
			// alert.setContentText("Please enter");
			alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
				public void accept(ButtonType rs) {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				}
			});

			int res_id_book = rs.getInt("BOOK_ID");

			String query = "INSERT INTO rents VALUES " +
					"(default, '" + date + "', (SELECT DATE_ADD('" + date + "', INTERVAL 20 DAY)), " +
					res_id_book + ", (SELECT users.USER_ID from users where users.USER_NAME = '" + client_name +"'), 0);";

			st.executeUpdate(query);

			Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
			alert_2.setTitle("Information");
			alert_2.setHeaderText("The rent is successfully made !!");
			alert_2.showAndWait().ifPresent(new Consumer<ButtonType>() {
				public void accept(ButtonType rs) {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				}
			});

		}
		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Records not found!! ");
			// alert.setContentText("Please enter");
			alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
				public void accept(ButtonType rs) {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				}
			});

		}
	}

	@FXML
	private void closeRent(ActionEvent event) throws SQLException{
		String book_name = tb_makeRent_bname.getText();
		String client_name = tb_makeRent_cname.getText();

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();

		String query = "UPDATE libr.rents " +
				" JOIN books on books.BOOK_ID = rents.BOOK_ID" +
				" JOIN books_info on books_info.BOOK_INFO_ID = books.BOOK_INFO_ID" +
				" JOIN users on users.USER_ID = rents.USER_ID" +
				" SET rents.RENT_STATUS = 1, books.BOOK_CONDITION = books.BOOK_CONDITION+1" +
				" WHERE users.USER_NAME = '" + client_name +"' AND books_info.BOOK_INFO_NAME = '" + book_name +"';";

		st.executeUpdate(query);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("The rent is successfully closed!!");
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
	private javafx.scene.control.TableColumn tc_class_name, tc_class_phone, tc_class_recDate, tc_class_loyalty;
	@FXML
	private TableView tw_class;

	@FXML
	private void classification_byLoyalty(ActionEvent event) {

		tw_class.getItems().clear();

		List<Client> res = userService.classifyClients("loyalty");

		for (int i = 0; i < res.size(); i++) {
			tc_class_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("name"));
			tc_class_phone.setCellValueFactory(new PropertyValueFactory<Object, Object>("phoneNum"));
			tc_class_recDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("recordDate"));
			tc_class_loyalty.setCellValueFactory(new PropertyValueFactory<Object, Object>("loyalty"));

			tw_class.getItems().add(res.get(i));
		}

	}

	@FXML
	private void classification_byRecDate(ActionEvent event) {

		tw_class.getItems().clear();

		List<Client> res = userService.classifyClients(" ");

		for (int i = 0; i < res.size(); i++) {
			tc_class_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("name"));
			tc_class_phone.setCellValueFactory(new PropertyValueFactory<Object, Object>("phoneNum"));
			tc_class_recDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("recordDate"));
			tc_class_loyalty.setCellValueFactory(new PropertyValueFactory<Object, Object>("loyalty"));

			tw_class.getItems().add(res.get(i));
		}

	}
}