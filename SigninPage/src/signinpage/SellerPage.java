package signinpage;

import java.sql.DriverManager;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import panelpackage.ButtonOutLine;
import panelpackage.MyTextField;
import panelpackage.Product;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import panelpackage.Custompanel;
import panelpackage.FadeOutTransition;
import panelpackage.ProductFrame;
import panelpackage.sellerId;

public class SellerPage extends javax.swing.JFrame {

    public SellerPage(sellerId seller) {
        initComponents();
        initMenuItem(seller, this);
        initialPage(seller);
    }

    public void initMenuItem(sellerId seller, SellerPage sp) {
        MenuItem.setBackground(new Color(176, 156, 200));
        MigLayout layout = new MigLayout("wrap", "push[center]push",
                "push[]25[]10[]10[]10[]10[]push");
        MenuItem.setLayout(layout);

        JLabel title = new JLabel("");
        title.setFont(new Font("sansserif", 1, 35));
        title.setForeground(new Color(245, 245, 245));
        title.setIcon(new ImageIcon(getClass().
                getResource("company.png")));
        title.setBounds(50, 20, 200, 50);
        MenuItem.add(title);

        ButtonOutLine addProducButton = new ButtonOutLine();
        addProducButton.setText("Add Product");
        addProducButton.setBackground(new Color(164, 113, 230));
        addProducButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(addProducButton, "w 80%, h 40");

        addProducButton.addActionListener((ActionEvent e) -> {
            GetProductDetails(seller);
        });

        ButtonOutLine viewOrdersButton = new ButtonOutLine();
        viewOrdersButton.setText("View Orders");
        viewOrdersButton.setBackground(new Color(164, 113, 230));
        viewOrdersButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(viewOrdersButton, "w 80%, h 40");

        ButtonOutLine allProductsButton = new ButtonOutLine();
        allProductsButton.setText("All Products");
        allProductsButton.setBackground(new Color(164, 113, 230));
        allProductsButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(allProductsButton, "w 80%, h 40");

        allProductsButton.addActionListener((ActionEvent e) -> {
            ShowAllProducts(seller, sp);
        });

        ButtonOutLine logoutButton = new ButtonOutLine();
        logoutButton.setText("Log Out");
        logoutButton.setBackground(new Color(164, 113, 230));
        logoutButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(logoutButton, "w 80%, h 40");

        logoutButton.addActionListener((e) -> {
            this.setVisible(false);
            new LoginForm().setVisible(true);

        });
    }

    public void ShowAllProducts(sellerId seller, SellerPage sp) {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,
                BoxLayout.PAGE_AXIS));
        JLabel allProductLabel=new JLabel("All Products");
        allProductLabel.setFont(new Font("serif", 1, 35));
        allProductLabel.setForeground(new Color(150, 18, 196));
                
        ArrayList<Product> products = GetProducts(seller.email);

        // Add components to the panel
        for (int i = 0; i < products.size(); i++) {
            //System.out.println(products.get(i).productName);
            Product product = products.get(i);
            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[]push", "push[]push"));
            customPanel.setPreferredSize(new Dimension(350, 30));
            customPanel.setBackground(new Color(230, 230, 250));
            JLabel j = new JLabel(products.get(i).productName);

            BufferedImage resizedImage = new BufferedImage(20, 20,
                    products.get(i).image.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(products.get(i).image, 0, 0, 20,
                    20, null);
            g.dispose();
            Image image = resizedImage.getScaledInstance(
                    resizedImage.getWidth(), resizedImage.getHeight(),
                    Image.SCALE_SMOOTH);

            j.setIcon(new ImageIcon(image));
            j.setFont(new Font("serif", 1, 30));
            j.setOpaque(true);
            j.setBackground(new Color(230, 230, 250));
            j.setForeground(new Color(150, 18, 196));
            j.setBorder(BorderFactory.createLineBorder(new Color(164,
                    113, 230), 2));

            j.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sp.setVisible(false);
                    ProductFrame pf = new ProductFrame(product, sp);
                    pf.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            customPanel.add(j, "span, wrap");
            customPanel.setOpaque(true);
            contentPanel.getPreferredSize();
            contentPanel.add(customPanel, "w 60%, h 40, fill");
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        MenuExplore.removeAll();
        MenuExplore.add(allProductLabel,"span, center, wrap");
        scrollPane.setBorder(null);
        MenuExplore.add(scrollPane, "span, center, wrap");
        MenuExplore.revalidate();
        MenuExplore.repaint();
    }

    public ArrayList<Product> GetProducts(String email) {

        ArrayList<Product> products = new ArrayList<>();
        String JDBC_URL = "jdbc:mysql://localhost:3306/myDb";
        String USER = "root";
        String PASSWORD = "1234";

        try (Connection connection = DriverManager.getConnection(JDBC_URL,
                USER, PASSWORD)) {
            String query = "SELECT * FROM product WHERE CompanyEmail=?";
            try (PreparedStatement preparedStatement
                    = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = new Product();
                        product.sellerEmail = resultSet.getString(
                                "CompanyEmail");
                        product.productName = resultSet.getString(
                                "ProductName");
                        product.productPrice = resultSet.getString(
                                "ProductPrice");
                        product.productQuantity = resultSet.getString(
                                "ProductQuantity");
                        product.productDescription = resultSet.getString(
                                "ProductDescription");
                        product.sold = resultSet.getString("Sold");

                        // Convert and set the image
                        byte[] imageData = resultSet.getBytes("Image");
                        if (imageData != null) {
                            BufferedImage image = decodeImage(imageData);
                            product.image = image;
                        }

                        products.add(product);
                    }
                }
            }
        } catch (SQLException e) {
        }

        return products;
    }

    public static BufferedImage decodeImage(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                    imageData);
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    public void GetProductDetails(sellerId seller) {

        MenuExplore.removeAll();

        // Set layout for MenuExplore
        MenuExplore.setLayout(new MigLayout("wrap 2", "push[center]push",
                "35[]25[]10[]10[]10[]10[]20[]15[]"));

        JLabel label = new JLabel("Product Details");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(164, 113, 230));
        MenuExplore.add(label, "span, center, wrap");

        // Add Product Name MyTextField
        MyTextField txtProductName = new MyTextField();
        txtProductName.setHint("Product Name");
        txtProductName.setText(""); // Set initial text if needed
        MenuExplore.add(txtProductName, "w 60%, wrap");

        // Add Price MyTextField
        MyTextField txtPrice = new MyTextField();
        txtPrice.setHint("Price");
        txtPrice.setText(""); // Set initial text if needed
        MenuExplore.add(txtPrice, "w 60%, wrap");

        // Add Quantity MyTextField
        MyTextField txtQuantity = new MyTextField();
        txtQuantity.setHint("Quantity");
        txtQuantity.setText(""); // Set initial text if needed
        MenuExplore.add(txtQuantity, "w 60%, wrap");

        // Add Description MyTextField
        MyTextField txtDescription = new MyTextField();
        txtDescription.setHint("Description");
        txtDescription.setText(""); // Set initial text if needed
        MenuExplore.add(txtDescription, "w 60%, wrap");

        //Image input
        JPanel imagePanel = new JPanel(new MigLayout("wrap 2", "20[]20[]20",
                "7[]1"));
        JLabel imageLabel = new JLabel("Choose an Image");
        imageLabel.setFont(new Font("serif", 1, 18));
        imageLabel.setForeground(new Color(164, 113, 230));
        imagePanel.add(imageLabel, "w 50%");

        Button imageButton = new Button("Search");
        imageButton.setBackground(new Color(230, 230, 250));
        imageButton.setForeground(new Color(164, 113, 230));
        imagePanel.add(imageButton, "w 25%, h 80, wrap 2");

        imageButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imageLabel.setText(selectedFile.toString());
            }
        });

        imagePanel.setBackground(new Color(250, 250, 250));
        MenuExplore.add(imagePanel, "w 60%, h 40, wrap");
        // Add Add Button
        Button btnAdd = new Button("Add");
        btnAdd.setBackground(new Color(230, 230, 250));
        btnAdd.setForeground(new Color(164, 113, 230));
        MenuExplore.add(btnAdd, "w 30%, h 40, wrap");

        // Add Back Button
        Button btnBack = new Button("Cancel");
        btnBack.setBackground(new Color(230, 230, 250));
        btnBack.setForeground(new Color(164, 113, 230));
        MenuExplore.add(btnBack, "w 30%, h 40, wrap");

        //ImageIcon productImage=new ImageIcon();
        //MenuExplore.add(productImage);
        btnAdd.addActionListener((ActionEvent e) -> {

            if ("".equals(txtProductName.getText())
                    || "".equals(txtPrice.getText())
                    || "".equals(txtQuantity.getText())
                    || "".equals(txtDescription.getText())
                    || "Choose an Image".equals(imageLabel.getText())) {

                JLabel errorLabel = new JLabel("Please Fill All the Sections");
                errorLabel.setFont(new Font("sansserif", Font.BOLD,
                        15));
                errorLabel.setForeground(new Color(164, 113, 230));
                MenuExplore.add(errorLabel);

                Timer timer = new Timer(2000, (ActionEvent ae) -> {
                    FadeOutTransition.fadeOutComponent(errorLabel,
                            1000, MenuExplore);
                    MenuExplore.revalidate();
                    MenuExplore.repaint();

                });
                timer.setRepeats(false);
                timer.start();
                MenuExplore.revalidate();
                MenuExplore.repaint();

            } else {

                File file = new File(imageLabel.getText());
                try {
                    BufferedImage image = ImageIO.read(file);
                    Product product = new Product(seller.email,
                            txtProductName.getText(),
                            txtPrice.getText(),
                            txtQuantity.getText(),
                            txtDescription.getText(), image, "0");
                    if (ProductExists(product)) {

                        if (MenuExplore.getComponentCount() >= 9) {
                            MenuExplore.remove(8);
                        }
                        JLabel errorLabel = new JLabel("Product "
                                + "Updated Successfully");
                        errorLabel.setFont(new Font("sansserif",
                                Font.BOLD, 15));
                        errorLabel.setForeground(new Color(164, 113, 230));
                        MenuExplore.add(errorLabel);

                        Timer timer = new Timer(2000, (ActionEvent ae) -> {
                            FadeOutTransition.fadeOutComponent(errorLabel,
                                    1000, MenuExplore);
                            MenuExplore.revalidate();
                            MenuExplore.repaint();

                        });
                        timer.setRepeats(false);
                        timer.start();
                        MenuExplore.revalidate();
                        MenuExplore.repaint();
                        txtProductName.setText("");
                        txtPrice.setText("");
                        txtQuantity.setText("");
                        txtDescription.setText("");
                        imageLabel.setText("Choose an Image");
                    } else if (AddProduct(product)) {
                        if (MenuExplore.getComponentCount() >= 9) {
                            MenuExplore.remove(8);
                        }
                        JLabel errorLabel = new JLabel("Product Added "
                                + "Successfully");
                        errorLabel.setFont(new Font("sansserif", Font.BOLD,
                                15));
                        errorLabel.setForeground(new Color(164, 113, 230));
                        MenuExplore.add(errorLabel);

                        Timer timer = new Timer(2000, (ActionEvent ae) -> {
                            FadeOutTransition.fadeOutComponent(errorLabel,
                                    1000, MenuExplore);
                            MenuExplore.revalidate();
                            MenuExplore.repaint();

                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    MenuExplore.revalidate();
                    MenuExplore.repaint();
                    txtProductName.setText("");
                    txtPrice.setText("");
                    txtQuantity.setText("");
                    txtDescription.setText("");
                    imageLabel.setText("Choose an Image");
                } catch (IOException ex) {
                    Logger.getLogger(SellerPage.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }

        });
        btnBack.addActionListener((ActionEvent e) -> {
            initialPage(seller);
        });

        // Refresh the layout
        MenuExplore.revalidate();
        MenuExplore.repaint();
    }

    public static boolean ProductExists(Product product) {

        String JDBC_URL = "jdbc:mysql://localhost:3306/myDb";
        String USERNAME = "root";
        String PASSWORD = "1234";

        try (Connection connection = DriverManager.getConnection(
                JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM Product WHERE CompanyEmail = ?"
                    + " AND ProductName = ? AND ProductPrice = ? "
                    + "AND ProductDescription = ?";
            PreparedStatement statement = connection.prepareStatement(
                    query);
            statement.setString(1, product.sellerEmail);
            statement.setString(2, product.productName);
            statement.setString(3, product.productPrice);
            statement.setString(4, product.productDescription);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("here");
                String existingQuantity = resultSet.getString(
                        "ProductQuantity");
                String newQuantity = Integer.toString(Integer.parseInt(
                        existingQuantity) + Integer.parseInt(
                                product.productQuantity));

                // Update the quantity in the database
                String updateQuery = "UPDATE Product SET productQuantity = ?"
                        + " WHERE CompanyEmail = ? AND ProductName = ?"
                        + " AND ProductPrice = ? AND ProductDescription = ?";
                PreparedStatement updateStatement = connection.prepareStatement(
                        updateQuery);
                updateStatement.setString(1, newQuantity);
                updateStatement.setString(2, product.sellerEmail);
                updateStatement.setString(3, product.productName);
                updateStatement.setString(4, product.productPrice);
                updateStatement.setString(5, product.productDescription);
                updateStatement.executeUpdate();
                return true;

            }
        } catch (SQLException e) {
            return false;
        }

        return false;

    }

    public static boolean AddProduct(Product product) throws IOException {

        String url = "jdbc:mysql://localhost:3306/myDb";
        String username = "root";
        String password = "1234";

        try {

            Connection connection = DriverManager.getConnection(url,
                    username, password);

            String query = "INSERT INTO product (CompanyEmail, ProductName,"
                    + " ProductPrice, ProductQuantity, ProductDescription,"
                    + " Image, Sold) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.sellerEmail);
            statement.setString(2, product.productName);
            statement.setString(3, product.productPrice);
            statement.setString(4, product.productQuantity);
            statement.setString(5, product.productDescription);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(product.image, "jpg", baos);
            byte[] imageData = baos.toByteArray();

            statement.setBytes(6, imageData);
            statement.setString(7, "0");

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public void initialPage(sellerId seller) {

        MenuExplore.removeAll();

        MigLayout layout = new MigLayout("wrap", "push[center]push",
                "push[]10[]push");
        MenuExplore.setLayout(layout);
        JLabel companyNameJLabel = new JLabel(seller.companyName);
        companyNameJLabel.setFont(new Font("sansserif", 1, 35));
        companyNameJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(companyNameJLabel);

        JLabel emailJLabel = new JLabel(seller.email);
        emailJLabel.setFont(new Font("sansserif", 1, 35));
        emailJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(emailJLabel);

        MenuExplore.revalidate();
        MenuExplore.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        MenuItem = new javax.swing.JPanel();
        MenuExplore = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setOpaque(true);
        jLayeredPane2.setPreferredSize(new java.awt.Dimension(1000, 625));

        MenuItem.setBackground(new java.awt.Color(255, 255, 255));
        MenuItem.setPreferredSize(new java.awt.Dimension(300, 625));

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
        MenuExplore.setPreferredSize(new java.awt.Dimension(700, 625));

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

        jLayeredPane2.setLayer(MenuItem, javax.swing.JLayeredPane.PALETTE_LAYER);
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
            .addComponent(MenuItem, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addComponent(MenuExplore, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(SellerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new SellerPage(new sellerId("c", "b", "c")).
                    setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuExplore;
    private javax.swing.JPanel MenuItem;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables

}
