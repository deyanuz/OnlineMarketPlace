
package panelpackage;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Timer;
import signinpage.CustomerPage;
import signinpage.LoginForm;

public final class PanelLoginRegister extends javax.swing.JLayeredPane {

    public PanelLoginRegister(LoginForm L){
        initComponents();
        initRegister();
        initLogin(L);
        login.setVisible(false);
        register.setVisible(true);
        
    }
    
    public void initRegister(){
    register.removeAll();
    register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]push"));
    JLabel label = new JLabel("Registeration Panel");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    register.add(label);
    
    Button cmdCustomer = new Button("Customer Register");
    cmdCustomer.setBackground(new Color(230,230,250));
    cmdCustomer.setForeground(new Color(164, 113, 230));
    register.add(cmdCustomer, "w 40%, h 40");
    cmdCustomer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerCustomer();
        }
    });
    
    Button cmdSeller = new Button("Seller Register");
    cmdSeller.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerSeller();
        }
    });
    cmdSeller.setBackground(new Color(230,230,250));
    cmdSeller.setForeground(new Color(164, 113, 230));
    register.add(cmdSeller, "w 40%, h 40");
}
    
    public void registerCustomer(){
    register.removeAll();
    
    register.setLayout(new MigLayout("wrap", "push[center]push", "50[]20[]7[]7[]7[]7[]15[]10[]10"));
    JLabel label = new JLabel("Customer Registration");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    register.add(label, "span, center, wrap");
    
    MyTextField txtName = new MyTextField();
    txtName.setPrefixIcon(new ImageIcon(getClass().getResource("user.png")));
    txtName.setHint("Name");
    register.add(txtName, "w 60%, wrap");
    
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("mail.png")));
    txtEmail.setHint("Email");
    register.add(txtEmail, "w 60%, wrap");
    
    MyPasswordField txtPass = new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
    txtPass.setHint("Password");
    register.add(txtPass, "w 60%, wrap");
    
    MyTextField txtSecurityQuestion = new MyTextField();
    txtSecurityQuestion.setPrefixIcon(new ImageIcon(getClass().getResource("sq.png")));
    txtSecurityQuestion.setHint("Security Question");
    register.add(txtSecurityQuestion, "w 60%, wrap");
    
    MyTextField txtSecurityQuestionAnswer = new MyTextField();
    txtSecurityQuestionAnswer.setPrefixIcon(new ImageIcon(getClass().getResource("sqa.png")));
    txtSecurityQuestionAnswer.setHint("Security Question Answer");
    register.add(txtSecurityQuestionAnswer, "w 60%, wrap");
    
    Button btnRegister = new Button("Register");
    btnRegister.setBackground(new Color(230,230,250));
    btnRegister.setForeground(new Color(164, 113, 230));
    btnRegister.addActionListener((ActionEvent ae) -> {
        registerCustomerToDatabase(txtName.getText(), txtEmail.getText(), String.valueOf(txtPass.getPassword()),txtSecurityQuestion.getText(),txtSecurityQuestionAnswer.getText());
    });
    
    register.add(btnRegister, "w 40%, h 35, align center");

    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    btnBack.addActionListener((ActionEvent ae) -> {
        initRegister(); 
        register.revalidate();
        register.repaint();
        
    });
    register.add(btnBack, "w 40%, h 35, align center");
    
    register.revalidate();
    register.repaint();
}
    
    public void registerCustomerToDatabase(String name, String email, String password, String securityQuestion, String securityQuestionAnswer) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb","root","1234");

        String sql = "INSERT INTO customer (Name, Email, Password, Security_Question, Security_Question_Answer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, password);
        stmt.setString(4, securityQuestion);
        stmt.setString(5, securityQuestionAnswer);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            successfulRegistration();
        } else {
            System.out.println("Registration failed.");
        }

        conn.close();
        
    } catch (SQLException e) {
    }
}
    
    public void successfulRegistration(){
    register.removeAll();
    
    register.setLayout(new MigLayout("wrap", "push[center]push", "125[]25[]10[]10[]15[]"));
    JLabel label = new JLabel("Registration Successful");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    register.add(label, "span, center, wrap");
    
    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    btnBack.addActionListener((ActionEvent ae) -> {
        initRegister(); 
        register.revalidate();
        register.repaint();
        
    });
    register.add(btnBack, "w 40%, h 40, align center");
    register.revalidate();
    register.repaint();
    }
    
    public void registerSeller(){

    register.removeAll();
    
    register.setLayout(new MigLayout("wrap", "push[center]push", "25[]15[]7[]7[]7[]7[]7[]10"));
    JLabel label = new JLabel("Seller Registration");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    register.add(label, "span, center, wrap");
    
    MyTextField txtName = new MyTextField();
    txtName.setPrefixIcon(new ImageIcon(getClass().getResource("user.png")));
    txtName.setHint("Shop Name");
    register.add(txtName, "w 60%, wrap");
    
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("mail.png")));
    txtEmail.setHint("Email");
    register.add(txtEmail, "w 60%, wrap");
    
    MyTextField txtLocation = new MyTextField();
    txtLocation.setPrefixIcon(new ImageIcon(getClass().getResource("location.png")));
    txtLocation.setHint("Location");
    register.add(txtLocation, "w 60%, wrap");
    
    MyPasswordField txtPass = new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
    txtPass.setHint("Password");
    register.add(txtPass, "w 60%, wrap");
    
    MyTextField txtSecurityQuestion = new MyTextField();
    txtSecurityQuestion.setPrefixIcon(new ImageIcon(getClass().getResource("sq.png")));
    txtSecurityQuestion.setHint("Security Question");
    register.add(txtSecurityQuestion, "w 60%, wrap");
    
    MyTextField txtSecurityQuestionAnswer = new MyTextField();
    txtSecurityQuestionAnswer.setPrefixIcon(new ImageIcon(getClass().getResource("sqa.png")));
    txtSecurityQuestionAnswer.setHint("Security Question Answer");
    register.add(txtSecurityQuestionAnswer, "w 60%, wrap");
    
    Button btnRegister = new Button("Register");
    btnRegister.setBackground(new Color(230,230,250));
    btnRegister.setForeground(new Color(164, 113, 230));
    btnRegister.addActionListener((ActionEvent ae) -> {
    registerSellerToDatabase(txtName.getText(), txtEmail.getText(), txtLocation.getText(), String.valueOf(txtPass.getPassword()),txtSecurityQuestion.getText(),txtSecurityQuestionAnswer.getText());
    });
    
    register.add(btnRegister, "w 40%, h 35, align center");
    
    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    
    btnBack.addActionListener((ActionEvent ae) -> {
        initRegister(); 
        register.revalidate();
        register.repaint();
        
    });
    register.add(btnBack, "w 40%, h 35, align center");
    register.revalidate();
    register.repaint();
}
    
    public void registerSellerToDatabase(String shopName, String email, String location, String password, String securityQuestion, String securityQuestionAnswer) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb","root","1234");

        String sql = "INSERT INTO seller ( Email, Location, Password, Security_Question, Security_Question_Answer,ShopName) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, email);
        stmt.setString(2, location);
        stmt.setString(3, password);
        stmt.setString(4, securityQuestion);
        stmt.setString(5, securityQuestionAnswer);
        stmt.setString(6, shopName);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            successfulRegistration();
        } else {
            System.out.println("Registration failed.");
        }

        conn.close();
    } catch (SQLException e) {
    }
}
    
    public void initLogin(LoginForm L){
    login.removeAll();
    login.setLayout(new MigLayout("wrap", "push[center]push", "125[]25[]10[]10[]15[]"));
    JLabel label = new JLabel("Login Panel");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    login.add(label, "span, center, wrap");
   
    Button cmd = new Button("Customer Login");
    cmd.setBackground(new Color(230,230,250));
    cmd.setForeground(new Color(164, 113, 230));
    cmd.addActionListener((ActionEvent e) -> {
        loginCustomer(L);
    });
    
    Button cmdSeller = new Button("Seller Login");
    cmdSeller.setBackground(new Color(230,230,250));
    cmdSeller.setForeground(new Color(164, 113, 230));
    cmdSeller.addActionListener((ActionEvent e) -> {
        loginSeller(L);
    });
    
    Button cmdAdmin = new Button("Admin Login");
    cmdAdmin.setBackground(new Color(230,230,250));
    cmdAdmin.setForeground(new Color(164, 113, 230));
    cmdAdmin.addActionListener((ActionEvent e) -> {
        loginAdmin(L);
    });
    
    login.add(cmd, "w 40%, h 40");
    login.add(cmdSeller, "w 40%, h 40");
    login.add(cmdAdmin, "w 40%, h 40");
    }

    public void loginCustomer(LoginForm L){
    
    login.removeAll();
    
    login.setLayout(new MigLayout("wrap", "push[center]push", "100[]25[]10[]25[]10[]15"));
    JLabel label = new JLabel("Customer Login");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    login.add(label, "span, center, wrap");
    
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("mail.png")));
    txtEmail.setHint("Email");
    login.add(txtEmail, "w 60%, wrap");
    
    MyPasswordField txtPass = new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
    txtPass.setHint("Password");
    login.add(txtPass, "w 60%, wrap");
    
    MouseListener l= new  MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String email = txtEmail.getText();
            PasswordReset(email,"customer",L);
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
    };
    
    JLabel errorLabel = new JLabel("Forgot Password? Enter Email And CLICK HERE");
    errorLabel.setFont(new Font("sansserif", Font.BOLD, 10));
    errorLabel.setForeground(new Color(164, 113, 230));
    errorLabel.addMouseListener(l);
    
    Button btnSignIn = new Button("Sign In");
    btnSignIn.setBackground(new Color(230,230,250));
    btnSignIn.setForeground(new Color(164, 113, 230));
    btnSignIn.addActionListener((ActionEvent ae) -> {
    String email = txtEmail.getText();
    String password = String.valueOf(txtPass.getPassword());
    
    if (verifyCustomer(email, password)) {
        login.removeAll();
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("sansserif", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(164, 113, 230));
        login.add(welcomeLabel, "span, center, wrap");
        login.revalidate();
        login.repaint();
        } else {
        errorLabel.setText("Incorrect Email or Password. Forgot Password?");
        errorLabel.setFont(new Font("sansserif", Font.BOLD, 10));
        errorLabel.setForeground(new Color(164, 113, 230));
        
        errorLabel.addMouseListener(l);
                
        login.add(errorLabel, "span, center, wrap");
        login.revalidate();
        login.repaint();
        }
    });
    login.add(btnSignIn, "w 40%, h 40, align center, wrap");

    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    btnBack.addActionListener((ActionEvent ae) -> {
        initLogin(L); 
        login.revalidate();
        login.repaint();
    });
    login.add(btnBack, "w 40%, h 40, align center");
    login.add(errorLabel, "span, center, wrap");
    login.revalidate();
    login.repaint();
    }
    
    public boolean verifyCustomer(String email, String password) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "root", "1234");

        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        boolean customerExists = rs.next();

        conn.close();

        return customerExists;
    } catch (SQLException e) {
        return false;
    }
    }
    
    public void loginSeller(LoginForm L) {

    login.removeAll();
    
    login.setLayout(new MigLayout("wrap", "push[center]push", "100[]25[]10[]25[]10[]15"));
    JLabel label = new JLabel("Seller Login");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    login.add(label, "span, center, wrap");
    
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("mail.png")));
    txtEmail.setHint("Email");
    login.add(txtEmail, "w 60%, wrap");
    
    MyPasswordField txtPass = new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
    txtPass.setHint("Password");
    login.add(txtPass, "w 60%, wrap");
    
    MouseListener l= new  MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String email = txtEmail.getText();
            PasswordReset(email,"seller",L);
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
    };
    
    JLabel errorLabel = new JLabel("Forgot Password? Enter Email And CLICK HERE");
    errorLabel.setFont(new Font("sansserif", Font.BOLD, 10));
    errorLabel.setForeground(new Color(164, 113, 230));
    errorLabel.addMouseListener(l);
    
    Button btnSignIn = new Button("Sign In");
    btnSignIn.setBackground(new Color(230,230,250));
    btnSignIn.setForeground(new Color(164, 113, 230));
    btnSignIn.addActionListener((ActionEvent ae) -> {
    String email = txtEmail.getText();    
    String password = String.valueOf(txtPass.getPassword());
    if (verifySeller(email, password)) {
        
        L.setVisible(false);
        new CustomerPage().setVisible(true);
        
        } else {
        errorLabel.setText("Incorrect Email or Password. Forgot Password?");
        errorLabel.setFont(new Font("sansserif", Font.BOLD, 10));
        errorLabel.setForeground(new Color(164, 113, 230));
        
        errorLabel.addMouseListener(l);
                
        login.add(errorLabel, "span, center, wrap");
        login.revalidate();
        login.repaint();
        }
    });
    login.add(btnSignIn, "w 40%, h 40, align center, wrap");
    
    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    btnBack.addActionListener((ActionEvent ae) -> {
        initLogin(L); 
        login.revalidate();
        login.repaint();
    });
    login.add(btnBack, "w 40%, h 40, align center");
    login.add(errorLabel, "span, center, wrap");
    login.revalidate();
    login.repaint();
    }
    
    public boolean verifySeller(String email, String password) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "root", "1234");

        String sql = "SELECT * FROM seller WHERE email = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        boolean sellerExists = rs.next();

        conn.close();

        return sellerExists;
    } catch (SQLException e) {
        return false;
    }
    }
    
    public void loginAdmin(LoginForm L) {

    login.removeAll();

    login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
    JLabel label = new JLabel("Admin Login");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(164, 113, 230));
    login.add(label, "span, center, wrap");
    
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("mail.png")));
    txtEmail.setHint("Email");
    login.add(txtEmail, "w 60%, wrap");
    
    MyPasswordField txtPass = new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
    txtPass.setHint("Password");
    login.add(txtPass, "w 60%, wrap");
    
    Button btnBack = new Button("Back");
    btnBack.setBackground(new Color(230,230,250));
    btnBack.setForeground(new Color(164, 113, 230));
    btnBack.addActionListener((ActionEvent ae) -> {
        initLogin(L); 
        login.revalidate();
        login.repaint();
    });
    login.add(btnBack, "w 40%, h 40, align center");
    
    login.revalidate();
    login.repaint();
}
    
    public void PasswordReset(String email, String user, LoginForm L){
        
        login.removeAll();
        login.setLayout(new MigLayout("wrap", "push[center]push", "150[]15[]15[]25[]15[]"));
        // Display Security Question
            String securityQuestion = getSecurityQuestion(email,user);
            if(securityQuestion==null){
                AccountDoesNotExist(user,L);
                return;
            }
            JLabel securityQuestionLabel = new JLabel("Answer Correctly To Reset Password");
            securityQuestionLabel.setFont(new Font("sansserif", Font.BOLD, 15));
            securityQuestionLabel.setForeground(new Color(164, 113, 230));
            
            JLabel questionLabel = new JLabel(securityQuestion);
            questionLabel.setFont(new Font("sansserif", Font.BOLD, 20));
            questionLabel.setForeground(new Color(164, 113, 230));
            
            login.add(securityQuestionLabel, "span, center, wrap");
            login.add(questionLabel, "span, center, wrap");

            MyTextField txtAnswer = new MyTextField();
            txtAnswer.setHint("Answer");
            login.add(txtAnswer, "w 60%, wrap");

            // Verify Security Question Answer
            Button btnVerifyAnswer = new Button("Verify Answer");
            btnVerifyAnswer.setBackground(new Color(230,230,250));
            btnVerifyAnswer.setForeground(new Color(164, 113, 230));
            btnVerifyAnswer.addActionListener((ActionEvent ae2) -> {
            String answer = txtAnswer.getText();
             if (verifySecurityAnswer(email, answer, user)) {
                login.removeAll();
                login.setLayout(new MigLayout("wrap", "push[center]push", "150[]25[]10[]25[]10[]15"));
                JLabel label = new JLabel("Enter New Password");
                label.setFont(new Font("sansserif", 1, 30));
                label.setForeground(new Color(164, 113, 230));
                login.add(label, "span, center, wrap");
                MyPasswordField txtNewPassword = new MyPasswordField();
                txtNewPassword.setPrefixIcon(new ImageIcon(getClass().getResource("pass.png")));
                txtNewPassword.setHint("New Password");
                login.add(txtNewPassword, "w 60%, wrap");

                Button btnResetPassword = new Button("Reset Password");
                btnResetPassword.setBackground(new Color(230,230,250));
                btnResetPassword.setForeground(new Color(164, 113, 230));
                btnResetPassword.addActionListener((ActionEvent ae3) -> {
                    String newPassword = String.valueOf(txtNewPassword.getPassword());
                    resetPassword(email, newPassword, user,L);   
                });
                login.add(btnResetPassword, "w 40%, h 40, align center, wrap");
                } else {
                    if(login.getComponentCount()>=6){
                        login.remove(5);
                    }
                    JLabel errorLabel = new JLabel("Incorrect Answer");
                    errorLabel.setFont(new Font("sansserif", Font.BOLD, 15));
                    errorLabel.setForeground(new Color(164, 113, 230));
                    login.add(errorLabel, "span, center, wrap");
                   
                    Timer timer = new Timer(2000, (ActionEvent e) -> {
                        FadeOutTransition.fadeOutComponent(errorLabel, 1000,login); 
                        login.revalidate();
                        login.repaint();
                        
                    });
                    timer.setRepeats(false); 
                    timer.start();
                }
                login.revalidate();
                login.repaint();
            });
            login.add(btnVerifyAnswer, "w 40%, h 40, align center, wrap");
            
        
            Button btnBack = new Button("Back");
            btnBack.setBackground(new Color(230,230,250));
            btnBack.setForeground(new Color(164, 113, 230));
            btnBack.addActionListener((ActionEvent ae) -> {
                init(user,L); 
                login.revalidate();
                login.repaint();
            });
        login.add(btnBack,"w 40%, h 40, align center");
        login.revalidate();
        login.repaint();
    }
    
    public String getSecurityQuestion(String email, String user) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "root", "1234");

        String sql = "SELECT Security_Question FROM "+user+" WHERE Email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();
        String securityQuestion = null;

        if (rs.next()) {
            securityQuestion = rs.getString("Security_Question");
        }

        conn.close();

        return securityQuestion;
    } catch (SQLException e) {
        return null; // Handle the exception appropriately in your application
    }
    }

    public boolean verifySecurityAnswer(String email, String answer, String user) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "root", "1234");

        String sql = "SELECT Security_Question_Answer FROM "+user+" WHERE Email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();
        String correctAnswer = null;

        if (rs.next()) {
            correctAnswer = rs.getString("Security_Question_Answer");
        }

        conn.close();

        return answer.equalsIgnoreCase(correctAnswer);
    } catch (SQLException e) {
        return false; // Handle the exception appropriately in your application
    }
    }

    public void init(String user,LoginForm L){
        if(user=="seller"){
            loginSeller(L);
            } else if(user=="customer"){
                loginCustomer(L);
            }else{
                loginAdmin(L);
            }
    }
    
    public void AccountDoesNotExist(String user,LoginForm L){
        login.removeAll();
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]15[]25[]push"));       
        JLabel accountDoesnotExist = new JLabel("Account Does Not Exist");
        accountDoesnotExist.setFont(new Font("sansserif", Font.BOLD, 30));
        accountDoesnotExist.setForeground(new Color(164, 113, 230));
        login.add(accountDoesnotExist,"w 40%, h 40, align center");
        
        Button btnBack = new Button("Back");
            btnBack.setBackground(new Color(230,230,250));
            btnBack.setForeground(new Color(164, 113, 230));
            btnBack.addActionListener((ActionEvent ae) -> {
                init(user,L); 
                login.revalidate();
                login.repaint();
            });
        login.add(btnBack,"w 40%, h 40, align center");
        login.revalidate();
        login.repaint();
    }
    
    public void resetPassword(String email, String newPassword, String user,LoginForm L) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "root", "1234");

        String sql = "UPDATE "+user+" SET Password = ? WHERE Email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, newPassword);
        stmt.setString(2, email);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            
            login.removeAll();
            
            JLabel successLabel = new JLabel("Password Reset Successful!");
            successLabel.setFont(new Font("sansserif", Font.BOLD, 30));
            successLabel.setForeground(new Color(164, 113, 230));
            login.add(successLabel, "span, center, wrap");
            
            Button btnBack = new Button("Back");
            btnBack.setBackground(new Color(230,230,250));
            btnBack.setForeground(new Color(164, 113, 230));
            btnBack.addActionListener((ActionEvent ae) -> {
                init(user,L);
                login.revalidate();
                login.repaint();
            });
            login.add(btnBack, "w 40%, h 40, align center");
            login.revalidate();
            login.repaint();
        } else {
        }

        conn.close();
    } catch (SQLException e) {
        // Handle the exception appropriately in your application
        
    }
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setForeground(new java.awt.Color(255, 255, 255));
        setOpaque(true);
        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card2");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card3");
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}