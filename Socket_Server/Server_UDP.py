import socket
import CSV_WRITER as csv_writer
import json
class run:
    def __init__(self, port, ip):
        self.port = port
        self.ip = ip

    def run(self):



        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        sock.bind((self.ip, self.port))

        while True:
            try:

                data, addr = sock.recvfrom(1024)
                info = data
                info = info.decode('utf8').replace("'", '"')

                if info != None:
                    info = json.loads(info)
                    csv_writer.CSV_WRITER(info).construct()

            except ValueError:
                print("Something went wrong with:", Exception.args)