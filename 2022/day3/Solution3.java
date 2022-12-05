import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Solution3 {

    public static void main(String args[]) throws IOException {
        problem1();
        problem2();
    }

    public static void problem1() throws IOException {
        String input = Solution3.getInputs("2022/day3/input.txt");

        Integer result = List.of(input.split("\n")).stream()
                .map(Solution3::splitHalfString)
                .map(Solution3::findDuplicateChar)
                .map(Solution3::charToPoint)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }

    public static void problem2() throws IOException {
        String input = Solution3.getInputs("2022/day3/input.txt");
        final AtomicInteger counter = new AtomicInteger();

        var result = List.of(input.split("\n")).stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 3)).values().stream()
                .map(Solution3::findDuplicateChar)
                .map(Solution3::charToPoint)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }

    public static List<String> splitHalfString(String s){
        return List.of(s.substring(0, (s.length()/2)), s.substring(s.length()/2));
    }

    public static char findDuplicateChar(List<String> sList){
        return sList.stream()
                .map(Solution3::deleteDuplicateInString) // 중복 제거 -> O(n log n) + O (n) => O(n log n)
                .flatMap(x -> x.stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream() // O(n)
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))// O(n log n)
                .findFirst().get()
                .getKey()
                .charAt(0); // k = sList.length => O(k * n log n) => O(n log n)
    }

    public static List<String> deleteDuplicateInString(String s){
        return List.of(s.split("")).stream().distinct().collect(toList());
    }

    public static int charToPoint(char c){
        int cAscii = c;

        if(cAscii > 96) return cAscii - 96; // a = 97 -> 1

        return cAscii - 38; // A = 65 -> 27
    }


    /* It is my first answer => It takes O(n ** n)
    public static char findDuplicateChar(List<String> sList){
        String s1 = sList.get(0);
        String s2 = sList.get(1);

        for(char c1 : s1.toCharArray()){
            for(char c2 : s2.toCharArray()){
                if(c1 == c2){
                    return c1;
                }
            }
        }

        return 00; // null
    }

    public static char findDuplicateCharWith3List(List<String> sList){
        String s1 = sList.get(0);
        String s2 = sList.get(1);
        String s3 = sList.get(2);

        for(char c1 : s1.toCharArray()){
            for(char c2 : s2.toCharArray()){
                for(char c3 : s3.toCharArray()){
                    if(c1 == c2 && c2 == c3){
                        return c1;
                    }
                }
            }
        }


        return 00; // null
    }
    */

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
