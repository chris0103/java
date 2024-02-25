package org.chris.study.instrumentation;

import java.lang.instrument.Instrumentation;

public class MyFirstAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("JavaAgent Started!");
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent main called, this should be accessed via Attach API.");
    }
}
