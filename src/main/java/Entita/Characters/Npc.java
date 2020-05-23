/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;
import java.util.ArrayList;

import General.Name;
import General.Eventi.Evento;

public class Npc extends Personaggio{

    private String presentazione;
    private boolean presentato  = false;
    private ArrayList<Evento> eventi = new ArrayList<>();

    public Npc(Name nome) {
        super(nome, 1);
    }

    public Npc(Name nome, String presentazione){
        super(nome, 1);
        this.presentazione = presentazione;
    }

    public void setPresentazione(String presentazione){
        this.presentazione = presentazione;
    }

    public String getPresentazione(){
        return presentazione;
    }

    public boolean  getPresentato(){
        return presentato;
    }

    public void confirmPresentato(){
        presentato = true;
    }

    public void addEvento(Evento e) {
        eventi.add(e);
    }

    public void removeEvento(Evento e) {
        eventi.remove(e);
    }

    public Evento getEvento(int i) {
        return eventi.get(i);
    }

    @Override
    public void getDamage(int danno) {}

}
