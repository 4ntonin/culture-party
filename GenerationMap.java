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
        Case casecreee = new Case;
        casecreee.type = type;
        casecreee.pos_prochain_voisin = pos_prochain_voisin;
        return casecreee;
    }

    Map creerMap() {
        Map carte = new Map;
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
        for (int i=0;i<carte.taille;i++) {
            random_pos_voisin = (int) (random() * 3);
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

        for (int i=0;i<carte.taille;i++) {
            carte.cases[i] = creerCase(map_events[i], mapnextvoisins[i]);
        }

        return carte;
    }

    boolean isOutOfRange(int position, char searchedmove, Map[] carte) {
        boolean autrecase = false;
        int nbmoves = 0;
        while (!autrecase && nbmoves < 2 && position >= 0) {
            if (carte[position - 1] == searchedmove) {
                nbmoves++;
            } else if (carte[position - 1] != DROITE) {
                autrecase = true;
            }
            position--;
        }
        if (nbmoves == 2) {
            return true;
        } else {
            return false;
        }
    }

    void afficherMap() {
        // ╔╗╚╝═║╦╩╠╣    = caractères utilisables pour la map
        String[][] printablemap = new String[10][151];
        final String LIGNEVIDE = "       ";
        for (int i=0;i<length(printablemap, 1);i++) {
            for (int j=0;j<length(printablemap, 2);j++) {
                printablemap[i][j] = LIGNEVIDE;
            }
        }
        final String NEXTCASEBAS = "╔═════╗\n║     ║\n║     ║\n╠═════╣\n";
        final String NEXTCASEHAUT = "╠═════╣\n║     ║\n║     ║\n╚═════╝\n";
        final String NEXTCASEDROITE = "╔═════╦\n║     ║\n║     ║\n╚═════╩\n";
        int posx = 0;
        int posy = 0;

        for (int i=0;i<carte.taille;i++) {
            if (carte.cases[i].pos_prochain_voisin == HAUT) {
                printablemap[x][y] = NEXTCASEHAUT;
                x -= 1;
            } else if (carte.cases[i].pos_prochain_voisin == BAS) {
                printablemap[x][y] = NEXTCASEBAS;
                x += 1;
            } else {
                printablemap[x][y] = NEXTCASEDROITE;
                y += 1;
            }
        }

        for (int i=0;i<length(printablemap, 1);i++) {
            for (int j=0;j<length(printablemap, 2);j++) {
                print(printablemap[j]);
            }
            println()
        }
    }

    void algorithm() {
        afficherMap();
    }
}