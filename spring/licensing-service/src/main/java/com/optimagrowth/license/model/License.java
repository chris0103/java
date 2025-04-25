package com.optimagrowth.license.model;

public class License {

    private int id;
    private String licenseId;
    private String organizationId;
    private String productName;
    private String licenseType;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", licenseId='" + licenseId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", productName='" + productName + '\'' +
                ", licenseType='" + licenseType + '\'' +
                '}';
    }
}
