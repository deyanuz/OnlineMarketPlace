
package signinpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import java.text.DecimalFormat;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import panelpackage.PanelCover;
import panelpackage.PanelLoginRegister;

public class LoginForm extends javax.swing.JFrame {
    
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginRegister loginRegister;
    private final double addSize=30;
    private final double coverSize=40;
    private final double loginSize=60;
    public boolean isLogin;
    private final DecimalFormat df=new DecimalFormat("##0.###");

    public LoginForm() {
        initComponents();
        init(this);
    }
    public void init(LoginForm L){
        layout=new MigLayout("fill,insets 0");
        cover=new PanelCover();
        loginRegister=new PanelLoginRegister(L);
         TimingTarget target=new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                
                double fractionCover;
                double fractionLogin;
                double size=coverSize;
                if(fraction<=0.5f){
                    size+=fraction*addSize;                  
                }else{
                    size+=addSize-fraction*addSize;
                }
                if(isLogin){
                    fractionCover=1f-fraction;
                    fractionLogin=fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                }else{
                    fractionCover=fraction;
                    fractionLogin=1f-fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginRegister.showRegister(isLogin);
                }
                
                fractionCover=Double.parseDouble(df.format(fractionCover));
                fractionLogin=Double.parseDouble(df.format(fractionLogin));
                layout.setComponentConstraints(cover,"width "+size+"%, pos "+fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginRegister,"width "+loginSize+"%, pos "+fractionLogin + "al 0 n 100%");
                background.revalidate();
            }
            
            public void end(){
                isLogin=!isLogin;
            }
                    
         };
         
        Animator animator=new Animator(800,target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        background.setLayout(layout);
        background.add(cover,"width "+coverSize+"%, pos 0al 0 n 100%");
        background.add(loginRegister,"width "+loginSize+"%, pos 1al 0 n 100%");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setOpaque(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginForm L=new LoginForm();
                L.setVisible(true);
            }
            public void close(LoginForm L){
                L.setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    // End of variables declaration//GEN-END:variables
}
