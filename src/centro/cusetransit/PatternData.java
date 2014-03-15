//////////////////////////////////////////////////////////////////////////////
// PatternData.java - Show Routesof the Bus                                  //                                      
// ver 1.0                                                                  //
// Language:    JAVA, Android SDK                                           //
// Platform:    Dell Inspiron N5010, Win7                                   //
// Application: CuseTransit,Independent Study, Fall 2013                    // 
// Author:      Sundar Lakshmanan, 751818942, Syracuse University           //
//              (315) 744-7468, slaksh02@syr.edu                            //
//////////////////////////////////////////////////////////////////////////////
/*   
 * Maintenance History:
 * --------------------
 * ver 1.0 : 12 Dec 2012
 * - first release
 */
package centro.cusetransit;

import java.util.ArrayList;

public class PatternData {
	private ArrayList<Double> LatList = new ArrayList<Double>();
	private ArrayList<Double> LonList = new ArrayList<Double>();
	
	public ArrayList<Double> getLatList() {
		return LatList;
	}
	public void setLatList(Double extractedString) {
		this.LatList.add(extractedString);
	}
	
	public ArrayList<Double> getLonList() {
		return LonList;
	}
	public void setLonList(Double extractedString) {
		this.LonList.add(extractedString);
	}
	private ArrayList<String> rt = new ArrayList<String>();
	private ArrayList<String> rtName = new ArrayList<String>();
	
	

	public void setrt(String extractedString) {
		this.rt.add(extractedString);
	}

	public ArrayList<String> getrt() {
		return rt;
	}
	
	public void setrtName(String extractedString) {
		this.rtName.add(extractedString);
	}

	public ArrayList<String> getrtName() {
		return rtName;
	}
}
