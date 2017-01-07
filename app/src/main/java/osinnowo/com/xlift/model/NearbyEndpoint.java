package osinnowo.com.xlift.model;

import java.util.List;

/**
 * Created by upperlink on 07/01/2017.
 */


public class NearbyEndpoint {

    public List<NearbyDriver> nearby_drivers = null;

    public List<NearbyDriver> getNearbyDrivers() {
        return nearby_drivers;
    }

    public void setNearbyDrivers(List<NearbyDriver> nearbyDrivers) {
        this.nearby_drivers = nearbyDrivers;
    }

}