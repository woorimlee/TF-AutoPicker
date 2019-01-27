import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CompareHistogram {
	
	public void run(Mat BaseImg, String card) {  
		String size = IniFile.screenSize;
        //! [Load three images with different environment settings]
        Mat srcBase = BaseImg;
        Mat srcBlue = Imgcodecs.imread(".\\img\\Blue"+size+".png");
        Mat srcRed = Imgcodecs.imread(".\\img\\Red"+size+".png");
        Mat srcGold = Imgcodecs.imread(".\\img\\Gold"+size+".png");
        Mat srcDefault = Imgcodecs.imread(".\\img\\Default"+size+".png");
        Mat srcCool = Imgcodecs.imread(".\\img\\Cool"+size+".png");

        if (srcBase.empty() || srcBlue.empty() || srcRed.empty() || srcGold.empty() || srcDefault.empty() || srcCool.empty()) {
            System.err.println("Cannot read the images");
            System.exit(0);
        }

        //! [Convert to HSV]
        Mat hsvBase = new Mat(), hsvBlue = new Mat(), hsvRed = new Mat(), hsvGold = new Mat(), 
        	hsvDefault = new Mat(), hsvCool = new Mat();
        
        Imgproc.cvtColor( srcBase, hsvBase, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor( srcBlue, hsvBlue, Imgproc.COLOR_BGR2HSV );
        Imgproc.cvtColor( srcRed, hsvRed, Imgproc.COLOR_BGR2HSV );
        Imgproc.cvtColor( srcGold, hsvGold, Imgproc.COLOR_BGR2HSV );
        Imgproc.cvtColor( srcDefault, hsvDefault, Imgproc.COLOR_BGR2HSV );
        Imgproc.cvtColor( srcCool, hsvCool, Imgproc.COLOR_BGR2HSV );
        //! [Convert to HSV]


        //! [Using 50 bins for hue and 60 for saturation]
        int hBins = 50, sBins = 60;
        int[] histSize = { hBins, sBins };

        // hue varies from 0 to 179, saturation from 0 to 255
        float[] ranges = { 0, 180, 0, 256 };

        // Use the 0-th and 1-st channels
        int[] channels = { 0, 1 };
        //! [Using 50 bins for hue and 60 for saturation]

        //! [Calculate the histograms for the HSV images]
		Mat histBase = new Mat(), histBlue = new Mat(), histRed = new Mat(), histGold = new Mat(), histDefault = new Mat(), histCool = new Mat();

        List<Mat> hsvBaseList = Arrays.asList(hsvBase);
        Imgproc.calcHist(hsvBaseList, new MatOfInt(channels), new Mat(), histBase, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histBase, histBase, 0, 1, Core.NORM_MINMAX);

        List<Mat> hsvGoldList = Arrays.asList(hsvGold);
        Imgproc.calcHist(hsvGoldList, new MatOfInt(channels), new Mat(), histGold, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histGold, histGold, 0, 1, Core.NORM_MINMAX);

        List<Mat> hsvBlueList = Arrays.asList(hsvBlue);
        Imgproc.calcHist(hsvBlueList, new MatOfInt(channels), new Mat(), histBlue, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histBlue, histBlue, 0, 1, Core.NORM_MINMAX);

        List<Mat> hsvRedList = Arrays.asList(hsvRed);
        Imgproc.calcHist(hsvRedList, new MatOfInt(channels), new Mat(), histRed, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histRed, histRed, 0, 1, Core.NORM_MINMAX);
        
        List<Mat> hsvDefaultList = Arrays.asList(hsvDefault);
        Imgproc.calcHist(hsvDefaultList, new MatOfInt(channels), new Mat(), histDefault, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histDefault, histDefault, 0, 1, Core.NORM_MINMAX);
        
        List<Mat> hsvCoolList = Arrays.asList(hsvCool);
        Imgproc.calcHist(hsvCoolList, new MatOfInt(channels), new Mat(), histCool, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histCool, histCool, 0, 1, Core.NORM_MINMAX);
        
        //! [Calculate the histograms for the HSV images]

        // Apply the histogram comparison methods  
        // 0 - correlation: the higher the metric, faster
        // 1 - chi-square: the lower the metric	: slow
        // 2 - intersection: the higher the metric : fastest
        // 3 - bhattacharyya: the lower the metric : fast

            double baseBase = Imgproc.compareHist( histBase, histBase, 2 );
            double baseGold = Imgproc.compareHist( histBase, histGold, 2 );
            double baseBlue = Imgproc.compareHist( histBase, histBlue, 2 );
            double baseRed = Imgproc.compareHist( histBase, histRed, 2 );
            double baseDefault = Imgproc.compareHist( histBase, histDefault, 2 );
            double baseCool = Imgproc.compareHist( histBase, histCool, 2 );
            double Biggest=0; String flag=null;
            
            if(baseGold>Biggest) { 
            	Biggest = baseGold;
            	flag="Gold";
            }
            if(baseBlue>Biggest) { 
            	Biggest = baseBlue;
            	flag="Blue";
            }
            if(baseRed>Biggest) { 
            	Biggest = baseRed;
            	flag="Red";
            }
            if(baseDefault>Biggest) { 
            	Biggest = baseRed;
            	flag="Default";
            }
            if(baseCool>Biggest) { 
            	Biggest = baseRed;
            	flag="Cool";
            }
            
            System.out.println("Base ↔ Base : " + baseBase
                    + "\nBase ↔ Gold : " + baseGold
                    + "\nBase ↔ Blue : " + baseBlue
                    + "\nBase ↔ Red : " + baseRed);
            System.out.println("Card State: " + flag);
            
            checkAndPress(flag,card);
    }
	
	public void checkAndPress(String flag, String card) {
		Robot rbt;
		if (flag==card) {		
			try {
				rbt = new Robot();
				rbt.keyRelease(KeyEvent.VK_CONTROL);
				//Control키를 누르고 있을시 W가 안눌리는 현상을 해결하기위해 Control키를 Release
				rbt.keyPress(KeyEvent.VK_W);
	            rbt.keyRelease(KeyEvent.VK_W);
			} catch (AWTException e) {e.printStackTrace();}
		}
	}
	
}
