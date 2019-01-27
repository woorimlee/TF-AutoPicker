
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Capture {
	
	//ĸó�� �̹����� ���ϱ����� BufferedImage���信��
	//OpenCV������ Mat���� ��ȯ
	public Mat bufferedImageToMat(BufferedImage inBuffImg){	
		BufferedImage image = new BufferedImage(inBuffImg.getWidth(), inBuffImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
	    Graphics2D g2d= image.createGraphics();
	    g2d.drawImage(inBuffImg, 0, 0, null);
	    g2d.dispose();

	    Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);//8��Ʈ 3ä��(RGB)
	    byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	    mat.put(0, 0, data);
	    return mat;
	}
	
	public BufferedImage ScreenCapture() {
		int size = Integer.parseInt(IniFile.screenSize);
		BufferedImage image = null;
		Rectangle rect = null;

		if (size==1920)
			rect = new Rectangle(800, 955, 50, 50);//1920x1080
		else if(size==2560)
			rect = new Rectangle(1070, 1270, 60, 60);//2560x1440
		else {
			System.err.println("Unavailable value : " + size);
			System.exit(0);
		}
		        
		try {
			//image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); ��üȭ�� ĸ��
			image = new Robot().createScreenCapture(rect);
			//ImageIO.write(image, "png", new File("Sample.png"));//�̹��� ������ �� �ʿ���.
		} catch (Exception e) {e.printStackTrace();}
      
        return image;
	}
	
}