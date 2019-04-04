import socket
import json
import CSV_WRITER as csv_writer
import Server_UDP as srvUdp
import argparse
import sys
import CSV_Collector as collector

def check_arg(args=None):
    parser = argparse.ArgumentParser(description='Basic Functions')
    parser.add_argument('-m', '--mode',
                        help='Get act',
                        required='True',
                        default='server')

    results = parser.parse_args(args)
    return (results.mode
            )




def main():
    m = check_arg(sys.argv[1:])
    print('MMM', m)
    if m == 'server':
        srvUdp.run(5000, '0.0.0.0').run()
    elif m == 'report':
        collector.run()
main()