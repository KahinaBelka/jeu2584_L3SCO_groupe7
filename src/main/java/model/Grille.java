/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public enum Grille implements Serializable {
    INSTANCE;
    
    private final int[][] grille = new int[4][4];
    
    @Override
    public String toString() {
        String result = "";
        for (int[] grille1 : this.grille) {
            result += Arrays.toString(grille1) + "\n";
        }
        return result;
    }
    
    public void init(){
        Random ra = new Random();
        this.grille[ra.nextInt(4)][ra.nextInt(4)]=(1+ra.nextInt(2))*2;
    }
}
