package com.next.graphics.menu;

public class MainMenu {

    public static String getTitle() {
        return """
                ****************************
                    Os Contos de Endeva!
                ****************************
                
                """;
    }

    public static String getMainMenu() {
        return """
                Bem vindo aos Contos de Endeva! Aventure-se pelo continente e viva as histórias dos heróis
                daquele mundo!
                
                Para continuar, selecione o personagem e a aventura que deseja jogar:
                """;
    }

    public static String[] getTalesOptions() {
        return new String[] { "Thomas, o Bruxo", "O Jovem Rei" };
    }
}
