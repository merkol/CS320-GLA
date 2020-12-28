package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database_Main {
	private static final String URL = "jdbc:mysql://localhost:3306/gla?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "MySQLRootPassword";

	private static Connection connection = null;

	public static void establishConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			String createTableQueryUsers = "CREATE TABLE IF NOT EXISTS Users ("
					+ "    uid INT(5) NOT NULL AUTO_INCREMENT, username VARCHAR(20),"
					+ "    password VARCHAR(20), balance FLOAT(5)  CHECK (balance >= 0)," + "    PRIMARY KEY(uid));";
			statement.executeUpdate(createTableQueryUsers);
			String createTableQueryGames = "CREATE TABLE IF NOT EXISTS Games ("
					+ "    gid INT(5) NOT NULL AUTO_INCREMENT, name VARCHAR(20),"
					+ "    description VARCHAR(40), publisher VARCHAR(20),"
					+ "    price FLOAT(5)  CHECK (price >= 0), score FLOAT(5)  CHECK (score >= 0),"
					+ "    is_multi BOOL, PRIMARY KEY (gid));";
			statement.executeUpdate(createTableQueryGames);
			String createTableQueryGame_Ownership = "CREATE TABLE IF NOT EXISTS Game_Ownership ("
					+ "    uid INT(5) NOT NULL, gid INT(5) NOT NULL, PRIMARY KEY (uid,gid),"
					+ "    FOREIGN KEY (uid) REFERENCES Users (uid) ON DELETE CASCADE,"
					+ "    FOREIGN KEY (gid) REFERENCES Games (gid) ON DELETE CASCADE);";
			statement.executeUpdate(createTableQueryGame_Ownership);

//			Scanner input = new Scanner(System.in);
//			System.out.print("Enter the user ID: ");
//			int uid = input.nextInt();
//			System.out.print("Enter the game ID: ");
//			int gid = input.nextInt();
//			addGameToUser(uid, gid);
//			ArrayList<Integer> gids = listGamesOfUser(uid);
//			for (int i = 0; i < gids.size(); i++) {
//				System.out.println(gids.get(i));
//			}
//			input.close();

			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addUser(String username, String password, float balance) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("insert into Users (username, password, balance) values (?,?,?);");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setFloat(3, balance);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addGame(String name, String description, String publisher, float price, float score,
			boolean is_multi) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"insert into Games (name, description, publisher, price, score, is_multi) values (?,?,?,?,?,?);");
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, publisher);
			stmt.setFloat(4, price);
			stmt.setFloat(5, score);
			stmt.setBoolean(6, is_multi);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addGameToUser(int uid, int gid) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Game_Ownership (uid, gid) values (?,?);");
			stmt.setInt(1, uid);
			stmt.setInt(2, gid);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static ArrayList<Integer> listGamesOfUser(int uid) {
		ArrayList<Integer> gids = new ArrayList<Integer>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT gid FROM Game_Ownership WHERE uid=?;");
			stmt.setInt(1, uid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				gids.add(rs.getInt("Game_Ownership.gid"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return gids;
	}

	public static int getUid(String username) {
		int uid = 0;
		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT uid FROM Users WHERE username=?;");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				uid = rs.getInt("Users.uid");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return uid;
	}

	public static String getUsername(String username) {
		String result = "";
		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT username FROM Users WHERE uid=?;");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("Users.username");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void setUsername(int uid, String username) {
		try {
			PreparedStatement stmt = connection.prepareStatement("UPDATE Users SET username=? WHERE uid=?;");
			stmt.setString(1, username);
			stmt.setInt(2, uid);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static String getPassword(String password) {
		String result = "";
		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT password FROM Users WHERE uid=?;");
			stmt.setString(1, password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("Users.username");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void setPassword(int uid, String password) {
		try {
			PreparedStatement stmt = connection.prepareStatement("UPDATE Users SET password=? WHERE uid=?);");
			stmt.setString(1, password);
			stmt.setInt(2, uid);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
