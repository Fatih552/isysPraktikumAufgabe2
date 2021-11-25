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
    public List<Double> row = new ArrayList<>();
    public List<Field> fields = new ArrayList<>();
    public List<List<Double>> landscape = new ArrayList<>();

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
            row = temp.stream().map(Double::parseDouble).collect(Collectors.toCollection(ArrayList::new));
            landscape.add(row);
        }

        for(List<Double> list : landscape) {
            count_y = 0;
            for(Double value : list) {
                Field field = new Field(count_x, count_y++, value);
                fields.add(field);
            }
            count_x++;
        }
        System.out.println("Reading process finished!");
    }

    public List<Field> getFields() {
        return fields;
    }

    public static void pretty_print(List<Field> list) {
        list.forEach(s -> System.out.println(s.toString()));
    }


}
