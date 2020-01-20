package api.DTO.bundles;

import java.util.List;

public class BundlesData {
    private String id;
    private String eoOrgId;
    private String bundleName;
    private String bundleDesc;
    private String bundleIcon;
    private String status;
    private String oneTimePrice;
    private String recurringPrice;
    private List<String> assignedAppmarketAppIds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEoOrgId() {
        return eoOrgId;
    }

    public void setEoOrgId(String eoOrgId) {
        this.eoOrgId = eoOrgId;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleDesc() {
        return bundleDesc;
    }

    public void setBundleDesc(String bundleDesc) {
        this.bundleDesc = bundleDesc;
    }

    public String getBundleIcon() {
        return bundleIcon;
    }

    public void setBundleIcon(String bundleIcon) {
        this.bundleIcon = bundleIcon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOneTimePrice() {
        return oneTimePrice;
    }

    public void setOneTimePrice(String oneTimePrice) {
        this.oneTimePrice = oneTimePrice;
    }

    public String getRecurringPrice() {
        return recurringPrice;
    }

    public void setRecurringPrice(String recurringPrice) {
        this.recurringPrice = recurringPrice;
    }

    public List<String> getAssignedAppmarketAppIds() {
        return assignedAppmarketAppIds;
    }

    public void setAssignedAppmarketAppIds(List<String> assignedAppmarketAppIds) {
        this.assignedAppmarketAppIds = assignedAppmarketAppIds;
    }
}
