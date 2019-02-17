
import csv
import sys
import ast
import time
import re


class CSV_WRITER:

    def __init__(self, info='', ip=''):
        self.info = info
        self.ip = ip
    def construct(self):

        try:

            listI = self.info

            LBSSID = [listI["BSSID"]]
            LRSSI = [listI["Signal"]]
            LSSID = [listI["SSID"]]
            LSEQ = [listI["sequence"]]
            LFREQ = [listI["frequency"]]



            print(LBSSID, LRSSI, LSSID, LFREQ, LSEQ)
            zipList = zip(LBSSID, LRSSI, LSSID, LFREQ, LSEQ)
            print(zipList)
            columns = ("BSSID", "RSSI", "SSID", "Frequency", "Sequency")
            if columns is None:
                return "Empty Columns"
            with open('result.csv', 'a') as csvfile:
                writer = csv.writer(csvfile, delimiter=';', lineterminator='\n')
                # writer.writerow(columns)
                for row in zipList:
                    print(row)
                    writer.writerow(row)
            return 'Excel file created'

        except:
             print('error', sys.exc_info()[0], sys.exc_info()[1])





