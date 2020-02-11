#!/usr/bin/python
# -*- coding: utf-8 -*-

import curses
import os

d = {"sout":"System","so":"S"}

def main(win):

    win.nodelay(True)
    key=""
    win.clear()
    win.addstr("")
    curline=0

    while 1:
        try:
            k = win.getkey()
            if k == os.linesep:
                break
            elif k == 'KEY_UP':
                curline-=1
            elif k == 'KEY_DOWN':
                curline+=1
            else:
                curline=0
                key += k

            win.clear()
            win.addstr(str(key))
            counter = 0

            for k in d.keys():
                if k.startswith(key):
                    sout = d[k]
                    if counter == curline:
                        sout='> ' + sout
                    win.addstr('\n' + sout)
                    counter+=1

        except Exception as e:
            pass

curses.wrapper(main)

# todo
## read from files
## copy on enter
