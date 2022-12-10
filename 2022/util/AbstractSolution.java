import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractSolution {

    public abstract void problem1() throws IOException;

    public abstract void problem2() throws IOException;
    public static String getInputs(String targetUrl) throws IOException {
        File file = new File(targetUrl);

        StringBuffer stringBuffer = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
