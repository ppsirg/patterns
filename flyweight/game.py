
from PyQt5.Qt import (QWidget, QLabel, QApplication, QThread, Qt)
from player import Player_Angrybird
from birds import AngryBird_ext
from random import randint
from time import sleep
import sys


class BirdActivity(QThread):
    def __init__(self, parent=None, interface=None):
        super().__init__()
        self.interface = interface

    def run(self):
        self.start_birds()

    def start_birds(self):
        while True:
            sleep(0.5)
            for l,b in self.interface.bird_lbls:
                if randint(0,2) == 1:
                    move = b.go_right()
                else:
                    move = b.go_left()
                l.move(*move)


class Game_Angrybird(QWidget):
    player = Player_Angrybird(900, 400)
    birds = None


    def __init__(self):                                                                                                                    
        super().__init__()
        # build graphic interface
        # put player
        self.player.start()
        self.player_lbl = QLabel(self)
        self.player_lbl.setPixmap(self.player.get_player())
        self.player_lbl.move(900, 400)
        #launch birds
        self.bird_lbls = []
        self.launch_birds()
        self.bird_thread = BirdActivity(interface=self)
        # setup window
        self.setWindowTitle('PUJ-Angrybird')
        self.resize(1000, 800)
        self.bird_thread.start()
        self.show()

    def launch_birds(self):
        # put birds
        for x in range(0,800,100):
            for y in range(0,700,100):
                bird = AngryBird_ext((x, y))
                bird_lbl = QLabel(self)
                bird_lbl.setPixmap(bird.get_bird())
                bird_lbl.move(x, y)
                self.bird_lbls.append([bird_lbl, bird])
    
    def keyPressEvent(self, event):
        if event.key() == Qt.Key_Down:
            print('libertarian')
            self.player_lbl.move(*self.player.move_down())
            self.player_lbl.setPixmap(self.player.get_player())
        elif event.key() == Qt.Key_Up:
            print('autoritarian')
            self.player_lbl.move(*self.player.move_up())
            self.player_lbl.setPixmap(self.player.get_player())
        elif event.key() == Qt.Key_Left:
            print('autoritarian')
            self.player_lbl.move(*self.player.move_left())
            self.player_lbl.setPixmap(self.player.get_player())
        elif event.key() == Qt.Key_Right:
            print('autoritarian')
            self.player_lbl.move(*self.player.move_right())
            self.player_lbl.setPixmap(self.player.get_player())
        else:
            print('bertovision')


if __name__ == "__main__":
    app = QApplication(sys.argv)
    game = Game_Angrybird()
    sys.exit(app.exec_())