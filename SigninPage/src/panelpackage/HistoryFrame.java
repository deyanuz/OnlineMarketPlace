package panelpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import signinpage.CustomerPage;

public class HistoryFrame extends javax.swing.JFrame {

    public HistoryFrame(Product product, CustomerPage sp) {
        initComponents();
        SetProduct(product, sp);

    }

    public void SetProduct(Product product, CustomerPage sp) {
        ImagePanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]40[]15[]15[]push"));
        InfoPanel.setLayout(new MigLayout("fill, insets 0", "push[left]push", "push[center]push"));
        InfoPanel.setBackground(new Color(230, 230, 250));
        ImagePanel.setBackground(new Color(230, 230, 250));
        BufferedImage resizedImage = new BufferedImage(140, 140,
                product.image.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(product.image, 0, 0, 140,
                140, null);
        g.dispose();
        Image image = resizedImage.getScaledInstance(
                resizedImage.getWidth(), resizedImage.getHeight(),
                Image.SCALE_SMOOTH);
        JLabel j = new JLabel("");
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 4));

        j.setIcon(new ImageIcon(image));
        ImagePanel.add(j);
        JPanel productPanel = new JPanel(new MigLayout("wrap", "push[center]push", "push[center]push"));

        j = new JLabel("Prodict Name: " + product.productName + " ");
        j.setFont(new Font("serif", 1, 18));
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 1));
        j.setForeground(new Color(164, 113, 230));
        productPanel.add(j, "span, left, wrap");

        j = new JLabel("Description: " + product.productDescription + " ");
        j.setFont(new Font("serif", 1, 18));
        j.setForeground(new Color(164, 113, 230));
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 1));
        productPanel.add(j, "span, left, wrap");

        j = new JLabel("Quantity: " + product.productQuantity + " ");
        j.setFont(new Font("serif", 1, 18));
        j.setForeground(new Color(164, 113, 230));
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 1));
        productPanel.add(j, "span, left, wrap");

        j = new JLabel("Price: " + product.productPrice + " Taka ");
        j.setFont(new Font("serif", 1, 18));
        j.setForeground(new Color(164, 113, 230));
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 1));
        productPanel.add(j, "span, left, wrap");

        j = new JLabel("Sold: " + product.sold + " Unit ");
        j.setFont(new Font("serif", 1, 18));
        j.setForeground(new Color(164, 113, 230));
        j.setBorder(BorderFactory.createLineBorder(new Color(164,
                113, 230), 1));
        productPanel.add(j, "span, left, wrap");

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        productPanel.setBackground(new Color(250, 250, 250));
        productPanel.setOpaque(true);
        InfoPanel.add(scrollPane, "span, left, wrap");
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);

        ButtonOutLine btnBack = new ButtonOutLine();
        btnBack.setText("Back");
        btnBack.setBackground(new Color(164, 113, 230));
        btnBack.setForeground(new Color(164, 113, 230));
        ImagePanel.add(btnBack, "w 80%, h 40");
        btnBack.addActionListener((ActionEvent e) -> {
            super.dispose();
            sp.setVisible(true);
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        ImagePanel = new javax.swing.JPanel();
        InfoPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jLayeredPane2.setBackground(new java.awt.Color(230, 230, 250));
        jLayeredPane2.setOpaque(true);
        jLayeredPane2.setPreferredSize(new java.awt.Dimension(600, 400));

        ImagePanel.setBackground(new java.awt.Color(255, 255, 255));
        ImagePanel.setPreferredSize(new java.awt.Dimension(240, 400));

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        InfoPanel.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayer(ImagePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(InfoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ImagePanel;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables
}
