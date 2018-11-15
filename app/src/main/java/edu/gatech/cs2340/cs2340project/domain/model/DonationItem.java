package edu.gatech.cs2340.cs2340project.domain.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
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

    private @ServerTimestamp Date timeStamp;
    private String donationItemName;
    private String locationName;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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
                if (itemList.get(minIndex).getTimeStamp().compareTo(itemList.get(j).getTimeStamp()) > 0) {
                    minIndex = j;
                }
            }

            DonationItem temp = itemList.get(i);
            itemList.set(i, itemList.get(minIndex));
            itemList.set(minIndex, temp);

        }
    }

}
