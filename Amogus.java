class Amogus extends Program {
    QuestionImposteur creerQuestion(String theme, String p1 ,String p2,String p3,String p4,String rep){
        QuestionImposteur q1 = new QuestionImposteur();
        q1.theme = theme;
        q1.choix[0] = p1;
        q1.choix[1] = p2;
        q1.choix[2] = p3;
        q1.choix[3] = p4;
        q1.rep =rep;
        return q1;
    }
    void testCreerQuestion(){
        QuestionImposteur test = creerQuestion("president","Donald Trump","Bill Gates","Francois Hollande","Emmanuel Macron","Bill Gates");
        assertEquals(test.choix[0],"Donald Trump");
        assertEquals(test.choix[1],"Bill Gates");
        assertEquals(test.choix[2],"Francois Hollande");
        assertEquals(test.choix[3],"Emmanuel Macron");
        assertEquals(test.theme,"president");
        assertEquals(test.rep,test.choix[1]);
    }
    QuestionImposteur tirageQuestion(QuestionImposteur[] bdd){
        return bdd[(int)(random()*length(bdd))];
    }

        void algorithm(){
        QuestionImposteur[] bddQuestion = new QuestionImposteur[]{creerQuestion("president","Donald Trump","Bill Gates","Francois Hollande","Emmanuel Macron","Bill Gates"),
                                                                creerQuestion("planete","Venus","Mars","Mercurochrome","Saturne","Mercurochrome"),
                                                                creerQuestion("monnaie","zeni","pesos","dollar","yen","zeni"),
                                                                creerQuestion("pays","Mongolie","Inde","Afrique","Colombie","Afrique")};
        QuestionImposteur selection = tirageQuestion(bddQuestion);
        println("Le thème de la question est : " + selection.theme);
        println("Votre objectif : Trouver l'imposteur");
        println("la personne A dit : " + selection.choix[0] + "     la personne B dit : " + selection.choix[1]);
        println("            ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀                            ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀ ");               
        println("         ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀                     ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀");            
        println("         ⣼⣿⠋        ⢀⣀⣀⠈⢻⣿⣿⡄                   ⣼⣿⠋        ⢀⣀⣀⠈⢻⣿⣿⡄ ");           
        println("        ⣸⣿⡏    ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄                 ⣸⣿⡏    ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄ ");    
        println("        ⣿⣿⠁  ⢰⣿⣿⣯⠁         ⠈⠙⢿⣷⡄             ⣿⣿⠁  ⢰⣿⣿⣯⠁         ⠈⠙⢿⣷⡄ ");          
        println(" ⣀⣤⣴⣶⣶⣿⡟     ⢸⣿⣿⣿⣆            ⣿⣷       ⣀⣤⣴⣶⣶⣿⡟     ⢸⣿⣿⣿⣆            ⣿⣷ ");
        println("⢰⣿⡟⠋⠉⣹⣿⡇    ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿         ⢰⣿⡟⠋⠉⣹⣿⡇    ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿  "); 
        println("⢸⣿⡇   ⣿⣿⡇     ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃        ⢸⣿⡇   ⣿⣿⡇     ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃  ");
        println("⣸⣿⡇  ⣿⣿⡇       ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇         ⣸⣿⡇  ⣿⣿⡇       ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇    ");
        println("⣿⣿⠁  ⣿⣿⡇                    ⢸⣿⣧      ⣿⣿⠁  ⣿⣿⡇                    ⢸⣿⣧  ");  
        println("⣿⣿    ⣿⣿                     ⢸⣿⣿     ⣿⣿    ⣿⣿                     ⢸⣿⣿");
        println("⣿⣿    ⣿⣿⡇                   ⢸⣿⣿      ⣿⣿    ⣿⣿⡇                   ⢸⣿⣿");
        println("⢿⣿⡆   ⣿⣿⡇                   ⢸⣿⡇      ⢿⣿⡆   ⣿⣿⡇                   ⢸⣿⡇");
        println("⠸⣿⣧⡀  ⣿⣿⡇                   ⣿⣿⠃     ⠸⣿⣧⡀  ⣿⣿⡇                   ⣿⣿⠃");
        println(" ⠛⢿⣿⣿⣿⣿⣇      ⣰⣿⣿⣷⣶⣶⣶⣶⠶ ⢠⣿⣿          ⠛⢿⣿⣿⣿⣿⣇      ⣰⣿⣿⣷⣶⣶⣶⣶⠶ ⢠⣿⣿");
        println("      ⣿⣿      ⣿⣿⡇ ⣽⣿⡏⠁  ⢸⣿⡇               ⣿⣿      ⣿⣿⡇ ⣽⣿⡏⠁  ⢸⣿⡇");
        println("      ⣿⣿      ⣿⣿⡇ ⢹⣿⡆   ⣸⣿⠇               ⣿⣿      ⣿⣿⡇ ⢹⣿⡆   ⣸⣿⠇");
        println("      ⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁ ⠈⠻⣿⣿⣿⣿⡿⠏                 ⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁ ⠈⠻⣿⣿⣿⣿⡿⠏");
        println("      ⠈⠛⠻⠿⠿⠿⠿⠋⠁                            ⠈⠛⠻⠿⠿⠿⠿⠋⠁");
                println("la personne C dit : " + selection.choix[2] + "     la personne D dit : " + selection.choix[3]);
        println("            ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀                            ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀ ");               
        println("         ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀                     ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀");            
        println("         ⣼⣿⠋        ⢀⣀⣀⠈⢻⣿⣿⡄                   ⣼⣿⠋        ⢀⣀⣀⠈⢻⣿⣿⡄ ");           
        println("        ⣸⣿⡏    ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄                 ⣸⣿⡏    ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄ ");    
        println("        ⣿⣿⠁  ⢰⣿⣿⣯⠁         ⠈⠙⢿⣷⡄             ⣿⣿⠁  ⢰⣿⣿⣯⠁         ⠈⠙⢿⣷⡄ ");          
        println(" ⣀⣤⣴⣶⣶⣿⡟     ⢸⣿⣿⣿⣆            ⣿⣷       ⣀⣤⣴⣶⣶⣿⡟     ⢸⣿⣿⣿⣆            ⣿⣷ ");
        println("⢰⣿⡟⠋⠉⣹⣿⡇    ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿         ⢰⣿⡟⠋⠉⣹⣿⡇    ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿  "); 
        println("⢸⣿⡇   ⣿⣿⡇     ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃        ⢸⣿⡇   ⣿⣿⡇     ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃  ");
        println("⣸⣿⡇  ⣿⣿⡇       ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇         ⣸⣿⡇  ⣿⣿⡇       ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇    ");
        println("⣿⣿⠁  ⣿⣿⡇                    ⢸⣿⣧      ⣿⣿⠁  ⣿⣿⡇                    ⢸⣿⣧  ");  
        println("⣿⣿    ⣿⣿                     ⢸⣿⣿     ⣿⣿    ⣿⣿                     ⢸⣿⣿");
        println("⣿⣿    ⣿⣿⡇                   ⢸⣿⣿      ⣿⣿    ⣿⣿⡇                   ⢸⣿⣿");
        println("⢿⣿⡆   ⣿⣿⡇                   ⢸⣿⡇      ⢿⣿⡆   ⣿⣿⡇                   ⢸⣿⡇");
        println("⠸⣿⣧⡀  ⣿⣿⡇                   ⣿⣿⠃     ⠸⣿⣧⡀  ⣿⣿⡇                   ⣿⣿⠃");
        println(" ⠛⢿⣿⣿⣿⣿⣇      ⣰⣿⣿⣷⣶⣶⣶⣶⠶ ⢠⣿⣿          ⠛⢿⣿⣿⣿⣿⣇      ⣰⣿⣿⣷⣶⣶⣶⣶⠶ ⢠⣿⣿");
        println("      ⣿⣿      ⣿⣿⡇ ⣽⣿⡏⠁  ⢸⣿⡇               ⣿⣿      ⣿⣿⡇ ⣽⣿⡏⠁  ⢸⣿⡇");
        println("      ⣿⣿      ⣿⣿⡇ ⢹⣿⡆   ⣸⣿⠇               ⣿⣿      ⣿⣿⡇ ⢹⣿⡆   ⣸⣿⠇");
        println("      ⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁ ⠈⠻⣿⣿⣿⣿⡿⠏                 ⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁ ⠈⠻⣿⣿⣿⣿⡿⠏");
        println("      ⠈⠛⠻⠿⠿⠿⠿⠋⠁                            ⠈⠛⠻⠿⠿⠿⠿⠋⠁");
        println("Selectioner votre réponse :");
        char choix_joueur = readChar();
        if (equals(selection.choix[choix_joueur-'A'],selection.rep)){
            println("Bien joué tu as trouvé l'imposteur!!!");
            println("tu gagne 2 pièces");
        }else{
            println("Dommage c'est une mauvaise réponse");
            println("tu perd 1 pièce");
        }
    }
}