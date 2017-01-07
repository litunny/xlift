package osinnowo.com.xlift.model;

import java.util.ArrayList;

/**
 * Created by upperlink on 07/01/2017.
 */

public class EtaEstimateEndpoint {
    private ArrayList<EtaEstimate> eta_estimates = null;

    public ArrayList<EtaEstimate> getEtaEstimates() {
        return eta_estimates;
    }

    public void setEtaEstimates(ArrayList<EtaEstimate> etaEstimates) {
        this.eta_estimates = etaEstimates;
    }
}
