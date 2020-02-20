def make_prefix_arr(vals):
    pre = [0]
    for val in vals:
        pre.append(pre[-1] + val)
    return pre[1:]

