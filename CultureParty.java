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
                randompos = (int) (random() * (taille - 2) + 1);
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

    void effectuerDeplacement(int deplacement, char[] map) {
        int i = 0, plus = 1, taillemap = length(map);
        if (deplacement < 0) {
            plus = -1;
            deplacement = abs(deplacement);
        }
        while (i < deplacement && joueur.position < taillemap-1) {
            joueur.position += plus;
            i++;
            clearTerminal();
            afficherMap(map);
            readString();  // A REMPLACER PAR FONCTION TIME pour attendre 0.5sec avant d'avancer
        }
        if (joueur.position < 0) joueur.position = 0;
    }

    void lancerEvent(char[] map) {
        char caseactuelle = map[joueur.position];
        if (caseactuelle == BOOSTER) {
            eventBooster(map);
        } else if (caseactuelle == RALENTISSEUR) {
            eventRalentisseur(map);
        } else if (caseactuelle == AJOUTPOINTS) {
            eventAjoutPoints();
        } else if (caseactuelle == SUPPRESSIONPOINTS) {
            eventSupressionPoints();
        } else if (caseactuelle == MINIJEU) {
            eventMiniJeu();
        } else {
            eventQuestion();
        }
    }

    void eventBooster(char[] map) {
        // dé
        int randint = (int) (random() * 3 + 1);
        effectuerDeplacement(randint, map);
    }

    void eventRalentisseur(char[] map) {
        // dé
        int randint = (int) (random() * 3 - 3);
        effectuerDeplacement(randint, map);
    }

    // PAS ENCORE IMPLANTABLES (donc vides)
    void eventAjoutPoints() {
        ;
    }

    void eventSupressionPoints() {
        ;
    }

    void eventMiniJeu() {
        ;
    }

    void eventQuestion() {
        ; // poser une question si case vide
    }

    void clearTerminal() {
        print("\033[H\033[2J");
        System.out.flush();
    }

    void algorithm() {

        // PARAMETRAGE
        clearTerminal();
        print("\n\n\n");
        for (int i=0;i<132/2-13;i++) print(" ");
        println("══════════════════════════");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("Bienvenue dans Culture Party");
        for (int i=0;i<132/2-13;i++) print(" ");
        println("══════════════════════════");
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
        for (int i=0;i<132/2-8;i++) print(" ");
        println("3       |       6");
        int taillede = 0, resde;
        while (taillede != 3 && taillede != 6) taillede = readInt();

        // JEU
        while (joueur.position < taillemap - 1) {
            clearTerminal();
            afficherMap(map);
            for (int i=0;i<132/2-18;i++) print(" ");
            print("\n\n\n");
            println("Appuyez sur Entrer pour lancer le dé");
            readString();
            print("\n\n");
            resde = lancerDe(taillede);
            println("Vous avez fait " + resde + " !");
            readString();
            effectuerDeplacement(resde, map);
            print("Vous êtes tombé sur une case ");
            if (map[joueur.position] == BOOSTER) {
                print("Booster!\nVous allez avancer d'un nombre de cases aléatoire.");
            } else if (map[joueur.position] == RALENTISSEUR) {
                print("Ralentisseur...\nVous allez reculer d'un nombre de cases aléatoire.");
            } else if (map[joueur.position] == AJOUTPOINTS) {
                print("Ajout de points!\nVous allez recevoir un nombre de points aléatoire.");
            } else if (map[joueur.position] == SUPPRESSIONPOINTS) {
                print("Supression de points...\nVous allez perdre un nombre de points aléatoire.");
            } else if (map[joueur.position] == MINIJEU) {
                print("Mini-Jeu!");
            } else {
                print("vide.\nVous devez répondre à une question pour gagner un point!");
            }
            readString();
            lancerEvent(map);
            print("\n\n");
            readString();
            if (map[joueur.position] != VIDE) {
                clearTerminal();
                afficherMap(map);
            }
        }
        println("Bravo! Vous avez fini cette partie avec un total de ?? points!");

    }
}

// il faudra écrire des fonctions test