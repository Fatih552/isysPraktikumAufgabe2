public class Field {

    public final int x;
    public final int y;
    public final int value;

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String toString() {
        return "Koordinate: (" + x + "/" + y + ") value: " + value;
    }

}
