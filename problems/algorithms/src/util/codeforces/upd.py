#!/usr/bin/python
# -*- coding: utf-8 -*-

from pathlib import Path


def touch_open(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)


def read_from_file(file_path):
    file = touch_open(file_path, 'r+', encoding='utf-8')
    params = file.read()
    file.close()
    return params


def write_to_file(file_name, text):
    with open(file_name, 'w+') as f:
        f.write(text)


if __name__ == "__main__":
    src_path="/Volumes/Greg/projects/algorithmic-problems/tools/snippets/data/cf"
    target_path=str(Path(__file__).parent.absolute())+'/'
    write_to_file(target_path+'Main.java',read_from_file(src_path))