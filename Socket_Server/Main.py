import socket
import json
import CSV_WRITER as csv_writer
import Server_TCP as srvTcp
import Server_UDP as srvUdp


def main():


    UDP_IP = '0.0.0.0'
    PORT = 5000

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    sock.bind((UDP_IP, PORT))

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

main()