from selenium.webdriver import Firefox, FirefoxProfile
from selenium.webdriver.firefox.options import Options
from utils import measure_time
from pprint import pprint
from time import time
from reusable import reusableBrowser
from copy import deepcopy


class AdvancedPool():
    _reusablePool = []
    _max_size = 10
    _min_size = 1
    created_objects = 0

    def __init__(self, max_size, min_size):
        self._max_size = max_size
        self._min_size = min_size
        self._reusablePool = [
            self._generateReusable()
            for a in range(self._min_size)
            ]
        AdvancedPool.created_objects = len(self._reusablePool)

    def acquireReusable(self):
        in_pool = len(self._reusablePool)
        if in_pool == 0 and self.created_objects < self._max_size:
            # return deepcopy(self._reusablePool[-1])
            AdvancedPool.created_objects += 1
            return self._generateReusable()
        else:
            return self._reusablePool.pop()

    def _generateReusable(self):
        return reusableBrowser()

    def releaseReusable(self, reusable):
        self._reusablePool.append(reusable)