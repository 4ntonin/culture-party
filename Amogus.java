import extensions.CSVFile;

class Amogus extends Program {
    CultureParty cp = new CultureParty();

    QuestionImposteur[] bddQuestion = creerBddQuestion();

    QuestionImposteur[] creerBddQuestion(){
        CSVFile csvQImposteur = loadCSV("./questions/QuestionImposteur.csv");
        int tailleFichier = rowCount(csvQImposteur);
        QuestionImposteur[] bdd = new QuestionImposteur[tailleFichier - 1];
        for(int i =1; i<tailleFichier;i= i + 1){
            QuestionImposteur quest = new QuestionImposteur();
            quest.theme = getCell(csvQImposteur,i,0);
            quest.choix[0] = getCell(csvQImposteur, i, 1);
            quest.choix[1] = getCell(csvQImposteur, i, 2);
            quest.choix[2] = getCell(csvQImposteur, i, 3);
            quest.choix[3] = getCell(csvQImposteur, i, 4);
            quest.rep = getCell(csvQImposteur, i, 5);
            bdd[i-1]= quest;
        }
        return bdd;
    }
    
    QuestionImposteur tirageQuestion(QuestionImposteur[] bdd){
        return bdd[(int)(random()*length(bdd))];
    }

    void afficherAmogus(){
        String[] amogus = new String[]{"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀",
                                       "⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀",
                                       "⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀",
                                       "⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀",
                                       "⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀",
                                       "⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀",
                                       "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀",
                                       "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀",
                                       "⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀",
                                       "⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀",
                                       "⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀",
                                       "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀"};
        println("A:                                 B:                              C:                              D:");
        for(int i =0;i<length(amogus);i=i+1){
            for(int j = 0; j<4;j=j+1){
                if(j==0){
                    text("red");
                }else if(j==1){
                    text("yellow");
                }else if(j==2){
                    text("blue");
                }else {
                    text("green");
                }
                print(amogus[i]);
            }
            println(); 
        }
        reset();
    }
    void afficherTheme(QuestionImposteur selection){
        // ┌ ─ │ ┐ └ ┘
        println("\n");
        print("┌");
        for (int i=0;i<30+length(selection.theme);i=i+1){
            print("─");
        }
        println("┐");
        println("│Le thème de la question est : "+ selection.theme +"│");
        print("└");
        for (int i=0;i<30+length(selection.theme);i=i+1){
            print("─");
        }
        println("┘");
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        for(int i=0;i<(132/2-21/2);i=i+1) print(" ");
        println("Le jeu de l'imposteur");
        print("\n\n");
        for(int i=0;i<(132/2-98/2);i=i+1) print(" ");
        println("les règles : un thème sera donné, parmi les réponses que vous donne les personnes, une est fausse.");
        for(int i=0;i<(132/2-53/2);i=i+1) print(" ");
        println("Votre mission si vous l'acceptez: trouver l'imposteur");
        readString();
        cp.clearTerminal();
        
        QuestionImposteur selection = tirageQuestion(bddQuestion);
        afficherAmogus();
        afficherTheme(selection);
        println("la personne A dit : " + selection.choix[0]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne B dit : " + selection.choix[1]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne C dit : " + selection.choix[2]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne D dit : " + selection.choix[3]);
        println("──────────────────────────────────────────────────────────────────");
        println("Sélectionner votre réponse :");

        int piecesgagnees = 0;
        char choix_joueur = readChar();
        if (equals(selection.choix[choix_joueur-'A'],selection.rep)){
            piecesgagnees = 2;
            println("Bien joué tu as trouvé l'imposteur!!!");
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




