import java.util.ArrayList;
import java.util.List;

public class Agent {


    public List<List<Field>> landscape;

    public Agent(List<List<Field>> landscape) {
        this.landscape = landscape;
    }

    public int filter() {
        int[] offset_x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] offset_y = {-1, 0, 1, -1, 1, -1, 0, 1};
        int nx;
        int ny;
        int counter = 0;

        for (int v = 0; v < landscape.size(); v++) {
            for (int w = 0; w < landscape.get(v).size(); w++) {
                for (int i = 0; i < 8; i++) {
                    nx = v + offset_x[i];
                    ny = w + offset_y[i];
                    if(nx < 0 || ny < 0 || nx > landscape.size() - 1 || ny > landscape.get(v).size() - 1) continue;
                    if(landscape.get(nx).get(ny).value > landscape.get(v).get(w).value) {
                        landscape.get(v).get(w).lokal_max = false;
                    }
                    else landscape.get(v).get(w).setLokal_max();
                }
            }
        }



        for(int i = 0; i < landscape.size(); i++) {
            for(int j = 0; j < landscape.get(i).size(); j++) {
                if (landscape.get(i).get(j).lokal_max) {
                    counter++;
                    System.out.println(landscape.get(i).get(j).toString());
                }
            }
        }

        return counter;


    }



}
