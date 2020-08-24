from time import time


def measure_time(fn):
    """decorator to measure time
    """
    def inner_func(*args, **kwargs):
        tm = time()
        resp = fn(*args, **kwargs)
        elapsed = time() - tm
        print(fn.__name__, {'elapsed': elapsed, 'response': resp})
        return {'elapsed': elapsed, 'response': resp}
    return inner_func