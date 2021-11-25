import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        DataReader dataReader = new DataReader("ressources/sample.csv");
        //LabelReader labelReader = new LabelReader("ressources/label.csv");
        dataReader.init();
        //labelReader.init();

        List<List<Field>> landscape = dataReader.getFields();


        for (int i = 0; i < landscape.size(); i++) {
            for (int j = 0; j < landscape.get(i).size(); j++) {




            }
        }

    }
}
