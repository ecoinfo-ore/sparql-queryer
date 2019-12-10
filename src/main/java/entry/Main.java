 package entry;

import java.io.File;
import java.io.PrintWriter;
import com.inra.coby.blz.manager.Http;
import static com.inra.coby.blz.manager.Utils.setDefault;
 import static com.inra.coby.blz.manager.Utils.getProperty;
 import static com.inra.coby.blz.manager.Utils.removeLastSlash;
import static com.inra.coby.blz.manager.Utils.removeWrappedSimpleOrDoubleQuotes;

 public class Main {
            
    public static void main(String[] args) throws Exception {
        
        if( System.getProperty("H") != null   || System.getProperty("Help") != null )                     {
           System.out.println("                                                                        ") ;
           System.out.println(" ################################################################       ") ;
           System.out.println(" ### SPARQL-QUERYER #  ##########################################       ") ;
           System.out.println("                                                                        ") ;
           System.out.println(" ----------------------------------------------------------------       ") ;
           System.out.println("                                                                        ") ;
           System.out.println(" Total Arguments  :  6                                                  ") ;
           System.out.println("  Query   : Sparql Query                                                ") ;
           System.out.println("  Accept          : Default text/tab-separated-values. Ex : text/rdf+n3 ") ;
           System.out.println("  Url             : EndPoint.                                           ") ;
           System.out.println("                    Default :  http://localhost:9999/blazegraph/        ") ;
           System.out.println("  Namespace       : Namespace                                           ") ;
           System.out.println("  Out             : File where output Sparql result Query               ") ;
           System.out.println("  IncludeInferred : IncludeInferred. Default : false                    ") ;
           System.out.println("                                                                        ") ;
           System.out.println(" ----------------------------------------------------------------       ") ;
           System.out.println("                                                                        ") ;
           System.out.println(" Command Ex :                                                           ") ;
           System.out.println("   java -DUrl=http://localhost:9999/blazegraph/                       \\") ;
           System.out.println("        -DNamespace=soere                                             \\") ;
           System.out.println("        -DAccept=text/tab-separated-values                            \\") ;
           System.out.println("        -DQuery=\"SELECT ?S ?P ?O WHERE { ?S ?P ?O . } LIMIT 1000\"     \\") ;
           System.out.println("        -IncludeInferred=true                                         \\") ;
           System.out.println("        -jar sparql-queryer.jar                                         ") ;
           System.out.println("                                                                        ") ;
           System.out.println(" ################################################################       ") ;
           System.out.println("                                                                        ") ;
           System.exit(0)    ;
        }
        
        String sparqlQuery  = setDefault( getProperty("Query"), "SELECT ?S ?P ?O WHERE { ?S ?P ?O . }" ) ;
        sparqlQuery         = removeWrappedSimpleOrDoubleQuotes(sparqlQuery)                             ;
        
        String accept       = setDefault( getProperty("Accept"), "text/tab-separated-values" )           ;
        
        String _url         = setDefault( getProperty("Url"), "http://localhost:9999/blazegraph/" )      ;
        String namespace    = setDefault( getProperty("Namespace"), "kb" )                               ;
        String outputFile   = getProperty("Out")                                                         ;
                
        boolean includeInferred =  ( getProperty("IncludeInferred") != null )                            ;
        
        String url = removeLastSlash(_url )                                                              ;
        
        if( ! namespace.trim().isEmpty()   && ! url.contains("/namespace/"))  {
              url += "/namespace/" + namespace + "/sparql"                    ;
        }
        
        PrintWriter out ; 

        if( outputFile != null && !outputFile.trim().isEmpty() ) {
          out = new PrintWriter(new File( outputFile)) ;
        } else {
          out = new PrintWriter(System.out)            ;
        }
        
        Http.invokeService( out, url, accept, sparqlQuery, includeInferred ) ;
    }
 }
