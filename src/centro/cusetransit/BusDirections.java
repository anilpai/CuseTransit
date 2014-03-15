
//////////////////////////////////////////////////////////////////////////////
// BusDirections.java - Show Routesof the Bus                                  //                                      
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

public class BusDirections {
	private ArrayList<String> dir = new ArrayList<String>();
	
	public void setDir(String extractedString) {
		this.dir.add(extractedString);
	}

	public ArrayList<String> getDir() {
		return dir;
	}
	
}
