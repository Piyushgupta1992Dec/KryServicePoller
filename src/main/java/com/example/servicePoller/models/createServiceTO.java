package com.example.servicePoller.models;

import java.util.Date;

public class createServiceTO {
    private String serviceUrl;
    private String serviceName;
    private Date createDateTime;
    private Date updateDateTime;

    public createServiceTO() {
    }

    public createServiceTO(String serviceUrl, String serviceName, Date createDateTime, Date updateDateTime) {
        this.serviceUrl = serviceUrl;
        this.serviceName = serviceName;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
