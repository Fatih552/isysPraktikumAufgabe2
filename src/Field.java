import java.util.ArrayList;
import java.util.List;

public class Field {

    public int x;
    public int y;
    public int value;
    public boolean lokal_max = false;
    public boolean visited = false;
    public List<List<Field>> landscape = new ArrayList<>();

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public List<Field> findEqualNeighbours() {
        List<Field> result = new ArrayList<>();
        int nx;
        int ny;

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                nx = this.x + i;
                ny = this.y + j;

                if(nx < 0 || ny < 0 || nx > landscape.size() - 1 ||
                        ny > landscape.get(0).size() - 1) continue;
                if(landscape.get(nx).get(ny).value == this.value) {
                    result.add(landscape.get(nx).get(ny));
                }
            }
        }

        return result;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setLandscape(List<List<Field>> landscape) {
        this.landscape = landscape;
    }

    /**
     * print a coordinate and its value on console
     * @return String.
     */
    public String toString() {
        return "Koordinate: (" + x + "/" + y + ") Wert: " + value;
    }

    public static Field max(Field f1, Field f2) {
        Field result = new Field(0,0,0);
        if(f1.value >= f2.value) {
            result.x = f1.x;
            result.y = f1.y;
            result.value = f1.value;
            return result;
        } else {
            result.x = f2.x;
            result.y = f2.y;
            result.value = f2.value;
            return result;
        }
    }


    public void setLokal_max() {
        lokal_max = true;
    }

}
