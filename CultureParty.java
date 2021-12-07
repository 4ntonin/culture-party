class CultureParty extends Program {
    // static char VIDE = 'V';
    // static char BOOSTER = 'B';
    // static char RALENTISSEUR = 'R';
    // static char AJOUTPOINTS = 'A';
    // static char SUPPRESSIONPOINTS = 'S';

    char[] creerMap(int taille) {
        return new char[taille];
    }

    void afficherMap(char[] map) {
        // ╔╗╚╝═║╦╩╠╣
        // println("╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗");
        // println("║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║");
        // println("║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║");
        // println("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝");

        int len = length(map);
        // utilisation de boucles for pour pouvoir gérer l'affichage du type de case et du joueur
        // haut
        print("╔═════");
        for (int i=1;i<len;i++) {
            print("╦═════");
        }
        println("╗");
        // joueur
        for (int i=0;i<len;i++) {
            print("║     ");
        }
        println("║");
        // type de case
        for (int i=0;i<len;i++) {
            print("║     ");
        }
        println("║");
        // bas
        print("╚═════");
        for (int i=1;i<len;i++) {
            print("╩═════");
        }
        println("╝");
    }

    void algorithm() {
        char[] map = creerMap(15);
        afficherMap(map);
    }
}