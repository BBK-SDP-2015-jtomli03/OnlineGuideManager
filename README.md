# An Online Programme Guide — developed with AngularJS and JAX-RS.

### **Please run in safari**

##How to Run

I deployed the project as a war file on apache-tomcat-8.0.24. 

1) The index.jsp (restful client for uploading the xml file) should open automatically upon successful deployment of the OnlineGuideManager. If not it should be found at http://localhost:8080 (or whichever port the application is deployed on). 

Before uploading a file, open a different tab in the browser and open the online programme guide for the bond channel http://localhost:8080/onlineSchedule/
This should display “No programmes available” but it will keep checking the server automatically via http get requests for updates.

2) Go back to the file upload page and upload the xml file provided in the folder. Once uploaded you should get a confirmation that the upload has been successful. 

3) Go back to the tab for the onlineSchedule and you will see that the guide has been updated automatically according to the schedule given by the xml file. Now if you click on a movie, you will see the pop-up box displayed stating that the particular movie is set to record.


