import extensions.CSVFile;

class Amogus extends Program {
    CultureParty cp = new CultureParty();

    QuestionImposteur[] bddQuestion = creerBddQuestion();

    QuestionImposteur[] creerBddQuestion(){
        CSVFile csvQImposteur = loadCSV("QuestionImposteur.csv");
        int tailleFichier = rowCount(csvQImposteur);
        QuestionImposteur[] bdd = new QuestionImposteur[tailleFichier];
        for(int i =1; i<tailleFichier;i= i + 1){
            bdd[i-1].theme = getCell(csvQImposteur,i,0);
            bdd[i-1].choix[0] = getCell(csvQImposteur, i, 1);
            bdd[i-1].choix[1] = getCell(csvQImposteur, i, 2);
            bdd[i-1].choix[2] = getCell(csvQImposteur, i, 3);
            bdd[i-1].choix[3] = getCell(csvQImposteur, i, 4);
            bdd[i-1].rep = getCell(csvQImposteur, i, 5);
        }
        return bdd;
    }
    
    QuestionImposteur tirageQuestion(QuestionImposteur[] bdd){
        return bdd[(int)(random()*length(bdd))];
    }
    //     void clearTerminal() {
    //     print("\033[H\033[2J");
    //     System.out.flush();
    // }
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
    // void afficherTheme(QuestionImposteur selection){
    //     // ┌ ─ │ ┐ └ ┘
    //     println("\n\nLe thème de la question est : " + selection.theme);
    // }

    void algorithm(){
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
        println("la personne A dit : " + selection.choix[0]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne B dit : " + selection.choix[1]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne C dit : " + selection.choix[2]);
        println("──────────────────────────────────────────────────────────────────");
        println("la personne D dit : " + selection.choix[3]);
        println("──────────────────────────────────────────────────────────────────");
        println("Sélectionner votre réponse :");

        
        char choix_joueur = readChar();
        if (equals(selection.choix[choix_joueur-'A'],selection.rep)){
            println("Bien joué tu as trouvé l'imposteur!!!");
            println("Tu gagnes 2 pièces.");
        }else{
            println("Dommage c'est une mauvaise réponse.");
            println("Tu perds 1 pièce.");  
        }
        readString();
    }
}




