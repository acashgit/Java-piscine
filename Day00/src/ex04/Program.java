package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        short[] charCount = getCharCount(input);
        char[] top = getTen(charCount);
        printResult(top, charCount);
    }

    private static char[] getTen(short[] parsedInput) {
        char[] ret = new char[10];

        for (int i = 0; i < 65535; i++) {
            short iCharCount = parsedInput[i];
            if (iCharCount > 0) {
                for (int j = 0; j < 10; j++) {
                    if (parsedInput[ret[j]] < iCharCount) {
                        ret = insertCharAt(ret, (char) i, j);
                        break;
                    }
                }
            }
        }
        return (ret);
    }

    private static char[] insertCharAt(char[] base, char c, int index) {
        char[] ret = new char[10];
        for (int i = 0; i < index; i++) {
            ret[i] = base[i];
        }
        ret[index] = c;
        for (int i = index + 1; i < 10; i++) {
            ret[i] = base[i - 1];
        }
        return (ret);
    }

    private static short[] getCharCount(String input) {
        short[] ret = new short[65535];
        char[] inputAsArray = input.toCharArray();

        for (int i = 0; i < input.length(); i++) {
            ret[inputAsArray[i]]++;
        }
        return (ret);
    }

    private static void printResult(char[] topTenChars, short[] charCount) {
        short max = charCount[topTenChars[0]];
        byte maxHeight = (byte) (max <= 10 ? max : 10);
        byte totalLines = (byte) (2 + maxHeight);
        byte[] graphs = new byte[10];

        for (int i = 0; i < 10; i++) {
            if (max <= 10) {
                graphs[i] = (byte) charCount[topTenChars[i]];
            } else {
                graphs[i] = (byte) (charCount[topTenChars[i]] * 10 / max);
            }
        }
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < 10; j++) {
                if (topTenChars[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.printf("%5d", charCount[topTenChars[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%5c", topTenChars[j]);
                    } else if (i + graphs[j] >= maxHeight) {
                        System.out.printf("%5c", '#');
                    }
                    if (j != 10 - 1 && topTenChars[j + 1] != 0 && i + graphs[j + 1] >= maxHeight) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}
