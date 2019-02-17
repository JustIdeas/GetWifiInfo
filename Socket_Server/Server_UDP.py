import socket

class run:
    def __init__(self, port, ip):
        self.port = port
        self.ip = ip

    def main(self):
        try:

            print("port:", self.port,
                  "ip:", self.ip)
            sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

            sock.bind((str(self.ip), int(self.port)))

            while True:
                data, addr = sock.recvfrom(1024)
                info = data
                print ("Message Received:", info)
                if info.decode() == "Ok":
                    send = sock.sendto(data, addr)
                    print ("foi")
                else:
                    print("Não Foi")
        except:
            print("Something went wrong with this:", Exception(), SystemError(), BaseException().args())
