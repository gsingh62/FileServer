This web app is to be run by running gradle war to generate the WAR file under build/libs.

Then configuring the Intellij Run configuration for the tomcat server which will serve the war file.

Once the tomcat server is running you can test in the following ways

curl -i "http://localhost:8080/file-resource-server-1.0-SNAPSHOT/api/file/getFile?filePath=../readme.txt"

curl -i "http://localhost:8080/file-resource-server-1.0-SNAPSHOT/api/file/getFile?filePath=readme.txt"

The point of this experiment was to verify that caller could not escape from base directory in accessing files.
