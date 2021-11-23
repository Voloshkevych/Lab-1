import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        String fname;
        System.out.println("Please provide the path to input file:");
        Scanner scanner = new Scanner(System.in);
        fname = scanner.next();
        try {
            HashSet<String> words = new HashSet<>();
            FileReader input = new FileReader(fname);
            StringBuilder word = new StringBuilder();
            int max_word = 30;
            int max_len = 0;
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int c;
            while ((c = input.read()) != -1) {
                char ch = (char) c;
                if (alphabet.indexOf(ch) != -1) {
                    if (word.length() < max_word)
                        word.append(ch);
                }
                else {
                    if (word.length() != 0) {
                        if (word.length() > max_len) {
                            words.clear();
                            words.add(word.toString());
                            max_len = word.length();
                        }
                        else if (word.length() == max_len) {
                            words.add(word.toString());
                        }
                        word.delete(0, word.length());
                    }
                }
            }
            input.close();

            System.out.print("Size of the longest word is: ");
            System.out.println(max_len);
            System.out.print("Total number of suitable words: ");
            System.out.println(words.size());
            FileWriter output = new FileWriter("output.txt");
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
