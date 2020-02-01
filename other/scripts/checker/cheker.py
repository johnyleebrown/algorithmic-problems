#!/usr/bin/python
# -*- coding: utf-8 -*-

### snippets
# dirs[:] = [d for d in dirs if d not in folders_to_exclude]
# local = [file for file in files if os.path.splitext(file)[1] in file_types]

### features:
# > create session
# enter button - session starts - first problem
# enter btn - starts timer
# button to exit exists always - click - session is removed
# 
# running timer
# display percentage
# > create plan
# use subjects that require more attention(more often / more problems)
# set program (set countdown to the interviews)
# > stats
# calculate weekly overall across all subjects
# display which subject requires more attention (bad results)

# change right window size dynamic
# get unique problems
# get through all subjects
# start timer on start
# record a time to the file on done
# record the best times on exit
## add idea setup to 'other' repository
# possible format for result: "problem_number,time_spent,start_date_with_timezone"
# 1 session = all subjects

# new - create a list of all results for the specific subject
#   > make sure that new random problem number is not in the last 'n' of the results
#   > where n is the total number of problems for the topic

import os
import random
import npyscreen
import time
from datetime import datetime


def touchopen(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)


def read_from_file(file_path):
    file = touchopen(file_path, 'r+', encoding='utf-8')
    params = file.read().splitlines()
    file.close()
    return params


def create_files_map(from_dir):
    result = {}
    for dirpath, subdirs, files in os.walk(from_dir, topdown=True):
        for x in files:
            if x.endswith(".java"):
                path = os.path.join(dirpath, x)
                num = find_problem_number_in_file(path)
                result[num] = {'path': path, 'name': x}

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


def get_random_problem():
    params = read_from_file('parameters.txt')
    params_map = {x[0]: x[1] for x in [param.split(",") for param in params]}

    main_dir = params_map['main_dir']

    topics = read_from_file('input.txt')
    topics_map = {x[0]: x[1] for x in [topic.split(",") for topic in topics]}

    rand_topic_ind = random_select(len(topics_map))
    rand_topic_key = list(topics_map)[rand_topic_ind]
    rand_topic_dir = main_dir + topics_map[rand_topic_key]

    result_map = create_files_map(rand_topic_dir)

    rand_problem_ind = random_select(len(result_map))
    rand_problem = list(result_map)[rand_problem_ind]

    return str(rand_topic_key).strip(), str(rand_problem).strip()


def get_best_result_for_problem(current_problem_number):
    results = read_from_file('results.txt')
    vals = []
    for result in results:
        result_data = result.split(",")
        if result_data[0] == current_problem_number:
            vals.append(float(result_data[1]))
    if len(vals) == 0:
        return -1
    return min(vals)


def get_comparison_to_best_result(current_problem_number, current_time):
    best_result = get_best_result_for_problem(current_problem_number)
    if best_result == -1:
        return f'[NEW {current_time :.4f}]', 'DEFAULT'
    increase = current_time - best_result
    increase_percentage = increase / best_result * 100
    if increase_percentage >= 0.0:
        return '[+{0:.2f}%]'.format(increase_percentage), 'DANGER'
    return '[{0:.2f}%]'.format(increase_percentage), 'GOOD'


class Action(npyscreen.MultiLineAction):
    text_controls_begin_session = 'begin session'
    text_controls_start_problem = 'start problem'
    text_controls_done = 'done'
    text_controls_next = 'next'
    text_controls_exit = 'exit'
    text_problems_in_progress = '[In Progress]'
    text_problems_done = '[Done]'

    controls_on_begin_session = [text_controls_start_problem, text_controls_exit]
    controls_on_start_problem = [text_controls_done, text_controls_exit]
    controls_on_done = [text_controls_next, text_controls_exit]

    current_problem_title = ''

    base_rel_x = 22
    base_rel_y = 3
    cur_rel_x = base_rel_x
    cur_rel_y = base_rel_y

    def get_current_problem_title(self):
        return self.current_problem_topic + ": " + self.current_problem_number

    def record_result(self, time_spent_val):
        newline = self.current_problem_topic + ',' + self.current_problem_number + ',' + str(time_spent_val) + ',' + str(datetime.utcnow())
        with open('results.txt', 'a+') as f:
            f.write(newline + '\n')

    def actionHighlighted(self, act_on_this, key_press):
        if act_on_this == self.text_controls_begin_session:
            self.action_on_begin_session()
        elif act_on_this == self.text_controls_start_problem:
            self.action_on_start_problem()
        elif act_on_this == self.text_controls_done:
            self.action_on_done()
        elif act_on_this == self.text_controls_next:
            self.action_on_next()
        elif act_on_this == self.text_controls_exit:
            self.action_on_exit()

    def action_on_begin_session(self):
        # update controls
        self.value = None
        self.values = self.controls_on_begin_session
        self.display()

        # add new problem title
        self.current_problem_topic, self.current_problem_number = get_random_problem()
        self.current_problem_title = self.get_current_problem_title()
        w = len(self.current_problem_title) + 1
        self.t = self.parent.add(npyscreen.FixedText,
                                 relx=self.cur_rel_x, rely=self.cur_rel_y,
                                 max_height=5, max_width=w, value=self.current_problem_title)

        # update last rel x and rel y
        self.cur_rel_x += w

    def action_on_start_problem(self):
        # start timer for problem
        self.timer_start = time.perf_counter()

        # update controls
        self.value = None
        self.values = self.controls_on_start_problem
        self.display()

        # add problem title in white color
        box_text = self.text_problems_in_progress
        w = len(box_text) + 1
        self.t = self.parent.add(npyscreen.FixedText,
                                 color='WARNING', relx=self.cur_rel_x, rely=self.cur_rel_y,
                                 max_height=5, max_width=w, value=box_text)

    def action_on_done(self):
        # record time
        self.timer_end = time.perf_counter()
        time_spent_val = self.timer_end - self.timer_start

        # update controls
        self.value = None
        self.values = self.controls_on_done
        self.display()

        # remove in progress text from problem title box
        self.t.value = None
        self.t.display()

        # add result box next to problem title
        box_text, color = get_comparison_to_best_result(self.current_problem_number, time_spent_val)
        w = len(box_text) + 1
        self.t = self.parent.add(npyscreen.FixedText,
                                 color=color, relx=self.cur_rel_x, rely=self.cur_rel_y,
                                 max_height=5, max_width=w, value=box_text)

        # update last rel x and rel y
        self.cur_rel_x = self.base_rel_x
        self.cur_rel_y += 2

        # record result
        self.record_result(time_spent_val)

    def action_on_next(self):
        # update controls
        self.value = None
        self.values = self.controls_on_begin_session
        self.display()

        # add next problem title
        self.current_problem_topic, self.current_problem_number = get_random_problem()
        self.current_problem_title = self.get_current_problem_title()
        w = len(self.current_problem_title) + 1
        self.t = self.parent.add(npyscreen.FixedText,
                                 relx=self.cur_rel_x, rely=self.cur_rel_y,
                                 max_height=5, max_width=w, value=self.current_problem_title)

        # update last rel x and rel y
        self.cur_rel_x += w

    def action_on_exit(self):
        exit(0)


class BoxWithSelects(npyscreen.BoxTitle):
    _contained_widget = Action


class MainForm(npyscreen.FormBaseNew):
    def create(self):
        self.add(BoxWithSelects, values=['begin session', 'exit'], max_height=5, max_width=18)


class App(npyscreen.StandardApp):
    def onStart(self):
        self.addForm("MAIN", MainForm, name="Checker")


def start():
    my_app = App()
    my_app.run()


if __name__ == "__main__":
    start()