package edu.gatech.cs2340.cs2340project.domain.model;

import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void findItem() {
        List<DonationItem> itemList = new LinkedList<>();
        DonationItem lochNess = new DonationItem(new Date(), "Loch-Ness Monster", "location",
                "sdesc", "fdesc", 3.50,
                DonationItem.DonationItemCategory.OTHER, "comments");
        DonationItem belt = new DonationItem(new Date(), "belt", "location",
                "sdesc", "fdesc", 4.20,
                DonationItem.DonationItemCategory.CLOTHES, "comments");
        itemList.add(new DonationItem(new Date(), "pants", "location",
                "sdesc", "fdesc", 69.69,
                DonationItem.DonationItemCategory.CLOTHES, "comments");
        Location location = new Location("loc", "location", "type",
                0.0, 0.0, "address", "phone", "website",
                itemList);

        //find Loch-Ness Monster
    }
}