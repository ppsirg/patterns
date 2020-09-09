from PyQt5.Qt import (QWidget, QHBoxLayout, QLabel, QApplication, Qt)
from player import Player_Angrybird
from birds import AngryBird_ext
import sys


class Game_Angrybird(QWidget):
    player = Player_Angrybird(700, 300)
    birds = None

    def __init__(self):                                                                                                                    
        super().__init__()
        # build graphic interface
        # put player
        self.player.start()
        self.player_lbl = QLabel(self)
        self.player_lbl.setPixmap(self.player.get_player())
        self.player_lbl.move(700, 300)
        # put birds
        self.bird = AngryBird_ext((100, 400))
        self.bird_lbl = QLabel(self)
        self.bird_lbl.setPixmap(self.bird.get_bird())
        self.bird_lbl.move(100, 400)
        #launch birds
        self.launch_birds()
        # setup window
        self.setWindowTitle('PUJ-Angrybird')
        self.resize(800, 600)
        self.show()

    def launch_birds(self):
        pass

    def keyPressEvent(self, event):
        if event.key() == Qt.Key_Down:
            print('libertarian')
            self.player_lbl.move(*self.player.move_down())
            self.player_lbl.setPixmap(self.player.get_player())
        elif event.key() == Qt.Key_Up:
            print('autoritarian')
            self.player_lbl.move(*self.player.move_up())
            self.player_lbl.setPixmap(self.player.get_player())
        else:
            print('bertovision')

    def get_birds(self):
        """Create birds for game
        """
        pass

    # def get_window(self):
    #     pass


if __name__ == "__main__":
    app = QApplication(sys.argv)
    game = Game_Angrybird()
    sys.exit(app.exec_())