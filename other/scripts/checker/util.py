#!/usr/bin/python
# -*- coding: utf-8 -*-


def touch_open(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)


def read_from_file(file_path):
    file = touch_open(file_path, 'r+', encoding='utf-8')
    params = file.read().splitlines()
    file.close()
    return params


def add_line_to_file(file_name, text):
    with open(file_name, 'a+') as f:
        f.write(text + '\n')
