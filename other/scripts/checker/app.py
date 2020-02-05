#!/usr/bin/python
# -*- coding: utf-8 -*-

import npyscreen
import time
from datetime import datetime

import util as u
from core_service import CoreService
import stats_service as stats

core: CoreService


class CustomWidget(npyscreen.MultiLineAction):
    text_controls_begin_session = 'begin session'
    text_controls_start_problem = 'start problem'
    text_controls_done = 'done'
    text_controls_next = 'next'
    text_controls_exit = 'exit'
    text_controls_weekly_stats = 'weekly stats'
    text_controls_overall_stats = 'overall stats'
    text_session_finished = '==== session is finished ===='
    text_problems_in_progress = '[In Progress]'
    text_problems_done = '[Done]'

    color_y = 'WARNING'

    controls_on_begin_session = [text_controls_start_problem, text_controls_exit, text_controls_weekly_stats]
    controls_on_start_problem = [text_controls_done, text_controls_exit, text_controls_weekly_stats]
    controls_on_done = [text_controls_next, text_controls_exit, text_controls_weekly_stats]
    controls_on_session_finished = [text_controls_exit, text_controls_weekly_stats]
    controls_on_stats = [text_controls_exit]

    current_problem_title = ''

    base_rel_x = 22
    base_rel_y = 3
    cur_rel_x = base_rel_x
    cur_rel_y = base_rel_y

    current_text_box = None
    timer_start = None
    timer_end = None
    current_problem_start_time = None
    current_problem_topic = ''
    current_problem_number = ''

    def __init__(self, *args, **keywords):

        super().__init__(*args, **keywords)

        self.action_switcher = {
            self.text_controls_begin_session: self.action_on_begin_session,
            self.text_controls_start_problem: self.action_on_start_problem,
            self.text_controls_done: self.action_on_done,
            self.text_controls_next: self.action_on_next,
            self.text_controls_exit: self.action_on_exit,
            self.text_controls_weekly_stats: self.action_on_weekly_stats
        }

    def get_current_problem_title(self):
        return self.current_problem_topic + ',' + self.current_problem_number

    def get_next_problem_title(self):
        self.current_problem_topic = core.get_next_topic()
        self.current_problem_number = core.get_random_problem(self.current_problem_topic, core.results)
        return self.current_problem_topic + ': ' + self.current_problem_number

    def record_result(self, time_spent_val):
        new_line_words = [self.get_current_problem_title(), time_spent_val, self.current_problem_start_time]
        u.add_line_to_file('results.txt', ','.join(new_line_words))

    def create_text_box_new_line(self, title, color='DEFAULT'):
        text_box = self.parent.add(npyscreen.FixedText,
                                   color=color, relx=self.cur_rel_x, rely=self.cur_rel_y,
                                   max_height=5, max_width=self.get_text_box_len(title), value=title)
        return text_box

    @staticmethod
    def get_text_box_len(text):
        return len(text) + 1

    def actionHighlighted(self, act_on_this, key_press):
        return self.action_switcher.get(act_on_this, "nothing")()

    def update_controls(self, controls):

        self.value = None

        if core.is_session_finished and controls == self.controls_on_done:
            self.values = self.controls_on_session_finished
        else:
            self.values = controls

        self.display()

    def action_on_begin_session(self):

        # update controls
        self.update_controls(self.controls_on_begin_session)

        # add new problem title
        title = self.get_next_problem_title()
        self.current_text_box = self.create_text_box_new_line(title)

        # update x margin
        self.cur_rel_x += self.get_text_box_len(title)

    def action_on_start_problem(self):

        # start timer for problem
        self.timer_start = time.perf_counter()
        self.current_problem_start_time = str(datetime.utcnow())

        # update controls
        self.update_controls(self.controls_on_start_problem)

        # add problem title in white color
        title = self.text_problems_in_progress
        self.current_text_box = self.create_text_box_new_line(title)

    def action_on_done(self):

        # record end time
        self.timer_end = time.perf_counter()
        time_spent_val = self.timer_end - self.timer_start

        # update controls
        self.update_controls(self.controls_on_done)

        # remove in progress text from problem title box
        self.current_text_box.value = None
        self.current_text_box.display()

        # add result box next to problem title
        title, color = stats.get_comparison_to_best_result(self.current_problem_number, time_spent_val, core.results)
        self.current_text_box = self.create_text_box_new_line(title, color)

        # update x,y margin
        self.cur_rel_x = self.base_rel_x
        self.cur_rel_y += 2

        # record result
        self.record_result(str(time_spent_val))

        # action if session finished
        if core.is_session_finished:
            self.create_text_box_new_line(self.text_session_finished, self.color_y)

    def action_on_next(self):

        # update controls
        self.update_controls(self.controls_on_begin_session)

        # add next problem title
        title = self.get_next_problem_title()
        self.current_text_box = self.create_text_box_new_line(title)

        # update x margin
        self.cur_rel_x += self.get_text_box_len(title)

    def action_on_weekly_stats(self):

        # update controls
        self.update_controls(self.controls_on_stats)

        # clear
        for x in self.parent._widgets__[1:]:
            x.value = None
            x.display()

        self.cur_rel_x = self.base_rel_x
        self.cur_rel_y = self.base_rel_y
        self.current_text_box = self.create_text_box_new_line("Stats")


    def action_on_exit(self):
        exit(0)


class BoxWithSelects(npyscreen.BoxTitle):
    _contained_widget = CustomWidget


class MainForm(npyscreen.FormBaseNew):
    def create(self):
        self.add(BoxWithSelects, values=['begin session', 'exit'], max_height=5, max_width=18)


class App(npyscreen.StandardApp):
    def onStart(self):
        global core
        core = CoreService()
        self.addForm("MAIN", MainForm, name="Checker")
