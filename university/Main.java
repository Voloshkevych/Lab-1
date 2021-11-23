package university;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static boolean is_good(String word) {
        return Pattern.matches("(.)*([b-zB-Z&&[^euioyEUIOY]])\\2(.)*", word);
    }

    public static void main(String[] args) {
        String fname;
        if (args.length == 0) {
            System.out.println("Please provide the path to input file:");
            Scanner scanner = new Scanner(System.in);
            fname = scanner.next();
        }
        else {
            fname = args[0];
        }
        System.out.println(fname);
        try {
            HashSet<String> words = new HashSet<>();
            FileReader input = new FileReader(fname);
            StringBuilder word = new StringBuilder();
            int max_len = 30;
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int c;
            while ((c = input.read()) != -1) {
                char ch = (char) c;
                if (alphabet.indexOf(ch) != -1) {
                    if (word.length() < max_len)
                        word.append(ch);
                }
                else {
                    if (word.length() != 0) {
                        if (is_good(word.toString()))
                            words.add(word.toString());
                        word.delete(0, word.length());
                    }
                }
            }
            input.close();

            System.out.print("Total number of suitable words: ");
            System.out.println(words.size());
            FileWriter output = new FileWriter(args.length > 1 ? args[1] : "output.txt");
            for (String w: words) {
                System.out.println(w);
                output.write(w);
                output.write('\n');
            }
            output.close();

        } catch (IOException e) {
            System.out.println("Something went wrong:");
            e.printStackTrace();
        }
    }
}
