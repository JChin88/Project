package edu.gatech.cs2340.cs2340project.domain.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

/**
 * @author Hoa V Luu
 */
public class DonationLocation extends BaseObservable {

    private String locationKey;
    private String name;
    private String type;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private String website;
    private List<DonationItem> _donationItemList;

    /**
     * default constructor for firestore
     */
    public DonationLocation() {

    }

    /**
     * constructor pojo
     * @param locationKey key of location
     * @param name name of location
     * @param type type of location
     * @param longitude longitude of location
     * @param latitude latitude of location
     * @param address address of lcoation
     * @param phoneNumber phone number of location
     * @param website website of location
     */
    public DonationLocation(String locationKey, String name, String type, double longitude, double latitude,
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

    /**
     * Constructor for location
     * @param locationKey key of location
     * @param name name of location
     * @param type type of location
     * @param longitude longitude of location
     * @param latitude latitude of location
     * @param address address of lcoation
     * @param phoneNumber phone number of location
     * @param itemList list of item for that location
     * @param website website of location
     */
    public DonationLocation(String locationKey, String name, String type, double longitude, double latitude,
                            String address, String phoneNumber, String website, List<DonationItem> itemList) {
        this(locationKey, name, type, longitude, latitude, address, phoneNumber, website);
        this._donationItemList = itemList;
    }

    /**
     * find item in firestore
     * @param itemName location name
     * @return a donation item in the firestore
     */
    public DonationItem findItem(String itemName) {
        for(DonationItem item : this._donationItemList) {
            if(item.getDonationItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     *
     * @return location  key
     */
    @Bindable
    public String getLocationKey() {
        return locationKey;
    }

    /**
     *
     * @param locationKey location key wanted to set
     */
    public void setLocationKey(String locationKey) {
        this.locationKey = locationKey;
    }

    /**
     *
     * @return location name
     */
    @Bindable
    public String getName() {
        return name;
    }

    /**
     *
     * @param name location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return location type
     */
    @Bindable
    public String getType() {
        return type;
    }

    /**
     *
     * @param type type of location wanted to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return longitude of location
     */
    @Bindable
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude longitude of location wanted to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return latitude of location
     */
    @Bindable
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude latitude wanted to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return location address
     */
    @Bindable
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address address wanted to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return phone number of location
     */
    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber phone number wanted to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return location website
     */
    @Bindable
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website website wanted to set for the location
     */
    public void setWebsite(String website) {
        this.website = website;
    }
}
