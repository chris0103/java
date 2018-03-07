package org.chris.quickstart;

import javax.swing.JFrame;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        new App().test();
    }

    public void test() {
        System.out.println(App.class.getClassLoader());
        JFrame f = new JFrame();
        f.setVisible(true);
        System.out.println(f.getClass().getClassLoader());
        SomeAppClass s = new SomeAppClass();
        System.out.println(s.getClass().getClassLoader());
    }
}

class SomeAppClass {

}
