import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabelReader {
    public String path;
    public String line = "";
    public String[] arr;
    public List<Point> label = new ArrayList<>();

    public LabelReader(String path) {
        this.path = path;
    }

    public void init() throws IOException {
        System.out.println("Reading Labels..");
        BufferedReader br = new BufferedReader(new FileReader(path));

        while((line = br.readLine()) != null) {
            arr = line.split(",");
            for(int i = 0; i < 1; i++) {
                Point point = new Point(Integer.parseInt(arr[i]), Integer.parseInt(arr[i + 1]));
                label.add(point);
            }

        }
    }

    public List<Point> getLabel() {
        return label;
    }

    public static void pretty_print(List<Point> list) {
        list.forEach(s -> System.out.println("Koordinate (" + s.x + "/" + s.y + ")"));
    }

}
