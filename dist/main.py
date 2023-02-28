import subprocess
import os, sys
from pynput.keyboard import Key, Listener
from pynput.mouse import Button, Controller
import pynput.mouse as mouse

altPressed = False
spacePressed = False
file = os.path.dirname(os.path.abspath(__file__)) +'\search.jar'
#print(file)
OPEN = False

def on_press(key):
    if key == Key.esc:
        sys.exit()
    global spacePressed
    global altPressed
    global OPEN
    if key == Key.space:
        spacePressed = True
    if key == Key.alt_l:
        altPressed = True

    if altPressed == True:
        if spacePressed == True:
            altPressed = False
            spacePressed = False
            if OPEN == False:
                p = subprocess.Popen(['java', '-jar', file])

def on_release(key):
    global altPressed
    global spacePressed
    if key == Key.alt_l:
        altPressed = False
    if key == Key.space:
        spacePressed = False

#mouse = Controller()

#def on_click(x, y, button, pressed):
#    print(button)

#with mouse.Listener(on_click = on_click, suppress = True) as listener:
    #listener.join()

with Listener(
    on_press = on_press,
    on_release = on_release,
    suppress = True
    ) as listener:
    listener.join()