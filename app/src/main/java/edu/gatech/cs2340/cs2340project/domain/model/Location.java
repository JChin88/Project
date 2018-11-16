package edu.gatech.cs2340.cs2340project.domain.model;

import java.util.List;

public class Location {

    protected String locationKey;
    protected String name;
    protected String type;
    protected double longitude;
    protected double latitude;
    protected String address;
    protected String phoneNumber;
    protected String website;
    List<DonationItem> _donationItemList;

    public Location() {

    }

    public Location(String locationKey, String name, String type, double longitude, double latitude,
                    String address, String phoneNumber, String website) {
        this.locationKey = locationKey;
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    public Location(String locationKey, String name, String type, double longitude, double latitude,
                    String address, String phoneNumber, String website, List<DonationItem> itemList) {
        this(locationKey, name, type, longitude, latitude, address, phoneNumber, website);
        this._donationItemList = itemList;
    }

    public DonationItem findItem(String itemName) {
        for(DonationItem item : this._donationItemList) {
            if(item.getDonationItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public String getLocationKey() {
        return locationKey;
    }

    public void setLocationKey(String locationKey) {
        this.locationKey = locationKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
