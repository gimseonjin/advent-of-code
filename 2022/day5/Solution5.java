import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution5 {

    public static void main(String[] args) throws IOException {
        problem1();
        problem2();
    }

    public static void problem1() throws IOException {
        List<List<String>> crates = Solution5.getCrates(); // 데이터 부분? 액션?? => getCrates 메소드는 액션이고 crates는 데이터인가?
        String input = Solution5.getInputs("2022/day5/input.txt");

        List.of(input.split("\n")).stream()
                .map(MoveRequest::createMoveRequest)
                .forEach(x -> Solution5.moveWithCrateMover9000(crates, x));

        List<String> result = crates.stream().map(x -> x.get(x.size()-1)).collect(Collectors.toList());

        System.out.println(String.join("", result));
    }

    public static void problem2() throws IOException {
        List<List<String>> crates = Solution5.getCrates(); // 데이터 부분? 액션?? => getCrates 메소드는 액션이고 crates는 데이터인가?
        String input = Solution5.getInputs("2022/day5/input.txt");

        List.of(input.split("\n")).stream()
                .map(MoveRequest::createMoveRequest)
                .forEach(x -> Solution5.moveWithCrateMover9001(crates, x));

        List<String> result = crates.stream().map(x -> x.get(x.size()-1)).collect(Collectors.toList());

        System.out.println(String.join("", result));
    }

    public static List<List<String>> getCrates() throws IOException {
        String crates = Solution5.getInputs("2022/day5/crates.txt");
        return List.of(crates.split("\n")).stream()
                .map(x -> Arrays.stream(x.split("")).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void moveWithCrateMover9000(List<List<String>> crates, MoveRequest moveRequest) { // 순수함수 아님! -> 왜냐하면 외부의 crates를 핸들링하니까!! -> 부수효과 발생
        List<String> fromCrates = crates.get(moveRequest.getFrom()-1);
        List<String> toCrates = crates.get(moveRequest.getTo()-1);

        for(int i = 0; i < moveRequest.getCount(); i++){
            String target = fromCrates.get(fromCrates.size()-1);
            fromCrates.remove(fromCrates.size()-1);
            toCrates.add(target);
        }
    }

    public static void moveWithCrateMover9001(List<List<String>> crates, MoveRequest moveRequest) { // 순수함수 아님! -> 왜냐하면 외부의 crates를 핸들링하니까!! -> 부수효과 발생
        List<String> fromCrates = crates.get(moveRequest.getFrom()-1);
        List<String> toCrates = crates.get(moveRequest.getTo()-1);

        List<String> temp = new ArrayList<>();
        for(int i = 0; i < moveRequest.getCount(); i++){
            String target = fromCrates.get(fromCrates.size()-1);
            temp.add(target);
            fromCrates.remove(fromCrates.size()-1);
        }

        Collections.reverse(temp);
        toCrates.addAll(temp);
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

class MoveRequest {

    private Integer count;
    private Integer from;
    private Integer to;

    public MoveRequest(Integer count, Integer from, Integer to) {
        this.count = count;
        this.from = from;
        this.to = to;
    }

    public static MoveRequest createMoveRequest(String s){
        String[] splitString = s.split(" "); // move, 3, from, 3, to, 7

        Integer count = Integer.parseInt(splitString[1]);
        Integer from = Integer.parseInt(splitString[3]);
        Integer to = Integer.parseInt(splitString[5]);

        return new MoveRequest(count, from, to);
    }

    public Integer getCount() {
        return count;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "MoveRequest{" +
                "count=" + count +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}