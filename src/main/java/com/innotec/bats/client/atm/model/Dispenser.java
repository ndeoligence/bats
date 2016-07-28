package com.innotec.bats.client.atm.model;

import java.util.*;

/**
 * Created by phoenix on 7/18/16.
 */
public class Dispenser {
    /**
     * The number of each note initially. {@code reset()} uses this value to figure out how many of each note to reset to.
     */
    private static int DEF_SLOT_SIZE = 500;
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
        slots = new ArrayList<>(notes.length);
        for (int i = 0; i < slots.size(); ++i) {
            slots.add(new ArrayList<ZAR_Note>(DEF_SLOT_SIZE));
            for (int j = 0; j < DEF_SLOT_SIZE; ++j) {
                slots.get(j).add(notes[j]);
            }
        }

    }
    public boolean dispense(double amount) { // takes care of user giving a double as argument
        if (amount == (int) amount)
            return dispense((int) amount);
        else throw new IllegalArgumentException("Invalid dispense amount");
    }

    /**
     * Simulates dispensing a specified amount to account holder.
     * The method first does a check to make sure the amount is available.
     * @param amount - the amount to be dispensed.
     * @return {@code true} if the balance was successfully reduced by the specified amount.
     * Otherwise {@code false} is returned.
     */
    public boolean dispense(int amount) {
        if (amount > getBalance())
            return false;
        int count;
        for (int i = 0; amount > 0; ++i) {

        }
        return true;
    }
    public double getBalance() {
        double total = 0;
        for (int i = 0; i < notes.length; ++i)
            total += notes[i].getValue() * slots.get(i).size();
        return total;
    }

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
        public static int[] getPossibleValues() {
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

    public static void main(String[] args) {
        Dispenser disp = new Dispenser();
        System.out.println("Dispenser bal = " + disp.getBalance());
    }
}
