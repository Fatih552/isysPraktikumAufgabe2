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

    public double relativeDistance(List<List<Field>> listOfPotentialLabels) {
        boolean found = false;

        int radius = 6;
        while (!found) {
            int nx;
            int ny;
            int temp = 0;
            int count = 0;
            radius++;
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    nx = this.x + i;
                    ny = this.y + j;

                    try {
                        if(i == radius || i == - radius || j == radius || j == -radius) {
                            if (landscape.get(nx).get(ny).lokal_max) {

                                for (List<Field> fields : listOfPotentialLabels) {
                                    if (!(fields.contains(landscape.get(nx).get(ny)) && fields.contains(this))) {
                                        found = true;
                                        j = radius + 1;
                                        i = radius + 1;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {

                    }




                }

            }

        }
        return radius;
    }



    //relative Steigung
    public double relativeGradiant(int radius) {
        int nx;
        int ny;
        int temp = 0;
        int count = 0;

        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                nx = this.x + i;
                ny = this.y + j;

                try {
                    temp = temp + landscape.get(nx).get(ny).value;
                    count++;
                } catch (Exception e) {

                }

                //if(nx < 0 || ny < 0 || nx > landscape.size() - 1 || ny > landscape.get(0).size() - 1) continue;

            }
        }
        return this.value - (double) temp / count;
    }

    public List<Field> findEqualNeighbours() {
        List<Field> result = new ArrayList<>();
        int nx;
        int ny;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                nx = this.x + i;
                ny = this.y + j;

                if (nx < 0 || ny < 0 || nx > landscape.size() - 1 ||
                        ny > landscape.get(0).size() - 1) continue;
                if (landscape.get(nx).get(ny).value == this.value) {
                    result.add(landscape.get(nx).get(ny));
                }
            }
        }

        return result;
    }

    public boolean checkHigherNeighbour() {
        int nx;
        int ny;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                nx = this.x + i;
                ny = this.y + j;

                if (nx < 0 || ny < 0 || nx > landscape.size() - 1 ||
                        ny > landscape.get(0).size() - 1) continue;
                if (landscape.get(nx).get(ny).value > this.value) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setLandscape(List<List<Field>> landscape) {
        this.landscape = landscape;
    }

    /**
     * print a coordinate and its value on console
     *
     * @return String.
     */
    public String toString() {
        return "Koordinate: (" + x + "/" + y + ") Wert: " + value;
    }

    public static Field max(Field f1, Field f2) {
        Field result = new Field(0, 0, 0);
        if (f1.value >= f2.value) {
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
