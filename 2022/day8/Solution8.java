import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solution8 extends AbstractSolution{
    public static void main(String[] args) throws IOException {
        Solution8 solution = new Solution8();

        solution.problem1();
        solution.problem2();
    }
    public void problem1() throws IOException {
        String input = Solution7.getInputs("2022/day8/input.txt");

        List<List<Integer>> splitInput = List.of(input.split("\n")).stream()
                                    .map(x -> List.of(x.split("")).stream()
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList()))
                                    .collect(Collectors.toList());

        Integer rows = splitInput.size();
        Integer cols = splitInput.get(0).size();

        boolean[][] isVisible = new boolean[rows][cols];

        Integer count = 0;

        // 위에서 봤을 때,
        for(int i = 1; i < rows-1; i++) {
            Integer tmp = splitInput.get(i).get(0); // x , 0
            for (int j = 1; j < cols-1; j++) {
                Integer target = splitInput.get(i).get(j); // x, y
                if(target > tmp ) {
                    if((!isVisible[i][j])) {
                        isVisible[i][j] = true;
                        count++;
                    }
                    tmp = target;
                }
            }
        }

        // 위에서 봤을 때,
        for(int i = 1; i < rows-1; i++) {
            Integer tmp = splitInput.get(i).get(cols-1);
            for (int j = cols-2; j > 0; j--) {
                Integer target = splitInput.get(i).get(j);
                if(target > tmp ) {
                    if((!isVisible[i][j])) {
                        isVisible[i][j] = true;
                        count++;
                    }
                    tmp = target;
                }
            }
        }

        // 위에서 봤을 때,
        for(int i = 1; i < rows-1; i++) {
            Integer tmp = splitInput.get(0).get(i); // [0, x]
            for (int j = 1; j < cols-1; j++) {
                Integer target = splitInput.get(j).get(i);
                if(target > tmp ) {
                    if((!isVisible[j][i])) {
                        isVisible[j][i] = true;
                        count++;
                    }
                    tmp = target;
                }
            }
        }

        // 위에서 봤을 때,
        for(int i = rows-2; i > 0; i--) {
            Integer tmp = splitInput.get(rows-1).get(i);
            for (int j = cols-2; j > 0; j--) {
                Integer target = splitInput.get(j).get(i);
                if(target > tmp ) {
                    if((!isVisible[j][i])) {
                        isVisible[j][i] = true;
                        count++;
                    }
                    tmp = target;
                }
            }
        }

        System.out.println(count + 2 * (rows + cols) - 4);
    }

    public void problem2() throws IOException {

        String input = Solution7.getInputs("2022/day8/input.txt");

        List<List<Integer>> splitInput = List.of(input.split("\n")).stream()
                .map(x -> List.of(x.split("")).stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        Integer rows = splitInput.size();
        Integer cols = splitInput.get(0).size();

        // 0 <- x -> rows
        // 0 <- y -> cols
        Integer totalResult = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Integer result = findVisibleTrees(splitInput, i, j);
                System.out.println(result);
            }
        }
    }

    public static Integer findVisibleTrees(List<List<Integer>> trees, Integer x, Integer y){

        Integer rows = trees.size();
        Integer cols = trees.get(0).size();

        Integer totalResult = 1;

        Integer tmp = trees.get(y).get(x);
        Integer count = 0;
        Boolean isBlocked = false;
        for(int i = x-1; i >= 0; i--){
            Integer target = trees.get(y).get(i);
            if(target <= tmp && !isBlocked){
                count++;
            }else if(target > tmp && !isBlocked){
                tmp = target;
                count++;
                isBlocked = true;
            }else if(isBlocked && target > tmp){
                tmp = target;
                count++;
            }
        }

        if(count != 0)
            totalResult *= count;

        tmp = trees.get(y).get(x);
        count = 0;
        isBlocked = false;
        for(int i = x+1; i < rows; i++){
            Integer target = trees.get(y).get(i);
            if(target <= tmp && !isBlocked){
                count++;
            }else if(target > tmp && !isBlocked){
                tmp = target;
                count++;
                isBlocked = true;
            }else if(isBlocked && target > tmp){
                tmp = target;
                count++;
            }
        }

        if(count != 0)
            totalResult *= count;

        tmp = trees.get(y).get(x);
        count = 0;
        isBlocked = false;
        for(int i = y+1; i < cols; i++){
            Integer target = trees.get(i).get(x);
            if(target <= tmp && !isBlocked){
                count++;
            }else if(target > tmp && !isBlocked){
                tmp = target;
                count++;
                isBlocked = true;
            }else if(isBlocked && target > tmp){
                tmp = target;
                count++;
            }
        }

        if(count != 0)
            totalResult *= count;

         tmp = trees.get(y).get(x);
         count = 0;
         isBlocked = false;

        for(int i = y-1; i >= 0; i--){
            Integer target = trees.get(i).get(x);
            if(target <= tmp && !isBlocked){
                count++;
            }else if(target > tmp && !isBlocked){
                tmp = target;
                count++;
                isBlocked = true;
            }else if(isBlocked && target > tmp){
                tmp = target;
                count++;
            }
        }

        if(count != 0)
            totalResult *= count;

        return totalResult;
    }
}
