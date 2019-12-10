
package com.inra.coby.blz.manager;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer ;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/**
 *
 * @author ryahiaoui
 */

public class Http {
    
   private static final int BUFF_SIZE = 4096 ;
    
    public static void invokeService ( Writer out        ,
                                       String url        ,
                                       String accept     , 
                                       String query      ,
                                       boolean includeInferred ) throws Exception {
        
       
      HttpClient client = new HttpClient();

      PostMethod method = new PostMethod(url)         ;
      method.addRequestHeader("Accept", accept)       ;
      method.addParameter("query", query)             ;
      method.addParameter("includeInferred", Boolean.toString(includeInferred)) ;
     
      try {
            int statusCode = client.executeMethod(method) ;

            if (statusCode != HttpStatus.SC_OK) {
              System.err.println("Method failed : " + method.getStatusLine())   ;
            }

            InputStream responseBodyAsStream = method.getResponseBodyAsStream() ;

            String line ;
            BufferedReader bufferedReader = new BufferedReader( 
                    new InputStreamReader( responseBodyAsStream ), BUFF_SIZE )  ;

            while( ( line = bufferedReader.readLine()) != null ) { 
                out.write( line + System.lineSeparator() ) ;
                out.flush()                                ;
            }
            
            System.out.println(" Sparql Query Execution Finished " )            ;
            
      } catch (HttpException e) {
          System.err.println("Fatal protocol violation: " + e.getMessage())     ;
          e.printStackTrace()                                                   ;
      } finally {
          method.releaseConnection() ;
      }  
    }
}
