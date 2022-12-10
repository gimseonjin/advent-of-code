import java.io.IOException;
import java.util.*;

public class Solution7 extends AbstractSolution{

    public static void main(String[] args) throws IOException {
        Solution7 solution7 = new Solution7();

        solution7.problem1();
        solution7.problem2();
    }

    @Override
    public void problem1() throws IOException {
        String input = Solution7.getInputs("2022/day7/input.txt");

        HashMap<String, DirectoryEntry> directoryList = getDirectoryEntryHashMap(input);

        Integer result = directoryList.values().stream()
                .map(x -> x.getSize())
                .filter(x -> x<100000)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }

    @Override
    public void problem2() throws IOException {
        String input = Solution7.getInputs("2022/day7/input.txt");

        HashMap<String, DirectoryEntry> directoryList = getDirectoryEntryHashMap(input);

        Integer totalSize = directoryList.get("/").getSize();

        Integer requiredSize = 30_000_000 + totalSize - 70_000_000;

        Integer result = directoryList.values().stream()
                .map(x -> x.getSize())
                .filter(x -> x > requiredSize).sorted()
                .findFirst().get();

        System.out.println(result);
    }

    public static HashMap<String, DirectoryEntry> getDirectoryEntryHashMap(String input){

        HashMap<String, DirectoryEntry> directoryEntryHashMap = new HashMap<>();
        DirectoryEntry root = new DirectoryEntry("/", null);

        directoryEntryHashMap.put("/", root);
        DirectoryEntry curDirectory = root;

        for(String command: input.split("\n")){
            String[] commandArr = command.split(" ");

            /*
            if(command.contains("$")){
                switch (handleCommand(command)){
                    case "go to root" -> curDirectory = root;
                    case "go parents" -> curDirectory.getParents();
                    case "go directory" -> curDirectory = curDirectory.getFile(commandArr[2]);
                    case "show directory" -> System.out.println("ls");
                }

             */

            if(commandArr[0].equals("$")){ // command with cd -> ls는 단순 조회여서 필요없음

                if(commandArr[1].equals("cd")) {
                    if (commandArr[2].equals("/")) {
                        curDirectory = root;
                    }

                    else if (commandArr[2].equals("..")) {
                        curDirectory = curDirectory.getParents();
                    }

                    else {
                        curDirectory = curDirectory.getFile(commandArr[2]);
                    }
                }

            }else if(commandArr[0].equals("dir")){ // add dir
                DirectoryEntry cur = curDirectory;
                DirectoryEntry de = new DirectoryEntry(commandArr[1], cur);
                cur.addFile(de);
                directoryEntryHashMap.put(commandArr[1], de);
            }

            else{ // add file
                curDirectory.addFile(new FileEntry(commandArr[1], Integer.parseInt(commandArr[0])));
            }
        }


        return directoryEntryHashMap;
    }

    public static String handleCommand(String command){
        if(command.contains("ls")) return "show directory";
        if(command.contains("cd /")) return "go to root";
        if(command.contains("cd ..")) return "go parents";
        return "go directory";
    }
}

class DirectoryEntry extends FileEntry{
    private DirectoryEntry parents;
    private HashMap<String, FileEntry> files = new HashMap();

    public DirectoryEntry(String name, DirectoryEntry parents) {
        super(name, 0);
        this.parents = parents;
    }

    public Integer getSize(){
        return files.values().stream().map(x -> x.getSize()).reduce(0, Integer::sum);
    }

    public void addFile(FileEntry fe){
        this.files.put(fe.getName(), fe);
    }

    public DirectoryEntry getParents() {
        return parents;
    }

    public DirectoryEntry getFile(String name){
        return (DirectoryEntry) files.get(name);
    }

}

class FileEntry{
    private String name;
    private Integer size;

    public FileEntry(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }
}