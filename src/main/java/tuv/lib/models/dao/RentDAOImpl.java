package tuv.lib.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tuv.lib.models.DBConnector;
import tuv.lib.models.Rent;

public class RentDAOImpl implements RentDAO {

	public int makeRent(Rent r) {
		Connection con = DBConnector.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs;
			String id_book = "SELECT books.BOOK_ID" + " FROM (" + "SELECT books.BOOK_ID" + " from books"
					+ " join books_info on books_info.BOOK_INFO_ID = books.BOOK_INFO_ID"
					+ " WHERE books_info.BOOK_INFO_NAME = '" + r.getBook().getName() + "'" + ") books"
					+ " left JOIN rents on rents.BOOK_ID = books.BOOK_ID"
					+ " where rents.RENT_STATUS is null or rents.RENT_STATUS = 1 limit 1;";

			rs = st.executeQuery(id_book);

			if (rs.next()) {
				int res_id_book = rs.getInt("BOOK_ID");
				String query = "INSERT INTO rents VALUES " + "(default, '" + r.getTakeDate() + "', (SELECT DATE_ADD('"
						+ r.getTakeDate() + "', INTERVAL 20 DAY)), " + res_id_book
						+ ", (SELECT users.USER_ID from users where users.USER_NAME = '" + r.getClient().getName()
						+ "'), 0);";

				st.executeUpdate(query);
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public int closeRent(Rent r) {
		Connection con = DBConnector.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			// r.setReturnDate(LocalDate.now());
			// TODO update client loyalty
			String query = "UPDATE libr.rents " + " JOIN books on books.BOOK_ID = rents.BOOK_ID"
					+ " JOIN books_info on books_info.BOOK_INFO_ID = books.BOOK_INFO_ID"
					+ " JOIN users on users.USER_ID = rents.USER_ID"
					+ " SET rents.RENT_STATUS = 1, books.BOOK_CONDITION = books.BOOK_CONDITION+1"
					+ " WHERE users.USER_NAME = '" + r.getClient().getName() + "' AND books_info.BOOK_INFO_NAME = '"
					+ r.getBook().getName() + "';";

			st.executeUpdate(query);
			r.updateLoyalty();
			
			String updateLoyalty = "UPDATE libr.users SET USER_LOYALTY = "+r.getClient().getLoyalty()+" WHERE USER_NAME = '"+ r.getClient().getName() +"' ;"; 			
			st.executeUpdate(updateLoyalty);
			
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
