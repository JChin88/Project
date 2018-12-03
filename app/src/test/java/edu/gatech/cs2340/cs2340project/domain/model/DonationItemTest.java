package edu.gatech.cs2340.cs2340project.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yumin Jeong
 */

@SuppressWarnings("deprecation")
public class DonationItemTest {

    private final List<DonationItem> donatedList = new ArrayList<>();
    private final List<DonationItem> donatedList2 = new ArrayList<>();
    private final List<DonationItem> donatedList3 = new ArrayList<>();
    private final List<DonationItem> donatedList4 = new ArrayList<>();

    private final Date A = new Date(2018-1900, 11,13);
    private final Date B = new Date(2018-1900, 11, 20);
    private final Date C = new Date(2018-1900, 11, 20);
    private final Date D = new Date(2018-1900, 12, 24);
    private final Date E = new Date(2019-1900, 5, 24);

    private final DonationItem itemA = new DonationItem(A, "name", "location", "item description",
            "November 13, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    private final DonationItem itemB = new DonationItem(B, "name", "location", "item description",
            "November 20, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    private final DonationItem itemC = new DonationItem(C, "name", "location", "item description",
            "November 20, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    private final DonationItem itemD = new DonationItem(D, "name", "location", "item description",
            "December 24, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    private final DonationItem itemE = new DonationItem(E, "name", "location", "item description",
            "May 24, 2019", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    /**
     * set up
     */
    @Before
    public void setup() {
        donatedList.add(itemC);
        donatedList.add(itemB);
        donatedList.add(itemE);
        donatedList.add(itemD);
        donatedList.add(itemA);

        donatedList2.add(itemA);

        donatedList3.add(itemC);
        donatedList3.add(itemB);

        donatedList4.add(itemA);
        donatedList4.add(itemA);
        donatedList4.add(itemA);
    }

    /**
     * test sort time
     */
    @Test
    public void testSortTime() {
        DonationItem.sortTime(donatedList);

        assertEquals(5, donatedList.size());
        assertSame(donatedList.get(0), itemA);
        assertSame(donatedList.get(1), itemB);
        assertSame(donatedList.get(2), itemC);
        assertSame(donatedList.get(3), itemD);
        assertSame(donatedList.get(4), itemE);
    }

    /**
     * test with one item
     */
    @Test
    public void testWithOneItem() {
        DonationItem.sortTime(donatedList2);

        assertEquals(1, donatedList2.size());
        assertSame(donatedList2.get(0), itemA);

    }

    /**
     * test in place
     */
    @Test
    public void testInPlace() {
        DonationItem.sortTime(donatedList3);

        assertEquals(2, donatedList3.size());
        assertSame(donatedList3.get(0), itemC);
        assertSame(donatedList3.get(1), itemB);
    }

    /**
     * test duplicate
     */
    @Test
    public void testDuplicates() {
        DonationItem.sortTime(donatedList4);

        assertEquals(3, donatedList4.size());
        for (int i =0; i < donatedList4.size(); i++) {
            assertSame(donatedList4.get(i), itemA);
        }
    }
}