package fr.uge.myproject.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodingReader {

    private static final Pattern ENCODING_PATTERN = Pattern.compile("\\b(\\w+)\\(([\\w\\s])\\)");

    public Map<String, String> readEncodings(String fileContent) throws IllegalArgumentException {
        return extracted(fileContent);
    }


	private Map<String, String> extracted(String fileContent) {
		Map<String, String> encodingMap = new HashMap<>();
        Set<String> usedCharacters = new HashSet<>();

        String[] lines = fileContent.split("\\n");
        for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            String line = lines[lineNumber];
            Matcher matcher = ENCODING_PATTERN.matcher(line);

            while (matcher.find()) {
                String elementName = matcher.group(1).trim();
                String encodingChar = matcher.group(2).trim();

                extracted(encodingMap, usedCharacters, lineNumber, elementName, encodingChar);
            }
        }

        return encodingMap;
	}


	private void extracted(Map<String, String> encodingMap, Set<String> usedCharacters, int lineNumber,
			String elementName, String encodingChar) {
		//duplicate encoding character
		if (usedCharacters.contains(encodingChar)) {
		    throw new IllegalArgumentException("Duplicate encoding letter: '" + encodingChar + "' found at " + (lineNumber + 1));
		}

		// Check the same element name
		if (encodingMap.containsKey(encodingChar) && !encodingMap.get(encodingChar).equals(elementName)) {
		    throw new IllegalArgumentException("Inconsistent encoding for element '" + elementName + "' at line " + (lineNumber + 1));
		}

		encodingMap.put(encodingChar, elementName);
		usedCharacters.add(encodingChar);
	}


    public static void printEncodingMap(Map<String, String> encodingMap) {
        System.out.println("Encoding Map:");
        for (Map.Entry<String, String> entry : encodingMap.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
    }
}
