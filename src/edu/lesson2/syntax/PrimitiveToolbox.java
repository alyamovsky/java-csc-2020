package edu.lesson2.syntax;

public class PrimitiveToolbox {
    public int parseStringLiteral(String literal) {
        boolean negative = literal.startsWith("-");
        literal = literal.replaceFirst("[+-]", "");

        int radix = 10;
        if (literal.matches("0[xX].+")) {
            radix = 16;
            literal = literal.substring(2);
        } else if (literal.matches("0[bB].+")) {
            radix = 2;
            literal = literal.substring(2);
        } else if (literal.matches("0[0-7].+")) {
            radix = 8;
            literal = literal.substring(1);
        }

        if (literal.startsWith("_") || literal.endsWith("_") || literal.contains("__")) {
            throw new NumberFormatException("Invalid underscore placement");
        }
        literal = literal.replace("_", "");

        int value = Integer.parseInt(literal, radix);
        return negative ? -value : value;
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

        var previous = 0d;
        while (value + 1.0 != 1.0) {
            previous = value;
            value /= 2;
        }

        return previous;
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

        for (int i = 0, j = codePoints.length - 1; i < j; i++, j--) {
            if (codePoints[i] != codePoints[j]) {
                return false;
            }
        }

        return true;
    }
}
