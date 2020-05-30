/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Resources.InterfaceText;
import java.awt.Dialog;
import javax.swing.JDialog;

/**
 *
 * @author Elio
 */
public class MainMenu extends javax.swing.JFrame {
    private final HowToPlayDialog howToPlay = new HowToPlayDialog(this, true);
    private final CreditsDialog credits = new CreditsDialog(this, true);

    /**
     * Creates new form mainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newGameButton = new javax.swing.JButton();
        howToPlayButton = new javax.swing.JButton();
        creditsButton = new javax.swing.JButton();
        titleLabel1 = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();
        titleLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UniVSDino");

        newGameButton.setText(InterfaceText.newGameButton);
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        howToPlayButton.setText(InterfaceText.howToPlayButton);
        howToPlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howToPlayButtonActionPerformed(evt);
            }
        });

        creditsButton.setText(InterfaceText.creditsButton);
        creditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditsButtonActionPerformed(evt);
            }
        });

        titleLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 3, 36)); // NOI18N
        titleLabel1.setText("UNIBA");
        titleLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        titleLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        titleLabel2.setText("VS");

        titleLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 36)); // NOI18N
        titleLabel3.setText("DINOSAURS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titleLabel2)
                .addGap(18, 18, 18)
                .addComponent(titleLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(creditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(howToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(titleLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(titleLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(howToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(creditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void howToPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howToPlayButtonActionPerformed
        howToPlay.setVisible(true);
    }//GEN-LAST:event_howToPlayButtonActionPerformed

    private void creditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditsButtonActionPerformed
        credits.setVisible(true);
    }//GEN-LAST:event_creditsButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        AdventureGUI avventura = new AdventureGUI();
        this.dispose();
        avventura.setVisible(true);
    }//GEN-LAST:event_newGameButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creditsButton;
    private javax.swing.JButton howToPlayButton;
    private javax.swing.JButton newGameButton;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    private javax.swing.JLabel titleLabel3;
    // End of variables declaration//GEN-END:variables
}