--- Day 5: Supply Stacks ---

The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be rearranged.

The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are rearranged, the desired crates will be at the top of each stack.

The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.

They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle input). For example:

~~~
    [D]    
[N] [C]    
[Z] [M] [P]
1   2   3
~~~
move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.

Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2 to stack 1, resulting in this configuration:

~~~
[D]        
[N] [C]    
[Z] [M] [P]
1   2   3
~~~

In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first crate to be moved (D) ends up below the second and third crates:

~~~
        [Z]
        [N]
    [C] [D]
    [M] [P]
1   2   3
~~~
Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up below crate M:

~~~
        [Z]
        [N]
[M]     [D]
[C]     [P]
1   2   3
~~~
Finally, one crate is moved from stack 1 to stack 2:

~~~
        [Z]
        [N]
        [D]
[C] [M] [P]
1   2   3
~~~
The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.

After the rearrangement procedure completes, what crate ends up on top of each stack?


~~~java
    public static void problem1() throws IOException {
        List<List<String>> crates = Solution5.getCrates();
        String input = Solution5.getInputs("2022/day5/input.txt");

        // 입력값을 읽고 MoveRequest 객체로 변환 후, mover9000로 움직임
        List.of(input.split("\n")).stream()
            .map(MoveRequest::createMoveRequest)
            .forEach(x -> Solution5.moveWithCrateMover9000(crates, x));

        // 각 crate의 마지막 단어(x.size()-1 번째)를 가져와 하나의 문자열로 합침
        List<String> result = crates.stream()
                .map(x -> x.get(x.size()-1))
                .collect(Collectors.toList());

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


~~~


Your puzzle answer was HBTMTBSDC.


===================

--- Part Two ---
As you watch the crane operator expertly rearrange the crates, you notice the process isn't following your prediction.

Some mud was covering the writing on the side of the crane, and you quickly wipe it away. The crane isn't a CrateMover 9000 - it's a CrateMover 9001.

The CrateMover 9001 is notable for many new and exciting features: air conditioning, leather seats, an extra cup holder, and the ability to pick up and move multiple crates at once.

Again considering the example above, the crates begin in the same configuration:

~~~
    [D]    
[N] [C]    
[Z] [M] [P]
1   2   3
~~~
Moving a single crate from stack 2 to stack 1 behaves the same as before:
~~~
[D]        
[N] [C]    
[Z] [M] [P]
1   2   3
~~~
However, the action of moving three crates from stack 1 to stack 3 means that those three moved crates stay in the same order, resulting in this new configuration:

~~~
        [D]
        [N]
    [C] [Z]
    [M] [P]
1   2   3
~~~
Next, as both crates are moved from stack 2 to stack 1, they retain their order as well:

~~~
        [D]
        [N]
[C]     [Z]
[M]     [P]
1   2   3
~~~
Finally, a single crate is still moved from stack 1 to stack 2, but now it's crate C that gets moved:

~~~
        [D]
        [N]
        [Z]
[M] [C] [P]
1   2   3
~~~
In this example, the CrateMover 9001 has put the crates in a totally different order: MCD.

Before the rearrangement process finishes, update your simulation so that the Elves know where they should stand to be ready to unload the final supplies. After the rearrangement procedure completes, what crate ends up on top of each stack?

~~~java
    public static void problem2() throws IOException {
        List<List<String>> crates = Solution5.getCrates(); // 데이터 부분? 액션?? => getCrates 메소드는 액션이고 crates는 데이터인가?
        String input = Solution5.getInputs("2022/day5/input.txt");

        // 입력값을 읽고 MoveRequest 객체로 변환 후, mover9001로 움직임
        List.of(input.split("\n")).stream()
            .map(MoveRequest::createMoveRequest)
            .forEach(x -> Solution5.moveWithCrateMover9001(crates, x));

        // 각 crate의 마지막 단어(x.size()-1 번째)를 가져와 하나의 문자열로 합침
        List<String> result = crates.stream()
                .map(x -> x.get(x.size()-1))
                .collect(Collectors.toList());

        System.out.println(String.join("", result));
    }

    public static List<List<String>> getCrates() throws IOException {
        String crates = Solution5.getInputs("2022/day5/crates.txt");
        return List.of(crates.split("\n")).stream()
                .map(x -> Arrays.stream(x.split("")).collect(Collectors.toList()))
                .collect(Collectors.toList());
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

~~~


Your puzzle answer was PQTJRSHWS.



===================

아래는 제가 사용한 MoveRequest Class 입니다.


~~~java

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
}
~~~