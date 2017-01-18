package com.innotec.bats.general;

import org.junit.Test;

/**
 * Created by phoenix on 12/31/16.
 */
public class AccountHolderTest {
    @Test
    public void testToString() {
        AccountHolder ah = new AccountHolder("John", "von Neumann", "1234567890123", "oso doaosdf as", "0120984834");
        System.out.println(ah);
        ah.setCard(new Card("1234567890", "1234", true));
        System.out.println(ah);
    }
}
