package edu.gatech.cs2340.cs2340project.domain.model;

public class DonationItem {

    public enum DonationItemCategory{
        CLOTHES, HAT, KITCHEN, ELECTRONICS, HOUSEHOULD, OTHER
    }

    private int timeStampLocation;
    private String _locationName;
    private Location _location;
    String shortDescription;
    String fullDescription;
    double value;
    DonationItemCategory _category;
    String comments;
    //Picture picture

    //Use Control + Return to make Getter/Setter

    public int getTimeStampLocation() {
        return timeStampLocation;
    }

    public void setTimeStampLocation(int timeStampLocation) {
        this.timeStampLocation = timeStampLocation;
    }

    public String get_locationName() {
        return _locationName;
    }

    public void set_locationName(String _locationName) {
        this._locationName = _locationName;
    }

    public Location get_location() {
        return _location;
    }

    public void set_location(Location _location) {
        this._location = _location;
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
        return _category;
    }

    public void setCategory(DonationItemCategory _category) {
        this._category = _category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
