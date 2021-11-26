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

        for (int i = 0; i < 8; i++) {
            nx = 1 + offset_x[i];
            ny = 1 + offset_y[i];
            if(landscape.get(nx).get(ny).value > landscape.get(1).get(1).value) {
                landscape.get(1).get(1).lokal_max = false;
                break;
            }
            else landscape.get(1).get(1).setLokal_max();
        }

        for(int i = 0; i < landscape.size(); i++) {
            for(int j = 0; j < landscape.get(i).size(); j++) {
                if (landscape.get(i).get(j).lokal_max) counter++;
            }
        }

        return counter;


    }



}
