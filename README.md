# GetWifiInfo
The proposal of this project is to offer a way to measure, using a smartphone, some information about the wifi status.  For now, you can only see the MAC, DATA RATE, BSSID, SSID and Signal strengh of your wifi connection. With these informations, you can monitor a roaming change or even a bandsteering. Off course that the software is really simple, for now, but, I will be adding more features in the near future.  There is the APK already compiled at this project, if you want to test.  APK is inside of the "debug" folder.

New Features:
- Get all wireless information with sequence time, and send to a specific IP SERVER, PORT, in a loop time (DELAY) as a JSON OBJECT (UDP traffic). Why that? to know if there was any data loss bettween the transition of the cellphone through the Access Points. 

With the last update of this project, now we are able to run the Socket Server, that is a python  project, and using the APP in a smartphone, capture all data on the server side. The server will receive the information and save all of it in a CSV file. 
There is a example CSV result on the project, showing what we can do with the information captured.

Executing the Main() of the Socket server, with option "-m server", it will continue acting as a server.
when use the option "-m report", it will get all csv file inside of it's folder, and get the average of jitter, packet loss and error lost, all between the exchange from one ap to another.

to the app side, I added a new field, called Test Name. that field is use to give a name to the CSV file that the socket server will create when starts the test.
