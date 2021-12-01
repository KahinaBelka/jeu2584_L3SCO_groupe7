/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Tuile2584 implements Tuile {
    private Grille grille;
    private int x,y;
    
    public Tuile2584(Grille g, int abs, int ord){
        this.grille=g;
        this.x=abs;
        this.y=ord;
    }

    @Override
    public void deplacer(Direction d) {
        if (d==Direction.GAUCHE){
            // Déplacer la tuile vers la gauche
        }
    }
    
}
