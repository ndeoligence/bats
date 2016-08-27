package com.innotec.bats.client.atm.admin.model;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by phoenix on 7/18/16.
 */
public class Dispenser {
	/** Note constants */
	public static final int R10 = 10, R20 = 20, R50 = 50, R100 = 100, R200 = 200;

	/**
	 * The number of each note initially. {@code reset()} uses this value to
	 * figure out how many of each note to reset to.
	 */
	private static int DEF_SLOT_SIZE = 500;
	/**
	 * Contains all the possible notes in an array. This will serve as a count
	 * of how many notes there are in total as well as their values.
	 */
	private static final int[] notesValues = { R10, R20, R50, R100, R200 };
	/** Keeps count of how many notes remain in the dispenser */
	private int[] notesCount;

	public Dispenser() {
		reset();
	}

	/**
	 * Simulates dispensing a specified amount to account holder. The method
	 * first does a check to make sure the amount is available.
	 * 
	 * @param amount
	 *            - the amount to be dispensed.
	 * @return an array containing a record of how many of each note was
	 *         dispensed. Returns null if nothing was dispensed, due to
	 *         insufficient funds in dispenser.
	 * @throws IllegalArgumentException
	 *             if the requested amount is impossible to dispense.
	 */
	public int[] dispense(int amount) {
		System.out.println("Attempting to dispense R" + amount + ".00");
		int[] record = new int[notesValues.length];
		if ((amount < 0) || (amount % R10 != 0))
			throw new IllegalArgumentException("Illegal amount entered + R" + amount + ".00");
		if (amount > getBalance())
			return null;
		if (dispense(amount, notesValues.length - 1, record) > 0)
			return null;

		return record;
	}

	/**
	 * Calls dispense(int) but takes care of user giving a double as argument
	 * 
	 * @param amount
	 *            - the amount to be dispensed.
	 * @return an array containing the count of dispensed notes.
	 */
	public int[] dispense(double amount) {
		if (amount == (int) amount)
			return dispense((int) amount);
		else
			throw new IllegalArgumentException("Invalid dispense amount");
	}

	/**
	 * Calculates the total sum of the notes within the dispenser.
	 * 
	 * @return the balance in the dispenser
	 */
	public int getBalance() {
		int total = 0;
		for (int i = 0; i < notesCount.length; ++i)
			total += notesValues[i] * notesCount[i];
		return total;
	}

	/**
	 * Gives a count of how many of each note still remains in the Dispenser.
	 * 
	 * @return an array containing a count of each note, in the order they
	 *         appear in within {@code notesValues}.
	 */
	public int[] getNotesCount() {
		return Arrays.copyOf(notesCount, notesCount.length);
	}

	/**
	 * Method works as though it returns a single (specified) entry from
	 * {@code getNotesCount()}.
	 * 
	 * @param note
	 *            - the note whose count is being enquired.
	 * @return the number of individual R[{@code note}] notes remaining.
	 */
	public int getSingleNoteCount(final int note) {
		for (int i = 0; i < notesValues.length; ++i) {
			if (note == notesValues[i])
				return notesCount[i];
		}
		throw new IllegalArgumentException("Invalid note amount! (Tip: Use Dispenser.R -constants)");
	}

	/**
	 * A helper to the dispense method.
	 * 
	 * @param amount
	 *            the amount to try to dispense
	 * @param startIndex
	 *            the start index
	 * @return {@code retVal} such that: If {@code retVal == 0}, the funds were
	 *         dispensed. If {@code retVal != 0}, the funds weren't dispensed,
	 *         but (if they were) the ATM would have been shot by {@code retVal}
	 *         .
	 */
	private int dispense(int amount, int startIndex, int[] record) {
		if (startIndex == -1 || amount == 0)
			return amount;

		int curNoteValue = notesValues[startIndex];
		int curNoteCount = notesCount[startIndex];

		if (amount >= curNoteValue) {
			int multiples = amount / curNoteValue;
			if (curNoteCount < multiples)
				multiples = curNoteCount;
			int rem = dispense(amount - curNoteValue * multiples, startIndex - 1, record);
			if (rem == 0) {
				notesCount[startIndex] -= multiples;
				record[startIndex] = multiples;
				return 0;
			} else {
				return rem;
			}
		} else {
			record[startIndex] = 0;
			return dispense(amount, startIndex - 1, record);
		}
	}

	private void reset() {
		notesCount = new int[notesValues.length];
		for (int i = 0; i < notesCount.length; ++i)
			notesCount[i] = DEF_SLOT_SIZE;
	}

	public static void main(String[] args) { // one main() to test them all!
		Dispenser disp = new Dispenser();
		while (true) {
			String ans = JOptionPane.showInputDialog(null, "Balance: R" + disp.getBalance() + ".00\nEnter amount:", "");
			if (ans == null)
				break;

			int[] record = disp.dispense(Double.parseDouble(ans));
			if (record != null) {
				System.out.println("Dispensed notes:");
				for (int i = 0; i < record.length; ++i) {
					if (record[i] > 0)
						System.out.println("\t" + record[i] + " * R" + notesValues[i]);
				}
			}
			System.out.println("Balances:");
			for (int i = 0; i < notesValues.length; ++i)
				System.out.println("\tR" + notesValues[i] + ": " + disp.notesCount[i]);
		}
	}
}
