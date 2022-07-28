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
    public String[] itemData;
    ConnectDB() {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            String sql = "CREATE TABLE IF NOT EXISTS Inventory (\n"
                    + "   ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "   name TEXT NOT NULL,\n"
                    + "   type TEXT ,\n"
                    + "   cost INTEGER NOT NULL,\n"
                    + "   selling INTEGER,\n"
                    + "   quantity INTEGER(3) NOT NULL,\n"
                    + "   total_inventory INTEGER\n"
                    + ");";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("database created");
            getSqlRows();
            
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
            data = new Object[getRowCount()][getColumnCount() + 1];
            String sql = "Select rowid, * FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int i = 0;
            while (result.next()) {
                data[i][0] = result.getString("ID");
                data[i][1] = result.getString("name");
                data[i][2] = result.getString("type");
                data[i][3] = result.getString("cost");
                data[i][4] = result.getString("selling");
                data[i][5] = result.getString("quantity");
                data[i][6] = result.getString("total_inventory");
                i++;

            }          
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public void insertRowSql(String name, String type, int cost, int selling, int quantity, int total_inventory) {
        try {
            String sql = "INSERT INTO Inventory (name,type,cost,selling,quantity,total_inventory)VALUES('" + name + "','" + type + "'," + cost + "," + selling + "," + quantity + "," + total_inventory + ");";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Insert");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTotalInventorySum(){
         try {

            String sql = "SELECT SUM(total_inventory)  FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);      
            return result.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    public void deleteRow(String id) {
        try {

            String sql = "DELETE  FROM Inventory\n"
                    + "WHERE ID = " + id + ";";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete sql");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateRow(String id, String name, String type, int cost,int selling, int quantity,int total_inventory){
        try {

            String sql = "UPDATE Inventory \n"
                    + "SET name = '"+name+"',\n"
                    + "type = '"+type+"',\n"
                    + "cost = "+cost+",\n"
                    + "selling = "+selling+",\n"
                    + "quantity = "+quantity+",\n"
                    + "total_inventory = "+total_inventory+"\n"
                    + "WHERE ID = " + id + ";";
            statement = connection.createStatement();
             statement.executeUpdate(sql);
            System.out.println("update sql");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public String getLastRowID() {
        try {
            data = new Object[getRowCount()][getColumnCount() + 1];
            String sql = "Select rowid, * FROM Inventory";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int i = 0;
            while (result.next()) {
                data[i][0] = result.getString("ID");
                i++;
            }
            return data[i - 1][0].toString();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getItem(String id, String item) {
        try {
            String sql = "SELECT *  FROM Inventory\n"
                    + "WHERE ID = " + id + ";";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            return result.getString(item);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void createItemTypeDatabase(){
        try{
            connection.close();
            connection = DriverManager.getConnection("jdbc:sqlite:src//ItemType");
             String sql = "CREATE TABLE IF NOT EXISTS ItemType (\n"
                    + "   name TEXT NOT NULL"                 
                    + ");";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
            getItemTypeRows();
            connection = DriverManager.getConnection(jdbcUrl);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
    }
    
    public void getItemTypeRows() {
        try {         
            itemData = new String[getItemTypeRowCount()+2];
            String sql = "Select rowid, * FROM ItemType";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int i = 1;
            while (result.next()) {               
                itemData[i] = result.getString("name");
                i++;
            }
            System.out.println(getItemTypeRowCount()+2);
            
            itemData[0] = "Select type";
            itemData[getItemTypeRowCount()+1] = "Add type";    
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getItemTypeRowCount() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src//ItemType");
            String sql = "SELECT COUNT(*) As recordCount FROM ItemType";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            int numberOfRows = result.getInt("recordCount");      
            connection.close();
            connection = DriverManager.getConnection(jdbcUrl);
            return numberOfRows;         
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public void insertRowItemType(String name) {
        try {
            connection.close();
            connection = DriverManager.getConnection("jdbc:sqlite:src//ItemType");
            String sql = "INSERT INTO ItemType VALUES('" + name + "');";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Insert");
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
