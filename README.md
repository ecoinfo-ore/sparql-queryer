
## Sparql-Queryer

==

 Blazegraph Sparql Endpoint Queryer

==

```bash
   mvn clean install assembly:single
```

Example :

```bash

     java -DUrl=http://localhost:9999/blazegraph/                     \
          -DAccept=text/tab-separated-values                          \
          -DNamespace=soere                                           \
          -DIncludeInferred=true                                      \
          -DQuery=\"SELECT ?S ?P ?O WHERE { ?S ?P ?O . } LIMIT 1000\" \
          -jar sparql-queryer.jar   

```
