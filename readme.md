# 정보
**이 프로젝트는 학습용으로 제작되었습니다. 사용으로 인한 불이익의 책임은 당사자에게 있습니다.**<br>
참조된 외부 라이브러리 :
[_JNativeHook2.1.0_](https://github.com/kwhat/jnativehook),
[_OpenCV3.4.5_](https://github.com/opencv/opencv)

**OpenCV compareHistogram**을 사용하여 사용자화면의 카드와 미리 저장된 [카드이미지](https://github.com/RyuSeonghyun/TF-AutoPicker/tree/master/TwistedFate%20Card%20AutoPicker/img)를
[_Intersection_](https://docs.opencv.org/3.4/d8/dc8/tutorial_histogram_comparison.html) 방식(값이 클수록 근사)으로 비교합니다.

## 사용법
W를 눌러 스킬을 사용하고, 뽑고싶은 카드에 해당하는 키를 누르고있는다.
<pre>
GoldCard    [Ctrl]

RedCard    [E]

BlueCard    [Caps Lock]
</pre>

## 지원 해상도
1920x1080<br>
2560x1440* (Default) 

전체화면 또는 테두리없음 환경에서만 작동합니다.<br>
1920x1080 해상도를 사용한다면 [setting.ini](https://github.com/RyuSeonghyun/TF-AutoPicker/blob/master/TwistedFate%20Card%20AutoPicker/setting.ini)파일을 수정해야합니다.
<br><br>
_setting.ini_
<pre>
#
#Your ScreenSize 1920x1080 => ScreenSize = 1920
#		 2560x1440 => ScreenSize = 2560
#
[Setting]
ScreenSize=1920
</pre>
## 시연
##### InGame
![demo1](https://github.com/RyuSeonghyun/TF-AutoPicker/blob/master/demo_gif/ingame.gif)
##### Console
![demo2](https://github.com/RyuSeonghyun/TF-AutoPicker/blob/master/demo_gif/console.gif)
