#!/usr/bin/python
# -*- coding: utf-8 -*-


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
