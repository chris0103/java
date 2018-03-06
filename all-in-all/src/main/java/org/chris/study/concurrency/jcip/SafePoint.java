package org.chris.study.concurrency.jcip;

import com.google.common.base.Objects;

public class SafePoint {
	
    private int x;
    private int y;

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     * The original version of constructor which is not thread-safe. 
     */
    /*
    public SafePoint(SafePoint safePoint) {
        this(safePoint.x, safePoint.y);
    }
    */

    public synchronized int[] getXY() {
        return new int[] {x, y};
    }

    public synchronized void setXY(int x, int y) {
        this.x = x;
        // simulate some resource intensive work that starts EXACTLY at this point, causing a small delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        this.y = y;
    }

    public String toString() {
    	return Objects.toStringHelper(this.getClass()).add("X", x).add("Y", y).toString();
    }
    
    public static void main(String[] args) throws Exception {
    	
        final SafePoint originalSafePoint = new SafePoint(1, 1);

        // one Thread is trying to change this SafePoint
        new Thread(new Runnable() {
        	
            @Override
            public void run() {
                originalSafePoint.setXY(2, 2);
                System.out.println("Original: " + originalSafePoint.toString());
            }
        }).start();

        // the other Thread is trying to create a copy. The copy, depending on the JVM, MUST be either (1,1) or (2,2) depending on which Thread starts first, but it can not
        // be (1,2) or (2,1) for example.
        new Thread(new Runnable() {
        	
            @Override
            public void run() {
                // SafePoint copySafePoint = new SafePoint(originalSafePoint);	// using the non thread-safe constructor
                SafePoint copySafePoint = originalSafePoint.cloneSafePoint(originalSafePoint);	// using the thread-safe copy method
                System.out.println("Copy: " + copySafePoint.toString());
            }
        }).start();
    }
    
    /*
     * This is a refactored method, instead of a constructor.
     */
    public SafePoint cloneSafePoint(SafePoint originalSafePoint) {
         int [] xy = originalSafePoint.getXY();
         return new SafePoint(xy[0], xy[1]);
    }
    
    /*
     * The refactored constructor using the thread-safe getXY() method. 
     */
    public SafePoint(SafePoint safePoint) {
        this(safePoint.getXY());
    }
    
    // Create the constructor that uses the result of the thread-safe getXY() method, making it private for internal use ONLY.
    private SafePoint(int[] xy) {
        this(xy[0], xy[1]);
    }
}
