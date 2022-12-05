import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solution4 {

    public static void main(String[] args) throws IOException {

        problem1();
        problem2();
    }

    public static void problem1() throws IOException {
        String input = Solution3.getInputs("2022/day4/input.txt");

        Long result = List.of(input.split("\n")).stream()
                .map(x -> x.split(","))
                .map(Solution4::makeSectionArray)
                .filter(sections -> Section.isContain(sections.get(0), sections.get(1)))
                .count();

        System.out.println(result);
    }

    public static void problem2() throws IOException {
        String input = Solution3.getInputs("2022/day4/input.txt");

        Long result = List.of(input.split("\n")).stream()
                .map(x -> x.split(","))
                .map(Solution4::makeSectionArray)
                .filter(sections -> Section.isOverlap(sections.get(0), sections.get(1)))
                .count();

        System.out.println(result);
    }

    public static List<Section> makeSectionArray(String [] strings){
        return List.of(strings).stream()
                .map(Section::createSection)
                .collect(Collectors.toList());
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

class Section{

    private Integer start;
    private Integer end;

    public Section(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public static Section createSection(String s){

        Integer start = Integer.parseInt(s.split("-")[0]);
        Integer end = Integer.parseInt(s.split("-")[1]);

        return new Section(start, end);
    }

    public static Boolean isContain(Section s1, Section s2){
        return (s1.start <= s2.start && s2.end <= s1.end) || (s2.start <= s1.start && s1.end <= s2.end);
    }

    public static Boolean isOverlap(Section s1, Section s2){
        return !(s1.start > s2.end || s2.start > s1.end);
    }
}