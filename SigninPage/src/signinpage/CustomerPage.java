/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package signinpage;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import panelpackage.ButtonOutLine;

/**
 *
 * @author Asus
 */
public class CustomerPage extends javax.swing.JFrame {

    /**
     * Creates new form CustomerPage
     */
    public CustomerPage() {
        initComponents();
        initMenuItem(); 
    }
    
    public  void initMenuItem(){
        MenuItem.setBackground(new Color(176, 156, 200));
        MigLayout layout = new MigLayout("wrap","[center]","push[]25[]10[]10[]10[]10[]push");
        MenuItem.setLayout(layout);
        MenuExplore.setLayout(layout);
        
        // Add CompanyName label with photo
        JLabel title = new JLabel("Company Name");
        title.setFont(new Font("sansserif", 1, 35));
        title.setForeground(new Color(245, 245, 245));
        title.setIcon(new ImageIcon(getClass().getResource("company.png")));
        title.setBounds(50, 20, 200, 50);
        MenuItem.add(title);
        
        
        ButtonOutLine addProducButton = new ButtonOutLine();
        addProducButton.setText("Add Product");
        addProducButton.setBackground(new Color(164, 113, 230));
        addProducButton.setForeground(new Color(164, 113, 230));
        MenuItem.add(addProducButton, "w 80%, h 40");
        
        ButtonOutLine viewOrdersButton = new ButtonOutLine();
        viewOrdersButton.setText("View Orders");
        viewOrdersButton.setBackground(new Color(164, 113, 230));
        viewOrdersButton.setForeground(new Color(164, 113, 230));
        MenuItem.add(viewOrdersButton, "w 80%, h 40");
        
        ButtonOutLine transactionButton = new ButtonOutLine();
        transactionButton.setText("Transaction");
        transactionButton.setBackground(new Color(164, 113, 230));
        transactionButton.setForeground(new Color(164, 113, 230));
        MenuItem.add(transactionButton, "w 80%, h 40");
        
        ButtonOutLine historyButton = new ButtonOutLine();
        historyButton.setText("History");
        historyButton.setBackground(new Color(164, 113, 230));
        historyButton.setForeground(new Color(164, 113, 230));
        MenuItem.add(historyButton, "w 80%, h 40");
        
        ButtonOutLine logoutButton = new ButtonOutLine();
        logoutButton.setText("Log Out");
        logoutButton.setBackground(new Color(164, 113, 230));
        logoutButton.setForeground(new Color(164, 113, 230));
        MenuItem.add(logoutButton, "w 80%, h 40");
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        MenuItem = new javax.swing.JPanel();
        MenuExplore = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setOpaque(true);

        MenuItem.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MenuItemLayout = new javax.swing.GroupLayout(MenuItem);
        MenuItem.setLayout(MenuItemLayout);
        MenuItemLayout.setHorizontalGroup(
            MenuItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        MenuItemLayout.setVerticalGroup(
            MenuItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        MenuExplore.setBackground(new java.awt.Color(255, 255, 255));
        MenuExplore.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout MenuExploreLayout = new javax.swing.GroupLayout(MenuExplore);
        MenuExplore.setLayout(MenuExploreLayout);
        MenuExploreLayout.setHorizontalGroup(
            MenuExploreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        MenuExploreLayout.setVerticalGroup(
            MenuExploreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayer(MenuItem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(MenuExplore, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(MenuItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuExplore, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuExplore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuExplore;
    private javax.swing.JPanel MenuItem;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables

    private void addButton(String add_Product, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
