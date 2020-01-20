#!/usr/bin/python
# -*- coding: utf-8 -*-

#### features:
## timer
## record best result, display percentage
## calculate weekly overall across all subjects
## display which subject requires more attention (bad results)
## give more problems for subjects which require more attention
## set countdown to the interviews
## smh use restrictions in the stats calculations -
# easy solved < 5 min, med < 10 min, hard < 15 min -
# these are the problems that already ve been solved so it s ok

#### func
# parse java files in folders rec
# parse problem number from the top of the file
# randomly generate a number

import os

import random

folders_to_exclude = ['ds']
file_types = ['.java']

def read_from_file(file_name):
    file = open(file_name, 'r', encoding='utf-8')
    params = file.read().splitlines()
    file.close()
    return params

def collect_files(from_dir):
    result = []
    for root, dirs, files in os.walk(from_dir,topdown=True):
        dirs[:] = [d for d in dirs if d not in folders_to_exclude]
        local = [file for file in files if os.path.splitext(file)[1] in file_types]
        result.extend(local)
    return result

def random_select(n):
    return random.randint(0, n - 1)

if __name__ == "__main__":
    params = read_from_file('parameters.txt')
    params_map = { x[0] : x[1] for x in [param.split(",") for param in params] }
    #print(params_map)
    
    main_dir = params_map['main_dir']
    #print(main_dir)
    
    topics = read_from_file('input.txt')
    topics_map = { x[0] : x[1] for x in [topic.split(",") for topic in topics] }
    #print(topics_map)
    
    rand_ind = random_select(len(topics_map))
    rand_key = list(topics_map)[rand_ind]
    rand_dir = main_dir + topics_map[rand_key]

    result = collect_files(rand_dir)
    for f in result:
        print(f)

    
