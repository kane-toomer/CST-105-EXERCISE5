import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordEncryptor {

    // Encryption algorithm
    public static String encryptWord(String word) {
        int kt_length = word.length();
        if (kt_length % 2 == 0) {
            int kt_n = kt_length / 2;
            String kt_firstHalf = word.substring(0, kt_n);
            String kt_secondHalf = word.substring(kt_n);
            return kt_secondHalf + kt_firstHalf;
        } else {
            int kt_n = (kt_length + 1) / 2;
            String kt_firstHalf = word.substring(0, kt_n);
            String kt_secondHalf = word.substring(kt_n);
            return kt_secondHalf + kt_firstHalf;
        }
    }

    public static void main(String[] args) {
        // File Inputs
        String kt_inputFileName = "input.in";
        String kt_outputFileName = "results.out";

        // Try and Catch for file input -> encryption -> file output
        try (FileInputStream fis = new FileInputStream(kt_inputFileName);
                FileOutputStream fos = new FileOutputStream(kt_outputFileName)) {

            StringBuilder sb = new StringBuilder();
            int kt_ch;
            while ((kt_ch = fis.read()) != -1) {
                sb.append((char) kt_ch);
            }
            String kt_content = sb.toString();

            String[] kt_lines = kt_content.split("\\n");
            StringBuilder kt_result = new StringBuilder("Original Word\tEncrypted Word\n");
            kt_result.append("---------------------\n");

            for (String kt_line : kt_lines) {
                String[] kt_words = kt_line.split("\\s+");
                for (String kt_word : kt_words) {
                    String kt_encryptedWord = encryptWord(kt_word.toUpperCase());
                    kt_result.append(kt_word).append("\t").append(kt_encryptedWord).append("\n");
                }
            }

            fos.write(kt_result.toString().getBytes());
            System.out.println("Encryption complete. Results written to " + kt_outputFileName);

        } catch (IOException e) {
            System.out.println("Error reading or writing the files: " + e.getMessage());
        }
    }
}
