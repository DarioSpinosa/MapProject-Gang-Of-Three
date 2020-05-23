/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

/**
 *
 * @author DarioSpinosa
 */

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Entita.Partita;

import java.util.ArrayList;
import General.Command;
import General.GestoreMessaggiEssentials;
import General.GestoreMessaggi;
import General.GestoreAzioni;
import General.GestoreAzioniEssentials;

public class MainProgram {
    
    public static void main(String[] args) throws InterruptedException {
        
        /*Partita nuovaPartita = new Partita();
        Scanner input = new Scanner(System.in);
        System.out.println("		  BENVENUTO IN 4 CASEIFICI CON DARIO SPINOSA\n");
        System.out.println("		  Sembra che tu sia nuovo da queste parti \n		  scegli il nome del tuo personaggio");
        System.out.println("		  e incomincia la tua magica avventura \n		  nella zona campus dell'UNIBA!");
        System.out.println("--> ");
        nuovaPartita.creaProtagonista(input.next());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("E cosi ti chiami " + nuovaPartita.getNomeProtagonista() + ", e' proprio un bel nome...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("MA CHE DICO, E' PROPRIO UN NOME DA RICCHIONI, vabbe lasciamo stare\n");
        System.out.println(nuovaPartita.getStanzaCorrente().getEvento(0));
        System.out.println("\nStanza attuale: " + nuovaPartita.getStanzaCorrente().getNome());
        
        while(true) {
            
            System.out.println("Dove vuoi andare? ");
            nuovaPartita.muovi(input.next());
            System.out.println("Stanza attuale: " + nuovaPartita.getStanzaCorrente().getNome());
            
        }*/
        AdventureGUI interfaccia = new AdventureGUI();
    }
}

