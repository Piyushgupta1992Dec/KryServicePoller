package com.example.servicePoller.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
@PropertySource(value = "classpath:services.json")
@ConfigurationProperties
public class ServiceDetail {
   private String serviceUrl;
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="DD-MM-YYYY HH:mm:ss", timezone="CET")
   private Date createDateTime;
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="DD-MM-YYYY HH:mm:ss", timezone="CET")
   private Date updateDateTime;

    public ServiceDetail() {
    }

    public ServiceDetail(String serviceUrl, Date createDateTime, Date updateDateTime) {
        this.serviceUrl = serviceUrl;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
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
