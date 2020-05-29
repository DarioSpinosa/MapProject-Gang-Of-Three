/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.JLabel;

/**
 *
 * @author Elio
 */
public class TimeThread extends Thread {
    private int minutes;
    private int seconds;
    private final JLabel minute;
    private final JLabel second;
    
    public TimeThread(int minutes, int seconds, JLabel minute, JLabel second){
        this.minutes = minutes;
        this.seconds = seconds;
        this.minute = minute;
        this.second = second;
    }
    
    @Override
    public void run(){
        printMinutes(minutes, minute);
        printSeconds(seconds, second);
        while(true){
                try{
                    if(minutes == 0 && seconds == -1){
                        break;
                    }
                    sleep(1000);
                    if(seconds < 0){
                        seconds = 59;
                        minutes--;
                        printMinutes(minutes, minute);
                    }
                    printSeconds(seconds, second);
                    seconds--;
                } catch(InterruptedException ex){
                    System.out.println(ex);
                }
        }
    }
    
    private void printSeconds(int seconds, JLabel second){
        if(seconds > 9){
            second.setText("" + seconds);
        } else {
            second.setText("0" + seconds);
        }
    }
    
    private void printMinutes(int minutes, JLabel minute){
        if(minutes > 9){
            minute.setText(minutes + " : ");
        } else {
            minute.setText("0" + minutes + " : ");
        }
    }
}
