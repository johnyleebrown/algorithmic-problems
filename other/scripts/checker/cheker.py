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

    return rand_topic_key, rand_problem


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
        return f' [NEW {current_time :.4f} sec.]'
    increase = current_time - best_result
    increase_percentage = increase/best_result * 100
    if increase_percentage >= 0.0:
        return ' [+{0:.2f}%]'.format(increase_percentage)
    return ' [{0:.2f}%]'.format(increase_percentage)


class Action(npyscreen.MultiLineAction):
    text_controls_begin_session = 'begin session'
    text_controls_start_problem = 'start problem'
    text_controls_done = 'done'
    text_controls_next = 'next'
    text_controls_exit = 'exit'

    text_problems_in_progress = ' [In Progress]'
    text_problems_done = ' [Done]'

    controls_on_begin_session = [text_controls_start_problem, text_controls_exit]
    controls_on_start_problem = [text_controls_done, text_controls_exit]
    controls_on_done = [text_controls_next, text_controls_exit]

    current_problem_name = ''

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
        self.value = None
        self.values = self.controls_on_begin_session
        self.display()

        self.session_box = self.parent.add(npyscreen.BoxTitle, custom_highlighting=True,relx=20, rely=2, max_height=15, max_width=70)
        color1 = self.parent.theme_manager.findPair(self, 'GOOD')
        self.session_box.highlighting_arr_color_data = [color1]
        self.current_problem_topic, self.current_problem_number = get_random_problem()

        self.session_box.values = [self.get_current_problem_title()]
        self.session_box.display()


    def get_current_problem_title(self):
        return self.current_problem_topic + ": " + self.current_problem_number


    def action_on_start_problem(self):
        self.timer_start = time.perf_counter()

        self.value = None
        self.values = self.controls_on_start_problem
        self.display()

        self.session_box.values[len(self.session_box.values) - 1] += self.text_problems_in_progress
        self.session_box.display()


    def action_on_done(self):
        self.timer_end = time.perf_counter()
        time_spent_val = self.timer_end - self.timer_start

        self.value = None
        self.values = self.controls_on_done
        self.display()

        comparison = get_comparison_to_best_result(self.current_problem_number, time_spent_val)
        updated_current_line = self.get_current_problem_title() + comparison
        self.session_box.values[len(self.session_box.values) - 1] = updated_current_line
        self.session_box.display()

        newline = self.current_problem_number + "," + str(time_spent_val) + "," + str(datetime.utcnow())
        with open("results.txt", "a+") as f:
            f.write(newline + "\n")


    def action_on_next(self):
        self.value = None
        self.values = self.controls_on_begin_session
        self.display()

        self.current_problem_topic, self.current_problem_number = get_random_problem()

        self.session_box.values.append(self.get_current_problem_title())
        self.session_box.display()


    def action_on_exit(self):
        exit(0)


class BoxWithSelects(npyscreen.BoxTitle):
    _contained_widget = Action


class MainForm(npyscreen.FormBaseNew):
    def create(self):
        self.add(BoxWithSelects, values=['begin session', 'exit'], max_height=4, max_width=18)


class App(npyscreen.StandardApp):
    def onStart(self):
        npyscreen.setTheme(npyscreen.Themes.ColorfulTheme)
        self.addForm("MAIN", MainForm, name="Checker")


def start():
    my_app = App()
    my_app.run()


if __name__ == "__main__":
    start()