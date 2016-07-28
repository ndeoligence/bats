package com.innotec.bats.client.atm.model;

import javax.swing.JOptionPane; // for testing (main)

/**
 * Created by phoenix on 7/18/16.
 */
public class Dispenser {
    /**
     * The number of each note initially. {@code reset()} uses this value to figure out how many of each note to reset to.
     */
    private static int DEF_SLOT_SIZE = 500;

    /**Note constants*/
    public static final int R10=10, R20=20, R50=50, R100=100, R200=200;
    private static final int[] notesValues = {
            R10, R20, R50, R100, R200
    };

    private int[] notesCount;
    public Dispenser() {
        reset();
    }
    private void reset() {
        notesCount = new int[notesValues.length];
        for (int i = 0; i < notesCount.length; ++i)
            notesCount[i] = DEF_SLOT_SIZE;
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
        if (amount % R10 != 0)
            return false;
        int rem = amount;
        for (int i = notesValues.length-1; i >= 0; --i) {
            return dispense(amount, notesValues.length-1) == 0;
        }
        return true;
    }
    private int dispense(int amount, int startIndex) {
        if (startIndex == -1 || amount == 0) return amount;

        int curNoteValue = notesValues[startIndex];
        int curNoteCount = notesCount[startIndex];

        if (amount >= curNoteValue) {
            int multiples = amount/curNoteValue;
            if (curNoteCount < multiples)
                multiples = curNoteCount;

            int rem = dispense(amount-curNoteValue*multiples,startIndex-1);
            if (rem==0) {
                notesCount[startIndex] -= multiples;
            } else {

            }
        } else {
            return dispense(amount,startIndex-1);
        }
    }
    public int getBalance() {
        int total = 0;
        for (int i = 0; i < notesCount.length; ++i)
            total += notesValues[i] * notesCount[i];
        return total;
    }

    public static void main(String[] args) { // for testing
        Dispenser disp = new Dispenser();
        double amount;
        while (true) {
            String ans = JOptionPane.showInputDialog(null,"Enter amount:","");
            if (ans == null)
                break;
            if (!disp.dispense(Double.parseDouble(ans)))
                System.out.println("Dispenser balance insufficient!");
            System.out.println("Balance: R" + disp.getBalance() + ".00");
        }
    }
}
