import extensions.CSVFile;

class CultureParty extends Program {
    final char VIDE = ' ';
    final char BOOSTER = '▶';
    final char RALENTISSEUR = '㐅';
    final char MINIJEU = '㐃';
    // ➫★⚝➜➕➖❌✕✖✗✰🌐🌞🎲🎱🠞🡆＋₊⚡乛㐃㐅▶✘⛒◌✪
    Joueur joueur = new Joueur();

    QuestionVide[] qvides = creerQuestionsCasesVides();

    QuestionVide[] creerQuestionsCasesVides() {
        CSVFile csv = loadCSV("./questions/QuestionsVide.csv");
        int csvheight = rowCount(csv) - 1;
        QuestionVide[] bdd = new QuestionVide[csvheight];
        QuestionVide q;
        for (int i=1;i<csvheight+1;i++) {
            q = new QuestionVide();
            q.question = getCell(csv, i, 0);
            if (equals(getCell(csv, i, 3), "-")) {
                q.choix = new String[2];
                q.choix[0] = getCell(csv, i, 1);
                q.choix[1] = getCell(csv, i, 2);
            } else {
                q.choix = new String[4];
                q.choix[0] = getCell(csv, i, 1);
                q.choix[1] = getCell(csv, i, 2);
                q.choix[2] = getCell(csv, i, 3);
                q.choix[3] = getCell(csv, i, 4);
            }
            q.rep = charAt(getCell(csv, i, 5), 0);
            q.explication = getCell(csv, i, 6);
            bdd[i-1] = q;
        }
        return bdd;
    }

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
            if (i%4 == 0) {
                map[randompos] = BOOSTER;
            } else if (i%4 == 1) {
                map[randompos] = RALENTISSEUR;
            } else {
                map[randompos] = MINIJEU;
            }
        }
        return map;
    }

    void afficherMap(char[] map) {
        println("Pièces : " + joueur.pieces);
        // ╔╗╚╝═║╦╩╠╣    = caractères utilisables pour la map
        // Exemple de Route 66:
        // ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝

        int len = length(map);
        // utilisation de boucles for pour pouvoir gérer l'affichage du type de case et du joueur
        println("\n");
        // haut -------------------------------------------------- 
        print("╔═════");
        for (int i=1;i<len;i++) {
            print("╦═════");
        }
        println("╗");
        // joueur ------------------------------------------------
        for (int i=0;i<len;i++) {
            if (i == joueur.position) {
                print("║  ");
                text("red");
                print(joueur.nom);
                reset();
                print("  ");
            } else {
                print("║     ");
            }
        }
        println("║");
        // type de case ------------------------------------------
        String completion;
        for (int i=0;i<len;i++) {
            print("║  ");
            completion = "  ";
            if (map[i] == BOOSTER) {
                text("green");
            } else if (map[i] == RALENTISSEUR) {
                text("yellow");
                completion = " ";
            } else if (map[i] == MINIJEU) {
                text("blue");
                completion = " ";
            }
            print(map[i] + completion);
            reset();
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
            delay(500);
        }
        if (joueur.position < 0) joueur.position = 0;
    }

    void affichageCaseActuelle(char[] map) {
        char caseactuelle = map[joueur.position];
        print("\n\nTu es tombé sur une case ");
        if (caseactuelle == BOOSTER) {
            print("Booster!\nTu vas avancer d'un nombre de cases aléatoire et gagner des pièces !");
        } else if (caseactuelle == RALENTISSEUR) {
            print("Ralentisseur...\nTu vas reculer d'un nombre de cases aléatoire.");
        } else if (caseactuelle == MINIJEU) {
            print("Mini-Jeu !");
        } else {
            print("vide.\nTu dois répondre à une question pour gagner un point !");
        }
        readString();
    }

    void lancerEvent(char[] map) {
        char caseactuelle = map[joueur.position];
        if (caseactuelle == BOOSTER) {
            eventBooster(map);
        } else if (caseactuelle == RALENTISSEUR) {
            eventRalentisseur(map);
        } else if (caseactuelle == MINIJEU) {
            eventMiniJeu();
        } else {
            eventQuestion();
        }
    }

    void eventBooster(char[] map) {
        // dé ?
        int randint = (int) (random() * 3 + 1);
        joueur.pieces += randint;
        effectuerDeplacement(randint, map);
        affichageCaseActuelle(map);
        lancerEvent(map);
    }

    void eventRalentisseur(char[] map) {
        // dé ?
        int randint = (int) (random() * 3 - 4);
        effectuerDeplacement(randint, map);
        affichageCaseActuelle(map);
        lancerEvent(map);
    }

    void eventMiniJeu() {
        int choix_jeu = (int)(random()*2);
        if(choix_jeu == 1){
            Amogus jeuamogus = new Amogus();
            joueur.pieces += jeuamogus.lancerJeu();
        }else{
            Unolingo jeuunolingo = new Unolingo();
            joueur.pieces += jeuunolingo.lancerJeu(); 
        }
    }

    void eventQuestion() {
        println("\n\n");
        QuestionVide randomq = qvides[(int) (random() * length(qvides))];
        int taillequestion = length(randomq.choix);
        print(randomq.question);
        println("\n");
        for (char i='A';i<taillequestion+'A';i=(char) (i+1)) {
            print(i + " : " + randomq.choix[i - 'A'] + "\n");
        }
        println();
        String guessinput = " ";
        if (taillequestion == 2) {
            while (!equals(toUpperCase(guessinput), "A") && !equals(toUpperCase(guessinput), "B")) {
                guessinput = readString();
                guessinput = toUpperCase(guessinput);
            }
        } else {
            while (!equals(toUpperCase(guessinput), "A") && !equals(toUpperCase(guessinput), "B") && !equals(toUpperCase(guessinput), "C") && !equals(toUpperCase(guessinput), "D")){
                guessinput = readString();
                guessinput = toUpperCase(guessinput);
            }
        }
        char guess = charAt(guessinput, 0); 
        if (guess == randomq.rep) {
            joueur.pieces++;
            print("\nBonne réponse! Tu gagnes une pièce.\n");
            print(randomq.explication);
            print("\nTu as maintenant " + joueur.pieces + " pièces!");
        } else {
            print("\nMauvaise réponse...\n");
            print(randomq.explication);
        }
        readString();
    }

    void clearTerminal() {
        print("\033[H\033[2J");
        System.out.flush();
    }

    // void afficherTexte(String s) {
    //     int len = length(s);
    //     for (int i=0;i<len;i++) {
    //         print(charAt(s, i));
    //         delay(25);
    //     }
    // }

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

        for (int i=0;i<132/2-16;i++) print(" ");
        println("Sélectionne une taille de Carte.\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("10     |     15     |     20");
        String taillemapinput = "";
        while (!equals(taillemapinput, "10") && !equals(taillemapinput, "15") && !equals(taillemapinput, "20")) taillemapinput = readString();
        int taillemap = stringToInt(taillemapinput);
        char[] map = creerMapRoute66(taillemap);
        clearTerminal();

        print("\n\n\n");
        for (int i=0;i<132/2-18;i++) print(" ");
        println("Sélectionne le nombre de faces du dé\n");
        for (int i=0;i<132/2-8;i++) print(" ");
        println("3       |       6");
        int resde;
        String tailledeinput = "0";
        while (!equals(tailledeinput, "3") && !equals(tailledeinput, "6")) tailledeinput = readString();
        int taillede = stringToInt(tailledeinput);
        clearTerminal();

        print("\n\n\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("Voici quelques indications :");
        for (int i=0;i<132/2-22;i++) print(" ");
        println("____________________________________________\n\n");
        text("red");
        for (int i=0;i<132/2-7;i++) print(" ");
        print(joueur.nom);
        reset();
        println(" : C'est toi !\n");
        text("green");
        for (int i=0;i<132/2-42;i++) print(" ");
        print(BOOSTER + "");
        reset();
        println(" : C'est un booster. Il te fera avancer de quelques cases et te donnera des pièces !\n");
        text("yellow");
        for (int i=0;i<132/2-31;i++) print(" ");
        print(RALENTISSEUR + "");
        reset();
        println(" : C'est un ralentisseur. Il te fera reculer de quelques cases.\n");
        text("blue");
        for (int i=0;i<132/2-23;i++) print(" ");
        print(MINIJEU + "");
        reset();
        println(" : Cette case lancera un mini-jeu aléatoire !\n");
        for (int i=0;i<132/2-95/2;i++) print(" ");
        println("Quand tu tomberas sur une case vide, tu devras répondre à une question pour gagner des pièces !\n");
        for (int i=0;i<132/2-105/2;i++) print(" ");
        print("À tout moment dans la partie, avant de lancer le dé, tu peux sauvegarder ta progression en entrant \"SAVE\"");
        print("\n\n\n\n\n\n\n");
        // ┌ ─ │ ┐ └ ┘
        for (int i=0;i<132/2-21;i++) print(" ");
        println("┌───────────────────────────────────────┐");
        for (int i=0;i<132/2-21;i++) print(" ");
        println("│ Appuies sur Entrée pour lancer le jeu │");
        for (int i=0;i<132/2-21;i++) print(" ");
        println("└───────────────────────────────────────┘");
        readString();

        // JEU
        while (joueur.position < taillemap - 1) {
            clearTerminal();
            afficherMap(map);
            for (int i=0;i<132/2-18;i++) print(" ");
            print("\n\n\n");
            print("Appuies sur Entrée pour lancer le dé");
            String ecrit =readString();
            if (equals(ecrit,"SAVE")){
                void saveCSV(String[][] tab = new String[][]{carte,nbpieces,positionsJoueur},{map,joueur.pieces,joueur.position}, String "./Save/SaveGame.csv")
            }
            print("\n\n");
            resde = lancerDe(taillede);
            print("Tu as fait " + resde + " !");
            readString();
            effectuerDeplacement(resde, map);
            affichageCaseActuelle(map);
            lancerEvent(map);
            print("\n\n");
            if (map[joueur.position] != VIDE) {
                clearTerminal();
                afficherMap(map);
            }
        }
        print("Bravo ! Tu as fini cette partie avec un total de " + joueur.pieces + " pièces !\n");
        readString();
        clearTerminal();
    }
}
