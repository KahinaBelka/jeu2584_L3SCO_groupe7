/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Tuiles implements Tuile {
    private ArrayList<Tuile> tuiles = new ArrayList<>();

    @Override
    public void deplacer(Direction d) {
        for (Tuile t : tuiles) { t.deplacer(d); }
    }
    
    public void add(Tuile t) { tuiles.add(t); }

    public void remove(Tuile t) { tuiles.remove(t); }
}
