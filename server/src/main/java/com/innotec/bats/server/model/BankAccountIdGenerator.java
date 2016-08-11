package com.innotec.bats.server.model;
import com.innotec.bats.general.*;

import java.math.BigInteger;

/**
 * Created by phoenix on 8/3/16.
 */
public class BankAccountIdGenerator {
    /**
     * The different types of possible accounts
     */
    public enum AccountType {CURRENT,SAVINGS,CREDIT}
    /**
     * An identifier for a card number
     */
    private static String BANK_CARD_NO = "160";
    /**
     * An identifier for bank account number
     */
    private static String BANK_ACCT_NO = "27";
    /**
     * Identifiers for the different types of accounts
     */
    private static String CUR_ACC ="10", SAV_ACC ="20", CRED_ACC ="30";

    public static String nextAccountHolderCardNo(String lastUsed) {
        if (lastUsed==null || lastUsed.length()!=AccountHolderCard.CARD_NO_LEN) {
            return generateNewAccountHolderCardNo();
        } else {
            if (lastUsed.indexOf(BANK_CARD_NO) != 0)
                return generateNewAccountHolderCardNo();
            else
                return generateNextAccountHolderCardNo(lastUsed);
        }
    }
    static String generateNextAccountHolderCardNo(String lastUsed) {
        int cardNoLen=AccountHolderCard.CARD_NO_LEN;
        if (cardNoLen <= (BANK_CARD_NO.length()))
            return BANK_CARD_NO +randomDigits(cardNoLen- BANK_CARD_NO.length());
        String incr = lastUsed.substring(BANK_CARD_NO.length());
        return BANK_CARD_NO + incrementNumberString(incr);
    }
    static String generateNewAccountHolderCardNo() {
        int cardNoLen=AccountHolderCard.CARD_NO_LEN;
        if (cardNoLen > (BANK_CARD_NO.length()))
            return BANK_CARD_NO +randomDigits(cardNoLen- BANK_CARD_NO.length());
        else return null;
    }
    public static String nextCurrentAccountNo(String lastUsed) {
        return nextAccountNo(AccountType.CURRENT,lastUsed);
    }
    public static String nextCreditAccountNo(String lastUsed) {
        return nextAccountNo(AccountType.CREDIT,lastUsed);
    }
    public static String nextSavingsAccountNo(String lastUsed) {
        return nextAccountNo(AccountType.SAVINGS,lastUsed);
    }
    private static String nextAccountNo(AccountType accountType, String lastUsed) {
        int accNoLen = Account.ACCOUNT_NO_LEN;
        String accNo;
        if (lastUsed==null || lastUsed.length()!=accNoLen) {
            accNo = BANK_ACCT_NO+randomDigits(accNoLen-BANK_ACCT_NO.length());
        } else {
            String incr=lastUsed.substring(BANK_ACCT_NO.length());
            accNo = incrementNumberString(lastUsed);
        }
        switch (accountType) {
            case CREDIT:
                return BANK_ACCT_NO+accNo.substring(0,3) + CRED_ACC + accNo.substring(6,accNo.length()-1);
            case CURRENT:
                return BANK_ACCT_NO+accNo.substring(0,3) + CUR_ACC + accNo.substring(6,accNo.length()-1);
            case SAVINGS:
                return BANK_ACCT_NO+accNo.substring(0,3) + SAV_ACC + accNo.substring(6,accNo.length()-1);
            default:
                throw new Error("Code that should never run just disobeyed!!");
        }
    }
    public static String nextAdminCardNo(String lastUsed) {
        int cardNoLen = AdminCard.CARD_NO_LEN;
        if (lastUsed==null || lastUsed.length()!=cardNoLen) {
            return randomDigits(cardNoLen);
        } else {
            return incrementNumberString(lastUsed);
        }
    }
    public static String nextEmployeeNo(String lastUsed) {
        int employeeNoLen = Employee.EMPLOYEE_NO_LEN;
        if (lastUsed==null || lastUsed.length()!=employeeNoLen) {
            return randomDigits(employeeNoLen);
        } else {
            return incrementNumberString(lastUsed);
        }
    }
    /**
     * Returns an int that's between {@code low} and {@code high} inclusive.<br/>
     * Uses: {@code java.lang.Math.random()}<br/>
     * Precondition {@code low} <= {@code high}<br/>
     * @param low - the lower limit for the target random int.
     * @param high - the upper limit for the target random int.
     * @return a pseudo-random integer that lies between {@code low} and {@code high} (both inclusive).<br/>
     * @throws IllegalArgumentException if precondition isn't met.
     */
    private static int randomInt(int low, int high) throws IllegalArgumentException
    {
        if (low > high)
            throw new IllegalArgumentException("low > high -> args: "+low+","+high);

        return (low + (int) ((high - low + 1) * java.lang.Math.random()) );
    }

    /**
     *
     * @param len the length of the required string of digits.
     * @return a string of a randomly-generated int with the specified length.
     * NB: The lowest value is always {@code 10E(len-1), if len>0} instead of zero.
     */
    private static String randomDigits(int len) {
        if (len<=0) return "";
        if (len > 4) {
            return randomDigits(4)+randomDigits(len-4);
        }
        int low = (int)Math.pow(10,len-1);
        int high = (int)(Math.pow(10,len)-1);
        String digits = Integer.toString(randomInt(low,high));
        return digits;
    }
    private static String incrementNumberString(String numStr) {
        if (numStr==null)
            return "-1";
        String result=(new BigInteger(numStr).add(BigInteger.ONE)).toString();
        if (result.length() == numStr.length()) return result;
        else if (result.length() > numStr.length()) {
            return result.substring(result.length()-numStr.length());
        } else {
            String temp = numStr.replaceAll("[1-9]","0");
            return temp.substring(0,numStr.length()-result.length())+result;
        }
    }

    public static void main(String[] args) {
        System.out.println("****************Testa for BankAccountIdGenerator****************");
        declareTesting("randomDigits(len="+7+","+8+","+13+")");
        printOutcome(randomDigits(7));
        printOutcome(randomDigits(8));
        printOutcome(randomDigits(13));
        declareTesting("incrementNumberString() > args: " + "null, "+"12345678, "+"003923429, "+"9999");
        printOutcome(incrementNumberString(null));
        printOutcome(incrementNumberString("12345678"));
        printOutcome(incrementNumberString("003923429"));
        printOutcome(incrementNumberString("9999"));
        declareTesting("nextAccountHolderCardNo(null | '1234567' | '9999' | '1605850177078193')");
        printOutcome(nextAccountHolderCardNo(null));
        printOutcome(nextAccountHolderCardNo("1234567"));
        printOutcome(nextAccountHolderCardNo("9999"));
        printOutcome(nextAccountHolderCardNo("1605850177078193"));
        declareTesting("nextAccountNo(CURRENT, null | CURRENT, '1234567' | SAVINGS, '9999')");
        printOutcome(nextAccountNo(AccountType.CURRENT,null));
        printOutcome(nextAccountNo(AccountType.CURRENT,"1234567"));
        printOutcome(nextAccountNo(AccountType.SAVINGS,"9999"));
        declareTesting("nextCurrentAccountNo(null | '1234567' | '9999')");
        printOutcome(nextCurrentAccountNo(null));
        printOutcome(nextCurrentAccountNo("1234567"));
        printOutcome(nextCurrentAccountNo("9999"));
        declareTesting("nextCreditAccountNo(null | '1234567' | '9999')");
        printOutcome(nextCreditAccountNo(null));
        printOutcome(nextCreditAccountNo("1234567"));
        printOutcome(nextCreditAccountNo("9999"));
        declareTesting("nextSavingsAccountNo(null | '1234567' | '9999')");
        printOutcome(nextSavingsAccountNo(null));
        printOutcome(nextSavingsAccountNo("1234567"));
        printOutcome(nextSavingsAccountNo("9999"));
        declareTesting("nextEmployeeNo(null | '1234567' | '9999')");
        printOutcome(nextEmployeeNo(null));
        printOutcome(nextEmployeeNo("1234567"));
        printOutcome(nextEmployeeNo("9999"));
        System.out.println("****************End Testa***************************************");
    }
    private static void declareTesting(String methodName) {
        System.out.println("TESTING METHOD: "+methodName);
    }
    private static void printOutcome(Object obj) {
        System.out.println("\tOUTCOME: "+obj);
    }
}
