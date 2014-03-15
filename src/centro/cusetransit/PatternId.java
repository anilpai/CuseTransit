//////////////////////////////////////////////////////////////////////////////
// PatternId.java - Show Routesof the Bus                                  //                                      
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

public class PatternId {

private ArrayList<String> pid = new ArrayList<String>();
	
	public void setPid(String extractedString) {
		this.pid.add(extractedString);
	}

	public ArrayList<String> getPid() {
		return pid;
	}
}
