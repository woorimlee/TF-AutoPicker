import java.io.IOException;
import org.opencv.core.Core;

/*
         	long startTime = System.currentTimeMillis();
        	long estimatedTime = System.currentTimeMillis() - startTime;     
            System.out.println("estimatedTime=" + estimatedTime + "ms");  //시간 측정
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Load the Native OpenCV Library : " + Core.NATIVE_LIBRARY_NAME);
        
        IniFile IniFile = new IniFile();//setting.ini 에서 설정을 불러옴
        
        GlobalKeyboardHook GlobalKeyboardHook = new GlobalKeyboardHook();
        GlobalKeyboardHook.StartGlobalHook();       
    }
    
}
