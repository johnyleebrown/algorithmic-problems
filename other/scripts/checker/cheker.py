#!/usr/bin/python
#!/usr/bin/env python3
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

import os
import random
import curses
import time
import npyscreen

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
    params_map = { x[0] : x[1] for x in [param.split(",") for param in params] }
    
    main_dir = params_map['main_dir']
    
    topics = read_from_file('input.txt')
    topics_map = { x[0] : x[1] for x in [topic.split(",") for topic in topics] }
    
    rand_topic_ind = random_select(len(topics_map))
    rand_topic_key = list(topics_map)[rand_topic_ind]
    rand_topic_dir = main_dir + topics_map[rand_topic_key]

    result_map = create_files_map(rand_topic_dir)

    rand_problem_ind = random_select(len(result_map))
    rand_problem = list(result_map)[rand_problem_ind]

    return rand_topic_key + ': ' + rand_problem

class InSessionBox(npyscreen.BoxTitle):
    _contained_widget = npyscreen.TitleText

# class SessionBox(npyscreen.BoxTitle):


class BoxWithSelects(npyscreen.BoxTitle):
    _contained_widget = npyscreen.SelectOne

class MainForm(npyscreen.FormBaseNew):
    controls_1 = ["start","exit"]
    def create(self):
        self.y, self.x = self.useable_space()
        self.control_box = self.add(BoxWithSelects, values=self.controls_1, max_height=5, max_width=15)
        self.control_box.value_changed_callback = self.select_control

    def select_control(self, widget):
        selected_values = self.control_box.get_value()
        if len(selected_values):
            selected_value = self.control_box.get_value()[0]
            if selected_value == 0:
                self.session_box = self.add(npyscreen.BoxTitle, relx=20, rely=2, max_height=15, max_width=30)
                self.session_box.values = ["a"]
                self.session_box.display()
            else:
                self.exit_app()

    def exit_app(self):
        curses.beep()
        self.parentApp.setNextForm(None)
        self.editing = False
        self.parentApp.switchFormNow()

class App(npyscreen.StandardApp):
    def onStart(self):
        self.addForm("MAIN", MainForm, name="Checker")

def start():
    MyApp = App()
    MyApp.run()

if __name__ == "__main__":
    start()