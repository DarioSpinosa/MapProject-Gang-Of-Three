/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Elio
 */
public class GameThread extends Thread {
	private final AdventureGUI interfaccia;
	private final GameOverDialog gameOver;
	private final int minutes;
	private final int seconds;

	public GameThread(AdventureGUI interfaccia, int minutes, int seconds) {
		this.interfaccia = interfaccia;
		gameOver = new GameOverDialog(interfaccia, true);
		this.minutes = minutes;
		this.seconds = seconds;
	}

	@Override
	public void run() {
		Thread time = new TimeThread(minutes, seconds, interfaccia.getMinute(), interfaccia.getSecond());
		time.start();
		gameCheck(time);
	}

	private void gameCheck(Thread time) {
		while (time.isAlive()) {
		}
		gameOver.setVisible(true);

		while (gameOver.isActive()) {
		}

		interfaccia.dispose();
	}
}
