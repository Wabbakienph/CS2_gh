import java.util.*;
import java.io.*;

public class TextAnalytics {
    public static void main(String[] args) {
        try {
            // Check if any command-line argument is provided
            if (args.length == 0) {
                System.out.println("How to run the program: java TextAnalytics <input_file>");
                return; // get out of program
            }

            String fileName = args[0]; // get the file's name from command-line arg
            File inputFile = new File(fileName);
            
            // Use try-with-resources to automatically close the Scanner
            Scanner scan = new Scanner(inputFile);

            // Skip lines prior to the start of the book 
            boolean hasStart = false; // validation check
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
                    hasStart = true; 
                    break;
                } 
            }
            
            // Handle texts without proper starting format
            if (!hasStart){
                System.out.println("Invalid format. The text should contain a proper start format: \"*** START OF THIS PROJECT GUTENBERG EBOOK\"");
                scan.close();
                return;
            }

            // Create a chaining HashMap to store word-occurence pairs
            ObjectHashMap wordMap = new ObjectHashMap(.9);

            // BOOK PROCESSING STARTS HERE
            while (scan.hasNextLine()) {
                // make the line is all lowercase for full tracking of word occurence, alphabetical-only
                String line = scan.nextLine();
                if (line.contains("*** END OF THIS PROJECT GUTENBERG EBOOK")){
                    break; // end the processing at the end of the book
                }
                String[] wordsInLine = line.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+");

                /* 
                O(n)
                */
                for (String word : wordsInLine) {
                    // if the word encountered is already stored, increment the word's curr value
                    // by 1 and reput the newly updated pair in the Map
                    if (!word.isEmpty()) { // eliminate empty spaces as words
                        if (wordMap.containsKey(word)) {
                            Integer newValue = (Integer) wordMap.find(word) + 1;
                            wordMap.put(word, newValue);
                        } else {    
                            wordMap.put(word, 1);
                        }
                    }
                }
            }
    
            Entry[] allWords = wordMap.getEntries(); // all word-occurence pairs gathered in a 1D arr
                insertionSort(allWords); // O(n^2)
                // TOP 5 MOST FREQUENTLY OCCURING WORDS 
                if (allWords.length > 5) {
                    System.out.println("--Top 5 Most Frequent Words--");
                    
                    // Getting the most frequent words = going back
                    for (int i = 0; i < 5; i++){
                        Entry entry = allWords[i];
                        System.out.println(i + 1 + ".) '" + (String) entry.key + "'   " + entry.value + " uses.");
                    }
                } else {
                    if (allWords.length == 1) {
                        System.out.println(1 + ".) '" + (String) allWords[0].key + "'   " + allWords[0].value + " uses.");
                    }

                    for (int i = 0; i < allWords.length; i++) {
                        Entry entry = allWords[i];
                        System.out.println(i + 1 + ".) '" + (String) entry.key + "'   " + entry.value + " uses.");
                    }
                }
            
                
            // Create a scanner for user input
            Scanner input = new Scanner(System.in);
            System.out.print("Type a word or type 'q' to quit: ");
            String command = input.nextLine().trim();

            while (!command.equalsIgnoreCase("q")) {
                    if (!wordMap.containsKey(command)) {
                        System.out.println("The word '" + command + "' is not present.");
                    } else {
                        System.out.println("The word '" + command + "' occurs " + wordMap.find(command) + " times.");
                    }

                    System.out.print("Type a word or type 'q' to quit: ");
                    command = input.nextLine().trim();
            }

            // 'q' (quit command): SUMMARIZE THE TOP WORDS and QUIT THE PROGRAM
            if (command.equalsIgnoreCase("q")) {
                scan.close();
                input.close();
                return;
            }

            scan.close();
            input.close();    
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        }
    }
    
    /*
     * A REVERSE INSERTION SORT METHOD that sort entries from largest to smallest values
     */
    public static void insertionSort(Entry[] arr) {
        int i = 1;
        while (i < arr.length) {
            Entry currEntry = arr[i]; // object of comparison
            int j = i - 1;
            // anything b4 and smaller than the object of cprs gets push behind it
            while (j >= 0 && (int) arr[j].value < (int) currEntry.value) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = currEntry; // Entry with largest value so far gets pushed on top of the array
            i++;
        }
    }
}
