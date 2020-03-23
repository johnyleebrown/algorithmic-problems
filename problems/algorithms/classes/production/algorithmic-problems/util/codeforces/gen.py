#!/usr/bin/python
# -*- coding: utf-8 -*-

import pyperclip
from pathlib import Path

# generates codeforces answer from Main class
# adds InputReader and other dependencies

src = '/Volumes/Greg/projects/algorithmic-problems/problems/algorithms/src'
cur=str(Path(__file__).parent.absolute())+'/'
imports='import java.io.*;\nimport java.lang.*;\nimport java.math.*;\nimport java.util.*;\n'

def touch_open(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)

def read_from_file(file_path):
    file = touch_open(file_path, 'r+', encoding='utf-8')
    params = file.read()
    file.close()
    return params

def parse_dep(line):
    return line.split(' ')[1].split(';')[0]

def get_path(dep):
    ret = src
    for s in dep.split('.'):
        ret+='/'+s
    return ret+'.java'
    
def get_dep_data(dep):
    dep_data = read_from_file(get_path(dep))
    ind = dep_data.find('public class')
    return dep_data[ind:]

def parse_cl():
    cl = read_from_file(cur+'Main.java')
    # rem last }
    lb = cl.rfind('}')
    cl = cl[:lb]
    lines = cl.split('\n')
    res = ''
    extra = ''
    i = 0
    while i < len(lines):
        if lines[i].startswith('package'):
            res+=imports
            i+=2
        elif lines[i].startswith('import'):
            if not lines[i].split(' ')[1].startswith('java'):
                dep = parse_dep(lines[i])
                dep_data = get_dep_data(dep)
                extra+='\n'+dep_data
                i+=1
            else:
                i+=1
        elif len(lines[i].strip()) != 0:
            res+=lines[i]+'\n'
            i+=1
        else:
            i+=1
    res+=extra+'\n}'
    return res

def copy_cl():
    pyperclip.copy(parse_cl())

copy_cl()
