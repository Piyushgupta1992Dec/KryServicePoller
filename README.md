This Service Poller application will load all the available service at startup and will provide you service status after doing get call to them.
 

**Refresh/Polling** on UI in every 1 minute that list will get refresh and it will udpate the service status in table.

**Add/Update** :- Before 1 min if any user add or modify any existing service that service table will again automatically get refreshed.


**Delete:-** If user delete any existing service from table then that particular service will be deleted from back end but on UI only the status will get change to FAIL.
Also if anyone delete teh service from back end then on UI that service status will get FAIL
In order to remove that deleted service from table we need to restart service beacuse on startup we get the list list of existing servces.

**prerequisite/Assumption**
To run this project you need to have Java, intelli, visual studio, angular install on your machine.

# **WorkSpcae Setup for service side Code**
1. You need to import peoject ServicePoller into intellij.

2. Run ServicePollerApplication.java

To verify service up and running hit get service status from chrom, service URL is:

http://localhost:7082/service/services

# **WorkSpcae Setup for UI side Code**
1. you need to import MY-SERVICE-POLLER project into visual studio.

2. Go to MY-SERVICE-POLLER folder from command prompt.

3. execute command - > ng serve

To verify UI is up and running hit UI url : http://localhost:4200/

###### **Limitation/Workaround**

My services are not of secure port(https) so you may get CORS error while UI gives call to service.

You have to open your chrome with below command.
    
    chrome.exe --disable-web-security --disable-gpu --user-data-dir=~/chromeTemp
 
Once chrome is open go to incognito mode and then run application (http://localhost:4200/).

by following above steps application will run smoothly and you will not get CORS error.

 