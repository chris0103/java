package chap05.control;

public class TestWithReturn {

    static int test(int testVal, int target) {
        if (testVal > target)
            return +1;
        else if (testVal < target)
            return -1;
        else
            return  0;
    }

    public static void main(String[] args) {
        System.out.println(test(10, 5));
        System.out.println(test(5, 10));
        System.out.println(test(5, 5));
    }
}
