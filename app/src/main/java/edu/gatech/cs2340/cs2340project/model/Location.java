package edu.gatech.cs2340.cs2340project.model;

public class Location {

    //K key;;
    String _name;
    String _type;
    double _longitude;
    double _latitude;
    String _address;
    String _phoneNumber;
    String _website;

    public Location() {

    }

    public Location(String name, String type, double longtitue, double latitude, String address, String phoneNumber) {
        _name = name;
        _type = type;
        _longitude = longtitue;
        _latitude = latitude;
        _address = address;
        _phoneNumber = phoneNumber;
    }

    // Getter/Setter for name
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    // Getter/Setter for type
    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    // Getter/Setter for longtitude
    public double getLongtitude() {
        return _longitude;
    }

    public void setLongtitude(double longtitude) {
        _longitude = longtitude;
    }

    // Getter/Setter for latitude
    public double getLatitude() {
        return _latitude;
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

    // Getter/Setter for address
    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    // Getter/Setter for phone number
    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }


    // Getter/Setter for website
    public String getWebsite() {
        return _website;
    }

    public void setWebsite(String _website) {
        this._website = _website;
    }

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
    }
}
