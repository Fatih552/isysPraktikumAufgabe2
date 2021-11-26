import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        DataReader dataReader = new DataReader("ressources/sample.csv");
        //LabelReader labelReader = new LabelReader("ressources/label.csv");
        dataReader.init();
        //labelReader.init();
        Agent bond = new Agent(dataReader.getFields());

        System.out.println(bond.filter());

    }
}
