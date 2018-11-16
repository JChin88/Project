package edu.gatech.cs2340.cs2340project.domain.model;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

/**
 * Donation item POJO
 */
@IgnoreExtraProperties
public class DonationItem {

    public enum DonationItemCategory{
        CLOTHES ("Clothes"), HAT ("Hat"), KITCHEN ("Kitchen"),
        ELECTRONICS ("Electronics"), HOUSEHOULD ("Household"), OTHER ("Other");

        private final String category;

        DonationItemCategory(String category) {
            this.category = category;
        }

        /**
         * getter for string
         * @return String value of DI category
         */
        public String getCategory() {
            return category;
        }
    }

    @ServerTimestamp
    private Date timeStamp;
    private String donationItemName;
    private String locationName;
    private String shortDescription;
    private String fullDescription;
    private double value;
    private DonationItemCategory category;
    private String comments;
//    //Picture picture

    //Use Control + Return to make Getter/Setter

    /**
     * Default constructor need for firestore
     */
    public DonationItem() {
        //empty constructor needed for firestore
    }

    /**
     * Constructor for firestore
     * @param timeStamp time stamp when donation item added
     * @param donationItemName donation item name
     * @param locationName location this donation item belong
     * @param shortDescription short description of the item
     * @param fullDescription full description of the item
     * @param value value of the item
     * @param category the category of the item
     * @param comments the comments of the item
     */
    public DonationItem(Date timeStamp, String donationItemName, String locationName,
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

    /**
     * Get time stamp
     * @return the time stamp of item
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

//    /**
//     * Set timestamp
//     * @param timeStamp
//     */
//    public void setTimeStamp(Date timeStamp) {
//        this.timeStamp = timeStamp;
//    }

    /**
     *
     * @return the donation item name
     */
    public String getDonationItemName() {
        return donationItemName;
    }

//    public void setDonationItemName(String donationItemName) {
//        this.donationItemName = donationItemName;
//    }

    /**
     *
     * @return the location this donation item belong to
     */
    public String getLocationName() {
        return locationName;
    }

//    public void setLocationName(String _locationName) {
//        this.locationName = _locationName;
//    }

    /**
     *
     * @return the short description of item
     */
    public String getShortDescription() {
        return shortDescription;
    }

//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }

    /**
     *
     * @return the full description of item
     */
    public String getFullDescription() {
        return fullDescription;
    }

//    public void setFullDescription(String fullDescription) {
//        this.fullDescription = fullDescription;
//    }

    /**
     *
     * @return the value of description of item
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param value value of item
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @return category of item
     */
    public DonationItemCategory getCategory() {
        return category;
    }

    /**
     *
     * @param _category item category
     */
    public void setCategory(DonationItemCategory _category) {
        this.category = _category;
    }

    /**
     *
     * @return comments of item
     */
    public String getComments() {
        return comments;
    }

//    public void setComments(String comments) {
//        this.comments = comments;
//    }

    /** Sorts the list of donated items in the order that they were donated to the location.
     *  If two (or more) items were donated at the same time, they stay the in the order that they
     *  were entered into the array.
     * @param itemList list of donated items
     */
    public static void sortTime(List<DonationItem> itemList) {

        int length = itemList.size();

        for (int i = 0; i < length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < length; j++) {
                if (itemList.get(minIndex).getTimeStamp().compareTo(itemList.get(j)
                        .getTimeStamp()) > 0) {
                    minIndex = j;
                }
            }

            DonationItem temp = itemList.get(i);
            itemList.set(i, itemList.get(minIndex));
            itemList.set(minIndex, temp);

        }
    }

}
