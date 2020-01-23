package api.DTO.device;

public class LicensesData {

    private Integer licensesUsed;
    private Integer licensesAvailable;

    public LicensesData(Integer licensesUsed, Integer licensesAvailable) {
        this.licensesUsed = licensesUsed;
        this.licensesAvailable = licensesAvailable;
    }

    public Integer getLicensesUsed() {
        return licensesUsed;
    }

    public void setLicensesUsed(Integer licensesUsed) {
        this.licensesUsed = licensesUsed;
    }

    public Integer getLicensesAvailable() {
        return licensesAvailable;
    }

    public void setLicensesAvailable(Integer licensesAvailable) {
        this.licensesAvailable = licensesAvailable;
    }
}
