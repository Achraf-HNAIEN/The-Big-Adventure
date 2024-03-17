package fr.uge.myproject.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeParser {

    private static final Pattern SIZE_PATTERN = Pattern.compile("\\s*size\\s*:\\s*\\((\\d+)x(\\d+)\\)\\s*");

    public void validateSize(String fileContent, int expectedWidth, int expectedHeight) throws IllegalArgumentException {
        Matcher matcher = SIZE_PATTERN.matcher(fileContent);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Size pattern not found or incorrect in the file content.");
        }

        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));

        if (width != expectedWidth || height != expectedHeight) {
            throw new IllegalArgumentException(
                    String.format("Size mismatch: expected (%dx%d) but found (%dx%d) in the file.",
                            expectedWidth, expectedHeight, width, height));
        }

        System.out.println("Size is correct: (" + width + "x" + height + ")");
    }
}
