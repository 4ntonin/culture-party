class GenerationMap extends Program {
    final char VIDE = ' ';
    final char BOOSTER = '▶';
    final char RALENTISSEUR = '㐅';
    final char MINIJEU = '㐃';
    final char HAUT = 'h';
    final char BAS = 'b';
    final char DROITE = 'd';

    Map carte = creerMap();

    Case creerCase(char type, char pos_prochain_voisin) {
        Case casecreee = new Case();
        casecreee.type = type;
        casecreee.pos_prochain_voisin = pos_prochain_voisin;
        return casecreee;
    }

    Map creerMap() {
        Map carte = new Map();
        char[] map_events = new char[carte.taille];
        for (int i=0;i<carte.taille;i++) {
            map_events[i] = VIDE;
        }
        int randompos;
        int nbevents = carte.taille / 2; // on laisse moitié de cases vide et on divise en parts égales pour les events
        for (int i=0;i<nbevents;i++) {
            do {
                randompos = (int) (random() * (carte.taille - 2) + 1);
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
        char[] mapnextvoisins = new char[carte.taille];
        mapnextvoisins[0] = DROITE;
        for (int i=1;i<carte.taille-1;i++) {
            random_pos_voisin = (int) (random() * 3);
            if (random_pos_voisin == 0 && mapnextvoisins[i-1] == HAUT) {
                do {
                    random_pos_voisin = (int) (random() * 3);
                } while (random_pos_voisin == 0);
            } else if (random_pos_voisin == 1 && mapnextvoisins[i-1] == BAS) {
                do {
                    random_pos_voisin = (int) (random() * 3);
                } while (random_pos_voisin == 1);
            }
            if (i > 3 && (mapnextvoisins[i-3] == HAUT || mapnextvoisins[i-3] == BAS)) {
                mapnextvoisins[i] = DROITE;
            } else if (random_pos_voisin == 0) {
                if (isOutOfRange(i, HAUT, carte)) {
                    mapnextvoisins[i] = DROITE;
                } else {
                    mapnextvoisins[i] = HAUT;
                }
            } else if (random_pos_voisin == 1) {
                if (isOutOfRange(i, BAS, carte)) {
                    mapnextvoisins[i] = DROITE;
                } else {
                    mapnextvoisins[i] = BAS;
                }
            } else {
                mapnextvoisins[i] = DROITE;
            }
        }
        mapnextvoisins[carte.taille-1] = DROITE;

        for (int i=0;i<carte.taille;i++) {
            carte.cases[i] = creerCase(map_events[i], mapnextvoisins[i]);
        }

        return carte;
    }

    boolean isOutOfRange(int position, char searchedmove, Map carte) {
        int nbmoves = 0;
        while (nbmoves < 2 && position > 1) {
            println(carte.cases[0].pos_prochain_voisin + "");
            if (carte.cases[position - 1].pos_prochain_voisin == searchedmove) {
                nbmoves++;
            } else if (carte.cases[position - 1].pos_prochain_voisin != DROITE) {
                nbmoves--;
            }
            position--;
        }
        if (nbmoves == 2) {
            return true;
        } else {
            return false;
        }
    }

    // String tableToString(String[] table) {
    //     String res = "";
    //     for (int i=0;i<4;i++) {
    //         res += table[i] + "\n";
    //     }
    //     return res;
    // }

    void afficherMap() {
        // ╔╗╚╝═║╦╩╠╣╬    = caractères utilisables pour la map
        String[][] printablemap = new String[10][25];
        final String LIGNEVIDE = "       ";
        for (int i=0;i<length(printablemap, 1);i++) {
            for (int j=0;j<length(printablemap, 2);j++) {
                printablemap[i][j] = LIGNEVIDE;
            }
        }
        String[] printableCase;
        int posx = 0;
        int posy = 0;


        printableCase = new String[]{"╔═════╗","║     ║","║     ║","╚═════╝"};
        if (carte.cases[1].pos_prochain_voisin == BAS) {
            printableCase[3] = "╚═════╬";
        } else if (carte.cases[1].pos_prochain_voisin == HAUT) {
            printableCase[0] = "╔═════╬";
        } else {
            printableCase[0] = "╔═════╦";
            printableCase[3] = "╚═════╩";
        }
        for (int i=0;i<4;i++) {
            printablemap[posx+i][posy] = printableCase[i];
        }
        posy += 1;


        for (int i=1;i<carte.taille-1;i++) {
            printableCase = new String[]{"╔═════╗","║     ║","║     ║","╚═════╝"};
            if (carte.cases[i].pos_prochain_voisin == HAUT) {
                if (carte.cases[i+1].pos_prochain_voisin == HAUT) {
                    printableCase[0] = "╠═════╣";
                } else if (carte.cases[i+1].pos_prochain_voisin == DROITE) {
                    printableCase[0] = "╠═════╬";
                }
                for (int j=0;j<4;j++) {
                    printablemap[posx+j][posy] = printableCase[j];
                }
                posx -= 1;
            } else if (carte.cases[i].pos_prochain_voisin == BAS) {
                if (carte.cases[i+1].pos_prochain_voisin == BAS) {
                    printableCase[3] = "╠═════╣";
                } else if (carte.cases[i+1].pos_prochain_voisin == DROITE) {
                    printableCase[3] = "╠═════╬";
                }
                for (int j=0;j<4;j++) {
                    printablemap[posx+j][posy] = printableCase[j];
                }
                posx += 1;
            } else {
                if (carte.cases[i+1].pos_prochain_voisin == BAS) {
                    printableCase[3] = "╚═════╬";
                } else if (carte.cases[i+1].pos_prochain_voisin == HAUT) {
                    printableCase[0] = "╔═════╬";
                } else {
                    printableCase[0] = "╔═════╦";
                    printableCase[3] = "╚═════╩";
                }
                for (int j=0;j<4;j++) {
                    printablemap[posx+j][posy] = printableCase[j];
                }
                posy += 1;
            }
            if (carte.cases[i-1].pos_prochain_voisin == HAUT) {
                printableCase[3] = "";
            } else if (carte.cases[i-1].pos_prochain_voisin == BAS) {
                printableCase[0] = "";
            } else {
                printableCase[0] = substring(printableCase[0], 1, length(printableCase[0]));
                printableCase[1] = substring(printableCase[1], 1, length(printableCase[1]));
                printableCase[2] = substring(printableCase[2], 1, length(printableCase[2]));
                printableCase[3] = substring(printableCase[3], 1, length(printableCase[3]));
            }
        }


        printableCase = new String[]{"═════╗","     ║","     ║","═════╝"};
        for (int i=0;i<4;i++) {
            printablemap[posx+i][posy] = printableCase[i];
        }


        for (int i=0;i<length(printablemap, 1);i++) {
            for (int j=0;j<length(printablemap, 2);j++) {
                print(printablemap[i][j]);
            }
            println();
        }
    }

    void algorithm() {
        afficherMap();
    }
}