package api.DTO.assign;

import java.util.List;

public class AssignToDeviceData {

    private String offerId;
    private List<Pois> pois;


    public AssignToDeviceData(String offerId, List<Pois> pois) {
        this.pois =pois;
        this.offerId = offerId;
    }

    public AssignToDeviceData(List<Pois> pois) {
        this.pois =pois;

    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public List<Pois> getPois() {
        return pois;
    }

    public void setPois(List<Pois> pois) {
        this.pois = pois;
    }

}
