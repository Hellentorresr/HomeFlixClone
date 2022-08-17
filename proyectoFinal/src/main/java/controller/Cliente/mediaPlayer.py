from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QHBoxLayout, QVBoxLayout, QStyle, QSlider, QFileDialog
from PyQt5.QtGui import QIcon, QPalette
from PyQt5.QtMultimedia import QMediaPlayer, QMediaContent
from PyQt5.QtMultimediaWidgets import QVideoWidget
from PyQt5.QtCore import Qt, QUrl
import sys

class Window(QWidget):

    def __init__(self):
        super().__init__()
        ##Set icon
        self.setWindowIcon(QIcon("player.ico"))
        self.setWindowTitle("HomeFlix")
        self.setGeometry(350,100,700,500)
        
        p = self.palette()
        p.setColor(QPalette.Window, Qt.GlobalColor.black)
        self.setPalette(p)
        self.createPlayer()

    def createPlayer(self):
        self.mediaPlayer = QMediaPlayer(None, QMediaPlayer.VideoSurface)
        videoWidget = QVideoWidget()
        
        self.openBtn = QPushButton('Open Video')
        self.openBtn.clicked.connect(self.openFile)
        
        self.playBtn = QPushButton()
        self.playBtn.setEnabled(False)
        self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPlay))
        self.playBtn.clicked.connect(self.playVideo)
        
        self.slider = QSlider(Qt.Horizontal)
        self.slider.setRange(0,0)
        
        
        hbox = QHBoxLayout()
        hbox.setContentsMargins(0,0,0,0)
        hbox.addWidget(self.openBtn)
        hbox.addWidget(self.playBtn)
        hbox.addWidget(self.slider)
        
        vbox = QVBoxLayout()
        vbox.addWidget(videoWidget)
        vbox.addLayout(hbox)
        
        self.mediaPlayer.setVideoOutput(videoWidget)
        self.setLayout(vbox)

    def openFile(self):
        fileName, _ = QFileDialog.getOpenFileName(self, "Open Video")
        
        if fileName != '':
            self.mediaPlayer.setMedia(QMediaContent(QUrl.fromLocalFile(fileName)))
            self.playBtn.setEnabled(True)
        
    def playVideo(self):   
        if self.mediaPlayer.state() == QMediaPlayer.PlayingState:
            self.mediaPlayer.pause()
        else:
            self.mediaPlayer.play()
    
    def mediaStateChange(self, state):
        if (self.mediaPlayer.state() == QMediaPlayer.PlayingState):
            self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPause))

        else:
            self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPlay))
            
    def positionChanged(self, position):
        self.slider.setValue(position)
        
    def duratioChanged(self, duration):
        self.slider.setRange(0, duration)
        
        
        
        
        
app = QApplication(sys.argv)
window = Window()
window.show()
sys.exit(app.exec_())