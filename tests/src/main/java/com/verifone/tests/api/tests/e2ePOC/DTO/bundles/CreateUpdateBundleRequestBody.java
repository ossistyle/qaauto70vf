package com.verifone.tests.api.tests.e2ePOC.DTO.bundles;

import org.apache.commons.lang3.StringUtils;

public class CreateUpdateBundleRequestBody {
    private CreateUpdateBundleRequestData data;

    /**
     * @param bundleName
     * @param bundleDescription
     * @param bundleIcon
     * pass null or empty String values for the fields that should not be affected
     */

    public CreateUpdateBundleRequestBody(String bundleName, String bundleDescription, String status, String bundleIcon) {
        this.data = new CreateUpdateBundleRequestData();

        if (StringUtils.isNoneBlank(bundleName)) {
            this.data.setBundleName(bundleName);
        }
        if (StringUtils.isNoneBlank(bundleDescription)) {
            this.data.setBundleDesc(bundleDescription);
        }
        if (StringUtils.isNoneBlank(status)) {
            this.data.setStatus(status);
        }
        if (StringUtils.isNoneBlank(bundleIcon)) {
            this.data.setBundleIcon(bundleIcon);
        }
    }

    public CreateUpdateBundleRequestData getData() {
        return data;
    }

    public void setData(CreateUpdateBundleRequestData data) {
        this.data = data;
    }
}
