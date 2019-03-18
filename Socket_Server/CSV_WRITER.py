
import csv
import sys


import ChangeManagement as ChangeMonitor
LastMac = ChangeMonitor.check().MacStore()
LastTime = ChangeMonitor.check().TimeStore()
# from datetime import datetime
# from time import mktime

class CSV_WRITER:

    def __init__(self, info='', ip=''):
        self.info = info
        self.ip = ip

    # def timestamp(self):
    #     dt = datetime.now()
    #     sec_since_epoch = mktime(dt.timetuple()) + dt.microsecond / 1000000.0
    #     millis = sec_since_epoch * 1000
    #     return millis

    def construct(self):

        try:

            listI = self.info
            LBSSID = [listI["BSSID"]]
            LRSSI = [listI["Signal"]]
            LSSID = [listI["SSID"]]
            LSEQ = [listI["sequence"]]
            LFREQ = [listI["frequency"]]
            NewTime = ChangeMonitor.check().timestamp()
            differtime = ChangeMonitor.check(LastTime, NewTime).GetDifTime(1)
            ChangeMonitor.check( 0, 0, LastMac, listI["BSSID"]).MacChange(differtime)
            LDIFF = [round(differtime)]

            zipList = zip(LBSSID, LRSSI, LSSID, LFREQ, LSEQ, LDIFF)
            columns = ("BSSID", "RSSI", "SSID", "Frequency", "Sequency", "Delay")
            if columns is None:
                return "Empty Columns"
            with open('result.csv', 'a') as csvfile:
                writer = csv.writer(csvfile, delimiter=';', lineterminator='\n')
                for row in zipList:
                    writer.writerow(row)
            return 'Excel file created'

        except:
             print('error', sys.exc_info()[0], sys.exc_info()[1])







