package main;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import entities.CommandType;
import entities.Game;
import entities.Prepositions;
import handlers.ActionsHandler;
import handlers.ActionsHandlerEssentials;
import handlers.ItalianMessages;
import handlers.MessagesEssentials;
import parser.ItalianParser;
import parser.ParserEssentials;
import parser.ParserOutput;
import resources.InterfaceText;


@SuppressWarnings("serial")
public class AdventureGUI extends javax.swing.JFrame {

	private ActionsHandlerEssentials actions;
	private final MessagesEssentials printer = new ItalianMessages(this);
	private ParserEssentials parser;
	private final int gameMinutes = 30;
	private final int gameSeconds = 0;

	/**
	 * Creates new form AdventureGUI
	 * @throws java.io.IOException
	 * @throws java.lang.ClassNotFoundException
	 */
	public AdventureGUI() throws IOException, ClassNotFoundException {
		initComponents();
		init();
	}

	private void init() throws IOException, ClassNotFoundException {
		Game game = new Game();
		Prepositions prepositions = new Prepositions();
		prepositions.addPreposition("in", new CommandType[] {CommandType.LASCIA, CommandType.METTI} );
		prepositions.addPreposition("dentro", CommandType.LASCIA);
		prepositions.addPreposition("da", CommandType.PRENDI);
		prepositions.addPreposition("con", new CommandType[] {CommandType.COMBINA, CommandType.APRI, CommandType.PARLA});
		prepositions.addPreposition("dal", CommandType.PRENDI);
		prepositions.addPreposition("dalla", CommandType.PRENDI);
		prepositions.addPreposition("nel",new CommandType[] { CommandType.LASCIA, CommandType.PRENDI});
		prepositions.addPreposition("e", CommandType.COMBINA);
		prepositions.addPreposition("all", CommandType.PARLA);
		prepositions.addPreposition("allo", CommandType.PARLA);
		prepositions.addPreposition("a", CommandType.PARLA);
		prepositions.addPreposition("ad", CommandType.PARLA);
		prepositions.addPreposition("al", CommandType.PARLA);
		prepositions.addPreposition("col", CommandType.PARLA);
		prepositions.addPreposition("nella", CommandType.METTI);
		actions = new ActionsHandler(game, printer, prepositions);
		actions.setObjectsList(game.getObjects());
		ArrayList<String> articles = new ArrayList<>();
		articles.add("la");
		articles.add("le");
		articles.add("i");
		articles.add("gli");
		articles.add("l");
		articles.add("il");
		articles.add("un");
		articles.add("una");
		articles.add("uno");
		parser = new ItalianParser(articles, prepositions);
		printer.beginningOfTheGameMessage(actions.getCurrentRoom().getName(),
				actions.getCurrentRoom().getDescription());
		Thread gameState = new GameThread(this, gameMinutes, gameSeconds);
		gameState.start();

		JLabel background;
		ImageIcon img = new ImageIcon("160606437-41ae2194-60e7-4424-8ea1-b43593fbd2bc.jpg");
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(-500, -30, 1500, 500);
		add(background);

	}

	public boolean isGameCompleted() {
		return actions.getCompleted();
	}

	public JLabel getMinute() {
		return jlMinute;
	}

	public JLabel getSecond() {
		return jlSecond;
	}

	public JTextArea getConsole() {
		return jtConsole;
	}

	public void printMessage(String message) {
		jtConsole.append(message + "\n");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jtConsole = new javax.swing.JTextArea();
		jlConsole = new javax.swing.JLabel();
		jbOvest = new javax.swing.JButton();
		jbNord = new javax.swing.JButton();
		jbSud = new javax.swing.JButton();
		jbEst = new javax.swing.JButton();
		jtComandi = new javax.swing.JTextField();
		jlComandi = new javax.swing.JLabel();
		jlMinute = new javax.swing.JLabel();
		jlSecond = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("UnibaCrisis");

		jtConsole.setEditable(false);
		jtConsole.setColumns(20);
		jtConsole.setRows(5);
		jtConsole.setMinimumSize(new java.awt.Dimension(164, 94));
		jScrollPane1.setViewportView(jtConsole);

		jlConsole.setText("Console");

		jbOvest.setText("OVEST");
		jbOvest.setPreferredSize(new java.awt.Dimension(70, 20));
		jbOvest.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbOvestActionPerformed(evt);
			}
		});

		jbNord.setText("NORD");
		jbNord.setPreferredSize(new java.awt.Dimension(70, 20));
		jbNord.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbNordActionPerformed(evt);
			}
		});

		jbSud.setText("SUD");
		jbSud.setPreferredSize(new java.awt.Dimension(70, 20));
		jbSud.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbSudActionPerformed(evt);
			}
		});

		jbEst.setText("EST");
		jbEst.setPreferredSize(new java.awt.Dimension(70, 20));
		jbEst.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbEstActionPerformed(evt);
			}
		});

		jtComandi.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jtComandiKeyPressed(evt);
			}
		});

		jlComandi.setText(InterfaceText.COMMANDS);

		jlMinute.setFont(new java.awt.Font("Jokerman", 1, 18)); // NOI18N

		jlSecond.setFont(new java.awt.Font("Jokerman", 1, 18)); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addGroup(layout.createSequentialGroup()
														.addGap(85, 85, 85)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jbNord, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jbSud, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(91, 91, 91))
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
														.addGap(8, 8, 8)
														.addComponent(jbOvest, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jbEst, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jtComandi)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jlComandi, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createSequentialGroup()
																		.addGap(3, 3, 3)
																		.addComponent(jlMinute)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(jlSecond)))
														.addGap(0, 0, Short.MAX_VALUE))))
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
								.addComponent(jlConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlConsole)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
														.addGap(30, 30, 30)
														.addComponent(jlComandi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(jbNord, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jbOvest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jbEst, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jtComandi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jbSud, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jlSecond)
												.addComponent(jlMinute))))
						.addGap(36, 36, 36))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jbSudActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbSudActionPerformed
		actions.moveSud();
	}// GEN-LAST:event_jbSudActionPerformed

	private void jbOvestActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbOvestActionPerformed
		actions.moveOvest();
	}// GEN-LAST:event_jbOvestActionPerformed

	private void jbNordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbNordActionPerformed
		actions.moveNord();
	}// GEN-LAST:event_jbNordActionPerformed

	private void jbEstActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbEstActionPerformed
		actions.moveEst();
	}// GEN-LAST:event_jbEstActionPerformed

	private void jtComandiKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jtComandiKeyPressed
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			String input = jtComandi.getText();
			jtComandi.setText("");
			ParserOutput azione = parser.parse(input, actions.getCommands(), actions.getObjects(),
					actions.getCharacters());
			if (azione != null) {
				actions.processAction(azione);
			} else {
				printer.notUnderstoodMessage();
			}
		}
	}// GEN-LAST:event_jtComandiKeyPressed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AdventureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdventureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdventureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdventureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new AdventureGUI().setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(AdventureGUI.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(AdventureGUI.class.getName()).log(Level.SEVERE, null, ex);
				} catch (NullPointerException ex) {
					System.out.println("Something went wrong");
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jbEst;
	private javax.swing.JButton jbNord;
	private javax.swing.JButton jbOvest;
	private javax.swing.JButton jbSud;
	private javax.swing.JLabel jlComandi;
	private javax.swing.JLabel jlConsole;
	private javax.swing.JLabel jlMinute;
	private javax.swing.JLabel jlSecond;
	private javax.swing.JTextField jtComandi;
	private javax.swing.JTextArea jtConsole;
	// End of variables declaration//GEN-END:variables
}
