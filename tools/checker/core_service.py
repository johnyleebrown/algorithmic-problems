#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
import random
from pathlib import Path

import util as u


class CoreService:
    root_path=str(Path(__file__).parent.absolute())+'/'
    is_session_finished = False

    topics_name_list = []
    topics_map = {}
    current_topic_ind = 0
    topic_to_problem_list_map = {}

    results = []
    init_errors = []

    @staticmethod
    def find_problem_number_in_file(file_path):

        file = open(file_path, 'r', encoding='utf-8')
        line = file.readline()
        next_line = False

        while line:
            if next_line:
                file.close()
                return line.split(" * ")[1].split('\n')[0]
            if line.startswith('/**'):
                next_line = True
            line = file.readline()

        return -1

    @staticmethod
    def random_select(n):
        return random.randint(0, n - 1)

    def create_problems_map_and_list(self, from_dir):

        problems_list = []
        problems_map = {}
        files_with_errors = []

        for dirpath, subdirs, files in os.walk(from_dir, topdown=True):
            subdirs[:] = [d for d in subdirs if d not in ['ds']]
            for x in files:
                if x.endswith(".java"):
                    path = os.path.join(dirpath, x)
                    num = self.find_problem_number_in_file(path)
                    if num == -1:
                        files_with_errors.append(x)
                        continue
                    problems_list.append(num)
                    problems_map[num] = {'path': path, 'name': x}

        return problems_map, problems_list, files_with_errors

    def get_next_topic(self):

        ret = self.topics_name_list[self.current_topic_ind]

        self.current_topic_ind += 1
        if self.current_topic_ind == len(self.topics_name_list):
            self.is_session_finished = True

        return ret

    def get_random_problem(self, t, results):

        total_problem_count = len(self.topic_to_problem_list_map[t])

        # create a list of all problems
        all_problems = []
        for result in results:
            result_data = result.split(",")
            if result_data[0] == t:
                all_problems.append(result_data[1])

        # generate a set of problems to avoid
        set_latest_problems = set()
        x = len(all_problems)
        if x != total_problem_count:
            while x % total_problem_count > 0:
                set_latest_problems.add(all_problems[x - 1])
                x -= 1

        rand_problem_ind = self.random_select(total_problem_count)
        while self.topic_to_problem_list_map[t][rand_problem_ind] in set_latest_problems:
            rand_problem_ind = self.random_select(total_problem_count)

        rand_problem = self.topic_to_problem_list_map[t][rand_problem_ind]
        return rand_problem.strip()

    def process_results(self):
        self.results = u.read_from_file(self.root_path + 'results.txt')

    def process_topics(self):

        topics = u.read_from_file(self.root_path + 'input.txt')
        
        problem_found = False
        not_valid_files = []

        for x in [topic.split(",") for topic in topics]:
            topic_name = x[0]
            topic_path = x[1]

            self.topics_name_list.append(topic_name)

            self.topics_map[topic_name] = {}
            self.topics_map[topic_name]['path'] = topic_path

            pm, pl, err = self.create_problems_map_and_list(self.main_dir + topic_path)
            if err:
                not_valid_files.extend(err)
                problem_found = True
                continue

            if problem_found:
                continue

            self.topics_map[topic_name]['problems'] = pm
            self.topic_to_problem_list_map[topic_name] = pl

        return problem_found, not_valid_files

    def process_parameters(self):
        params = u.read_from_file(self.root_path + 'parameters.txt')
        params_map = {x[0]: x[1] for x in [param.split(",") for param in params]}
        self.main_dir = params_map['main_dir']

    def __init__(self):
        self.process_parameters()
        is_invalid_path, errors = self.process_topics()
        if not is_invalid_path:
            self.process_results()
        else:
            error_msg=['==== ERROR IN FILES ====']
            self.init_errors = error_msg + errors

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        pass
"""
if __name__ == "__main__":
    c = CoreService()
"""

