#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
import random
from datetime import *
from pathlib import Path

import util as u


class CoreService:
    root_path=str(Path(__file__).parent.absolute())+'/'
    is_session_finished = False

    topics_name_list = []
    topics_map = {}
    current_topic_ind = 0
    current_cant_solve = -1
    topic_to_problem_list_map = {}
    seen_topics = set()

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

    def create_problems_map_and_list(self, topic_name):

        from_dir = self.topics_map[topic_name]['path']
        excluded = self.topics_map[topic_name]['excluded']
        problems_list = []
        problems_map = {}
        files_with_errors = []

        for dirpath, subdirs, files in os.walk(from_dir, topdown=True):
            if dirpath in excluded:
                subdirs[:] = []
                files[:] = []
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

    def get_next_cant_solve(self):
        if self.cant_solve_mode:
            len_topics = len(self.cant_solves)
            if len_topics == 1:
                self.is_session_finished = True
            if len_topics > 0:
                self.current_cant_solve+=1
                return self.cant_solves[self.current_cant_solve]
            else:
                return "none"

    def get_next_topic(self):

        n = len(self.topics_name_list)

        selected = self.random_select(n)
        while selected in self.seen_topics:
            selected = self.random_select(n)
        self.seen_topics.add(selected)

        self.current_topic_ind += 1
        if self.current_topic_ind == n:
            self.is_session_finished = True

        return self.topics_name_list[selected]

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
            self.topics_map[topic_name]['path'] = self.main_dir + topic_path
            self.topics_map[topic_name]['excluded'] = [self.main_dir + a for a in x[2:]]

            pm, pl, err = self.create_problems_map_and_list(topic_name)
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

    def process_cant_solves(self):
        self.cant_solves=[]
        x = self.root_path + 'next_day_repeat.txt'
        todos = u.read_from_file(x)
        u.clear_file(x)
        for todo in todos:
            t = todo.split(",")
            local_data = []
            local_data.append(t[0])
            local_data.append(t[1])
            local_data.append(t[2])
            if datetime.utcnow() >= u.get_date(t[2]):
                self.cant_solves.append(local_data)
            else:
                u.add_line_to_file(x,todo)

        return len(self.cant_solves) > 0

    def __init__(self):

        self.process_parameters()

        self.cant_solve_mode = self.process_cant_solves()
        if self.cant_solve_mode:
            return

        is_invalid_path, errors = self.process_topics()
        if is_invalid_path:
            error_msg = ['==== ERROR IN FILES ====']
            self.init_errors = error_msg + errors
            return

        self.process_results()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        pass

'''
if __name__ == "__main__":
    c = CoreService()
    print(c.topic_to_problem_list_map) 
'''
