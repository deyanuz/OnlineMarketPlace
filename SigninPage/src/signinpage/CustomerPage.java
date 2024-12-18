package signinpage;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import panelpackage.ButtonOutLine;
import panelpackage.CustomerProductFrame;
import panelpackage.Custompanel;
import panelpackage.HistoryFrame;
import panelpackage.MyTextField;
import panelpackage.OrderedProductFrame;
import panelpackage.Product;
import panelpackage.customerId;
import static signinpage.SellerPage.decodeImage;

public final class CustomerPage extends javax.swing.JFrame {

    public ArrayList<Product> cart = new ArrayList<>();

    public CustomerPage(customerId customer) {
        initComponents();
        initMenuItem(customer, this);
        initialPage(customer);
    }

    public void initMenuItem(customerId customer, CustomerPage cp) {
        MenuItem.setBackground(new Color(176, 156, 200));
        MigLayout layout = new MigLayout("wrap", "push[center]push",
                "push[]25[]10[]10[]10[]10[]push");
        MenuItem.setLayout(layout);

        String Icon;
        if (customer.customerGender.strip() == null) {
            Icon = "Custom.png";
        } else {
            Icon = customer.customerGender.strip() + ".png";
        }

        JLabel title = new JLabel("");
        title.setFont(new Font("sansserif", 1, 35));
        title.setForeground(new Color(245, 245, 245));
        title.setIcon(new ImageIcon(getClass().
                getResource(Icon)));

        title.setBounds(50, 20, 200, 50);
        MenuItem.add(title);

        ButtonOutLine browseProducButton = new ButtonOutLine();
        browseProducButton.setText("Browse Products");
        browseProducButton.setBackground(new Color(164, 113, 230));
        browseProducButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(browseProducButton, "w 80%, h 40");

        browseProducButton.addActionListener((ActionEvent e) -> {
            ShowAllProducts(customer, cp);
        });

        ButtonOutLine viewOrderButton = new ButtonOutLine();
        viewOrderButton.setText("View Order");
        viewOrderButton.setBackground(new Color(164, 113, 230));
        viewOrderButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(viewOrderButton, "w 80%, h 40");

        viewOrderButton.addActionListener((ActionEvent e) -> {

            ViewOrderedProducts(cp, customer);
        });

        ButtonOutLine historyButton = new ButtonOutLine();
        historyButton.setText("History");
        historyButton.setBackground(new Color(164, 113, 230));
        historyButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(historyButton, "w 80%, h 40");

        historyButton.addActionListener((ActionEvent e) -> {
            ShowHistory(customer, cp);
        });

        ButtonOutLine logoutButton = new ButtonOutLine();
        logoutButton.setText("Log Out");
        logoutButton.setBackground(new Color(164, 113, 230));
        logoutButton.setForeground(new Color(230, 230, 250));
        MenuItem.add(logoutButton, "w 80%, h 40");

        logoutButton.addActionListener((e) -> {
            JFrame myframe = new JFrame();
            JLabel j = new JLabel("Are You Sure?");
            myframe.setSize(300, 200);
            JButton okButton = new JButton("  OK  ");
            JButton cancelButton = new JButton("Cancel");
            okButton.addActionListener((ActionEvent e1) -> {
                CustomerPage.this.setVisible(false);
                new LoginForm().setVisible(true);
                myframe.dispose();
            });
            cancelButton.addActionListener((ActionEvent e1) -> {
                myframe.dispose();
            });
            JPanel panel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[center]push"));
            panel.add(j, "span, wrap");
            panel.add(okButton);
            panel.add(cancelButton);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = (int) ((screenSize.getWidth() - myframe.getWidth()) / 2);
            int centerY = (int) ((screenSize.getHeight() - myframe.getHeight()) / 2);
            myframe.setLocation(centerX, centerY);
            myframe.add(panel);
            myframe.setUndecorated(true);
            myframe.setVisible(true);

        });
    }

    public void ShowHistory(customerId customer, CustomerPage cp) {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,
                BoxLayout.PAGE_AXIS));

        JLabel historyLabel = new JLabel("History");
        historyLabel.setFont(new Font("serif", 1, 35));
        historyLabel.setForeground(new Color(150, 18, 196));

        ArrayList<Product> products = GetHistory(customer);

        // Add components to the panel
        for (int i = 0; i < products.size(); i++) {
            //System.out.println(products.get(i).productName);
            Product product = products.get(i);
            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[center]push"));
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
                    cp.setVisible(false);
                    HistoryFrame pf = new HistoryFrame(product, cp);
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

        if (contentPanel.getComponentCount() == 0) {

            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[]push", "150[center]push"));
            customPanel.setPreferredSize(new Dimension(350, 30));
            customPanel.setBackground(new Color(230, 230, 250));
            JLabel jj = new JLabel("No Previous Orders");
            jj.setFont(new Font("serif", 1, 30));
            jj.setForeground(new Color(164, 113, 230));
            customPanel.add(jj, "span, wrap");
            contentPanel.add(customPanel, "w 60%, h 40, fill");
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 350));

        MenuExplore.removeAll();
        MenuExplore.add(historyLabel);
        scrollPane.setBorder(null);

        MenuExplore.add(scrollPane, "span, center, wrap");
        MenuExplore.revalidate();
        MenuExplore.repaint();
    }

    public void ViewOrderedProducts(CustomerPage cp, customerId customer) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,
                BoxLayout.PAGE_AXIS));
        JLabel allProductLabel = new JLabel("Ordered Products");
        allProductLabel.setFont(new Font("serif", 1, 35));
        allProductLabel.setForeground(new Color(150, 18, 196));

        for (int i = 0; i < cart.size(); i++) {
            //System.out.println(products.get(i).productName);
            Product product = cart.get(i);
            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[center]push"));
            customPanel.setPreferredSize(new Dimension(350, 30));
            customPanel.setBackground(new Color(230, 230, 250));
            JLabel j = new JLabel(cart.get(i).productName);

            BufferedImage resizedImage = new BufferedImage(20, 20,
                    cart.get(i).image.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(cart.get(i).image, 0, 0, 20,
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
                    cp.setVisible(false);
                    OrderedProductFrame pf = new OrderedProductFrame(product, cp, customer);
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

        if (contentPanel.getComponentCount() == 0) {

            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[]push", "150[center]push"));
            customPanel.setPreferredSize(new Dimension(350, 30));
            customPanel.setBackground(new Color(230, 230, 250));
            JLabel jj = new JLabel("No Products Ordered");
            jj.setFont(new Font("serif", 1, 30));
            jj.setForeground(new Color(164, 113, 230));
            customPanel.add(jj, "span, wrap");
            contentPanel.add(customPanel, "w 60%, h 40, fill");
        }

        JPanel optionPanel = new JPanel(new MigLayout("wrap 2", "20[]20[]20",
                "7[]1"));
        optionPanel.setBackground(new Color(255, 255, 255));

        Button removeAllButton = new Button("Remove All");
        removeAllButton.setBackground(new Color(230, 230, 250));
        removeAllButton.setForeground(new Color(164, 113, 230));

        removeAllButton.addActionListener((ActionEvent e) -> {
            cart.clear();

            ViewOrderedProducts(cp, customer);
        });

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setBackground(new Color(230, 230, 250));
        placeOrderButton.setForeground(new Color(164, 113, 230));
        optionPanel.add(removeAllButton, "w 80%, h 40");
        optionPanel.add(placeOrderButton, "w 80%, h 40");

        placeOrderButton.addActionListener((ActionEvent e) -> {
            JFrame myframe = new JFrame();
            JLabel j = new JLabel("Are You Sure?");
            myframe.setSize(300, 200);
            JButton okButton = new JButton("  OK  ");
            JButton cancelButton = new JButton("Cancel");
            okButton.addActionListener((ActionEvent e1) -> {
                try {
                    PlaceOrder(cp, customer);
                } catch (IOException ex) {
                    Logger.getLogger(CustomerPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                myframe.dispose();
            });
            cancelButton.addActionListener((ActionEvent e1) -> {
                myframe.dispose();
            });
            JPanel panel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[center]push"));
            panel.add(j, "span, wrap");
            panel.add(okButton);
            panel.add(cancelButton);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = (int) ((screenSize.getWidth() - myframe.getWidth()) / 2);
            int centerY = (int) ((screenSize.getHeight() - myframe.getHeight()) / 2);
            myframe.setLocation(centerX, centerY);
            myframe.add(panel);
            myframe.setUndecorated(true);
            myframe.setVisible(true);
        });

        MenuExplore.removeAll();
        scrollPane.setBorder(null);
        MenuExplore.add(allProductLabel);
        MenuExplore.add(scrollPane, "span, center, wrap");
        if (!cart.isEmpty()) {
            MenuExplore.add(optionPanel, "span, center, wrap");
        }
        MenuExplore.revalidate();
        MenuExplore.repaint();
    }

    public void PlaceOrder(CustomerPage cp, customerId customer) throws IOException {
        for (Product i : cart) {
            String url = "jdbc:mysql://localhost:3306/myDb";
            String username = "root";
            String password = "1234";

            try {

                Connection connection = DriverManager.getConnection(url,
                        username, password);

                String query = "INSERT INTO `order` (CustomerEmail, CompanyEmail, ProductName,"
                        + " ProductPrice, ProductQuantity, ProductDescription,"
                        + " Image, Shiped) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                // Prepare the statement
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, customer.customerEmail);
                statement.setString(2, i.sellerEmail);
                statement.setString(3, i.productName);
                statement.setString(4, i.productPrice);
                statement.setString(5, i.productQuantity);
                statement.setString(6, i.productDescription);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(i.image, "jpg", baos);
                byte[] imageData = baos.toByteArray();

                statement.setBytes(7, imageData);
                statement.setString(8, "false");

                // Execute the query
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("here");
                    UpdateQuantity(i);
                }
            } catch (SQLException e) {
            }
        }
        cart.clear();
        initialPage(customer);
    }

    public static boolean UpdateQuantity(Product product) {

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
                String existingQuantity = resultSet.getString(
                        "ProductQuantity");
                String newQuantity = Integer.toString(Integer.parseInt(
                        existingQuantity) - Integer.parseInt(
                                product.productQuantity));

                String existingSold = resultSet.getString(
                        "Sold");
                String newSold = Integer.toString(Integer.parseInt(
                        existingSold) + Integer.parseInt(
                                product.productQuantity));


                // Update the quantity in the database
                String updateQuery = "UPDATE Product SET productQuantity = ? , Sold = ?"
                        + " WHERE CompanyEmail = ? AND ProductName = ?"
                        + " AND ProductPrice = ? AND ProductDescription = ? ";
                PreparedStatement updateStatement = connection.prepareStatement(
                        updateQuery);
                updateStatement.setString(1, newQuantity);
                updateStatement.setString(3, product.sellerEmail);
                updateStatement.setString(4, product.productName);
                updateStatement.setString(5, product.productPrice);
                updateStatement.setString(6, product.productDescription);
                updateStatement.setString(2, newSold);
                updateStatement.executeUpdate();
                return true;

            }
        } catch (SQLException e) {
            return false;
        }

        return false;

    }

    public void ShowAllProducts(customerId customer, CustomerPage cp) {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,
                BoxLayout.PAGE_AXIS));

        JPanel searchPanel = new JPanel(new MigLayout("wrap 2", "20[]20[]20",
                "7[]1"));
        searchPanel.setBackground(new Color(255, 255, 255));

        MyTextField searchProduct = new MyTextField();
        searchProduct.setHint("Product Name");

        searchProduct.setPrefixIcon(new ImageIcon(getClass().getResource("searchh.png")));
        Button searchButton = new Button("Search");
        searchPanel.add(searchProduct, "w 75%, h 50");
        searchButton.setBackground(new Color(230, 230, 250));
        searchButton.setForeground(new Color(164, 113, 230));

        ArrayList<Product> products = GetProducts();

        // Add components to the panel
        for (int i = 0; i < products.size(); i++) {
            //System.out.println(products.get(i).productName);
            Product product = products.get(i);
            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[center]push"));
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
                    cp.setVisible(false);
                    CustomerProductFrame pf = new CustomerProductFrame(product, cp);
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
        searchButton.addActionListener((ActionEvent e) -> {
            String name = searchProduct.getText();
            if ("".equals(name)) {

            } else {
                ShowSearchProducts(customer, cp, name);
            }
        });
        searchPanel.add(searchButton, "w 25%, h 50, wrap");

        MenuExplore.removeAll();
        MenuExplore.add(searchPanel, "w 90%, h 50, wrap");
        scrollPane.setBorder(null);

        MenuExplore.add(scrollPane, "span, center, wrap");
        MenuExplore.revalidate();
        MenuExplore.repaint();
    }

    public void ShowSearchProducts(customerId customer, CustomerPage cp, String productName) {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,
                BoxLayout.PAGE_AXIS));

        JPanel searchPanel = new JPanel(new MigLayout("wrap 2", "20[]20[]20",
                "7[]1"));
        searchPanel.setBackground(new Color(255, 255, 255));

        MyTextField searchProduct = new MyTextField();
        searchProduct.setHint("Product Name");

        searchProduct.setPrefixIcon(new ImageIcon(getClass().getResource("searchh.png")));
        Button searchButton = new Button("Search");
        searchPanel.add(searchProduct, "w 75%, h 50");
        searchButton.setBackground(new Color(230, 230, 250));
        searchButton.setForeground(new Color(164, 113, 230));

        ArrayList<Product> products = GetProducts();

        // Add components to the panel
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.productName.equals(productName)) {
                Custompanel customPanel = new Custompanel();
                customPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[center]push"));
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
                        cp.setVisible(false);
                        CustomerProductFrame pf = new CustomerProductFrame(product, cp);
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
        }
        if (contentPanel.getComponentCount() == 0) {

            Custompanel customPanel = new Custompanel();
            customPanel.setLayout(new MigLayout("wrap", "push[]push", "150[center]push"));
            customPanel.setPreferredSize(new Dimension(350, 30));
            customPanel.setBackground(new Color(230, 230, 250));
            JLabel jj = new JLabel("Product Does Not Exist");
            jj.setFont(new Font("serif", 1, 30));
            jj.setForeground(new Color(164, 113, 230));
            customPanel.add(jj, "span, wrap");
            contentPanel.add(customPanel, "w 60%, h 40, fill");
        }
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        searchButton.addActionListener((ActionEvent e) -> {
            String name = searchProduct.getText();
            ShowSearchProducts(customer, cp, name);
        });
        searchPanel.add(searchButton, "w 25%, h 50, wrap");

        MenuExplore.removeAll();
        MenuExplore.add(searchPanel, "w 90%, h 50, wrap");
        scrollPane.setBorder(null);
        MenuExplore.add(scrollPane, "span, center, wrap");
        MenuExplore.revalidate();
        MenuExplore.repaint();

    }

    public ArrayList<Product> GetHistory(customerId customer) {

        ArrayList<Product> products = new ArrayList<>();
        String JDBC_URL = "jdbc:mysql://localhost:3306/myDb";
        String USER = "root";
        String PASSWORD = "1234";

        try (Connection connection = DriverManager.getConnection(JDBC_URL,
                USER, PASSWORD)) {
            String query = "SELECT * FROM `order` ";
            try (PreparedStatement preparedStatement
                    = connection.prepareStatement(query)) {

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

                        // Convert and set the image
                        byte[] imageData = resultSet.getBytes("Image");
                        if (imageData != null) {
                            BufferedImage image = decodeImage(imageData);
                            product.image = image;
                        }
                        if ("true".equals(resultSet.getString(
                                "Shiped")) && customer.customerEmail.equals(resultSet.getString(
                                        "CustomerEmail"))) {
                            products.add(product);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }

        return products;
    }

    public ArrayList<Product> GetProducts() {

        ArrayList<Product> products = new ArrayList<>();
        String JDBC_URL = "jdbc:mysql://localhost:3306/myDb";
        String USER = "root";
        String PASSWORD = "1234";

        try (Connection connection = DriverManager.getConnection(JDBC_URL,
                USER, PASSWORD)) {
            String query = "SELECT * FROM product ";
            try (PreparedStatement preparedStatement
                    = connection.prepareStatement(query)) {

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

    public void initialPage(customerId customer) {

        MenuExplore.removeAll();

        MigLayout layout = new MigLayout("wrap", "push[left]push",
                "push[]20[]10[]10[]push");
        MenuExplore.setLayout(layout);
        
        JLabel titleJLabel = new JLabel("Customer Information");
        titleJLabel.setFont(new Font("serif", 3, 35));
        titleJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(titleJLabel);
        
        JLabel companyNameJLabel = new JLabel("Customer Name: "+customer.customerName);
        companyNameJLabel.setFont(new Font("serif", 2, 25));
        companyNameJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(companyNameJLabel);

        JLabel companyEmailJLabel = new JLabel("Customer Email: "+customer.customerEmail);
        companyEmailJLabel.setFont(new Font("serif", 2, 25));
        companyEmailJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(companyEmailJLabel);

        JLabel companyGenderJLabel = new JLabel("Customer Gender: "+customer.customerGender);
        companyGenderJLabel.setFont(new Font("serif", 2, 25));
        companyGenderJLabel.setForeground(new Color(164, 113, 230));
        MenuExplore.add(companyGenderJLabel);

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
        setUndecorated(true);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(() -> {
            new CustomerPage(new customerId("abc", "xyz",
                    "Female")).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuExplore;
    private javax.swing.JPanel MenuItem;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables

}
