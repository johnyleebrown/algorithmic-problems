#!/usr/bin/python
# -*- coding: utf-8 -*-

from datetime import *


def touch_open(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)


def read_from_file(file_path):
    file = touch_open(file_path, 'r+', encoding='utf-8')
    params = file.read().splitlines()
    file.close()
    return params


def write_to_file(file_name, text):
    with open(file_name, 'w+') as f:
        f.write(text)


def add_line_to_file(file_name, text):
    with open(file_name, 'a+') as f:
        f.write(text + '\n')


def clear_file(file_name):
    open(file_name, 'w+').close()


def record_test_case(str, path):
    add_line_to_file(path + 'test.txt', str)


def get_date(s):
    return datetime.strptime(s, '%Y-%m-%d %H:%M:%S.%f')


def get_dt_with_delta(dt,d):
    return dt+timedelta(days=d)
