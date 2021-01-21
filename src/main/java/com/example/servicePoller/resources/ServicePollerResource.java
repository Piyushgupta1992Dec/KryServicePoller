package com.example.servicePoller.resources;

import ch.qos.logback.core.CoreConstants;
import com.example.servicePoller.models.ServiceDetail;
import com.example.servicePoller.models.createServiceTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/service")
public class ServicePollerResource {

    private File jsonFile = new File("src/main/resources/services.json");

    private ObjectMapper mapper = new ObjectMapper();

    private Map<String,ServiceDetail> ServicesMap = new HashMap<String,ServiceDetail>();

    private Map<String,ServiceDetail> ServicesMapTransaction = new HashMap<String,ServiceDetail>();

    @PostConstruct
    public void postConstruct() {
        try {
            ServicesMap = mapper.readValue(jsonFile, Map.class);
            ServicesMapTransaction = mapper.readValue(jsonFile, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin
    @GetMapping("/services")
    public Map<String,ServiceDetail> getAllServices() {
        return ServicesMap;
    }

    @GetMapping("/servicesCurrent")
    public Map<String,ServiceDetail> getAllCurrentServices() {
        return ServicesMapTransaction;
    }

    @GetMapping("/services/{serviceName}")
    public Map<String,String> getServiceDetail(@PathVariable("serviceName") String serviceName) {
            if(ServicesMapTransaction.get(serviceName) != null){
                return sendResponse("OK");
            } else {
                return sendResponse("FAIL");
            }
    }

    @PostMapping("/services")
    public Map<String,String>  insertService(@RequestBody createServiceTO payload) {
        Date date = new Date();
        ServicesMap.put(payload.getServiceName(), new ServiceDetail(payload.getServiceUrl(), date, date));
        ServicesMapTransaction.put(payload.getServiceName(), new ServiceDetail(payload.getServiceUrl(), date, date));
        writeJsonFile(jsonFile, ServicesMapTransaction);
        return sendResponse("OK");
    }

    @DeleteMapping("/services/{serviceName}")
    public Map<String,String> deleteEmployee(@PathVariable("serviceName") String serviceName) {
        ServicesMapTransaction.remove(serviceName);
        writeJsonFile(jsonFile, ServicesMapTransaction);
        return sendResponse("OK");
    }

    @PutMapping("/services")
    public Map<String,String>  insertOrUpdateService(@RequestBody createServiceTO payload) {
        Date date = new Date();
        ServicesMap.put(payload.getServiceName(), new ServiceDetail(payload.getServiceUrl(), payload.getCreateDateTime(), date));
        ServicesMapTransaction.put(payload.getServiceName(), new ServiceDetail(payload.getServiceUrl(), payload.getCreateDateTime(), date));
        writeJsonFile(jsonFile, ServicesMapTransaction);
        return sendResponse("OK");
    }

    public String writeJsonFile(File jsonFile, Map<String,ServiceDetail> ServicesMapTransaction){
        try {
            mapper.writeValue(jsonFile, ServicesMapTransaction);
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public Map<String,String> sendResponse(String response){
        Map<String,String> statusMap = new HashMap<String,String>();
        statusMap.put("status",response);
        return statusMap;
    }
}
