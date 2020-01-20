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

def read_from_file(file_path):
    file = open(file_path, 'r', encoding='utf-8')
    params = file.read().splitlines()
    file.close()
    return params

def create_files_map(from_dir):
    result = {}
    for dirpath, subdirs, files in os.walk(from_dir,topdown=True):
        for x in files:
            if x.endswith(".java"):
                path = os.path.join(dirpath, x)
                num = find_problem_number_in_file(path)
                result[num] = {'path': path, 'name': x}

        # dirs[:] = [d for d in dirs if d not in folders_to_exclude]
        # local = [file for file in files if os.path.splitext(file)[1] in file_types]
        # for a in local:
        #     print(a)
        # for b in dirs:
        #     print(b)
        # result.extend(local)
    return result

def find_problem_number_in_file(file_path):
    file = open(file_path, 'r', encoding='utf-8')
    line = file.readline()
    next_line = False
    while line:
        if next_line:
            return line.split(" * ")[1].split('\n')[0]
        if line.startswith('/**'):
            next_line = True
        line = file.readline()
    file.close()

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
    
    rand_topic_ind = random_select(len(topics_map))
    rand_topic_key = list(topics_map)[rand_topic_ind]
    rand_topic_dir = main_dir + topics_map[rand_topic_key]

    result = create_files_map(rand_topic_dir)

    rand_problem_ind = random_select(len(topics_map))
    rand_problem = list(result)[rand_problem_ind]

    print(rand_topic_key + ': ' + rand_problem)