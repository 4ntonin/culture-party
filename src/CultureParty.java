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
    String[][] bddScore = recupBddScore();

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
    String[][] recupBddScore(){
        CSVFile csv = loadCSV("../ressources/TopScore.csv");
        int csvheight = rowCount(csv);
        String[][] bddScore = new String[csvheight][2];
        for (int i=0;i<csvheight;i++) {
            bddScore[i][0] = getCell(csv, i, 0);
            bddScore[i][1] = getCell(csv, i, 1);
        }
        return bddScore;
    }

    String[][] modifTabClassement(String nom,int score,String[][] tab){
        int cpt = 0;
        while(cpt<5 && score<stringToInt(tab[cpt][1])){
            cpt = cpt +1;
        }
        if(cpt<4){
            for(int i=3;i>=cpt;i=i-1){
                tab[i+1][0]=tab[i][0];
                tab[i+1][1]=tab[i][1];
            }
            tab[cpt][0] = nom;
            tab[cpt][1] = ""+score;
        }
        return tab;
    }

    void afficherTableauScore(String[][] tab){
        for(int i=0;i<5;i=i+1){
            afficherTexte((int)(i+1)+" : " +tab[i][0]+" | "+tab[i][1]);
        }
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
        hide();
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
        show();
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
                clearLines(1);
            }
            clearLines(7);
        } else {
            while (!equals(toUpperCase(guessinput), "A") && !equals(toUpperCase(guessinput), "B") && !equals(toUpperCase(guessinput), "C") && !equals(toUpperCase(guessinput), "D")){
                guessinput = readString();
                guessinput = toUpperCase(guessinput);
                clearLines(1);
            }
            clearLines(9);
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
        for (int i=0;i<132/2-len/2;i++) print(" ");
        println(s);
    }

    void afficherTexte(String[] s) {
        int len = length(s);
        for (int i=0;i<len;i++) {
            for (int j=0;j<132/2-length(s[i])/2;j++) print(" ");
            println(s[i]);
        }
    }

    void clearLines(int number) {
        for (int i=0;i<number;i++) {
            up();
            clearLine();
        }
    }

    boolean contient(String s, char c) {
        boolean res = false;
        int i = 0, len = length(s);
        while (!res && i<len) {
            if (charAt(s, i) == c) res = true;
            i++;
        }
        return res;
    }

    void sauvegarder(Map map) {
        String itemsmap = "";
        for (int i=0;i<map.taille;i++) {
            itemsmap += map.cases[i].type;
            itemsmap += map.cases[i].direction;
        }
        String[][] save = new String[][]{{joueur.nom, ""+joueur.pieces,""+joueur.position, itemsmap}};
        saveCSV(save, "../ressources/Save.csv");
    }

    Map charger() {
        CSVFile csvsave = loadCSV("../ressources/Save.csv");
        joueur.nom = getCell(csvsave, 0, 0);
        joueur.pieces = stringToInt(getCell(csvsave, 0, 1));
        joueur.position = stringToInt(getCell(csvsave, 0, 2));
        String itemsmap = getCell(csvsave, 0, 3);
        int len = length(itemsmap), cpt = 0;
        Map map = new Map();
        for (int i=0;i<len;i=i+2) {
            map.cases[cpt] = fonctionsMap.creerCase(charAt(itemsmap, i), charAt(itemsmap, i+1));
            cpt++;
        }
        return map;
    }

    void algorithm() {

        // PARAMETRAGE
        clearTerminal();
        print("\n\nPour une meilleure expérience visuelle, il vous est recommandé de mettre votre terminal en 132x43 caractères.\nSi cela vous est impossible, mettez votre fenêtre en plein écran.\n\n\n\n\n\n");
        println("Une fois cela effectué, appuyez sur Entrée.");
        readString();

        String[] chaine;
        chaine = new String[]{" ,-----.        ,--.  ,--.                            ,------.                  ,--.           ",
                              "'  .--./,--.,--.|  |,-'  '-.,--.,--.,--.--. ,---.     |  .--. ' ,--,--.,--.--.,-'  '-.,--. ,--.",
                              "|  |    |  ||  ||  |'-.  .-'|  ||  ||  .--'| .-. :    |  '--' |' ,-.  ||  .--''-.  .-' \\  '  / ",
                              "'  '--'\\'  ''  '|  |  |  |  '  ''  '|  |   \\   --.    |  | --' \\ '-'  ||  |     |  |    \\   '  ",
                              " `-----' `----' `--'  `--'   `----' `--'    `----'    `--'      `--`--'`--'     `--'  .-'  /   ",
                              "                                                                                      `---'    "};
        hide();
        clearTerminal();
        print("\n\n\n\n\n");
        text("blue");
        afficherTexte(chaine);
        reset();
        print("\n\n\n\n\n");
        afficherTexte("┌─────────────────────┐");
        afficherTexte("│  Appuie sur Entrée  │");
        afficherTexte("└─────────────────────┘");
        readString();

        String inputJoueur, inputJoueurverif;
        joueur.nom = getCell(loadCSV("../ressources/Save.csv"), 0, 0);
        clearLines(4);
        do {
            afficherTexte("1. Nouvelle Partie");
            afficherTexte("2. Charger une partie");
            for (int j=0;j<132/2;j++) print(" ");
            inputJoueur = readString();
            if (equals(inputJoueur, "2")) {
                println();
                clearLines(4);
                do {
                    afficherTexte("Es-tu sûr de vouloir charger la partie de \"" + joueur.nom + "\" ?");
                    println();
                    afficherTexte("1. Je suis sûr");
                    afficherTexte("2. Annuler");
                    inputJoueurverif = readString();
                } while (!equals(inputJoueurverif, "1") && !equals(inputJoueurverif, "2"));
                if (equals(inputJoueurverif, "2")) {
                    inputJoueur = "";
                    clearLines(5);
                }
            }
        } while (!equals(inputJoueur, "1") && !equals(inputJoueur, "2"));
        Map map;
        if (equals(inputJoueur, "2")) {
            map = charger();
        } else {
            map = fonctionsMap.creerMap();
            clearTerminal();
            print("\n\n\n");
            afficherTexte("Voici quelques indications :");
            afficherTexte("____________________________________________\n\n");
            text("red");
            for (int i=0;i<132/2-7;i++) print(" ");
            print(joueur.car);
            reset();
            println(" : C'est toi !\n");
            text("green");
            for (int i=0;i<132/2-42;i++) print(" ");
            print(BOOSTER + "");
            reset();
            print(" : C'est un ");
            text("green");
            print("booster");
            reset();
            println(". Il te fera avancer de quelques cases et te donnera des pièces !\n");
            text("yellow");
            for (int i=0;i<132/2-31;i++) print(" ");
            print(RALENTISSEUR + "");
            reset();
            print(" : C'est un ");
            text("yellow");
            print("ralentisseur");
            reset();
            println(". Il te fera reculer de quelques cases.\n");
            text("blue");
            for (int i=0;i<132/2-23;i++) print(" ");
            print(MINIJEU + "");
            reset();
            print(" : Cette case lancera un ");
            text("blue");
            print("mini-jeu");
            reset();
            println(" aléatoire !\n");
            for (int i=0;i<132/2-95/2;i++) print(" ");
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
            show();
            clearTerminal();
            text("blue");
            print("\n\n\n\n\n\n\n");
            chaine = new String[]{" _______         __                                      __                                        ",
                                  "|    ___|.-----.|  |_.----.-----.-----.    .--.--.-----.|  |_.----.-----.    .-----.-----.--------.",
                                  "|    ___||     ||   _|   _|  -__|-- __|    |  |  |  _  ||   _|   _|  -__|    |     |  _  |        |",
                                  "|_______||__|__||____|__| |_____|_____|     \\___/|_____||____|__| |_____|    |__|__|_____|__|__|__|",};
            afficherTexte(chaine);
            reset();
            print("\n\n\n\n\n");
            do {
                for (int j=0;j<132/2-1;j++) print(" ");
                joueur.nom = readString();
            } while (length(joueur.nom) < 1 || contient(joueur.nom, ','));
            joueur.nom = toUpperCase(joueur.nom);
        }

        clearTerminal();

        int resde;
        fonctionsMap.joueur = joueur;

        inputJoueur = "";

        // JEU
        while (joueur.position < map.taille - 1 && !equals(inputJoueur, "SAVE")) {
            clearTerminal();
            text("yellow");
            println();
            afficherTexte("Pièces : " + joueur.pieces + "\n");
            reset();
            fonctionsMap.joueur = joueur;
            fonctionsMap.afficherMap(map);
            for (int i=0;i<132/2-18;i++) print(" ");
            print("\n\n\n");
            afficherTexte("Appuies sur Entrée pour lancer le dé");
            inputJoueur = readString();
            clearLines(2);
            if (equals(inputJoueur, "SAVE")){
                sauvegarder(map);
            } else {
                print("\n\n");
                resde = lancerDe(6);
                afficherTexte("Tu as fait " + resde + " !");
                readString();
                effectuerDeplacement(resde, map);
                affichageCaseActuelle(map);
                clearLines(3);
                lancerEvent(map);
                print("\n\n");
            }
        }
        if (!equals(inputJoueur, "SAVE")){
            for (int i=0;i<132/2-62/2;i++) print(" ");
            print("Bravo ! Tu as fini cette partie avec un total de ");
            text("yellow");
            print(joueur.pieces + " pièces !\n");
            reset();
            bddScore = modifTabClassement(joueur.nom,joueur.pieces,bddScore);
            saveCSV(bddScore,"../ressources/TopScore.csv");
            print("\n\n");
            afficherTableauScore(bddScore);
            readString();
            clearTerminal();
        } else {
            afficherTexte("La partie a été sauvegardée.");
            afficherTexte("Appuyez sur Entrée pour quitter.");
            readString();
            clearTerminal();
        }
    }
}
