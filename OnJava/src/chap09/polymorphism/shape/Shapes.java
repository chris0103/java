package chap09.polymorphism.shape;

// Polymorphism in Java
public class Shapes {

    public static void main(String[] args) {
        RandomShapes gen = new RandomShapes();
        for (Shape shape : gen.array(9)) {
            shape.draw();
        }
    }
}
