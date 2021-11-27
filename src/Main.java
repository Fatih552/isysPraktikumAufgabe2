import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        DataReader dataReader = new DataReader("ressources/sample.csv");
        LabelReader labelReader = new LabelReader("ressources/label.csv");
        dataReader.init();
        labelReader.init();
        Agent bond = new Agent(dataReader.getFields(), labelReader.getLabel());

        System.out.println("Gesamt lokale Maxima: " + bond.filter());



        /*
        bond.calculateStats();
        System.out.println("Recall: " + bond.getRecall());
        System.out.println("Precision: " + bond.getPrecision());
        System.out.println("F-Score: " + bond.getFScore());

         */

    }
}
