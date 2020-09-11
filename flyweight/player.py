from PyQt5.QtGui import QPixmap


class Player_Angrybird():
    _x = 0
    _y = 0
    _orientation = 'up'
    _delta = 50
    _limits = (800, 600)
    _images = None

    def __init__(self, x, y):
        self._x = x
        self._y = y

    def start(self):
        positions = ['up', 'down']
        self._images = {a: QPixmap(f'kisuke_{a}.png') for a in positions}

    def get_player(self):
        return self._images[self._orientation]

    def move_up(self):
        self._y -= self._delta
        self._orientation = 'up'
        return (self._x, self._y)
    
    def move_down(self):
        self._y += self._delta
        self._orientation = 'down'
        return (self._x, self._y)

    def move_left(self):
        self._x -= self._delta
        return (self._x, self._y)
    
    def move_right(self):
        self._x += self._delta
        return (self._x, self._y)