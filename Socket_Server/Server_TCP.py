import socket


class run:
    def __init__(self, port, ip):
        self.port = port
        self.ip = ip

    def main(self):
        try:
            print("Address port received:", self.port
                  , "Address IP received:", self.ip)
            BUFFER_SIZE = 20

            tcpSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            tcpSock.bind((self.ip, int(self.port)))
            tcpSock.listen(1)

            conn, addr = tcpSock.accept()
            print ("connection address:", addr)

            while True:
                data = conn.recv(BUFFER_SIZE)
                array = data.decode()
                if not data: break
                print ("Message Received:", array)
                conn.send(data)
            conn.close()
        except:
            print("something went wrong with:", Exception)


