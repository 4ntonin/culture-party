class CultureParty extends Program {
    // static char VIDE = 'V';
    // static char BOOSTER = 'B';
    // static char RALENTISSEUR = 'R';
    // static char AJOUTPOINTS = 'A';
    // static char SUPPRESSIONPOINTS = 'S';

    char[] creerMap(int taille) {
        return new char[taille];
    }

    void afficherMap(char[] map, Joueur joueur) {
        // ╔╗╚╝═║╦╩╠╣
        // println("╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗");
        // println("║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║");
        // println("║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║");
        // println("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝");

        int len = length(map);
        // utilisation de boucles for pour pouvoir gérer l'affichage du type de case et du joueur
        // haut
        print("╔══════");
        for (int i=1;i<len;i++) {
            print("╦══════");
        }
        println("╗");
        // joueur
        for (int i=0;i<len;i++) {
            if (i == joueur.position) {
                print("║  " + joueur.nom + "   ");
            } else {
                print("║      ");
            }
        }
        println("║");
        // type de case
        for (int i=0;i<len;i++) {
            print("║      ");
        }
        println("║");
        // bas
        print("╚══════");
        for (int i=1;i<len;i++) {
            print("╩══════");
        }
        println("╝");
    }

    Joueur creerJoueur() {
        Joueur joueur = new Joueur();
        return joueur;
    }

    void effectuerDeplacement(Joueur joueur) {
        joueur.position++;
    }

    void algorithm() {
        char[] map = creerMap(10);
        Joueur joueur = creerJoueur();
        afficherMap(map, joueur);
        effectuerDeplacement(joueur);
        afficherMap(map, joueur);
    }
}