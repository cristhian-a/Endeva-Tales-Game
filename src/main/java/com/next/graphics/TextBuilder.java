package com.next.graphics;

public class TextBuilder {

    public String buildMenuText(String baseText, String[] options) {
        StringBuilder optionsText = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            int optionIndex = i + 1;
            optionsText.append("    [")
                    .append(optionIndex)
                    .append("] ")
                    .append(options[i])
                    .append("\n");
        }

        return baseText + "\n" + optionsText;
    }
}
