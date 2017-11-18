import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileCounter{
    
       private int characterCount, wordCount, lineCount;
       
       public FileCounter(){
          characterCount = 0;
          wordCount = 0;
          lineCount = 0;
       }
    
       /**
          Processes an input source and adds its character, word, and line
          counts to the respective variables.
          @param in the scanner to process
       */
       public void read(Scanner in) throws IOException {
            String line;
            try {
                while ((line = in.nextLine()) != null) {
                    lineCount++;
                    String[] temp = line.split("\\s+");
                    wordCount += temp.length;
                    for (String s : temp) {
                        characterCount += s.length();
                    }
                }
            } catch (NoSuchElementException e) {
            }
       }

	public int getCharacterCount() {
		return characterCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getLineCount() {
		return lineCount;
	}
}