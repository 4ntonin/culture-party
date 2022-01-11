import extensions.CSVFile;

class Amogus extends Program {
    CultureParty cp = new CultureParty();

    QuestionImposteur[] bddQuestion = creerBddQuestion();

    QuestionImposteur[] creerBddQuestion(){
        CSVFile csvQImposteur = loadCSV("../ressources/QuestionImposteur.csv");
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
        println("      A:                              B:                              C:                              D:");
        for(int i =0;i<length(amogus);i=i+1){
            print("      ");
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
        String s = "";
        s += "┌";
        for (int i=0;i<34+length(selection.theme);i=i+1){
            s += "─";
        }
        s += "┐";
        cp.afficherTexte(s);
        cp.afficherTexte("│  Le thème de la question est : " + selection.theme + "  │");
        s = "";
        s += "└";
        for (int i=0;i<34+length(selection.theme);i=i+1){
            s += "─";
        }
        s += "┘";
        cp.afficherTexte(s);
    }

    int lancerJeu(){
        cp.clearTerminal();
        print("\n\n\n\n\n");
        cp.afficherTexte("Le jeu de l'imposteur");
        print("\n\n\n");
        cp.afficherTexte("Les règles : un thème sera donné, parmi les réponses que vous donne les personnes, une est fausse.");
        cp.afficherTexte("Votre mission si vous l'acceptez : Trouver l'imposteur.");
        readString();
        cp.clearTerminal();
        
        int piecesgagnees = 0;
        QuestionImposteur selection = tirageQuestion(bddQuestion);
        afficherAmogus();
        afficherTheme(selection);
        cp.afficherTexte("La personne A dit : " + selection.choix[0]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("La personne B dit : " + selection.choix[1]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("La personne C dit : " + selection.choix[2]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("La personne D dit : " + selection.choix[3]);
        cp.afficherTexte("──────────────────────────────────────────────────────────────────");
        cp.afficherTexte("Sélectionnez votre réponse :");

        String choix_joueur_input = " ";
        while (!equals(toUpperCase(choix_joueur_input), "A") && !equals(toUpperCase(choix_joueur_input), "B") && !equals(toUpperCase(choix_joueur_input), "C") && !equals(toUpperCase(choix_joueur_input), "D")) {
            for (int i=0;i<132/2;i++) print(" ");
            choix_joueur_input = readString();
            choix_joueur_input = toUpperCase(choix_joueur_input);
        }
        char choix_joueur = charAt(choix_joueur_input, 0);
        if (equals(selection.choix[choix_joueur-'A'],selection.rep)){
            piecesgagnees = 2;
            cp.afficherTexte("Bien joué ! Tu as trouvé l'imposteur !");
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




