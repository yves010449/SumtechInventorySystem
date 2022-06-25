package inventorysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HomeGUI extends JFrame {

    JFrame mainFrame;

    BorderLayout mainBorderLayout = new BorderLayout();
   

    HomeGUI() {
        initializeComponents();
    }

    private void initializeComponents() {
        initializeFrame();
        initializeTopPage();
        initializeCenterPage();
        initializeBottomPage();
        mainFrame.setVisible(true);
    }

    void initializeFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(1000, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Inventory System 0.0.1");
        //mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(mainBorderLayout);
    }


    void initializeTopPage() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.yellow);
        headerPanel.setPreferredSize(new Dimension(200, 100));
        mainFrame.add(headerPanel, mainBorderLayout.PAGE_START);
    }

    void initializeCenterPage() {
        JPanel Panel = new JPanel();
        Panel.setBackground(Color.red);
        Panel.setPreferredSize(new Dimension(200, 100));
        Panel.setLayout(new GridLayout(1, 0));
        initializeTable();
        
        Panel.add(initializeTable());
        mainFrame.add(Panel, mainBorderLayout.CENTER);
    }

    void initializeBottomPage() {
        JPanel Panel = new JPanel();
        Panel.setBackground(Color.blue);
        Panel.setPreferredSize(new Dimension(200, 75));
        Panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        JButton addBtn = new JButton();
        addBtn.setPreferredSize(new Dimension(150, 40));
        addBtn.setText("Add");
        Panel.add(addBtn);

        JButton deleteBtn = new JButton();
        deleteBtn.setPreferredSize(new Dimension(150, 40));
        deleteBtn.setText("Delete");
        Panel.add(deleteBtn);

        JButton editButton = new JButton();
        editButton.setPreferredSize(new Dimension(150, 40));
        editButton.setText("Edit");
        Panel.add(editButton);
        
        mainFrame.add(Panel, mainBorderLayout.PAGE_END);
    }

    JTable j;
    JTable initializeTable(){
        String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
        };
        String[] columnNames = { "Name", "Roll Number", "Department" };
 
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        add(new JScrollPane(j));       
        mainFrame.add(sp);
        return j;
    }
}
