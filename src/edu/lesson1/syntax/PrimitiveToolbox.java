package edu.lesson1.syntax;

public class PrimitiveToolbox {
    public int parseStringLiteral(String literal) {
        literal = literal.trim().toLowerCase().replaceAll("[_\\s]", "");
        var sign = literal.charAt(0) == '-' ? "-" : "+";
        literal = literal.replaceAll("[+-]", "");
        var radix = getRadix(literal);
        literal = literal.replaceAll("[a-z]", "");

        return Integer.parseInt(sign + literal, radix);
    }

    private static int getRadix(String literal) {
        if (literal.length() == 1) {
            return 10;
        }
        return switch (literal.substring(0, 2)) {
            case String s when s.equals("0x") -> 16;
            case String s when s.equals("0b") -> 2;
            case String s when s.charAt(0) == '0' -> 8;
            default -> 10;
        };
    }

    public long safeAdd(long a, long b) {
        try {
            return Math.addExact(a, b);
        } catch (ArithmeticException exception) {
            throw new OverflowException();
        }
    }

    public double machineEpsilon() {
        var value = 0.01;

        while (value + 1.0 != 1.0) {
            value /= 2;
        }

        return value;
    }

    public int[] codePoints(String input) {
        var codePointsCount = input.codePointCount(0, input.length());
        int[] result = new int[codePointsCount];
        for (int i = 0, j = 0; j < input.length(); i++) {
            var codePoint = input.codePointAt(j);
            j += Character.charCount(codePoint);
            result[i] = codePoint;
        }

        return result;
    }

    public boolean isPalindrome(String input) {
        var codePoints = codePoints(input);

        for (int i = 0, j = codePoints.length - 1; i < codePoints.length; i++, j--) {
            if (codePoints[i] != codePoints[j]) {
                return false;
            }
        }

        return true;
    }
}
