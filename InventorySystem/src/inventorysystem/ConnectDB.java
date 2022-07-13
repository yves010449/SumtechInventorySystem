/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {

    Statement statement = null;
    ResultSet result = null;
    Connection connection = null;
    String jdbcUrl = "jdbc:sqlite:src//Inventory";

    public Object[][] data;

    ConnectDB() {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            String sql = "CREATE TABLE IF NOT EXISTS people (\n"
                    + "   person_id INTEGER PRIMARY KEY,\n"
                    + "   first_name TEXT,\n"
                    + "   last_name TEXT,\n"
                    + "   address_id INTEGER,\n"
                    + "   FOREIGN KEY (address_id) \n"
                    + "      REFERENCES addresses (address_id)\n"
                    + ");";
             statement = connection.createStatement();
             statement.executeUpdate(sql);
             System.out.println("database created");

            data = new Object[getRowCount()][getColumnCount()];
            getSqlRows(0);

        } catch (SQLException ex) {
            System.out.println("Connection Error");
            System.out.println(ex);
        }
    }

    public int getColumnCount() {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Inventory");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            return numberOfColumns;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int getRowCount() {
        try {
            String sql = "SELECT COUNT(*) As recordCount FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int numberOfRows = result.getInt("recordCount");
            return numberOfRows;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public String getSqlColumns(int index) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Inventory");
            ResultSetMetaData rsmd = rs.getMetaData();
            return rsmd.getColumnName(index);

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public String getSqlRows(int i) {
        try {
            String sql = "Select * FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                data[i][0] = result.getString("name");
                data[i][1] = result.getString("price");
                data[i][2] = result.getString("Cost");
                i++;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

}
