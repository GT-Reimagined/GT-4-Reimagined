from os import listdir,rename
import argparse

parser = argparse.ArgumentParser(description='Model renamer.')
parser.add_argument('file')
res = parser.parse_args()
file = res.file

folders = {"assembler", "disassembler", "macerator", "universal_macerator", "watermill"}


for folder in folders:
    path = file + "/" + folder + "/"
    rename(path + "south.json", path + "south.jsonn")
    rename(path + "north.json", path + "south.json")
    rename(path + "south.jsonn", path + "north.json") 
