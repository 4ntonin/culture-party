import extensions.CSVFile;

class Unolingo extends Program {
    QuestionLingo[] bddQuestion = creerBddQuestion();

    CultureParty cp = new CultureParty();

    QuestionLingo[] creerBddQuestion(){
        CSVFile csvQlingo = loadCSV("../ressources/QuestionLingo.csv");
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
        String s = "";
        s += "┌";
        for (int i=0;i<22+length(selection.traduire);i=i+1){
            s += "─";
        }
        s += "┐";
        cp.afficherTexte(s);
        cp.afficherTexte("│Vous devez traduire : "+ selection.traduire +"│");
        s = "";
        s += "└";
        for (int i=0;i<22+length(selection.traduire);i=i+1){
            s += "─";
        }
        s += "┘";
        cp.afficherTexte(s);
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        cp.afficherTexte("Unolingo");
        print("\n\n");
        cp.afficherTexte("Les règles : un mot en anglais te sera donné, il suffit d'y entrer la traduction en français.");
        cp.afficherTexte("Pour passer au jeu, appuie sur Entrée.");
        readString();
        cp.clearTerminal();

        int piecesgagnees = 0;
        QuestionLingo selection = tirageQuestion(bddQuestion);
        afficherQuestion(selection);
        cp.afficherTexte("Entrer la réponse :");
        for (int i=0;i<132/2-2;i++) print(" ");
        String choix_joueur_input = "";
        choix_joueur_input = readString();
        choix_joueur_input = toLowerCase(choix_joueur_input); 
        if (equals(selection.traduction,choix_joueur_input)){
            piecesgagnees = 2;
            cp.afficherTexte("Bien joué ! Tu as trouvé la bonne traduction !");
            cp.afficherTexte("Tu gagnes 2 pièces.");
        }else{
            piecesgagnees = -1;
            cp.afficherTexte("Dommage, c'est une mauvaise réponse.");
            cp.afficherTexte("Tu perds 1 pièce.");  
        }
        readString();
        return piecesgagnees;   
    }
}
