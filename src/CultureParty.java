import extensions.CSVFile;

class CultureParty extends Program {
    final char VIDE = ' ';
    final char BOOSTER = '▶';
    final char RALENTISSEUR = '㐅';
    final char MINIJEU = '㐃';
    // ➫★⚝➜➕➖❌✕✖✗✰🌐🌞🎲🎱🠞🡆＋₊⚡乛㐃㐅▶✘⛒◌✪
    Joueur joueur = new Joueur();
    GenerationMap fonctionsMap = new GenerationMap();

    QuestionVide[] qvides = creerQuestionsCasesVides();

    QuestionVide[] creerQuestionsCasesVides() {
        CSVFile csv = loadCSV("../ressources/QuestionsVide.csv");
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

    int lancerDe(int taille) {
        return (int) (random() * taille + 1);
    }

    void effectuerDeplacement(int deplacement, Map map) {
        int i = 0, plus = 1;
        if (deplacement < 0) {
            plus = -1;
            deplacement = abs(deplacement);
        }
        while (i < deplacement && joueur.position < map.taille-1) {
            joueur.position += plus;
            i++;
            clearTerminal();
            text("yellow");
            println();
            afficherTexte("Pièces : " + joueur.pieces + "\n");
            reset();
            fonctionsMap.joueur = joueur;
            fonctionsMap.afficherMap(map);
            delay(500);
        }
        if (joueur.position < 0) joueur.position = 0;
    }

    void affichageCaseActuelle(Map map) {
        char caseactuelle = map.cases[joueur.position].type;
        println("\n");
        if (caseactuelle == BOOSTER) {
            afficherTexte("Tu es tombé sur une case Booster!");
            afficherTexte("Tu vas avancer d'un nombre de cases aléatoire et gagner des pièces !");
        } else if (caseactuelle == RALENTISSEUR) {
            afficherTexte("Tu es tombé sur une case Ralentisseur...");
            afficherTexte("Tu vas reculer d'un nombre de cases aléatoire.");
        } else if (caseactuelle == MINIJEU) {
            afficherTexte("Tu es tombé sur une case Mini-Jeu !");
        } else {
            afficherTexte("Tu es tombé sur une case vide.");
            afficherTexte("Tu dois répondre à une question pour gagner un point !");
        }
        readString();
    }

    void lancerEvent(Map map) {
        char caseactuelle = map.cases[joueur.position].type;
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

    void eventBooster(Map map) {
        // dé ?
        int randint = (int) (random() * 3 + 1);
        joueur.pieces += randint;
        effectuerDeplacement(randint, map);
        affichageCaseActuelle(map);
        lancerEvent(map);
    }

    void eventRalentisseur(Map map) {
        // dé ?
        int randint = (int) (random() * 3 - 4);
        effectuerDeplacement(randint, map);
        affichageCaseActuelle(map);
        lancerEvent(map);
    }

    void eventMiniJeu() {
        int choix_jeu = (int)(random()*3);
        if(choix_jeu == 0){
            Amogus jeuamogus = new Amogus();
            joueur.pieces += jeuamogus.lancerJeu();
        }else if(choix_jeu == 1){
            Unolingo jeuunolingo = new Unolingo();
            joueur.pieces += jeuunolingo.lancerJeu(); 
        }else{
            JeuGrammaire jeugrammaire = new JeuGrammaire();
            joueur.pieces += jeugrammaire.lancerJeu(); 

        }
    }

    void eventQuestion() {
        println("\n\n");
        QuestionVide randomq = qvides[(int) (random() * length(qvides))];
        int taillequestion = length(randomq.choix);
        afficherTexte(randomq.question);
        println("\n");
        for (char i='A';i<taillequestion+'A';i=(char) (i+1)) {
            afficherTexte(i + " : " + randomq.choix[i - 'A']);
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
        println();
        if (guess == randomq.rep) {
            joueur.pieces++;
            afficherTexte("Bonne réponse! Tu gagnes une pièce.");
            afficherTexte(randomq.explication);
            afficherTexte("Tu as maintenant " + joueur.pieces + " pièces!");
        } else {
            afficherTexte("Mauvaise réponse...");
            afficherTexte(randomq.explication);
        }
        readString();
    }

    void clearTerminal() {
        print("\033[H\033[2J");
        System.out.flush();
    }

    void afficherTexte(String s) {
        int len = length(s);
        for (int i=0;i<190/2-len/2;i++) print(" ");
        println(s);
    }

    void algorithm() {

        // PARAMETRAGE
        clearTerminal();
        print("\n\n\n\n\n");
        afficherTexte("══════════════════════════");
        afficherTexte("Bienvenue dans Culture Party");
        afficherTexte("══════════════════════════");
        print("\n\n\n\n\n");
        afficherTexte("┌─────────────────────┐");
        afficherTexte("│  Appuie sur Entrée  │");
        afficherTexte("└─────────────────────┘");
        readString();

        Map map = fonctionsMap.creerMap();
        clearTerminal();

        int resde;
        fonctionsMap.joueur = joueur;

        print("\n\n\n");
        afficherTexte("Voici quelques indications :");
        afficherTexte("____________________________________________\n\n");
        text("red");
        for (int i=0;i<190/2-7;i++) print(" ");
        print(joueur.nom);
        reset();
        println(" : C'est toi !\n");
        text("green");
        for (int i=0;i<190/2-42;i++) print(" ");
        print(BOOSTER + "");
        reset();
        print(" : C'est un ");
        text("green");
        print("booster");
        reset();
        println(". Il te fera avancer de quelques cases et te donnera des pièces !\n");
        text("yellow");
        for (int i=0;i<190/2-31;i++) print(" ");
        print(RALENTISSEUR + "");
        reset();
        print(" : C'est un ");
        text("yellow");
        print("ralentisseur");
        reset();
        println(". Il te fera reculer de quelques cases.\n");
        text("blue");
        for (int i=0;i<190/2-23;i++) print(" ");
        print(MINIJEU + "");
        reset();
        print(" : Cette case lancera un ");
        text("blue");
        print("mini-jeu");
        reset();
        println(" aléatoire !\n");
        for (int i=0;i<190/2-95/2;i++) print(" ");
        print("Quand tu tomberas sur une case vide, tu devras répondre à une question pour gagner des ");
        text("yellow");
        print("pièces");
        reset();
        println(" !\n\n");
        afficherTexte("À tout moment dans la partie, avant de lancer le dé, tu peux sauvegarder ta progression en entrant \"SAVE\"");
        print("\n\n\n\n\n\n\n");
        afficherTexte("┌──────────────────────────────────────┐");
        afficherTexte("│ Appuie sur Entrée pour lancer le jeu │");
        afficherTexte("└──────────────────────────────────────┘");
        readString();

        // JEU
        while (joueur.position < map.taille - 1) {
            clearTerminal();
            text("yellow");
            println();
            afficherTexte("Pièces : " + joueur.pieces + "\n");
            reset();
            fonctionsMap.joueur = joueur;
            fonctionsMap.afficherMap(map);
            for (int i=0;i<190/2-18;i++) print(" ");
            print("\n\n\n");
            afficherTexte("Appuies sur Entrée pour lancer le dé");
            readString();
            // String ecrit =readString();
            // if (equals(ecrit,"SAVE")){
            //     void saveCSV(String[][] tab = new String[][]{carte,nbpieces,positionsJoueur},{map,joueur.pieces,joueur.position}, String "./Save/SaveGame.csv")
            // }
            print("\n\n");
            resde = lancerDe(6);
            afficherTexte("Tu as fait " + resde + " !");
            readString();
            effectuerDeplacement(resde, map);
            affichageCaseActuelle(map);
            lancerEvent(map);
            print("\n\n");
            if (map.cases[joueur.position].type != VIDE) {
                clearTerminal();
                text("yellow");
                println();
                afficherTexte("Pièces : " + joueur.pieces + "\n");
                reset();
                fonctionsMap.joueur = joueur;
                fonctionsMap.afficherMap(map);
            }
        }
        for (int i=0;i<190/2-62/2;i++) print(" ");
        print("Bravo ! Tu as fini cette partie avec un total de ");
        text("yellow");
        print(joueur.pieces + " pièces !\n");
        reset();
        readString();
        clearTerminal();
    }
}
