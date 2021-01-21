import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
servicesList : any[] = [];
headers : any[] = ['Service Name', 'Service URL', 'Created Date Time', 'Updated Date Time', 'Service Status'];
Columns : any[] = ['serviceName', 'serviceUrl', 'createDateTime', 'updateDateTime', 'status'];
slist;
tempStatus= {};

set servicesStatusList(ipValue) {
  this.slist = ipValue;
  if (ipValue){
    const tempServiceList = [];
    this.servicesList.forEach(res => {
      tempServiceList.push({...res, status : this.slist[res.serviceName]});
    });
    this.servicesList = tempServiceList;
  }  
}

get servicesStatusList() {
  return this.slist;
}

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.refreshData();
    setInterval(() => {
      this.refreshData();
    }, 60000);
  }

  add(){
    let serviceName = document.getElementById('txtServiceName');
    let serviceUrl= document.getElementById('txtServiceUrl');
    let payload = {'serviceName': serviceName['value'], 'serviceUrl': serviceUrl['value']};
    this.addService(payload).subscribe((status: any) => {
      if(status.status === 'OK')
        this.refreshData();});
  }

  update(){
    let serviceName = document.getElementById('txtServiceName');
    let serviceUrl= document.getElementById('txtServiceUrl');
    let payload = {'serviceName': serviceName['value'], 'serviceUrl': serviceUrl['value'], 'createDateTime' : new Date()};
    this.updateService(payload).subscribe((status: any) => {
      if(status.status === 'OK')
        this.refreshData();});
  }

  delete(deleteService){
    this.deleteService(deleteService).subscribe((status: any) => {
      if(status.status === 'OK')
        this.refreshData();});
  }

  getAllServices() {
    return this.http.get('http://localhost:7082/service/services');
  }

  addService(payload) {
    return this.http.post('http://localhost:7082/service/services', payload);
  }

  deleteService(serviceName) : Observable<any> {
    return this.http.delete(`http://localhost:7082/service/services/${serviceName}`);
  }

  updateService(payload) {
    return this.http.put('http://localhost:7082/service/services', payload);
  }

  getServicesStatus(serviceName) {
    return this.http.get(`http://localhost:7082/service/services/${serviceName}`);
  }

  refreshData() {
    this.servicesList = [];
    this.getAllServices().subscribe((response: any) => {
      Object.keys(response).forEach((res) => {
        this.servicesList.push({ ...response[res], serviceName: res });
        this.tempStatus[res] = '';
        this.getServicesStatus(res).subscribe((status: any) => {
          this.tempStatus[res] = status.status;
          this.servicesStatusList = this.tempStatus;
        });
      });
    });
  }
}




