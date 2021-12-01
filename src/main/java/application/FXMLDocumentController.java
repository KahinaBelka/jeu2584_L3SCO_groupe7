/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Modele2048;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author castagno
 */
public class FXMLDocumentController implements Initializable {
    /*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
    @FXML
    private Label moves1, moves2, score1, score2, best1, best2; //déplacements, score et meilleur score de chacun des joueurs
    @FXML
    private GridPane grille1, grille2;//grille de chacun des joueurs
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre

    // variables globales non définies dans la vue (fichier .fxml)
    private final Pane p1 = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c1 = new Label("2");
    private final Pane p2 = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c2 = new Label("2");
    private int x1 = 24, y1 = 191;
    private int objectifx1 = 24, objectify1 = 191;
    private int x2 = 445, y2 = 191;
    private int objectifx2 = 445, objectify2 = 191;
    private Modele2048 m;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p1.getStyleClass().add("pane");
        c1.getStyleClass().add("tuile");
        grille1.getStyleClass().add("gridpane");
        GridPane.setHalignment(c1, HPos.CENTER);
        fond.getChildren().add(p1);
        p1.getChildren().add(c1);

        p2.getStyleClass().add("pane");
        c2.getStyleClass().add("tuile");
        grille2.getStyleClass().add("gridpane");
        GridPane.setHalignment(c2, HPos.CENTER);
        fond.getChildren().add(p2);
        p2.getChildren().add(c2);

        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p1.setLayoutX(x1);
        p1.setLayoutY(y1);
        p1.setVisible(true);
        c1.setVisible(true);

        p2.setLayoutX(x2);
        p2.setLayoutY(y2);
        p2.setVisible(true);
        c2.setVisible(true);
        
        // création du modèle
        m = new Modele2048();
    }

    /*
     * Méthodes listeners pour gérer les événements (portent les mêmes noms que
     * dans Scene Builder
     */
    @FXML
    private void handleDragAction(MouseEvent event) {
        System.out.println("Glisser/déposer sur la grille avec la souris");
        double x = event.getX();//translation en abscisse
        double y = event.getY();//translation en ordonnée
        if (x > y) {
            for (int i = 0; i < grille1.getChildren().size(); i++) { //pour chaque colonne
                //for (int j = 0; j < grille.getRowConstraints().size(); j++) { //pour chaque ligne
                System.out.println("ok1");
                grille1.getChildren().remove(i);

                /*Node tuile = grille.getChildren().get(i);
                 if (tuile != null) {
                 int rowIndex = GridPane.getRowIndex(tuile);
                 int rowEnd = GridPane.getRowIndex(tuile);
                 }*/
                // }
            }
        } else if (x < y) {
            System.out.println("ok2");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Pane p = new Pane();
                    p.getStyleClass().add("pane");
                    grille1.add(p, i, j);
                    p.setVisible(true);
                    grille1.getStyleClass().add("gridpane");
                }
            }
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    @FXML
    public void keyPressed (KeyEvent ke) {
        System.out.println("touche appuyée 1");
        String touche1 = ke.getText();
        String touche2 = ke.getText();
        if (touche1.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx1 > 24) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx1 -= (int) 397 / 4; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                moves1.setText(Integer.toString(Integer.parseInt(moves1.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche1.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx1 < (int) 445 - 2 * 397 / 4 - 24) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx1 += (int) 397 / 4;
                moves1.setText(Integer.toString(Integer.parseInt(moves1.getText()) + 1));
            }
        }else if (touche1.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            if (objectify1 < (int) 613 - 2 * 397 / 4 - 25) { // possible uniquement si on est pas dans la ligne la plus en bas
                objectify1 += (int)(397 / 4);
                moves1.setText(Integer.toString(Integer.parseInt(moves1.getText()) + 1));
            }
        }else if (touche1.compareTo("z") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            if (objectify1 >191) { // possible uniquement si on est pas dans la ligne la plus en bas (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectify1 -= (int) 397 / 4;
                moves1.setText(Integer.toString(Integer.parseInt(moves1.getText()) + 1));
            }
        }

        System.out.println("objectifx1=" + objectifx1);
        System.out.println("objectify1=" +objectify1);

        Task task1 = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                while (x1 != objectifx1) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (x1 < objectifx1) {
                        x1 += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        x1 -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                            //javaFX operations should go here
                            //System.out.println("déplacement en cours");
                            p1.relocate(x1, y1); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                            p1.setVisible(true);
                        }
                    }
                    );
                    Thread.sleep(5);
                }
                while (y1 != objectify1) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (y1 < objectify1) {
                        y1 += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        y1 -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                        //javaFX operations should go here
                        //System.out.println("déplacement en cours");
                        p1.relocate(x1, y1); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                        p1.setVisible(true);
                        }
                    }
                    );
                    Thread.sleep(5);
                }// end while
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
            } // end call

        };
        Thread th1 = new Thread(task1); // on crée un contrôleur de Thread
        th1.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th1.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)

        System.out.println("touche appuyée 2");
        if (touche2.compareTo("k") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx2 > 445) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx2 -= (int) 397 / 4; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                moves2.setText(Integer.toString(Integer.parseInt(moves2.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche2.compareTo("m") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx2 < (int) 866 - 2 * 397 / 4 - 24) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx2 += (int) 397 / 4;
                moves2.setText(Integer.toString(Integer.parseInt(moves2.getText()) + 1));
            }
        }else if (touche2.compareTo("l") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            if (objectify2 < (int) 613 - 2 * 397 / 4 - 25) { // possible uniquement si on est pas dans la ligne la plus en bas
                objectify2 += (int)(397 / 4);
                moves2.setText(Integer.toString(Integer.parseInt(moves2.getText()) + 1));
            }
        }else if (touche2.compareTo("o") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            if (objectify2 >191) { // possible uniquement si on est pas dans la ligne la plus en bas (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectify2 -= (int) 397 / 4;
                moves2.setText(Integer.toString(Integer.parseInt(moves2.getText()) + 1));
            }
        }

        System.out.println("objectifx2=" + objectifx2);
        System.out.println("objectify2=" +objectify2);

        Task task2 = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                while (x2 != objectifx2) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (x2 < objectifx2) {
                        x2 += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        x2 -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                                          @Override
                                          public void run() {
                                              //javaFX operations should go here
                                              //System.out.println("déplacement en cours");
                                              p2.relocate(x2, y2); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                              p2.setVisible(true);
                                          }
                                      }
                    );
                    Thread.sleep(5);
                }
                while (y2 != objectify2) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (y2 < objectify2) {
                        y2 += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        y2 -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                                          @Override
                                          public void run() {
                                              //javaFX operations should go here
                                              //System.out.println("déplacement en cours");
                                              p2.relocate(x2, y2); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                              p2.setVisible(true);
                                          }
                                      }
                    );
                    Thread.sleep(5);
                }// end while
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
            } // end call

        };
        Thread th2 = new Thread(task2); // on crée un contrôleur de Thread
        th2.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th2.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)

    }

}
