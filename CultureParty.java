import extensions.CSVFile;

class CultureParty extends Program {
    final char VIDE = ' ';
    final char BOOSTER = 'B';
    final char RALENTISSEUR = 'R';
    final char MINIJEU = 'J';
    Joueur joueur = new Joueur();


    // QuestionVide[] qvides = new QuestionVide[]{creerQuestionVide("Quel est le nom de la planète sur laquelle nous vivons ?", "La Terre", "Mars", "La Lune", "Le Japon", 'A', "Notre planète s'appelle La Terre."),
    //                                         creerQuestionVide("Quel est le nom du satellite naturel de la Terre ?", "Mars", "Jupiter", "La Lune", "Spoutnik", 'C', "Le satellite naturel de la Terre est la Lune."),
    //                                         creerQuestionVide("Vrai ou Faux ? Le corps humain est composé à environ 70% d'eau.", "Vrai", "Faux", 'A', "Pour un humain adulte, le corps humain est composé d'environ 70% d'eau.")};

    QuestionVide[] qvides = creerQuestionsCasesVides();

    // UTILISER FONCTION CREER QUESTION
    QuestionVide[] creerQuestionsCasesVides() {
        CSVFile csvqvides = loadCSV("./questions/QuestionsVide.csv");
        int csvqvidesheight = rowCount(csvqvides) - 1;
        int csvqvideswidth = columnCount(csvqvides);
        QuestionVide[] qvidebdd = new QuestionVide[csvqvidesheight];
        for (int i=1;i<csvqvidesheight;i++) {
            qvidebdd[i].question = getCell(csvqvides, i, 1);
            if (equals(getCell(csvqvides, i, 3), "")) {
                qvidebdd[i].choix = new String[2];
                qvidebdd[i].choix[1] = getCell(csvqvides, i, 1);
                qvidebdd[i].choix[2] = getCell(csvqvides, i, 2);
            } else {
                qvidebdd[i].choix = new String[4];
                qvidebdd[i].choix[1] = getCell(csvqvides, i, 1);
                qvidebdd[i].choix[2] = getCell(csvqvides, i, 2);
                qvidebdd[i].choix[3] = getCell(csvqvides, i, 3);
                qvidebdd[i].choix[4] = getCell(csvqvides, i, 4);
            }
            qvidebdd[i].rep = charAt(getCell(csvqvides, i, 5), 0);
            qvidebdd[i].explication = getCell(csvqvides, i, 6);
        }
        return qvidebdd;
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
        for (int i=0;i<len;i++) {
            print("║  ");
            if (map[i] == BOOSTER) {
                text("green");
            } else if (map[i] == RALENTISSEUR) {
                text("yellow");
            } else if (map[i] == MINIJEU) {
                text("blue");
            }
            print(map[i] + "");
            reset();
            print("  ");
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

    QuestionVide creerQuestionVide(String question, String choix1, String choix2, char rep, String explication) {  // 2 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[2];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.rep = rep;
        q.explication = explication;
        return q;
    }

    QuestionVide creerQuestionVide(String question, String choix1, String choix2, String choix3, String choix4, char rep, String explication) {  // 4 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[4];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.choix[2] = choix3;
        q.choix[3] = choix4;
        q.rep = rep;
        q.explication = explication;
    void affichageCaseActuelle(char[] map) {
        char caseactuelle = map[joueur.position];
        print("\n\nVous êtes tombé sur une case ");
        if (caseactuelle == BOOSTER) {
            print("Booster!\nVous allez avancer d'un nombre de cases aléatoire et gagner des pièces!");
        } else if (caseactuelle == RALENTISSEUR) {
            print("Ralentisseur...\nVous allez reculer d'un nomb
    QuestionVide creerQuestionVide(String question, String choix1, String choix2, char rep, String explication) {  // 2 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[2];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.rep = rep;
        q.explication = explication;
        return q;
    }

    QuestionVide creerQuestionVide(String question, String choix1, String choix2, String choix3, String choix4, char rep, String explication) {  // 4 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[4];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.choix[2] = choix3;
        q.choix[3] = choix4;
        q.rep = rep;
        q.explication = explication;re de cases aléatoire.");
        } else if (caseactuelle == MINIJEU) {
            print("Mini-Jeu!");
        } else {
            print("vide.\nVous devez répondre à une question pour gagner un point!");
        }
        readString();
    }

    QuestionVide creerQuestionVide(String question, String choix1, String choix2, char rep, String explication) {  // 2 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[2];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.rep = rep;
        q.explication = explication;
        return q;
    }

    QuestionVide creerQuestionVide(String question, String choix1, String choix2, String choix3, String choix4, char rep, String explication) {  // 4 choix
        QuestionVide q = new QuestionVide();
        q.question = question;
        q.choix = new String[4];
        q.choix[0] = choix1;
        q.choix[1] = choix2;
        q.choix[2] = choix3;
        q.choix[3] = choix4;
        q.rep = rep;
        q.explication = explication;
        return q;
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
        int randint = (int) (random() * 3 - 3);
        effectuerDeplacement(randint, map);
        affichageCaseActuelle(map);
        lancerEvent(map);
    }

    void eventMiniJeu() {
        ;
    }

    void eventQuestion() {
        println("\n\n");
        QuestionVide randomq = qvides[(int) (random() * length(qvides))];
        println(randomq.question);
        println("\n");
        for (char i='A';i<length(randomq.choix)+'A';i=(char) (i+1)) {
            println(i + " : " + randomq.choix[i - 'A']);
        }
        println();
        char guess = ' ';
        do {
            try {
                print("Entrez votre réponse : ");
                guess = readChar();
            } catch (Exception e) {
                print("Erreur. ");
            }
        } while (guess < 'A' || guess > 'A' + length(randomq.choix));
        if (guess == randomq.rep) {
            joueur.pieces++;
            println("\nBonne réponse! Vous gagnez une pièce.");
            println(randomq.explication);
            println("\nVous avez maintenant " + joueur.pieces + " pièces!");
        } else {
            println("\nMauvaise réponse...");
            println(randomq.explication);
        }
        readString();
    }

    void clearTerminal() {
        print("\033[H\033[2J");
        System.out.flush();
    }

    void afficherTexte(String s) {
        int len = length(s);
        for (int i=0;i<len;i++) {
            print(charAt(s, i));
            delay(50);
        }
    }

    void algorithm() {

        // PARAMETRAGE
        clearTerminal();
        print("\n\n\n");
        for (int i=0;i<132/2-13;i++) print(" ");
        println("══════════════════════════");
        for (int i=0;i<132/2-14;i++) print(" ");
        // ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ║     ║     ║     ║     ║     ║     ║     ║     ║     ║     ║
        // ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝
        println("Bienvenue dans Culture Party");
        for (int i=0;i<132/2-13;i++) print(" ");
        println("══════════════════════════");
        print("\n\n\n");

        for (int i=0;i<132/2-21;i++) print(" ");
        println("Veuillez sélectionner une taille de Carte.\n");
        for (int i=0;i<132/2-14;i++) print(" ");
        println("10     |     15     |     20");
        int taillemap = 0;
        do {
            try {
                taillemap = readInt();
            } catch (Exception e) {
                print("Erreur. ");
            }
        } while (taillemap != 10 && taillemap != 15 && taillemap != 20);
        char[] map = creerMapRoute66(taillemap);
        clearTerminal();

        print("\n\n\n");
        for (int i=0;i<132/2-23;i++) print(" ");
        println("Veuillez sélectionner le nombre de faces du dé\n");
        for (int i=0;i<132/2-8;i++) print(" ");
        println("3       |       6");
        int taillede = 0, resde;
        while (taillede != 3 && taillede != 6) taillede = readInt();
        clearTerminal();



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
            affichageCaseActuelle(map);
            lancerEvent(map);
            print("\n\n");
            if (map[joueur.position] != VIDE) {
                clearTerminal();
                afficherMap(map);
            }
        }
        println("Bravo! Vous avez fini cette partie avec un total de " + joueur.pieces + " pièces!");

    }
}

// il faudra écrire des fonctions test