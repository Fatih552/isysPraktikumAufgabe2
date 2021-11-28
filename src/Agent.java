import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Agent {

    public List<List<Field>> landscape;
    public List<Point> label;
    public List<List<Field>> plateaus = new ArrayList<>();
    public List<List<Field>> listOfFoundLabels = new ArrayList<>();
    public List<List<Field>> listOfFoundLabelsTemp = new ArrayList<>();
    int foundLabel;
    int actualLabel;
    double recall;
    double precision;
    double fscore;

    public Agent(List<List<Field>> landscape, List<Point> label) {
        this.landscape = landscape;
        this.label = label;
        actualLabel = label.size();
        filter();
    }

    public void filter() { //findlockmax

        System.out.println("initialize values..");

        int[] offset_x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] offset_y = {-1, 0, 1, -1, 1, -1, 0, 1};
        int nx;
        int ny;

        for (int v = 0; v < landscape.size(); v++) {
            for (int w = 0; w < landscape.get(v).size(); w++) {

                if(!landscape.get(v).get(w).visited) {
                    setLokMaxPlateau(landscape.get(v).get(w));
                }
            }
        }

        //40 = schwellwert ab so einer steigung radius nehmen wir auf
        //20 = radius welche werte ziehen wir in betracht
        //alle plateaus die über einen schwellwert kommen sind potentielle labels
        for(List<Field> plateau : plateaus) {
            listOfFoundLabelsTemp.add(plateau);
            if(plateau.get(plateau.size() / 2).relativeGradiant(20) > 115) {
                if(plateau.get(plateau.size() / 2).relativeDistance(listOfFoundLabelsTemp) > 11) {
                    listOfFoundLabels.add(plateau);
                }
            }
        }


        /*
        for (int v = 0; v < landscape.size(); v++) {
            for (int w = 0; w < landscape.get(v).size(); w++) {

                List<Field> neighbours = new ArrayList<>();
                List<Field> plateau = new ArrayList<>();
                Field temp = new Field(0,0,0);

                for (int i = 0; i < 8; i++) {
                    nx = v + offset_x[i];
                    ny = w + offset_y[i];
                    if(nx < 0 || ny < 0 || nx > landscape.size() - 1 || ny > landscape.get(v).size() - 1
                    || landscape.get(v).get(w).visited) continue;
                    else neighbours.add(landscape.get(nx).get(ny));

                }

                for(int i = 0; i < neighbours.size(); i++) {
                    temp = Field.max(neighbours.get(i), temp);
                }

                if(temp.value <= landscape.get(v).get(w).value) {
                    landscape.get(v).get(w).setLokal_max();
                    landscape.get(v).get(w).visited = true;
                    plateau.add(landscape.get(v).get(w));
                    plateaus.add(plateau);


                    for(int i = 0; i < neighbours.size(); i++) {

                        if(neighbours.get(i).value == landscape.get(v).get(w).value) {
                            landscape.get(v).get(w).setLokal_max();
                            landscape.get(v).get(w).visited = true;
                            plateau.add(landscape.get(v).get(w));
                            plateaus.add(plateau);
                        }

                    }


                }



            }
        }
         */

        /*
        for (int v = 0; v < landscape.size(); v++) {
            for (int w = 0; w < landscape.get(v).size(); w++) {

                int temp = 0;
                List<Integer> neighbours = new ArrayList<>();

                for (int i = 0; i < 8; i++) {
                    nx = v + offset_x[i];
                    ny = w + offset_y[i];
                    if(nx < 0 || ny < 0 || nx > landscape.size() - 1 || ny > landscape.get(v).size() - 1) continue;
                    else neighbours.add(landscape.get(nx).get(ny).value);
                }

                for(int i = 0; i < neighbours.size(); i++) {
                    temp = Math.max(neighbours.get(i), temp);
                }

                if(temp <= landscape.get(v).get(w).value) landscape.get(v).get(w).setLokal_max();
            }
        }

         */

        /*
        for(int i = 0; i < landscape.size(); i++) {
            for(int j = 0; j < landscape.get(i).size(); j++) {
                if (landscape.get(i).get(j).lokal_max) {
                    precisionFoundLabel.add(landscape.get(i).get(j));
                }
            }
        }

         */

        foundLabel = listOfFoundLabels.size();

    }

    public void setLokMaxPlateau(Field field) {


        List<Field> current = new ArrayList<>();

        int size = 0;

        current.add(field);

        while(size != current.size()) {

            size = current.size();
            for(int i = 0; i < size; i++) {
                current.addAll(current.get(i).findEqualNeighbours());
                current = current.stream().distinct().collect(Collectors.toList());
            }

        }

        for(Field field1 : current) {
            field1.setVisited();
            if(field1.checkHigherNeighbour()) {
                return;
            }
        }

        for(Field field1 : current) {
            field1.setLokal_max();
        }

        plateaus.add(current);

    }

    public void getFoundLabel() {
        System.out.println("\nAnzahl gefundener lokaler Maxima: " + foundLabel);
        plateaus.forEach(System.out::println);
    }

    public int getActualLabel() {
        return actualLabel;
    }

    public double getRecall() {
        return recall;
    }

    public double getPrecision() {
        return precision;
    }

    public double getFScore() {
        return fscore;
    }

    public void calculateRecall() {
        int count = 0;

        List<Field> result = new ArrayList<>();

        for(int i = 0; i < listOfFoundLabels.size(); i++) {
            for(int j = 0; j < listOfFoundLabels.get(i).size(); j++) {
                int x = listOfFoundLabels.get(i).get(j).x;
                int y = listOfFoundLabels.get(i).get(j).y;
                Point temp = new Point(x, y);
                if(label.contains(temp)) {
                    count++;
                    result.add(landscape.get(x).get(y));
                    j = listOfFoundLabels.get(i).size();
                }
            }
        }
        List<Double> integers = new ArrayList<>();

        for(Field field : result) {
            integers.add(field.relativeDistance(listOfFoundLabels));
        }

        System.out.println("avg " + integers.stream().flatMapToDouble(aDouble -> DoubleStream.of(aDouble)).average());

        recall = (double) count / label.size();
        /*
        recall = (double) foundLabel / actualLabel;

         */
    }
    public void calculatePrecision() {
        int count = 0;

        for(int i = 0; i < listOfFoundLabels.size(); i++) {
            for(int j = 0; j < listOfFoundLabels.get(i).size(); j++) {
                int x = listOfFoundLabels.get(i).get(j).x;
                int y = listOfFoundLabels.get(i).get(j).y;
                Point temp = new Point(x, y);
                if(label.contains(temp)) {
                    count++;
                    j = listOfFoundLabels.get(i).size();
                }
            }
        }

        precision = (double) count / listOfFoundLabels.size();


        /*
        List<Field> labelWerte = new ArrayList<>();
        int counter = 0;

        for(int i = 0; i < landscape.size(); i ++) {
            for(int j = 0; j < landscape.get(i).size(); j++) {
                for (Point p : label) {
                    if(landscape.get(i).get(j).x == p.x && landscape.get(i).get(j).y == p.y) {
                        Field field = new Field(landscape.get(i).get(j).x, landscape.get(i).get(j).y, landscape.get(i).get(j).value);
                        labelWerte.add(field);
                    }
                }
            }
        }

        for(Field f1 : precisionFoundLabel) {
            for (Field f2 : labelWerte) {
                if(f1.x == f2.x && f1.y == f2.y && f1.value == f2.value)
                    counter++;
            }
        }

        precision = (double) counter / precisionFoundLabel.size();
        */
    }

    public void calculateFScore() {
        fscore = (2 * getRecall() * getPrecision()) / (getRecall() + getPrecision());
    }

    public void calculateStats() {
        System.out.println("Calculating Metrics..");
        calculateRecall();
        calculatePrecision();
        calculateFScore();

    }

}
