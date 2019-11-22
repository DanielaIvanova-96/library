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
import tuv.lib.models.interfaces.RentService;
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
	private RentService rentService;
	private Map<Button, Pane> panes;
	private Operator operator;

	public OperatorController(User u) {
		this.operator = new Operator(u);
	}

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
		this.userService = new UserServiceImpl();
		this.bookService = new BookServiceImpl();
		this.rentService = new RentServiceImpl();

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
	private void addBook(ActionEvent event) {
		String name = tb_addBook_name.getText();
		// TODO validate split for empty entities
		List<String> authors = Arrays.asList(tb_addBook_author.getText().split(" "));
		String genre = tb_addBook_genre.getText();
		String number = tb_addBook_number.getText();

		Book book = new Book(name, authors, genre, number);

		bookService.addBook(book);

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
		int cond = Integer.parseInt(tb_removeBook_number.getText());
		Book b = new Book(name,cond);
		
		this.bookService.removeBook(b);
		
		tb_removeBook_name.clear();
		tb_removeBook_number.clear();
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
	private void findClient(ActionEvent event) {
		String name = tb_findClient_name.getText();
		String phone = tb_findClient_phone.getText();

		List<Client> res = userService.findClients(name);

		// ZM not sure how this works
		tc_findClient_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("name"));
		tc_findClient_phone.setCellValueFactory(new PropertyValueFactory<Object, Object>("phoneNum"));
		tc_findClient_loyalty.setCellValueFactory(new PropertyValueFactory<Object, Object>("loyalty"));
		tc_findClient_recDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("recordDate"));
		for (int i = 0; i < res.size(); i++) {
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

	private void displayBooks(List<Book> books) {
		// TODO move in the validator class
		if (books.isEmpty() || books == null) {
			tb_findBook_name.clear();
			tb_findBook_author.clear();
			tb_findBook_genre.clear();
			tb_findBook_condition.clear();

			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No Records");
			alert.setHeaderText("No records found !!");
			// alert.setContentText("Please enter");
			alert.showAndWait();
			return;
		}

		tw_findBook.getItems().clear();

		tc_findBook_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("Name"));
		tc_findBook_author.setCellValueFactory(new PropertyValueFactory<Object, Object>("Authors"));
		tc_findBook_genre.setCellValueFactory(new PropertyValueFactory<Object, Object>("Genre"));
		tc_findBook_invNum.setCellValueFactory(new PropertyValueFactory<Object, Object>("invNumber"));
		tc_findBook_condition.setCellValueFactory(new PropertyValueFactory<Object, Object>("Condition"));

		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			tw_findBook.getItems().add(iterator.next());
		}
	}

	@FXML
	private void findBook(ActionEvent event) {

		if (cb_findBook.getValue() == "Book Name") {
			String name = tb_findBook_name.getText();
			// tb_findBook_name.clear();
			tb_findBook_author.clear();
			tb_findBook_genre.clear();
			tb_findBook_condition.clear();

			this.displayBooks(bookService.getBookbyName(name));
		} else if (cb_findBook.getValue() == "Book Author") {
			String author = tb_findBook_author.getText();
			tb_findBook_name.clear();
			// tb_findBook_author.clear();
			tb_findBook_genre.clear();
			tb_findBook_condition.clear();

			this.displayBooks(bookService.getBooksByAuthor(author));
		} else if (cb_findBook.getValue() == "Book Genre") {
			String genre = tb_findBook_genre.getText();
			tb_findBook_name.clear();
			tb_findBook_author.clear();
			// tb_findBook_genre.clear();
			tb_findBook_condition.clear();

			this.displayBooks(bookService.getBooksByGenre(genre));
		} else if (cb_findBook.getValue() == "Condition") {
			String str_condition = tb_findBook_condition.getText();
			int condition = Integer.parseInt(str_condition);
			tb_findBook_name.clear();
			tb_findBook_author.clear();
			tb_findBook_genre.clear();
			// tb_findBook_condition.clear();

			this.displayBooks(bookService.getBookByCondition(condition));
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

		Book bk = new Book(book_name);
		Client cl = new Client(client_name);

		Rent r = new Rent(cl, bk);
		int status = rentService.makeRent(r);

		if (status == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("SUCCESS");
			alert.setHeaderText("The rent is successfully made !!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("FAIL");
			alert.setHeaderText("The cannot be made !!");
			alert.showAndWait();
		}
		tb_makeRent_bname.clear();
		tb_makeRent_cname.clear();
	}

	@FXML
	private void closeRent(ActionEvent event) throws SQLException {
		String book_name = tb_makeRent_bname.getText();
		String client_name = tb_makeRent_cname.getText();

		Book bk = new Book(book_name);
		Client cl = new Client(client_name);

		Rent r = new Rent(cl, bk);
		int status = rentService.closeRent(r);

		if (status == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("SUCCESS");
			alert.setHeaderText("The rent is successfully cloesd !!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("FAIL");
			alert.setHeaderText("The cannot be cloesd !!");
			alert.showAndWait();
		}
		tb_makeRent_bname.clear();
		tb_makeRent_cname.clear();
	}

	@FXML
	private javafx.scene.control.TableColumn tc_class_name, tc_class_phone, tc_class_recDate, tc_class_loyalty;
	@FXML
	private TableView tw_class;

	private void displayClients(List<Client> res) {
		tw_class.getItems().clear();

		for (int i = 0; i < res.size(); i++) {
			tc_class_name.setCellValueFactory(new PropertyValueFactory<Object, Object>("name"));
			tc_class_phone.setCellValueFactory(new PropertyValueFactory<Object, Object>("phoneNum"));
			tc_class_recDate.setCellValueFactory(new PropertyValueFactory<Object, Object>("recordDate"));
			tc_class_loyalty.setCellValueFactory(new PropertyValueFactory<Object, Object>("loyalty"));

			tw_class.getItems().add(res.get(i));
		}
	}

	@FXML
	private void classification_byLoyalty(ActionEvent event) {
		List<Client> res = userService.classifyClients("loyalty");
		displayClients(res);
	}

	@FXML
	private void classification_byRecDate(ActionEvent event) {
		List<Client> res = userService.classifyClients(" ");
		displayClients(res);
	}
}
