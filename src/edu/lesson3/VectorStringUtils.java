package edu.lesson3;

import java.util.*;
import java.util.stream.Collectors;

public class VectorStringUtils {
    public static String toCsvLine(ImmutableVector v) {
        return String.format(Locale.US, "%.3f;%.3f;%.3f", v.getX(), v.getY(), v.getZ());
    }

    public static Optional<ImmutableVector> fromCsvLine(String csv) {
        var arr = Arrays.stream(csv.split(";", 3)).map(Double::parseDouble).toArray(Double[]::new);
        return Optional.of(ImmutableVector.of(arr[0], arr[1], arr[2]));
    }

    public static String csvDump(Collection<ImmutableVector> vectors) {
        var sb = new StringBuilder();
        for (var vector : vectors) {
            sb.append(VectorStringUtils.toCsvLine(vector)).append("\n");
        }

        return sb.toString();
    }

    public static String csvDumpStream(Collection<ImmutableVector> vectors) {
        return vectors.stream().map(VectorStringUtils::toCsvLine).collect(Collectors.joining("\n")) + "\n";
    }

    public static String prettyTable(List<ImmutableVector> vectors) {
        String[] header = {"X", "Y", "Z", "LEN"};
        List<String[]> rows = new ArrayList<>();
        rows.add(header);
        for (var v : vectors) {
            rows.add(new String[]{
                String.format(Locale.US, "%.3f", v.getX()),
                String.format(Locale.US, "%.3f", v.getY()),
                String.format(Locale.US, "%.3f", v.getZ()),
                String.format(Locale.US, "%d", v.length()
                )
            });
        }

        var cols = header.length;
        var maxWidth = new int[cols];
        for (var row : rows) {
            for (int c = 0; c < cols; c++) {
                maxWidth[c] = Math.max(maxWidth[c], row[c].length());
            }
        }

        var fmt = new StringBuilder();
        var sep = new StringBuilder();
        fmt.append("|");
        sep.append("| ");
        for (var c = 0; c < cols; c++) {
            fmt.append(" %-").append(maxWidth[c]).append("s |");
            sep.append(repeatDelimiter(maxWidth[c])).append(" | ");
        }
        String rowFormat = fmt.toString();

        var sb = new StringBuilder();
        sb.append(String.format(rowFormat, (Object[]) header)).append("\n");
        sb.append(sep).append("\n");
        for (var i = 1; i < rows.size(); i++) {
            sb.append(String.format(rowFormat, (Object[]) rows.get(i))).append("\n");
        }
        return sb.toString();
    }

    private static String repeatDelimiter(int times) {
        return String.join("", Collections.nCopies(times, "-"));
    }
}
