import csv
import os
import Jitter_Average
import Packet_loss
import sys, operator

path = os.getcwd()
def Report(info, filescount):
    try:

        listI = info
        AVERAGE = [listI["Average"]]
        ERRORS = [listI["Errors"]]
        FILENAME = [listI["Filename"]]
        JITTER = [listI["Jitter"]]

        zipList = zip(FILENAME, JITTER, AVERAGE, ERRORS)
        columns = ("Filename", "Jitter", "Suc Average", "Errors")
        if columns is None:
            return "Empty Columns"
        with open('Final_Report.csv', 'a') as csvfile:


            writer = csv.writer(csvfile, delimiter=';', lineterminator='\n')
            if filescount == 1:
                writer.writerow(columns)
            for row in zipList:
                writer.writerow(row)
        return 'Excel file created'

    except:
        print('error', sys.exc_info()[0], sys.exc_info()[1])

def run():
    count = 0
    for r, d, f in os.walk(path):
        for file in f:

            if '.csv' in file:

                with open(file, 'r') as csvfile:
                        count = count +1
                        file = csv.reader(csvfile, delimiter=';', lineterminator='\n')
                        file = sorted(file, key=lambda row: int(row[4]))
                        #print(sorted(file, key=operator.itemgetter(4)))
                        info = list(file)
                        try:
                            PL = Packet_loss.average(info).run()
                            JIResult = Jitter_Average.jitter(info).run()
                            Report({"Filename": str(csvfile.name) ,"Average": PL["Average"], "Errors": PL["Errors"], "Jitter": JIResult},count)


                            print("FILE_NAME:",str(csvfile.name),"PACKET_SUCCESS:", PL, "JITTER:", JIResult)
                        except Exception as detail:
                            print("Something went wrong with:", Exception.args, sys.exc_info()[0])
                            print(detail)
                            continue

