#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys
from datetime import *

from core_service import CoreService


def get_best_result_for_problem(current_problem_number, results):
    vals = []
    for result in results:
        result_data = result.split(",")
        if result_data[1] == current_problem_number:
            vals.append(float(result_data[2]))
    if len(vals) == 0:
        return -1
    return min(vals)


def get_comparison_to_best_result(current_problem_number, current_time, results):
    best_result = get_best_result_for_problem(current_problem_number, results)
    if best_result == -1:
        return f'[NEW {current_time :.4f}]', 'DEFAULT'
    increase = current_time - best_result
    increase_percentage = increase / best_result * 100
    if increase_percentage >= 0.0:
        return '[+{0:.2f}%]'.format(increase_percentage), 'DANGER'
    return '[{0:.2f}%]'.format(increase_percentage), 'GOOD'


def get_title(s):
    tot=30
    l='='*3
    empt=' '
    leftover='='*(tot-len(l)-len(s))
    return l + empt + s + empt + leftover


def get_now_with_delta(d):
    now = datetime.utcnow()
    return now+timedelta(days=d)


def get_fastest_solved_problem(n, results):
    res_seconds=sys.float_info.max
    res_title=''
    now_with_delta = get_now_with_delta(-n)
    for r in results:
        res_data=r.split(",")
        dt = get_date(res_data[3])
        if dt >= now_with_delta:
            cur=float(res_data[2])
            if cur < res_seconds:
                res_seconds=cur
                res_title=res_data[0] + ', ' + res_data[1]
    res_minutes = res_seconds/60
    s=f'[{res_minutes :.2f}]'
    return [get_title('FASTEST'), s + ' ' + res_title]


def get_slowest_solved_problem(n, results):
    res_seconds=sys.float_info.min
    res_title=''
    now_with_delta=get_now_with_delta(-n)
    for r in results:
        res_data=r.split(",")
        dt = get_date(res_data[3])
        if dt>=now_with_delta:
            cur = float(res_data[2])
            if cur > res_seconds:
                res_seconds = cur
                res_title = res_data[0] + ', ' + res_data[1]
    res_minutes = res_seconds/60
    s=f'[{res_minutes :.2f}]'
    return [get_title('SLOWEST'), s + ' ' + res_title]


def get_date(s):
    return datetime.strptime(s, '%Y-%m-%d %H:%M:%S.%f')


def get_(n,results):
    now_with_delta = get_now_with_delta(-n)
    topics={}
    for r in results:
        res_data=r.split(",")
        dt=get_date(res_data[3])
        if dt>=now_with_delta:
            if res_data[0] in topics:
                topics[res_data[0]] += 1
            else:
                topics[res_data[0]] = 1
    return topics


def get_topics_problems_count(n, results):
    topics=get_(n,results)
    res=[get_title('WEEK')]
    for k, v in sorted(topics.items(), key=lambda item: item[1], reverse=True):
        res.append(str(v) + ' ' + k)
    return res


def get_for_period(n, results):
    res = []
    res.extend(get_fastest_solved_problem(n, results))
    res.extend(get_slowest_solved_problem(n, results))
    res.extend(get_topics_problems_count(n, results))
    return res


def get_weekly(r):
    return get_for_period(7,r)


def get_month(r):
    return get_for_period(30,r)

# if __name__ == "__main__":
#     core = CoreService()
#     print(get_fastest_solved_problem(7,core.results))

# add good or bad stats
# for each topic it should be 7 solves per week