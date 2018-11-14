package edu.gatech.cs2340.cs2340project.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class DonationItemTest {

    private List<DonationItem> donatedList = new ArrayList<DonationItem>();
    private List<DonationItem> donatedList2 = new ArrayList<DonationItem>();
    private List<DonationItem> donatedList3 = new ArrayList<DonationItem>();
    private List<DonationItem> donatedList4 = new ArrayList<DonationItem>();

    Date A = new Date(2018-1900, 11,13);
    Date B = new Date(2018-1900, 11, 20);
    Date C = new Date(2018-1900, 11, 20);
    Date D = new Date(2018-1900, 12, 24);
    Date E = new Date(2019-1900, 5, 24);

    DonationItem itemA = new DonationItem(A, "name", "location", "item description",
            "November 13, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    DonationItem itemB = new DonationItem(B, "name", "location", "item description",
            "November 20, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    DonationItem itemC = new DonationItem(C, "name", "location", "item description",
            "November 20, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    DonationItem itemD = new DonationItem(D, "name", "location", "item description",
            "December 24, 2018", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

    DonationItem itemE = new DonationItem(E, "name", "location", "item description",
            "May 24, 2019", 1, DonationItem.DonationItemCategory.CLOTHES, "comment");

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

    @Test
    public void testSortTime() {
        DonationItem.sortTime(donatedList);

        assertTrue(donatedList.size() == 5);
        assertTrue(donatedList.get(0) == itemA);
        assertTrue(donatedList.get(1) == itemB);
        assertTrue(donatedList.get(2) == itemC);
        assertTrue(donatedList.get(3) == itemD);
        assertTrue(donatedList.get(4) == itemE);
    }

    @Test
    public void testWithOneItem() {
        DonationItem.sortTime(donatedList2);

        assertTrue(donatedList2.size() == 1);
        assertTrue(donatedList2.get(0) == itemA);

    }

    @Test
    public void testInPlace() {
        DonationItem.sortTime(donatedList3);

        assertTrue(donatedList3.size() == 2);
        assertTrue(donatedList3.get(0) == itemC);
        assertTrue(donatedList3.get(1) == itemB);
    }

    @Test
    public void testDuplicates() {
        DonationItem.sortTime(donatedList4);

        assertTrue(donatedList4.size() == 3);
        for (int i =0; i < donatedList4.size(); i++) {
            assertTrue(donatedList4.get(i) == itemA);
        }
    }
}