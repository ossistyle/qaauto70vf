package api.DTO.assign;

import java.util.List;

public class AssignToDeviceRequestBody {

    private AssignToDeviceData data;

    public AssignToDeviceRequestBody(List<Pois> pois) {
        this.data = new AssignToDeviceData( pois);
    }

    public AssignToDeviceRequestBody(String offerId, List<Pois> pois) {
        this.data = new AssignToDeviceData(offerId, pois);
    }

    public AssignToDeviceData getData() {
        return data;
    }

    public void setData(AssignToDeviceData data) {
        this.data = data;
    }
}
