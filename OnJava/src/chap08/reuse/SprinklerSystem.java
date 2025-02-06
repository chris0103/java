package chap08.reuse;

class WaterSource {

    private String s;

    WaterSource() {
        System.out.println("WaterSource()");
        s = "Constructed";
    }

    @Override
    public String toString() {
        return s;
    }
}

// Composition for code reuse
public class SprinklerSystem {

    private String valve1, valve2, valve3, valve4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;

    @Override
    public String toString() {
        return "value1 = " + valve1 + " "
                + "value2 = " + valve2 + " "
                + "value3 = " + valve3 + " "
                + "value4 = " + valve4 + "\n"
                + "i = " + i + " " + "f = " + f + " "
                + "source = " + source;
    }

    public static void main(String[] args) {
        SprinklerSystem sprinklers = new SprinklerSystem();
        System.out.println(sprinklers);
    }
}
