package com.innotec.bat.atm.model;
import java.util.*;

/**
 * Created by phoenix on 7/18/16.
 */
public class Dispenser {
    /**
     * The count of slots of different notes within the dispenser.
     * Essentially a count of the different possible notes
     * In the case of ZAR_Note,
     * this just counts notes from R10 to R200
     */
    static public final int COUNT_SLOT = 5;
    /**
     * The number of each note initially. reset() uses this value to figure out how many of each note to reset to.
     */
    static private int COUNT_NOTES_DEF = 500;
    /**
     * Contains pointers to the notes.
     */
    private Object[] slots = new Object[COUNT_SLOT];
    public Dispenser() {
        reset();
    }
    private void reset() {
        slots = new
    }
}
