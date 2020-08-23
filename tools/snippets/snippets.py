#!/usr/bin/python
# -*- coding: utf-8 -*-

import curses
import os
import pyperclip

ppath="/Users/grigorii/Desktop"


def touch_open(filename, *args, **kwargs):
    open(filename, "a").close()
    return open(filename, *args, **kwargs)


def read_from_file(file_path):
    file = touch_open(file_path, 'r+', encoding='utf-8')
    params = file.read()
    file.close()
    return params


def parse_snip_names():
    from_dir = ppath+'/projects/algorithmic-problems/tools/snippets/data'
    names = []
    names_to_path_map = {}
    default_p = from_dir + '/'
    for dir_path, sub_dirs, files in os.walk(from_dir, topdown=True):
        for f in files:
            names.append(f.split('.')[0])
            names_to_path_map[names[len(names) - 1]] = default_p + f

    return sorted(names), names_to_path_map


def main(win):

    datas, datas_map = parse_snip_names()

    win.nodelay(True)
    key = ""
    win.clear()
    win.addstr("")
    cur_line = 0
    cur_key = datas[0]
    while 1:
        try:
            k = win.getkey()
            if key in ('KEY_BACKSPACE', '\b', '\x7f'):
                win.addstr('\n' + key)
                win.addstr('\n' + 'sasa')
            elif k == os.linesep:
                path = datas_map[cur_key]
                pyperclip.copy(read_from_file(path))
                exit(0)
            elif k == 'KEY_UP':
                cur_line -= 1
            elif k == 'KEY_DOWN':
                cur_line += 1
            elif k.isalpha() and len(k) == 1:
                cur_line = 0
                key += k
            else:
                continue

            win.clear()
            win.addstr(str(key))
            counter = 0

            for d in datas:
                if key and d.startswith(key):
                    sout = d
                    if counter == cur_line:
                        cur_key = d
                        sout = '> ' + sout
                    win.addstr('\n' + sout)
                    counter += 1

        except Exception as e:
            pass


if __name__ == "__main__":
    curses.wrapper(main)
