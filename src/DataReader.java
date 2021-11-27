import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    public String path;
    public String line = "";
    public String[] arr;
    public List<String> temp = new ArrayList<>();
    public List<Integer> row = new ArrayList<>();
    public List<Field> fields = new ArrayList<>();
    public List<List<Integer>> landscape = new ArrayList<>();

    public List<List<Field>> zweiD = new ArrayList<>();



    int count_x = 0;
    int count_y = 0;

    public DataReader(String path) {
        this.path = path;
    }

    public void init() throws IOException {
        System.out.println("Reading Data..");
        BufferedReader br = new BufferedReader(new FileReader(path));

        while((line = br.readLine()) != null) {
            arr = line.split(",");
            temp = Arrays.asList(arr);
            row = temp.stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
            landscape.add(row);
        }

        for(List<Integer> list : landscape) {
            count_y = 0;
            List<Field> zeile = new ArrayList<>();
            for(Integer value : list) {
                Field field = new Field(count_x, count_y++, value);
                zeile.add(field);
            }
            zweiD.add(zeile);
            count_x++;
        }
    }

    public List<List<Field>> getFields() {
        return zweiD;
    }

    public static void pretty_print(List<Field> list) {
        list.forEach(s -> System.out.println(s.toString()));
    }


}
