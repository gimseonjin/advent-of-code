import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Utils2 {

    static HashMap<String, Integer> GUIDE = new HashMap<String, Integer>() {{
        put("A", 1);
        put("B", 2);
        put("C", 3);
        put("X", 1);
        put("Y", 2);
        put("Z", 3);
    }};

    static HashMap<String, String> TO_WIN = new HashMap<String, String>() {{
        put("A", "Y");
        put("B", "Z");
        put("C", "X");
    }};

    static HashMap<String, String> TO_LOSE = new HashMap<String, String>() {{
        put("A", "Z");
        put("B", "X");
        put("C", "Y");
    }};

    static HashMap<String, String> TO_DRAW = new HashMap<String, String>() {{
        put("A", "X");
        put("B", "Y");
        put("C", "Z");
    }};

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
