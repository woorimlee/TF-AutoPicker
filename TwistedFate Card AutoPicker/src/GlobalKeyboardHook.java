//Line 2~4 : ���콺�̺�Ʈ ��� ���� ���̺귯��
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.opencv.core.Mat;

/* nativeKeyPressed : Ű���尡 ������ ��
 * nativeKeyReleased : Ű���带 ���� ��
 * nativeKeyTyped : ���� ���� ����
 */

public class GlobalKeyboardHook implements NativeKeyListener {

	Capture Capture = new Capture(); 

	public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
			Mat BaseImg = Capture.bufferedImageToMat(Capture.ScreenCapture());
	        new CompareHistogram().run(BaseImg, "Gold");
		}
		if (e.getKeyCode() == NativeKeyEvent.VC_CAPS_LOCK) {
			Mat BaseImg = Capture.bufferedImageToMat(Capture.ScreenCapture());
	        new CompareHistogram().run(BaseImg, "Blue");
		}
		if (e.getKeyCode() == NativeKeyEvent.VC_E) {
			Mat BaseImg = Capture.bufferedImageToMat(Capture.ScreenCapture());
	        new CompareHistogram().run(BaseImg, "Red");
		}
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) {
	//	System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
	//	System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public void StartGlobalHook() {
		System.out.println("Start GlobalHook...");
		LogManager.getLogManager().reset();//���콺 �̺�Ʈ OFF
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		
		GlobalScreen.addNativeKeyListener(new GlobalKeyboardHook());
	}

	public void UnHooking() {
		try {
			GlobalScreen.unregisterNativeHook();
			System.out.println("...Finish GlobalHook");
		} catch (NativeHookException e1) {
			e1.printStackTrace();
		}
	}
	
}