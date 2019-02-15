# GetWifiInfo
The proposal of this project is to offer a way to measure, using a smartphone, some information about the wifi status.  For now, you can only see the MAC, DATA RATE, BSSID, SSID and Signal strengh of your wifi connection. With these informations, you can monitor a roaming change or even a bandsteering. Off course that the software is really simple, for now, but, I will be adding more features in the near future.  There is the APK already compiled at this project, if you want to test.  APK is inside of the "debug" folder.

New Features:
- Get all wireless information with sequence time, and send to a specific IP SERVER, PORT, in a loop time (DELAY) as a JSON OBJECT (UDP traffic). Why that? to know if there was any data loss bettween the transition of the cellphone through the Access Points. 
