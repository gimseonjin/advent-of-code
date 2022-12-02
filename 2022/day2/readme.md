
--- Day 2: Rock Paper Scissors ---

The Elves begin to set up camp on the beach. To decide whose tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already in progress.

Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round, the players each simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then, a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose the same shape, the round instead ends in a draw.

Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) that they say will be sure to help you win. "The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. The second column--" Suddenly, the Elf is called away to help with someone's tent.

The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors. Winning every time would be suspicious, so the responses must have been carefully chosen.

The winner of the whole tournament is the player with the highest score. Your total score is the sum of your scores for each round. The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the score you would get if you were to follow the strategy guide.

For example, suppose you were given the following strategy guide:

A Y
B X
C Z
This strategy guide predicts and recommends the following:

In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won).
In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss for you with a score of 1 (1 + 0).
The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).

What would your total score be if everything goes exactly according to your strategy guide?

~~~java
    public static void problem1() throws IOException {
        String input = Utils2.getInputs("2022/day2/input.txt");

        Integer result = List.of(input.split("\n")).stream().
            map(x -> x.split(" ")).
            map(x -> Solution2.getMyPointInPartOne(x)).
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
~~~

Your puzzle answer was 11603.


===================

--- Part Two ---
The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

The total score is still calculated in the same way, but now you need to figure out what shape to choose so the round ends as indicated. The example above now goes like this:

In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y), so you also choose Rock. This gives you a score of 1 + 3 = 4.
In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a score of 1 + 0 = 1.
In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.

Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?

~~~java
    public static void problem2() throws IOException {
    
        String input = Utils2.getInputs("2022/day2/input.txt");

        Integer result = List.of(input.split("\n")).stream().
            map(x -> x.split(" ")).
            map(x -> Solution2.getMyPointInPartTwo(x)).
            reduce(Integer::sum).get();

        System.out.println(result);
    }


    public static Integer getMyPointInPartTwo(String [] input){

        String you = input[0];
        String me = "";

        switch (input[1]){
            case "X": me = Utils2.TO_LOSE.get(you); break;
            case "Y": me = Utils2.TO_DRAW.get(you); break;
            case "Z": me = Utils2.TO_WIN.get(you); break;
        }

        return Utils2.GUIDE.get(me) + Solution2.getOutcome(you, me);
    }
        
    
    public static Integer getOutcome(String you, String me){

        if((you.equals("A") && me.equals("X")) || (you.equals("B") && me.equals("Y")) || (you.equals("C") && me.equals("Z")))
            return 3;

        if((you.equals("A") && me.equals("Z")) || (you.equals("B") && me.equals("X")) || (you.equals("C") && me.equals("Y")))
            return 0;

        return 6;
    }
~~~


Your puzzle answer was 12725.

===================
This IS Utils For Day2

~~~java
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
~~~

===================