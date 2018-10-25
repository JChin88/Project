package edu.gatech.cs2340.cs2340project.domain.model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class DonationItem {

    public enum DonationItemCategory{
        CLOTHES ("Clothes"), HAT ("Hat"), KITCHEN ("Kitchen"),
        ELECTRONICS ("Electronics"), HOUSEHOULD ("Household"), OTHER ("Other");

        private final String category;

        DonationItemCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

//    private Timestamp timeStamp;
    private String timeStamp;
    private String donationItemName;
    private String locationName;
//    private Location location;
    private String shortDescription;
    private String fullDescription;
    private double value;
    private DonationItemCategory category;
    private String comments;
//    //Picture picture

    //Use Control + Return to make Getter/Setter

    public DonationItem() {
        //empty constructor needed for firestore
    }

    public DonationItem(String timeStamp, String donationItemName, String locationName,
                        String shortDescription, String fullDescription, double value,
                        DonationItemCategory category, String comments) {
        this.timeStamp = timeStamp;
        this.donationItemName = donationItemName;
        this.locationName = locationName;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

//    public DonationItem (int timeStampLocation, String donationItemName, String locationName,
//                         String shortDescription, String fullDescription,
//                         double value, DonationItemCategory category, String comments) {
//        this.timeStampLocation = timeStampLocation;
//        this.donationItemName = donationItemName;
//        this.locationName = locationName;
////        this.location = location;
//        this.shortDescription = shortDescription;
//        this.fullDescription = fullDescription;
//        this.value = value;
//        this.category = category;
//        this.comments = comments;
//    }


//    public Timestamp getTimeStamp() {
//        return timeStamp;
//    }

//    public void setTimeStamp(String timeStamp) {
//        long time = Long.parseLong(timeStamp);
//        Timestamp newTimestamp = new Timestamp(new Date(time));
//        this.timeStamp = newTimestamp;
//    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDonationItemName() {
        return donationItemName;
    }

    public void setDonationItemName(String donationItemName) {
        this.donationItemName = donationItemName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String _locationName) {
        this.locationName = _locationName;
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location _location) {
//        this.location = _location;
//    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DonationItemCategory getCategory() {
        return category;
    }

    public void setCategory(DonationItemCategory _category) {
        this.category = _category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
