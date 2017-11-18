import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class LyricAnalyzer {
    private HashMap<String, ArrayList<Integer>> map;
    private int wordCount;

    public LyricAnalyzer() {
        map = new HashMap<String, ArrayList<Integer>>();
    }

    public void read(File file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int wordPosition = 1;
        try {
            while ((line = br.readLine()) != null) {
                String[] wordList = line.split("\\s+");
                for (int i = 0; i < wordList.length; i++, wordPosition++) {
                    if (i == (wordList.length - 1)) {
                        add(wordList[i], -wordPosition);
                    } else {
                        add(wordList[i], wordPosition);
                    }
                }
            }
            wordCount = wordPosition - 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void add(String lyricWord, int wordPosition) {
        if (map.containsKey(lyricWord)) {
            map.get(lyricWord).add(wordPosition);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(wordPosition);
            map.put(lyricWord, list);
        }
    }

    public void displayWords() {
        Set<String> wordSet = map.keySet();
        ArrayList<String> list = new ArrayList<String>(wordSet);
        Collections.sort(list);
        for (String word : list) {
            StringJoiner sj = new StringJoiner(",");
            for (Integer i : map.get(word)) {
                sj.add(i.toString());
            }
            System.out.println(word + ": " + sj.toString());
        }
    }

    public void writeLyrics(File file) {
        String[] s = new String[wordCount + 1];
        for (int i = 0; i < s.length; i++) {
            s[i] = "";
        }
        for (String word : map.keySet()) {
            ArrayList<Integer> list = map.get(word);
            for (Integer i : list) {
                if (i > 0) {
                    s[i] = word + " ";
                } else {
                    s[-i] = word + "\n";
                }
            }
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            for (int i = 0; i < s.length; i++) {
                pw.print(s[i]);
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int count() {
        return map.size();
    }

    public String mostFrequentWord() {
        int maxFreq = 0;
        String maxWord = null;
        for (String word : map.keySet()) {
            int freq = map.get(word).size();
            if (freq > maxFreq) {
                maxFreq = freq;
                maxWord = word;
            }
            if (freq == maxFreq) {
                maxWord = word.compareTo(maxWord) < 0 ? word : maxWord;
            }
        }
        return maxWord;
    }

    public static void main(String[] args) {
        LyricAnalyzer la1 = new LyricAnalyzer();
        LyricAnalyzer la2 = new LyricAnalyzer();
        LyricAnalyzer la3 = new LyricAnalyzer();
        LyricAnalyzer la4 = new LyricAnalyzer();
        try {
            la1.read(new File("Question2_test1.txt"));
            la2.read(new File("Question2_test2.txt"));
            la3.read(new File("Question2_test3.txt"));
            la4.read(new File("Question2_test4.txt"));
            la1.displayWords();
            la2.displayWords();
            la3.displayWords();
            la4.displayWords();
            la1.writeLyrics(new File("1.txt"));
            la2.writeLyrics(new File("2.txt"));
            la3.writeLyrics(new File("3.txt"));
            la4.writeLyrics(new File("4.txt"));
            System.out.println(la1.mostFrequentWord());
            System.out.println(la2.mostFrequentWord());
            System.out.println(la3.mostFrequentWord());
            System.out.println(la4.mostFrequentWord());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}