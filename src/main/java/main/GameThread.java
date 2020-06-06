/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Elio
 */
public class GameThread extends Thread {
	private final AdventureGUI gui;
	private final int minutes;
	private final int seconds;

	public GameThread(AdventureGUI gui, int minutes, int seconds) {
		this.gui = gui;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	@Override
	public void run() {
		Thread time = new TimeThread(minutes, seconds, gui.getMinute(), gui.getSecond());
		time.start();
		gameCheck(time);
	}

	private void gameCheck(Thread time) {
		while (time.isAlive() && !gui.isGameCompleted()) {
		}
                if(!time.isAlive()){
                    GameOverDialog gameOver = new GameOverDialog(gui, true);
                    gameOver.setVisible(true);

                    while (gameOver.isActive()) {
                    }
                    
                } else if(gui.isGameCompleted()){
                    ((TimeThread)time).stopCount();
                    VictoryDialog victory = new VictoryDialog(gui, true);
                    int remainingMinutes = ((TimeThread)time).getMinutes();
                    int remainingSeconds = ((TimeThread)time).getSeconds();
                    int finalMinutes = minutes  - remainingMinutes;
                    int finalSeconds = seconds - remainingSeconds;
                    if(finalSeconds < 0) {
                    	finalSeconds = 60 + finalSeconds;
                    	finalMinutes = finalMinutes - 1;
                    }
                    victory.setTimeLabel(finalMinutes, finalSeconds);
                    victory.setVisible(true);
                    
                    while(victory.isActive()){                        
                    }
                }

		gui.dispose();
	}
}
