import java.io.IOException;
import java.util.List;

public class Solution2 {

    public static void main(String args[]) throws IOException {
        Solution2.problem1();
        Solution2.problem2();
    }

    public static void problem1() throws IOException {
        String input = Utils2.getInputs("2022/day2/input.txt");

        Integer result = List.of(input.split("\n")).stream().
                map(x -> x.split(" ")).
                map(x -> Solution2.getMyPointInPartOne(x)).
                reduce(Integer::sum).get();

        System.out.println(result);
    }

    public static void problem2() throws IOException {
        String input = Utils2.getInputs("2022/day2/input.txt");

        Integer result = List.of(input.split("\n")).stream().
                map(x -> x.split(" ")).
                map(x -> Solution2.getMyPointInPartTwo(x)).
                reduce(Integer::sum).get();

        System.out.println(result);
    }

    public static Integer getMyPointInPartOne(String [] input){

        String you = input[0];
        String me = input[1];

        return Utils2.GUIDE.get(me) + Solution2.getOutcome(you, me);
    }

    public static Integer getOutcome(String you, String me){

        if((you.equals("A") && me.equals("X")) || (you.equals("B") && me.equals("Y")) || (you.equals("C") && me.equals("Z")))
            return 3;

        if((you.equals("A") && me.equals("Z")) || (you.equals("B") && me.equals("X")) || (you.equals("C") && me.equals("Y")))
            return 0;

        return 6;
    }

    public static Integer getMyPointInPartTwo(String [] input){

        String you = input[0];
        String me = "";

        switch (input[1]){
            case "X" -> me = Utils2.TO_LOSE.get(you);
            case "Y" -> me = Utils2.TO_DRAW.get(you);
            case "Z" -> me = Utils2.TO_WIN.get(you);
        }

        return Utils2.GUIDE.get(me) + Solution2.getOutcome(you, me);
    }

}
