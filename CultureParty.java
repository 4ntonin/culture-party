class CultureParty extends Program {
    final char VIDE = ' ';
    final char BOOSTER = 'B';
    final char RALENTISSEUR = 'R';
    final char AJOUTPOINTS = 'A';
    final char SUPPRESSIONPOINTS = 'S';
    final char MINIJEU = 'J';
    Joueur joueur = new Joueur();

    char[] creerMapRoute66(int taille) {
        char[] map = new char[taille];
        for (int i=0;i<taille;i++) {
            map[i] = VIDE;
        }
        int randompos;
        int nbevents = taille / 2; // on laisse moitié de cases vide et on divise en parts égales pour les events
        for (int i=0;i<nbevents;i++) {
            do {
                randompos = (int) (random() * (taille - 1) + 1);
            } while (map[randompos] != VIDE);
            if (i%5 == 0) {
                map[randompos] = BOOSTER;
            } else if (i%5 == 1) {
                map[randompos] = RALENTISSEUR;
            } else if (i%5 == 2) {
                map[randompos] = AJOUTPOINTS;
            } else if (i%5 == 3) {
                map[randompos] = SUPPRESSIONPOINTS;
            } else {
                map[randompos] = MINIJEU;
            }
        }
        return map;
    }


    void afficherMap(char[] map) {
        // ╔╗╚╝═║╦╩╠╣    = caractères utilisables pour la map
        // Exemple de Route 66:
        // ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝

        int len = length(map);
        // utilisation de boucles for pour pouvoir gérer l'affichage du type de case et du joueur
        // haut -------------------------------------------------- 
        print("╔═════");
        for (int i=1;i<len;i++) {
            print("╦═════");
        }
        println("╗");
        // joueur ------------------------------------------------
        for (int i=0;i<len;i++) {
            if (i == joueur.position) {
                print("║  " + joueur.nom + "  ");
            } else {
                print("║     ");
            }
        }
        println("║");
        // type de case ------------------------------------------
        for (int i=0;i<len;i++) {
            print("║  " + map[i] + "  ");
            // print("║      ");
        }
        println("║");
        // bas ---------------------------------------------------
        print("╚═════");
        for (int i=1;i<len;i++) {
            print("╩═════");
        }
        println("╝");
    }

    int lancerDe(int taille) {
        return (int) (random() * taille + 1);
    }

    void effectuerDeplacement(int deplacement) {
        joueur.position += deplacement;
        if (joueur.position < 0) joueur.position = 0;
    }

    void lancerEvent(char[] map) {
        char caseactuelle = map[joueur.position];
        if (caseactuelle == BOOSTER) {
            eventBooster();
        } else if (caseactuelle == RALENTISSEUR) {
            eventRalentisseur();
        } else if (caseactuelle == AJOUTPOINTS) {
            eventAjoutPoints();
        } else if (caseactuelle == SUPPRESSIONPOINTS) {
            eventSupressionPoints();
        } else if (caseactuelle == MINIJEU) {
            eventMiniJeu();
        }
    }

    void eventBooster() {
        // dé
        int randint = (int) (random() * 3 + 1);
        effectuerDeplacement(randint);
    }

    void eventRalentisseur() {
        // dé
        int randint = (int) (random() * 3 - 3);
        effectuerDeplacement(randint);
    }

    // PAS ENCORE IMPLANTABLE (donc vide)

    void eventAjoutPoints() {
        ;
    }

    void eventSupressionPoints() {
        ;
    }

    void eventMiniJeu() {
        ;
    }

    void clearTerminal() {
        print("\033[H\033[2J");
        System.out.flush();
    }

    void algorithm() {

        // PARAMETRAGE
        clearTerminal();
        print("\n\n\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("Bienvenue dans Culture Party");
        print("\n\n\n");

        for (int i=0;i<132/2-21;i++) print(" ");
        println("Veuillez sélectionner une taille de Carte.\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("10     |     15     |     20");
        int taillemap = 0;
        while (taillemap != 10 && taillemap != 15 && taillemap != 20) taillemap = readInt();
        char[] map = creerMapRoute66(taillemap);
        clearTerminal();

        print("\n\n\n");
        for (int i=0;i<132/2-23;i++) print(" ");
        println("Veuillez sélectionner le nombre de faces du dé\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("3       |       6");
        int taillede = 0;
        while (taillede != 3 && taillede != 6) taillede = readInt();

        // JEU
        while (joueur.position < taillemap - 1) {
            clearTerminal();
            afficherMap(map);
            for (int i=0;i<132/2-18;i++) print(" ");
            print("\n\n\n");
            println("Appuyez sur Entrer pour lancer le dé");
            readString();
            effectuerDeplacement(lancerDe(taillede));
            lancerEvent(map);
        }
        afficherMap(map);
    }
}

// il faudra écrire des fonctions test