from PyQt5.QtGui import QPixmap


class AngryBird_ext():
    _x = 0
    _y = 0
    _delta = 10
    _orientation = 'right'
    _state_intr = None
    _limits = (800, 600)

    def __init__(self, initial_position):
        self._x, self._y = initial_position
        factory = BirdFactory()
        self._state_intr = factory.get_bird_intrinsec('red')

    def get_bird(self):
        return self._state_intr.render_bird('angry')

    def go_right(self, speed=False):
        delta = speed if speed else self._delta
        self._x += delta
        self.orientation = 'right'
        return (self._x, self._y)


class Angrybird_int():
    _image_files = {}
    color = 'red'

    def __init__(self, color='red'):
        emotions = [
            'happy', 'angry', 'sleep'
        ]
        self._image_files = {a: QPixmap(f'{color}_{a}.png') for a in emotions}

    def render_bird(self, emotion):
        return self._image_files[emotion]


class BirdFactory():
    _bird_collection = {}

    def __init__(self):
        for color in ['red']:
            self._bird_collection[color] = Angrybird_int(color=color)
    
    def get_bird_intrinsec(self, color):
        bird = self._bird_collection.get(color)
        return bird