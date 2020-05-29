/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Elio
 */
public class PrintThread extends Thread{
    private final JLabel second;
    private final JTextArea console;
    private final Thread time;
    
    public PrintThread(JLabel second, JTextArea console, Thread time){
        this.second = second;
        this.console = console;
        this.time = time;
    }
    
    @Override
    public void run(){
        while(time.isAlive() && !time.isInterrupted()){
            if(second.getText().equals("59")){
                console.append("Il tempo passa...\n");
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrintThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
