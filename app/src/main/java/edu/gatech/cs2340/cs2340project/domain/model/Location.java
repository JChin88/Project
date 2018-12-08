package edu.gatech.cs2340.cs2340project.domain.model;

import java.util.List;

/**
 * @author Hoa V Luu
 */
public class Location {

<<<<<<< HEAD
    //K key;;
    String _name;
    String _type;
    double _longitude;
    double _latitude;
    String _address;
    String _phoneNumber;
    String _website;
    List<DonationItem> _donationItemList;

=======
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
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public Location() {

    }

<<<<<<< HEAD
    public Location(String name, String type, double longtitue, double latitude, String address, String phoneNumber) {
        _name = name;
        _type = type;
        _longitude = longtitue;
        _latitude = latitude;
        _address = address;
        _phoneNumber = phoneNumber;
    }

    // Getter/Setter for name
=======
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
    public Location(String locationKey, String name, String type, double longitude, double latitude,
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
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public String getName() {
        return _name;
    }

    /**
     *
     * @param name location name
     */
    public void setName(String name) {
        _name = name;
    }

<<<<<<< HEAD
    // Getter/Setter for type
=======
    /**
     *
     * @return location type
     */
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public String getType() {
        return _type;
    }

    /**
     *
     * @param type type of location wanted to set
     */
    public void setType(String type) {
        _type = type;
    }

<<<<<<< HEAD
    // Getter/Setter for longtitude
    public double getLongtitude() {
        return _longitude;
    }

    public void setLongtitude(double longtitude) {
        _longitude = longtitude;
    }

    // Getter/Setter for latitude
=======
    /**
     *
     * @return longitude of location
     */
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
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public double getLatitude() {
        return _latitude;
    }

    /**
     *
     * @param latitude latitude wanted to set
     */
    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

<<<<<<< HEAD
    // Getter/Setter for address
=======
    /**
     *
     * @return location address
     */
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public String getAddress() {
        return _address;
    }

    /**
     *
     * @param address address wanted to set
     */
    public void setAddress(String address) {
        _address = address;
    }

<<<<<<< HEAD
    // Getter/Setter for phone number
=======
    /**
     *
     * @return phone number of location
     */
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public String getPhoneNumber() {
        return _phoneNumber;
    }

    /**
     *
     * @param phoneNumber phone number wanted to set
     */
    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

<<<<<<< HEAD

    // Getter/Setter for website
=======
    /**
     *
     * @return location website
     */
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public String getWebsite() {
        return _website;
    }

    public void setWebsite(String _website) {
        this._website = _website;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Location{" +
                "\n_name = '" + _name + '\'' +
                "\n _type = '" + _type + '\'' +
                "\n _longitude = " + _longitude +
                "\n _latitude = " + _latitude +
                "\n _address = '" + _address + '\'' +
                "\n _phoneNumber = " + _phoneNumber +
                "\n _website = '" + _website + '\'' +
                '}';
=======
    /**
     *
     * @param website website wanted to set for the location
     */
    public void setWebsite(String website) {
        this.website = website;
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    }
}
