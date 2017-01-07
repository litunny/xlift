package osinnowo.com.xlift.model;

/**
 * Created by upperlink on 07/01/2017.
 */


public class EtaEstimate {

    private String display_name;
    private String ride_type;
    private Integer eta_seconds;
    private Boolean is_valid_estimate;

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String displayName) {
        this.display_name = displayName;
    }

    public String getRideType() {
        return ride_type;
    }

    public void setRideType(String rideType) {
        this.ride_type = rideType;
    }

    public Integer getEtaSeconds() {
        return eta_seconds;
    }

    public void setEtaSeconds(Integer etaSeconds) {
        this.eta_seconds = etaSeconds;
    }

    public Boolean getIsValidEstimate() {
        return is_valid_estimate;
    }

    public void setIsValidEstimate(Boolean isValidEstimate) {
        this.is_valid_estimate = isValidEstimate;
    }

}