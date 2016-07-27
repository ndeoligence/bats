package com.innotec.bats.client.atm.model;

import java.util.*;

/**
 * Created by phoenix on 7/18/16.
 */
public class Dispenser {
    /**
     * The count of slots of different notes within the dispenser.
     * Essentially a count of the different possible notes
     * In the case of ZAR_Note,
     * this just counts distinct notes from R10 to R200
     */
    // static public final int COUNT_SLOT = 5;
    /**
     * The number of each note initially. reset() uses this value to figure out how many of each note to reset to.
     */
    static private int COUNT_NOTES_DEF = 500;
    /**
     * Contains pointers to the notes.
     */
    private ArrayList<ArrayList<ZAR_Note>> slots;
    private static ZAR_Note[] notes = {
        new ZAR_Note(ZAR_Note.R10),
        new ZAR_Note(ZAR_Note.R20),
        new ZAR_Note(ZAR_Note.R50),
        new ZAR_Note(ZAR_Note.R100),
        new ZAR_Note(ZAR_Note.R200),
    };

    public Dispenser() {
        reset();
    }
    private void reset() {
        slots = new ArrayList<>(COUNT_SLOT);
        for (int i = 0; i < COUNT_SLOT; ++i) {
            slots.add(new ArrayList<ZAR_Note>(COUNT_NOTES_DEF));
        }

    }
    public boolean dispense(double amount) {
        if (amount == (int) amount)
            return dispense((int) amount);
        else throw new IllegalArgumentException("Invalid dispense amount");
    }
    public boolean dispense(int amount) {
        return false;
    }
    public double getBalance() {return 0.0;}

    private static class ZAR_Note implements Comparable{
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
}
