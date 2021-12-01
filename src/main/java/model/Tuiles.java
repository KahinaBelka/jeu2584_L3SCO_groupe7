/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Sylvain
 */
public class Tuiles implements model.Tuile {
    private ArrayList<model.Tuile> tuiles = new ArrayList<>();

    @Override
    public void deplacer(Direction d) {
        for (model.Tuile t : tuiles) { t.deplacer(d); }
    }
    
    public void add(model.Tuile t) { tuiles.add(t); }

    public void remove(model.Tuile t) { tuiles.remove(t); }
}
