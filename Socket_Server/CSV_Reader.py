import csv
import os

path = os.getcwd()
for r, d, f in os.walk(path):
    for file in f:
        if '.csv' in file:
            with open(file, 'r') as csvfile:
                file = csv.reader(csvfile, delimiter=';', lineterminator='\n')
                time = []
                lastMac = ''
                for row in file:
                    try:

                        time.append(int(row[5]))
                        Lastsize = len(time)
                        # print(Lastsize)
                        if row[0] != lastMac:
                            # print("Trocou", row[0])
                            Firstsize = len(time) - 50
                            # print(Firstsize)
                        if Lastsize - Firstsize == 100:
                            Betweenlist = time[Firstsize:Lastsize]
                            while Betweenlist.count(0) > 0:
                                Betweenlist.remove(0)

                            # print("RANGE", Lastsize - Firstsize)
                            # print("BETWEEN", time[Firstsize:Lastsize], len(time[Firstsize:Lastsize]))
                            print("RESULT FROM FILE:",str(csvfile.name),"AVERAGE:",sum(Betweenlist) / len(Betweenlist))
                    except:
                        print("Something went wrong with:", Exception.args)
                        continue
                    lastMac = row[0]

