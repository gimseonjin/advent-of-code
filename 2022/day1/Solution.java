
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws IOException {
        Solution.problem1();
        Solution.problem2();
    }

    public static void problem1() throws IOException {
        String input = Solution.getInputs("2022/day1/input.txt");

        Integer result = List.of(input.split(" ")).stream() // 칼로리를 엘프 별로 분리합니다.
                .map(x -> List.of(x.split("\n"))) // 엘프 별로 구한 칼로리를 분리합니다.
                .map(x -> x.stream().mapToInt(Integer::parseInt).sum()) // 구한 칼로리를 합해줍니다.
                .sorted(Comparator.reverseOrder()) // 합한 칼로리를 역순으로 정렬합니다.
                .findFirst().get(); // 가장 높은 값을 가져옵니다.

        System.out.println("Problem 1 => : " + result);
    }

    public static void problem2() throws IOException {
        String input = Solution.getInputs("2022/day1/input.txt");

        Integer result = List.of(input.split(" ")).stream() // 칼로리를 엘프 별로 분리합니다.
                .map(x -> List.of(x.split("\n"))) // 엘프 별로 구한 칼로리를 분리합니다.
                .map(x -> x.stream().mapToInt(Integer::parseInt).sum()) // 구한 칼로리를 합해줍니다.
                .sorted(Comparator.reverseOrder()) // 합한 칼로리를 역순으로 정렬합니다.
                .limit(3) // 가장 높은 3명의 값을 가져옵니다.
                .reduce(Integer::sum).get(); // 총 합을 구합니다.

        System.out.println("Problem 2 => : " + result);
    }

    public static String getInputs(String targetUrl) throws IOException {
        File file = new File(targetUrl);

        StringBuffer stringBuffer = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(""))
                    stringBuffer.append(" ");
                else {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
