#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
import random

import util as u


class CoreService:
    main_dir: str

    is_session_finished = False

    topics_name_list = []
    topics_map = {}
    current_topic_ind = 0
    topic_to_problem_list_map = {}

    results = []

    @staticmethod
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

    @staticmethod
    def random_select(n):
        return random.randint(0, n - 1)

    def create_problems_map_and_list(self, from_dir):
        problems_list = []
        problems_map = {}
        for dirpath, subdirs, files in os.walk(from_dir, topdown=True):
            for x in files:
                if x.endswith(".java"):
                    path = os.path.join(dirpath, x)
                    num = self.find_problem_number_in_file(path)
                    problems_list.append(num)
                    problems_map[num] = {'path': path, 'name': x}

        return problems_map, problems_list

    def get_next_topic(self):
        ret = self.topics_name_list[self.current_topic_ind]
        self.current_topic_ind += 1
        if self.current_topic_ind == len(self.topics_name_list):
            self.is_session_finished = True
        return ret

    def get_random_problem(self, t):
        set_latest_problems = set()
        problems_count = len(self.topic_to_problem_list_map[t])

        for result in self.results:
            result_data = result.split(",")
            if result_data[0] == t and problems_count > 0:
                set_latest_problems.add(result_data[1])
                problems_count -= 1

        rand_problem_ind = self.random_select(problems_count)
        while self.topic_to_problem_list_map[t][rand_problem_ind] in set_latest_problems:
            print(rand_problem_ind)
            rand_problem_ind = self.random_select(problems_count)

        rand_problem = self.topic_to_problem_list_map[t][rand_problem_ind]
        return rand_problem.strip()

    def process_results(self):
        self.results = u.read_from_file('results.txt')

    def process_topics(self):
        topics = u.read_from_file('input.txt')
        for x in [topic.split(",") for topic in topics]:
            topic_name = x[0]
            topic_path = x[1]

            self.topics_name_list.append(topic_name)

            self.topics_map[topic_name] = {}
            self.topics_map[topic_name]['path'] = topic_path

            pm, pl = self.create_problems_map_and_list(self.main_dir + topic_path)
            self.topics_map[topic_name]['problems'] = pm
            self.topic_to_problem_list_map[topic_name] = pl

    def process_parameters(self):
        params = u.read_from_file('parameters.txt')
        params_map = {x[0]: x[1] for x in [param.split(",") for param in params]}
        self.main_dir = params_map['main_dir']

    def __init__(self):
        self.process_parameters()
        self.process_topics()
        self.process_results()
