package com.verifone.tests.api.tests.e2ePOC.DTO.bundles;

public class CreateUpdateBundleRequestData {
    private String bundleName;
    private String bundleDesc;
    private String bundleIcon;

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
}
