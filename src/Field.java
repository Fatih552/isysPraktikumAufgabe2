public class Field {

    public int x;
    public int y;
    public int value;
    public boolean lokal_max = false;

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String toString() {
        return "Koordinate: (" + x + "/" + y + ") value: " + value;
    }

    public void setLokal_max() {
        lokal_max = true;
    }

}
