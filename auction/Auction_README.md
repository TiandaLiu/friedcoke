Compile:
export CLASSPATH=/extensions/gson-2.8.6.jar:/extensions/postgresql-42.2.8.jar:.

javac /src/pro/src/main/java/com/friedcoke/rmi/*.java /src/pro/src/main/java/com/friedcoke/utils/*.java /src/pro/src/main/java/com/friedcoke/state/*.java

Server:
java /src/pro/src/main/java/com/friedcoke/AuctionRMIServer
