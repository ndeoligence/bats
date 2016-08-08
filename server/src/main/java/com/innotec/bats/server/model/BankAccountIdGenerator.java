package com.innotec.bats.server.model;
import com.innotec.bats.general.*;

/**
 * Created by phoenix on 8/3/16.
 */
public class BankAccountIdGenerator {

    public enum AccountType {CURRENT,SAVINGS,CREDIT}
    private static String CUR_ACC ="10", SAV_ACC ="20", CRED_ACC ="30";

    public static String nextAccountHolderCardNo(String lastUsed) {
        int cardNoLen = AccountHolderCard.CARD_NO_LEN;
        if (lastUsed==null || lastUsed.length()!=cardNoLen) {
            return randomDigits(cardNoLen);
        } else {
            return incrementNumberString(lastUsed);
        }
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
            accNo = randomDigits(accNoLen);
        } else {
            accNo = incrementNumberString(lastUsed);
        }
        switch (accountType) {
            case CREDIT:
                return accNo.substring(0,3) + CRED_ACC + accNo.substring(6,accNo.length()-1);
            case CURRENT:
                return accNo.substring(0,3) + CUR_ACC + accNo.substring(6,accNo.length()-1);
            case SAVINGS:
                return accNo.substring(0,3) + SAV_ACC + accNo.substring(6,accNo.length()-1);
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
        int low = (int)Math.pow(10,len-1);
        int high = (int)(Math.pow(10,len)-1);
        String digits = Integer.toString(randomInt(low,high));
        return digits;
    }
    private static String incrementNumberString(String numStr) {
        String result = Integer.toString((new Integer(numStr))+1);
        if (result.length() == numStr.length()) return result;
        else if (result.length() > numStr.length()) {
            return result.substring(result.length()-numStr.length());
        } else {
            String temp = numStr.replaceAll("[1-9]","0");
            return temp.substring(0,numStr.length()-result.length())+result;
        }
    }
}
