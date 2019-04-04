import CSV_WRITER as info
from datetime import datetime
from time import mktime

class check():
    def __init__(self, LasTime=0, NewTime=0, LastMAC='', NewMAC=''):
        self.LasTime = LasTime
        self.NewTime = NewTime
        self.LastMAC = LastMAC
        self.NewMAC = NewMAC

    def GetDifTime(self,method=0):

        if method == 1:
            newvalue = self.NewTime - self.LasTime
            info.LastTime = self.NewTime
            return (newvalue)
        info.LastTime = self.NewTime

    def MacChange(self, timeBetween):

        if (self.LastMAC != self.NewMAC) and self.LastMAC != None:
            info.LastMac = self.NewMAC
            print("Changed from ", self.LastMAC, "to ", self.NewMAC, "Delay: ", timeBetween)
        else:
            info.LastMac = self.NewMAC


    def MacStore(self):
        mac = 0
        return mac

    def TimeStore(self):
        time = 0
        return time

    def timestamp(self):
        dt = datetime.now()
        sec_since_epoch = mktime(dt.timetuple()) + dt.microsecond / 1000000.0
        millis = sec_since_epoch * 1000
        return millis

