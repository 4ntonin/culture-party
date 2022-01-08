class GenerationMap extends Program {
    final char VIDE = ' ';
    final char BOOSTER = '▶';
    final char RALENTISSEUR = '㐅';
    final char MINIJEU = '㐃';
    final char HAUT = 'h';
    final char BAS = 'b';
    final char DROITE = 'd';
    Joueur joueur;

    Case creerCase(char type, char direction) {
        Case casecreee = new Case();
        casecreee.type = type;
        casecreee.direction = direction;
        return casecreee;
    }

    Map creerMap() {
        Map map = new Map();
        char[] map_events = new char[map.taille];
        for (int i=0;i<map.taille;i++) {
            map_events[i] = VIDE;
        }
        int randompos;
        int nbevents = map.taille / 2; // on laisse moitié de cases vide et on divise en parts égales pour les events
        for (int i=0;i<nbevents;i++) {
            do {
                randompos = (int) (random() * (map.taille - 2) + 1);
            } while (map_events[randompos] != VIDE);
            if (i%4 == 0) {
                map_events[randompos] = BOOSTER;
            } else if (i%4 == 1) {
                map_events[randompos] = RALENTISSEUR;
            } else {
                map_events[randompos] = MINIJEU;
            }
        }

        int random_pos_voisin;
        int posx = 0, posy = 0;
        int cptdroite = 0;
        char[] mapnextvoisins = new char[map.taille];
        mapnextvoisins[0] = DROITE;
        mapnextvoisins[1] = DROITE;
        for (int i=2;i<map.taille-2;i++) {
            random_pos_voisin = (int) (random() * 3);
            if (random_pos_voisin == 0 && mapnextvoisins[i-1] == BAS) {
                do {
                    random_pos_voisin = (int) (random() * 3);
                } while (random_pos_voisin == 0);
            } else if (random_pos_voisin == 1 && mapnextvoisins[i-1] == HAUT) {
                do {
                    random_pos_voisin = (int) (random() * 3);
                } while (random_pos_voisin == 1);
            }
            if (i > 2 && (mapnextvoisins[i-2] == HAUT || mapnextvoisins[i-2] == BAS)) {
                mapnextvoisins[i] = DROITE;
            } else if (random_pos_voisin == 0) {
                if (posx == 0) {
                    mapnextvoisins[i] = DROITE;
                    posy++;
                } else {
                    mapnextvoisins[i] = HAUT;
                    posx--;
                }
            } else if (random_pos_voisin == 1) {
                if (posx == 2) {
                    mapnextvoisins[i] = DROITE;
                    posy++;
                } else {
                    mapnextvoisins[i] = BAS;
                    posx++;
                }
            } else {
                mapnextvoisins[i] = DROITE;
                cptdroite++;
                posy++;
            }
        }

        for (int i=0;i<map.taille;i++) {
            map.cases[i] = creerCase(map_events[i], mapnextvoisins[i]);
        }

        return map;
    }

    void gestionBordures(Map map, String[] printablecase, int i) {
        if (map.cases[i-1].direction == HAUT) {
            if (map.cases[i-2].direction == DROITE) {
                if (map.cases[i].direction == DROITE) {
                    printablecase[3] = "─────┼";
                } else {
                    printablecase[3] = "─────┤";
                }
            } else {
                if (map.cases[i].direction == DROITE) {
                    printablecase[3] = "├─────┼";
                } else {
                    printablecase[3] = "├─────┤";
                }
            }
        } else if (map.cases[i-1].direction == BAS) {
            if (map.cases[i-2].direction == DROITE) {
                if (map.cases[i].direction == DROITE) {
                    printablecase[0] = "─────┼";
                } else {
                    printablecase[0] = "─────┤";
                }
            } else {
                if (map.cases[i].direction == DROITE) {
                    printablecase[0] = "├─────┼";
                } else {
                    printablecase[0] = "├─────┤";
                }
            }
        } else {
            printablecase[0] = substring(printablecase[0], 1, length(printablecase[0]));
            printablecase[1] = substring(printablecase[1], 1, length(printablecase[1]));
            printablecase[2] = substring(printablecase[2], 1, length(printablecase[2]));
            printablecase[3] = substring(printablecase[3], 1, length(printablecase[3]));
        }
    }

    void ajoutPrintableCaseAMap(String[][] printablemap, String[] printablecase, int posx, int posy) {
        if (posx == 0) {
            for (int j=0;j<4;j++) {
                printablemap[posx+j][posy] = printablecase[j];
            }
        } else if (posx == 1) {
            for (int j=0;j<4;j++) {
                printablemap[2+posx+j][posy] = printablecase[j];
            }
        } else {
            for (int j=0;j<4;j++) {
                printablemap[4+posx+j][posy] = printablecase[j];
            }
        }
    }

    void gestionEspacesEnTrop(String[][] printablemap, int posx, int posy) {
        if (posx == 0) {
            printablemap[4][posy] = substring(printablemap[4][posy], 1, length(printablemap[4][posy]));
            printablemap[5][posy] = substring(printablemap[5][posy], 1, length(printablemap[5][posy]));
            printablemap[6][posy] = substring(printablemap[6][posy], 1, length(printablemap[6][posy]));
            printablemap[7][posy] = substring(printablemap[7][posy], 1, length(printablemap[7][posy]));
            printablemap[8][posy] = substring(printablemap[8][posy], 1, length(printablemap[8][posy]));
            printablemap[9][posy] = substring(printablemap[9][posy], 1, length(printablemap[9][posy]));
        } else if (posx == 1) {
            printablemap[0][posy] = substring(printablemap[0][posy], 1, length(printablemap[0][posy]));
            printablemap[1][posy] = substring(printablemap[1][posy], 1, length(printablemap[1][posy]));
            printablemap[2][posy] = substring(printablemap[2][posy], 1, length(printablemap[2][posy]));
            printablemap[3][posy] = substring(printablemap[3][posy], 1, length(printablemap[3][posy]));
            printablemap[7][posy] = substring(printablemap[7][posy], 1, length(printablemap[7][posy]));
            printablemap[8][posy] = substring(printablemap[8][posy], 1, length(printablemap[8][posy]));
            printablemap[9][posy] = substring(printablemap[9][posy], 1, length(printablemap[9][posy]));
        } else {
            printablemap[0][posy] = substring(printablemap[0][posy], 1, length(printablemap[0][posy]));
            printablemap[1][posy] = substring(printablemap[1][posy], 1, length(printablemap[1][posy]));
            printablemap[2][posy] = substring(printablemap[2][posy], 1, length(printablemap[2][posy]));
            printablemap[3][posy] = substring(printablemap[3][posy], 1, length(printablemap[3][posy]));
            printablemap[4][posy] = substring(printablemap[4][posy], 1, length(printablemap[4][posy]));
            printablemap[5][posy] = substring(printablemap[5][posy], 1, length(printablemap[5][posy]));
            printablemap[6][posy] = substring(printablemap[6][posy], 1, length(printablemap[6][posy]));
        }
    }

    void affichageJoueur(String[] printablecase, int i) {
        if (joueur.position == i) {
            printablecase[1] = substring(printablecase[1], 0, 3) + joueur.nom + substring(printablecase[1], 4, 7);
        }
    }

    void affichageType(Map map, String[] printablecase, int i) {
        if (map.cases[i].type == MINIJEU || map.cases[i].type == RALENTISSEUR) {
            printablecase[2] = substring(printablecase[2], 0, 3) + map.cases[i].type + substring(printablecase[2], 5, 7);
        } else {
            printablecase[2] = substring(printablecase[2], 0, 3) + map.cases[i].type + substring(printablecase[2], 4, 7);
        }
    }

    void afficherMap(Map map) {
        // ─│┌┐└┘├┤┬┴┼    = caractères utilisables pour la map
        String[][] printablemap = new String[10][25];
        final String LIGNEVIDE = "       ";
        for (int i=0;i<length(printablemap, 1);i++) {
            for (int j=0;j<length(printablemap, 2);j++) {
                printablemap[i][j] = LIGNEVIDE;
            }
        }
        String[] printablecase;
        int posx = 0;
        int posy = 0;

        printablecase = new String[]{"┌─────┐","│     │","│     │","└─────┘"};
        if (map.cases[1].direction == BAS) {
            printablecase[0] = "┌─────┬";
            printablecase[3] = "└─────┼";
        } else {
            printablecase[0] = "┌─────┬";
            printablecase[3] = "└─────┴";
        }
        affichageJoueur(printablecase, 0);
        for (int i=0;i<4;i++) {
            printablemap[posx+i][posy] = printablecase[i];
        }
        posy += 1;

        for (int i=1;i<map.taille-1;i++) {
            printablecase = new String[]{"┌─────┐","│     │","│     │","└─────┘"};
            if (map.cases[i].direction == HAUT) {
                if (map.cases[i+1].direction == HAUT) {
                    printablecase[0] = "├─────┤";
                } else if (map.cases[i+1].direction == DROITE) {
                    printablecase[0] = "├─────┼";
                    printablecase[3] = "├─────┘";
                }
                affichageJoueur(printablecase, i);
                affichageType(map, printablecase, i);
                gestionBordures(map, printablecase, i);
                ajoutPrintableCaseAMap(printablemap, printablecase, posx, posy);
                posx -= 1;
            } else if (map.cases[i].direction == BAS) {
                if (map.cases[i+1].direction == BAS) {
                    printablecase[3] = "├─────┤";
                } else if (map.cases[i+1].direction == DROITE) {
                    printablecase[0] = "├─────┐";
                    printablecase[3] = "├─────┼";
                }
                affichageJoueur(printablecase, i);
                affichageType(map, printablecase, i);
                gestionBordures(map, printablecase, i);
                ajoutPrintableCaseAMap(printablemap, printablecase, posx, posy);
                posx += 1;
            } else {
                if (map.cases[i+1].direction == BAS) {
                    printablecase[0] = "┌─────┬";
                    printablecase[3] = "└─────┼";
                } else if (map.cases[i+1].direction == HAUT) {
                    printablecase[0] = "┌─────┼";
                    printablecase[3] = "└─────┴";
                } else {
                    printablecase[0] = "┌─────┬";
                    printablecase[3] = "└─────┴";
                }
                affichageJoueur(printablecase, i);
                affichageType(map, printablecase, i);
                gestionBordures(map, printablecase, i);
                if (map.cases[i-1].direction == DROITE) {
                    gestionEspacesEnTrop(printablemap, posx, posy);
                    if (map.cases[i+1].direction != DROITE) {
                        gestionEspacesEnTrop(printablemap, posx, posy);
                    }
                }
                ajoutPrintableCaseAMap(printablemap, printablecase, posx, posy);
                posy += 1;
            }
        }


        printablecase = new String[]{"─────┐","     │","     │","─────┘"};
        for (int j=0;j<4;j++) {
            if (posx == 0) {
                printablemap[posx+j][posy] = printablecase[j];
            } else if (posx == 1) {
                printablemap[2+posx+j][posy] = printablecase[j];
            } else {
                printablemap[4+posx+j][posy] = printablecase[j];
            }
        }

        int lenligne;
        for (int i=0;i<length(printablemap, 1);i++) {
            print("       ");
            for (int j=0;j<length(printablemap, 2);j++) {
                lenligne = length(printablemap[i][j]);
                if (i == 1 || i == 4 || i == 7) {
                    print(substring(printablemap[i][j], 0, 2));
                    if (charAt(printablemap[i][j], 2) == joueur.nom || charAt(printablemap[i][j], 3) == joueur.nom) {
                        text("red");
                    }
                    print(charAt(printablemap[i][j], 2) + "" + charAt(printablemap[i][j], 3));
                    reset();
                    print(substring(printablemap[i][j], 4, length(printablemap[i][j])));
                } else if (i == 2 || i == 5 || i == 8) {
                    print(substring(printablemap[i][j], 0, 2));
                    if (charAt(printablemap[i][j], 2) == BOOSTER || charAt(printablemap[i][j], 3) == BOOSTER) {
                        text("green");
                    } else if (charAt(printablemap[i][j], 2) == RALENTISSEUR || charAt(printablemap[i][j], 3) == RALENTISSEUR) {
                        text("yellow");
                    } else if (charAt(printablemap[i][j], 2) == MINIJEU || charAt(printablemap[i][j], 3) == MINIJEU) {
                        text("blue");
                    }
                    print(charAt(printablemap[i][j], 2) + "" + charAt(printablemap[i][j], 3));
                    reset();
                    print(substring(printablemap[i][j], 4, length(printablemap[i][j])));
                } else {
                    print(printablemap[i][j]);
                }
            }
            println();
        }
    }
}
