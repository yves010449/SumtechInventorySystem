/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author yves0
 */
public class InventorySystem {

/* TODO
    make location center
    disable main frame when using add
    */
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new LoginGUI().setVisible(true);   
                new MainPageGUI().setVisible(true);
            }
        });
    }
    
}
