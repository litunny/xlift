package osinnowo.com.xlift.model;

import java.util.List;

/**
 * Created by upperlink on 07/01/2017.
 */



public class NearbyDriver {

    private List<Driver> drivers = null;
    private String ride_type;

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public String getRideType() {
        return ride_type;
    }

    public void setRideType(String rideType) {
        this.ride_type = rideType;
    }

}