package org.parsemylegacy.utils;

public class Strings {

    public static String trim(String source, Character trimCharacter, TrimDirection direction) {
        switch (direction) {
            case LEFT:
                return ltrim(source, trimCharacter);
            case RIGHT:
                return rtrim(source, trimCharacter);
            case BOTH:
                return rtrim(ltrim(source, trimCharacter), trimCharacter);
            default:
                return source;
        }
    }

    private static String ltrim(String source, Character trimCharacter) {
        int length = source.length();
        int firstNonTrimCharacterPosition;

        for (firstNonTrimCharacterPosition = 0; firstNonTrimCharacterPosition < length; firstNonTrimCharacterPosition++) {
            if (!(source.charAt(firstNonTrimCharacterPosition) == trimCharacter)) {
                break;
            }
        }

        return source.substring(firstNonTrimCharacterPosition, length);
    }

    private static String rtrim(String source, Character trimCharacter) {
        int lastNonTrimCharacterPosition;

        for (lastNonTrimCharacterPosition = source.length() - 1; lastNonTrimCharacterPosition >= 0; lastNonTrimCharacterPosition--) {
            if (!(source.charAt(lastNonTrimCharacterPosition) == trimCharacter)) {
                break;
            }
        }

        return source.subSequence(0, lastNonTrimCharacterPosition + 1).toString();
    }

}
