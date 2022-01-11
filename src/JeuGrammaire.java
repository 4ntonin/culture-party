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
        String s = "";
        s += "┌";
        for (int i=0;i<32+length(selection.question);i=i+1){
            s += "─";
        }
        s += "┐";
        cp.afficherTexte(s);
        cp.afficherTexte("│  "+ selection.question +"  │");
        s = "";
        s += "└";
        for (int i=0;i<32+length(selection.question);i=i+1){
            s += "─";
        }
        s += "┘";
        cp.afficherTexte(s);
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        cp.afficherTexte("Le jeu de \"Grammaire\"");
        print("\n\n");
        cp.afficherTexte("les règles : un question sera posée il te faut selectionner la bonne réponse.");
        cp.afficherTexte("Appuie sur Entrée pour lancer le jeu");
        readString();
        cp.clearTerminal();
        
        QuestionGrammaire selection = tirageQuestion(bddQuestion);
        afficherQuestion(selection);
        cp.afficherTexte("A : " + selection.choix[0]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("B : " + selection.choix[1]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("C : " + selection.choix[2]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("Sélectionner votre réponse :");

        int piecesgagnees = 0;
        String choix_joueur_input = " ";
        while (!equals(toUpperCase(choix_joueur_input), "A") && !equals(toUpperCase(choix_joueur_input), "B") && !equals(toUpperCase(choix_joueur_input), "C")) {
            choix_joueur_input = readString();
            choix_joueur_input = toUpperCase(choix_joueur_input);
        }
        char choix_joueur = charAt(choix_joueur_input, 0);
        if (equals(selection.choix[choix_joueur-'A'],selection.reponse)){
            piecesgagnees = 2;
            cp.afficherTexte("Bien joué tu as trouvé la bonne réponse !");
            cp.afficherTexte("Tu gagnes 2 pièces.");
        }else{
            piecesgagnees = -1;
            cp.afficherTexte("Dommage c'est une mauvaise réponse.");
            cp.afficherTexte("Tu perds 1 pièce.");  
        }
        readString();
        return piecesgagnees;
    }
}
