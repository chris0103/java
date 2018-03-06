package org.chris.study.concurrency.jcip.examples.chap04;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.*;

/**
 * Listing 4.9. Delegating thread safety to multiple underlying state variables.
 */
public class VisualComponent {
	
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
