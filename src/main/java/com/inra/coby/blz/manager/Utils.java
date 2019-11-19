
package com.inra.coby.blz.manager;

import java.io.File ;

/**
 *
 * @author ryahiaoui
 */

public class Utils {
    
    public static String getProperty(String property) {
        return System.getProperty(property) != null
               ? System.getProperty(property) : ""     ;
    }

    public static String removeLastSlash(String directoryPath )           {
        if ( ! directoryPath.isEmpty()            && 
               directoryPath.trim().endsWith(File.separator))             {
            return directoryPath.substring ( 0     ,
                                             directoryPath.length() - 1 ) ;
        }
        return directoryPath ;
    }
    
    public static String setDefault (String param , String defValue ) {
        if( param == null || param.trim().isEmpty()) {
            return defValue.trim() ;
        }
        return param.trim() ;
    }

}
