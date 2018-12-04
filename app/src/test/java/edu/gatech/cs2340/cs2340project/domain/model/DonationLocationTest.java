package edu.gatech.cs2340.cs2340project.domain.model;

import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jonathan Chin
 */
public class DonationLocationTest {

    /**
     * test find item
     */
    @Test
    public void findItem() {
        List<DonationItem> itemList = new LinkedList<>();
        DonationItem lochNess = new DonationItem(new Date(), "Loch-Ness Monster", "location",
                "sdesc", "fdesc", 3.50,
                DonationItem.DonationItemCategory.OTHER, "comments");
        DonationItem belt = new DonationItem(new Date(), "belt", "location",
                "sdesc", "fdesc", 4.20,
                DonationItem.DonationItemCategory.CLOTHES, "comments");
        DonationItem pants = new DonationItem(new Date(), "pants", "location",
                "sdesc", "fdesc", 69.69,
                DonationItem.DonationItemCategory.CLOTHES, "comments");
        itemList.add(lochNess);
        itemList.add(belt);
        itemList.add(pants);
        DonationLocation location = new DonationLocation("loc", "location", "type",
                0.0, 0.0, "address", "phone", "website",
                itemList);

        DonationItem result;
        //find Loch-Ness Monster
        result = location.findItem("Loch-Ness Monster");
        assertEquals(result, lochNess);

        //find belt
        result = location.findItem("belt");
        assertEquals(result, belt);

        //find pants
        result = location.findItem("pants");
        assertEquals(result, pants);

        //item not in list
        result = location.findItem("dummy");
        assertNull(result);
    }
}