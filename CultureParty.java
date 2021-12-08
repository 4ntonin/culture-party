class CultureParty extends Program {
    static char VIDE = ' ';
    static char BOOSTER = 'B';
    static char RALENTISSEUR = 'R';
    static char AJOUTPOINTS = 'A';
    static char SUPPRESSIONPOINTS = 'S';
    static char MINIJEU = 'J';

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


    void afficherMap(char[] map, Joueur joueur) {
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

    Joueur creerJoueur() {
        Joueur joueur = new Joueur();
        return joueur;
    }

    void effectuerDeplacement(Joueur joueur, int deplacement) {
        joueur.position += deplacement;
        if (joueur.position < 0) joueur.position = 0;
    }

    void algorithm() {  // algo de test du jeu (même s'il faudra écrire des fonctions test pour la note ;) )
        char[] map = creerMapRoute66(13);  // peut-être laisser le joueur choisir entre 3 tailles genre 10, 15, 20
        Joueur joueur = creerJoueur();
        afficherMap(map, joueur);
        int randdeplacement = (int) (random() * 5 - 2);  // effectué ici en test mais sera effectué plus tard
        if (randdeplacement == 0) randdeplacement = 1;   // à l'aide des cases évènements
        effectuerDeplacement(joueur, randdeplacement);
        afficherMap(map, joueur);
    }
}