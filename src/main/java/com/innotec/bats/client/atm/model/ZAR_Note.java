package com.innotec.bats.client.atm.model;

/**
 * Created by phoenix on 7/18/16.
 */
public class ZAR_Note implements Comparable{
    public static final int R10 = 10;
    public static final int R20 = 20;
    public static final int R50 = 50;
    public static final int R100 = 100;
    public static final int R200 = 200;
    private int value;
    public ZAR_Note(int value) {
        if (!(value==R10 || value==R20 || value==R50 || value==R100 || value==R200))
            throw new IllegalArgumentException("Invalid note value");
        this.value = value;
    }
    public int getValue() {return value;}
    public int[] getPossibleValues() {
        return new int[] {R10,R20,R50,R100,R200};
    }
    @Override
    public int compareTo(Object other) {
        if (! (other instanceof ZAR_Note))
            throw new IllegalArgumentException("Comparison of incompatible types");
        return (value-((ZAR_Note) other).value);
    }
    @Override
    public boolean equals(Object other) {
        if (! (other instanceof ZAR_Note))
            return false;
        return value==((ZAR_Note) other).value;
    }
    @Override
    public String toString() {return "R" + Integer.toString(value) + ".00";}
}
