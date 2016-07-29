package com.innotec.bats.client.atm.model;

import javax.swing.JOptionPane; // for testing (main)
import java.util.Stack;

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
    /**
     * Simulates dispensing a specified amount to account holder.
     * The method first does a check to make sure the amount is available.
     * @param amount - the amount to be dispensed.
     * @return {@code true} if the balance was successfully reduced by the specified amount.
     * Otherwise {@code false} is returned.
     */
    public int[] dispense(int amount) {
        System.out.println("Attempting to dispense R"+amount+".00");
        int[] record = new int[notesValues.length];
        if (amount < 0 || amount > getBalance())
            return record;
        if (amount % R10 != 0)
            return record;
        if (dispense(amount, notesValues.length-1, record) > 0);

        return record;
    }

    /**
     * Calls dispense(int) but takes care of user giving a double as argument
     * @param amount - the amount to be dispensed.
     * @return an array containing the count of dispensed notes.
     */
    public int[] dispense(double amount) {
        if (amount == (int) amount)
            return dispense((int) amount);
        else throw new IllegalArgumentException("Invalid dispense amount");
    }
    /**
     * A helper to the dispenser class
     * @param amount the amount to try to dispense
     * @param startIndex the start index
     * @return {@code retVal} such that:
     * If {@code retVal} == 0, the funds were dispensed.
     * If {@code retVal} != 0, the funds weren't dispensed,
     * but (if they were) the ATM would have been shot by {@code retVal}.
     */
    private int dispense(int amount, int startIndex, int[] record) {
        if (startIndex == -1 || amount == 0) return amount;

        int curNoteValue = notesValues[startIndex];
        int curNoteCount = notesCount[startIndex];

        if (amount >= curNoteValue) {
            int multiples = amount/curNoteValue;
            if (curNoteCount < multiples)
                multiples = curNoteCount;
            int rem = dispense(amount-curNoteValue*multiples,startIndex-1, stack);
            if (rem==0) {
                notesCount[startIndex] -= multiples;
                stack.push(""+multiples+"*R"+notesValues[startIndex]);
                return 0;
            } else {
                return rem;
            }
        } else {
            stack.push("0*R"+notesValues[startIndex]);
            return dispense(amount,startIndex-1, stack);
        }
    }

    /**
     * Calculates the total sum of the notes within the dispenser.
     * @return the balance in the dispenser
     */
    public int getBalance() {
        int total = 0;
        for (int i = 0; i < notesCount.length; ++i)
            total += notesValues[i] * notesCount[i];
        return total;
    }
    public int getRemainingNoteCount(final int note) {
        for (int i = 0; i < notesValues.length; ++i) {
            if (note == notesValues[i])
                return notesCount[i];
        }
        throw new IllegalArgumentException("Invalid note amount! (Tip: Use Dispenser.R -constants)");
    }
    public static void main(String[] args) { // for testing
        Dispenser disp = new Dispenser();
        while (true) {
            String ans = JOptionPane.showInputDialog(null,"Balance: R" + disp.getBalance() + ".00\nEnter amount:","");
            if (ans == null)
                break;
            if (!disp.dispense(Double.parseDouble(ans)))
                System.out.println("Dispenser returned an error!");
            else {
                for (int i = 0; i < notesValues.length; ++i)
                    System.out.println("R"+notesValues[i]+": " + disp.notesCount[i]);
            }
        }
    }
}