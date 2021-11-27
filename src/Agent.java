import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Agent {


    public List<List<Field>> landscape;
    public List<Point> label;
    public List<Field> precisionFoundLabel = new ArrayList<>();
    int foundLabel;
    int actualLabel;
    double recall;
    double precision;
    double fscore;

    public Agent(List<List<Field>> landscape, List<Point> label) {
        this.landscape = landscape;
        this.label = label;
        actualLabel = label.size();
    }

    public int filter() {

        int[] offset_x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] offset_y = {-1, 0, 1, -1, 1, -1, 0, 1};
        int nx;
        int ny;
        int counter = 0;


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


        for(int i = 0; i < landscape.size(); i++) {
            for(int j = 0; j < landscape.get(i).size(); j++) {
                if (landscape.get(i).get(j).lokal_max) {
                    precisionFoundLabel.add(landscape.get(i).get(j));
                    System.out.println(landscape.get(i).get(j).toString());
                    counter++;
                }
            }
        }

        foundLabel = counter;
        return counter;

    }

    public int getFoundLabel() {
        return foundLabel;
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
        recall = (double) foundLabel / actualLabel;
    }
    public void calculatePrecision() {
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
    }

    public void calculateFScore() {
        fscore = 2 * getRecall() * getPrecision() / (getRecall() + getPrecision());
    }

    public void calculateStats() {
        System.out.println("Calculating Metrics..");
        calculateRecall();
        calculatePrecision();
        calculateFScore();

    }

}
