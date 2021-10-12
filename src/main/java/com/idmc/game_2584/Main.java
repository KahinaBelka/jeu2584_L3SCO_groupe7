package com.idmc.game_2584;

public class Main {
    public static void main(String[] args) {
        // TODO code application logic here

        Grille g1 = new Grille(); //grille 1 du joueur 1
        Grille g2 = new Grille(); //grille du joueur 2

        //initialisation de la grille 1
        boolean b = g1.nouvelleCase();
        b = g1.nouvelleCase();

        //initialisation de la grille g2 mais pas égale a g1
        boolean bg2 = g2.nouvelleCase();
        bg2 = g2.nouvelleCase();

        System.out.println("Grille joueur 1 : \n" + g1);
        System.out.println("Grille joueur 2 : \n" + g2);
        Scanner sc1 = new Scanner(System.in);

        while (!g1.partieFinie() && !g2.partieFinie()) {
            //tour joueur 1
            System.out.println("C'est au tour du joueur 1, voici votre grille : \n" + g1);
            System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
            String s = sc1.nextLine();
            s.toLowerCase();
            if(!(s.equals("d") || s.equals("q") || s.equals("z") || s.equals("s"))){
                do {
                    System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas");
                    s = sc1.nextLine();
                } while (!(s.equals("d") || s.equals("q") || s.equals("z") || s.equals("s")));
            }

            int direction;
            if (s.equals("d")) {
                direction = DROITE;
            } else if (s.equals("q")) {
                direction = GAUCHE;
            } else if (s.equals("z")) {
                direction = HAUT;
            } else {
                direction = BAS;
            }
            boolean b2 = g1.lanceurDeplacerCases(direction);
            if (b2) {
                b = g1.nouvelleCase();
                if (!b) {
                    g1.gameOver();
                }
            }
            System.out.println("Grille du joueur 1 : \n" + g1);
            if (g1.getValeurMax() >= OBJECTIF) {
                g1.victory();
            }

            //tour joueur 2
            System.out.println("C'est au tour du joueur 2, voici votre grille : \n" + g2);
            System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");
            String sc = sc1.nextLine();
            sc.toLowerCase();
            if (!(sc.equals("m") || sc.equals("k") || sc.equals("o") || sc.equals("l"))){
                do {
                    System.out.println("Vous devez écrire m pour Droite, k pour Gauche, o pour Haut ou l pour Bas");
                    sc=sc1.nextLine();
                } while (!(sc.equals("m") || sc.equals("k") || sc.equals("o") || sc.equals("l")));
            }
            if (sc.equals("m")) {
                direction = DROITE;
            } else if (sc.equals("k")) {
                direction = GAUCHE;
            } else if (sc.equals("o")) {
                direction = HAUT;
            } else {
                direction = BAS;
            }
            boolean b3 = g2.lanceurDeplacerCases(direction);
            if (b3) {
                bg2 = g2.nouvelleCase();
                if (!bg2) {
                    g2.gameOver();
                }
            }
            System.out.println("Grille du joueur 2 : \n" + g2);
            if (g2.getValeurMax() >= OBJECTIF) {
                g2.victory();
            }
        }
        if (g1.partieFinie()) {
            System.out.println("Joueur 1 a perdu!");
            g1.gameOver();
        }

        if (g2.partieFinie()) {
            System.out.println("Joueur 2 a perdu!");
            g2.gameOver();
        }

        //fin méthode main
    }
}
