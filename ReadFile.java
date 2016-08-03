import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileHighlighter {

    public static void main(final String[] args) {

        final String inputFileLocation = args[0];
        final String outputFileLocation = args[1];

        final File inputFile = new File(inputFileLocation);
        final File outputFile = new File(outputFileLocation);
        
        System.out.println("File transformation started");

        FileHighlighter fileHighlighter = new FileHighlighter();
        fileHighlighter.transformer(inputFile, outputFile);
        
        System.out.println("File transformation ended");
    }

    private void transformer(File inputFile, File outputFile) {
        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.ISO_8859_1);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.ISO_8859_1);
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter), true);) {

            String line;
            boolean firstLine = true;
            String[] keywords = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (firstLine) {
                    keywords = line.split(",");
                    firstLine = false;
                } else {
                    for (final String keyword : keywords) {
                        line = line.replaceAll(keyword, "<b>" + keyword + "</b>");
                    }
                }
                printWriter.println(line);
            }

        } catch (final IOException e) {
            System.out.println("Error while transforming the file " + e.getMessage());
        }
    }
}
