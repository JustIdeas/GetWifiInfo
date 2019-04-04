import csv
import os
import sys

path = os.getcwd()

class jitter():

    def __init__(self, file):
        self.file = file

    def run(self):
            Average = []
            time = []
            lastMac = ''
            for row in self.file:

                try:
                    time.append(int(row[5]))
                    Lastsize = len(time)
                    if row[0] != lastMac:
                        Firstsize = len(time) - 50
                    if Lastsize - Firstsize == 100:
                        Betweenlist = time[Firstsize:Lastsize]
                        while Betweenlist.count(0) > 0:
                            Betweenlist.remove(0)
                        Average.append(sum(Betweenlist) / len(Betweenlist))


                except Exception as detail:
                    print("Something went wrong with:", Exception.args, sys.exc_info()[0])
                    print(detail,"JITTER")
                    continue
                lastMac = row[0]
            Average = sum(Average)/len(Average)
            return round(Average)

