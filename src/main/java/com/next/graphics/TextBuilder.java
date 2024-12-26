package com.next.graphics;

import java.util.List;

public class TextBuilder {

    public String buildMenuText(String baseText, List<String> options) {
        StringBuilder optionsText = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            int optionIndex = i + 1;
            optionsText.append("    [")
                    .append(optionIndex)
                    .append("] ")
                    .append(options.get(i))
                    .append("\n");
        }

        return baseText + "\n" + optionsText + "\n";
    }
}
