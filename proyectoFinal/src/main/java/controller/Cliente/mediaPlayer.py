
from fileinput import filename
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
        
        #self.openBtn = QPushButton('Open Video')
        #self.openBtn.clicked.connect(self.openFile)
        
        self.playBtn = QPushButton()
        self.playBtn.setEnabled(True)
        self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPlay))
        self.playBtn.clicked.connect(self.playVideo)
        
        self.slider = QSlider(Qt.Horizontal)
        self.slider.setRange(0,0)
        self.slider.sliderMoved.connect(self.set_position)
        
        hbox = QHBoxLayout()
        hbox.setContentsMargins(0,0,0,0)
        #hbox.addWidget(self.openBtn)
        hbox.addWidget(self.playBtn)
        hbox.addWidget(self.slider)
        
        vbox = QVBoxLayout()
        vbox.addWidget(videoWidget)
        vbox.addLayout(hbox)
        
        self.mediaPlayer.setVideoOutput(videoWidget)
        self.setLayout(vbox)
        
        self.mediaPlayer.stateChanged.connect(self.mediaStateChange)
        self.mediaPlayer.stateChanged.connect(self.positionChanged)
        self.mediaPlayer.stateChanged.connect(self.duratioChanged)
        
    
    def openFile(self,fileName):      
        print(fileName)
        if fileName != '':
            self.mediaPlayer.setMedia(QMediaContent(QUrl.fromLocalFile(fileName)))
            #self.mediaPlayer.play
            self.playBtn.setEnabled(True)
            if self.mediaPlayer.state() != QMediaPlayer.PlayingState:
                self.mediaPlayer.play()
            
        
    def playVideo(self):   
        if self.mediaPlayer.state() != QMediaPlayer.PlayingState:
            self.mediaPlayer.play()
        
    def pauseVideo(self):
        if self.mediaPlayer.state() == QMediaPlayer.PlayingState:
            self.mediaPlayer.pause()
    
    def mediaStateChange(self, state):
        if (self.mediaPlayer.state() == QMediaPlayer.PlayingState):
            self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPause))

        else:
            self.playBtn.setIcon(self.style().standardIcon(QStyle.SP_MediaPlay))
            
    def positionChanged(self, position):
        self.slider.setValue(position)
        
    def duratioChanged(self, duration):
        self.slider.setRange(0, duration)
        
    def set_position(self, position):
        self.mediaPlayer.setPosition(position)      
    
def runApp(fileName):
    app = QApplication(sys.argv)
    print("esto es lo que tiene que entrar fuck: ",fileName)
    window = Window()
    window.openFile(fileName)
    window.show()
    sys.exit(app.exec_()) 
  
def pauseAPP():
    window = Window()
    window.pauseVideo

def playApp():
    window = Window()
    window.playVideo
        
