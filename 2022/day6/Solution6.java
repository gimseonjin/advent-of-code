import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution6 {

    public static void main(String[] args) throws IOException {
        problem1();
        problem2();
    }

    public static void problem1() throws IOException {
        String input = Solution5.getInputs("2022/day6/input.txt");

        findStartOfPacketMarker(input, 4);
    }

    public static void problem2() throws IOException {
        String input = Solution5.getInputs("2022/day6/input.txt");

        findStartOfPacketMarker(input, 14);
    }

    public static void findStartOfPacketMarker(String input, Integer packetLength){
        for(int i = 0; i < input.length(); i++){
            String target = input.substring(i, i+packetLength);

            if(isStartOfPacketMarker(target)){
                System.out.println(i+target.length());
                break;
            }
        }
    }

    public static Boolean isStartOfPacketMarker(String target){
        Long distinctStringCount = List.of(target.split("")).stream().distinct().count();
        return distinctStringCount == target.length();
    }


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
