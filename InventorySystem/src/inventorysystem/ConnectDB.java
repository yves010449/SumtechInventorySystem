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

            String sql = "CREATE TABLE IF NOT EXISTS Inventory (\n"
                    + "   name TEXT NOT NULL,\n"
                    + "   cost INTEGER NOT NULL,\n"
                    + "   selling INTEGER,\n"
                    + "   quantity INTEGER(3) NOT NULL,\n"
                    + "   total_items INTEGER,\n"
                    + "   total_inventory INTEGER\n"
                    + ");";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("database created");
            
            
            

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

    public String getSqlRows() {
        try {
            data = new Object[getRowCount()][getColumnCount()];
            String sql = "Select * FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int i = 0;
            while (result.next()) {
                data[i][0] = result.getString("name");
                data[i][1] = result.getString("cost");
                data[i][2] = result.getString("selling");
                data[i][3] = result.getString("quantity");
                data[i][4] = result.getString("total_items");
                data[i][5] = result.getString("total_inventory");
                i++;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertRowSql(String name, int cost,int selling,int quantity,int total_items,int total_inventory) {
        try {
            String sql = "INSERT INTO Inventory VALUES('"+name+"',"+cost+","+selling+","+quantity+","+total_items+","+total_inventory+");";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Insert");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
