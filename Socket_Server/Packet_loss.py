import csv
import os
import sys





class average():

    def __init__(self, file):
        self.file = file

    def run(self):
        CalcSuccess = []
        Average = []
        time = []
        lastMac = ''
        ErrorsFound = 0
        SuccessFound = 0
        Betweenlist = 0
        for row in self.file:
            try:

                time.append(int(row[4]))
                Lastsize = len(time)
                if row[0] != lastMac:
                    Firstsize = len(time) - 50

                if Lastsize - Firstsize == 100:

                    Betweenlist = time[Firstsize:Lastsize]
                    firstValue = Betweenlist[0]

                    for i in range(len(Betweenlist)):
                        Differ = Betweenlist[i] - firstValue
                        if Differ <= 1:
                            firstValue = Betweenlist[i]
                            SuccessFound = SuccessFound + 1
                        else:
                            ErrorsFound = ErrorsFound + Differ
                            firstValue = Betweenlist[i]

                    CalcSuccess.append(SuccessFound*100/len(Betweenlist))
                    SuccessFound = 0


            except Exception as detail:

                print("Something went wrong with:", Exception.args, sys.exc_info()[0])

                print(detail,"PACKET")
                continue
            lastMac = row[0]
        Average = {"Average":round(sum(CalcSuccess) / len(CalcSuccess)), "Errors":ErrorsFound}
        return Average
