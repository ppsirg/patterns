from reusable import reusableBrowser


class Pool():
    _reusablePool = []
    _max_size = 1

    def __init__(self, max_size):
        self._max_size = max_size
        self._reusablePool = [
            self._generateReusable()
            for a in range(self._max_size)
            ]

    def acquireReusable(self):
        return self._reusablePool.pop()

    def _generateReusable(self):
        return reusableBrowser()

    def releaseReusable(self, reusable):
        self._reusablePool.append(reusable)

    def close(self):
        for item in self._reusablePool:
            item.clean_up()