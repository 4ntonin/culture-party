import extensions.CSVFile;

class Unolingo extends Program {
    QuestionLingo[] bddQuestion = creerBddQuestion();

    CultureParty cp = new CultureParty();

    QuestionLingo[] creerBddQuestion(){
        CSVFile csvQlingo = loadCSV("./questions/QuestionLingo.csv");
        int tailleFichier = rowCount(csvQlingo);
        QuestionLingo[] bdd = new QuestionLingo[tailleFichier - 1];
        for(int i =1; i<tailleFichier;i= i + 1){
            QuestionLingo quest = new QuestionLingo();
            quest.traduire = getCell(csvQlingo,i,0);
            quest.traduction = getCell(csvQlingo, i, 1);
            bdd[i-1]= quest;
        }
        return bdd;
    }

    QuestionLingo tirageQuestion(QuestionLingo[] bdd){
        return bdd[(int)(random()*length(bdd))];
    }

    void afficherQuestion(QuestionLingo selection){
        // ┌ ─ │ ┐ └ ┘
        println("\n");
        print("┌");
        for (int i=0;i<22+length(selection.traduire);i=i+1){
            print("─");
        }
        println("┐");
        println("│Vous devez traduire : "+ selection.traduire +"│");
        print("└");
        for (int i=0;i<22+length(selection.traduire);i=i+1){
            print("─");
        }
        println("┘");
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        for(int i=0;i<(132/2-8/2);i=i+1) print(" ");
        println("Unolingo");
        print("\n\n");
        for(int i=0;i<(132/2-70/2);i=i+1) print(" ");
        println("les règles : un mot te sera donné, il suffit d'y entrer la traduction.");
        for(int i=0;i<(132/2-53/2);i=i+1) print(" ");
        println("Pour passer au jeu, appuye sur une touche.");
        readString();
        cp.clearTerminal();

        QuestionLingo selection = tirageQuestion(bddQuestion);
        afficherQuestion(selection);
        println("Entrer la réponse :");
        int piecesgagnees = 0;
        String choix_joueur_input = "";
        choix_joueur_input = readString();
        choix_joueur_input = toLowerCase(choix_joueur_input); 
        if (equals(selection.traduction,choix_joueur_input)){
            piecesgagnees = 2;
            println("Bien joué tu as trouvé la bonne traduction!!!");
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