import extensions.CSVFile;

class JeuGrammaire extends Program {
    CultureParty cp = new CultureParty();

    QuestionGrammaire[] bddQuestion = creerBddQuestion();

    QuestionGrammaire[] creerBddQuestion(){
        CSVFile csvQGrammaire = loadCSV("../ressources/QuestionsGrammaire.csv");
        int tailleFichier = rowCount(csvQGrammaire);
        QuestionGrammaire[] bdd = new QuestionGrammaire[tailleFichier - 1];
        for(int i =1; i<tailleFichier;i= i + 1){
            QuestionGrammaire quest = new QuestionGrammaire();
            quest.question = getCell(csvQGrammaire,i,0);
            quest.choix[0] = getCell(csvQGrammaire, i, 1);
            quest.choix[1] = getCell(csvQGrammaire, i, 2);
            quest.choix[2] = getCell(csvQGrammaire, i, 3);
            quest.reponse = getCell(csvQGrammaire, i, 4);
            bdd[i-1]= quest;
        }
        return bdd;
    }
    
    QuestionGrammaire tirageQuestion(QuestionGrammaire[] bdd){
        return bdd[(int)(random()*length(bdd))];
    }

    void afficherQuestion(QuestionGrammaire selection){
        // ┌ ─ │ ┐ └ ┘
        println("\n");
        print("┌");
        for (int i=0;i<30+length(selection.question);i=i+1){
            print("─");
        }
        println("┐");
        println("│Le thème de la question est : "+ selection.question +"│");
        print("└");
        for (int i=0;i<30+length(selection.question);i=i+1){
            print("─");
        }
        println("┘");
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        for(int i=0;i<(132/2-22/2);i=i+1) print(" ");
        println("Le jeu de \"Grammaire\"");
        print("\n\n");
        for(int i=0;i<(132/2-77/2);i=i+1) print(" ");
        println("les règles : un question sera posée il te faut selectionner la bonne réponse.");
        for(int i=0;i<(132/2-69/2);i=i+1) print(" ");
        println("Maintenat que tu as compris, appuye sur une touche pour lancer le jeu");
        readString();
        cp.clearTerminal();
        
        QuestionGrammaire selection = tirageQuestion(bddQuestion);
        afficherQuestion(selection);
        println("A : " + selection.choix[0]);
        println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        println("B : " + selection.choix[1]);
        println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        println("C : " + selection.choix[2]);
        println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        println("Sélectionner votre réponse :");

        int piecesgagnees = 0;
        String choix_joueur_input = " ";
        while (!equals(toUpperCase(choix_joueur_input), "A") && !equals(toUpperCase(choix_joueur_input), "B") && !equals(toUpperCase(choix_joueur_input), "C")) {
            choix_joueur_input = readString();
            choix_joueur_input = toUpperCase(choix_joueur_input);
        }
        char choix_joueur = charAt(choix_joueur_input, 0);
        if (equals(selection.choix[choix_joueur-'A'],selection.reponse)){
            piecesgagnees = 2;
            println("Bien joué tu as trouvé la bonne réponse !");
            println("Tu gagnes 2 pièces.");
        }else{
            piecesgagnees = -1;
            println("Dommage c'est une mauvaise réponse.");
            println("Tu perds 1 pièce.");  
        }
        readString();
        return piecesgagnees;
    }
}
