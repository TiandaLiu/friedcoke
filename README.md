# friedcoke

## Team Member & Distribution
Chaoqin Li <chaoqinli@uchicago.edu> - User System\
Tianda Liu <tliu77@uchicago.edu> - Web Server, Metadata\
Sihan Li <samli@uchicago.edu> - Notification System\
Sunan Xiang <xsunan@uchicago.edu> - Auction System

## How to test with metadata and webserver
Open a new container:

    docker run -dit -p 8080:8080 --name TestServer -v <path to friedcoke>:/friedcoke ubuntu:18.04

Update container:

    apt-get update
    apt-get install -y vim
    apt-get install -y openjdk-8-jdk
    apt-get install -y net-tools
    apt-get install -y openssh-client
    apt-get install -y unzip

Run Metadata:

open a new terminal, run:

    docker exec -it TestServer /bin/bash

in the container: 

    java -jar /friedcoke/webserver/target/webserver-0.0.1-SNAPSHOT.jar

Run Wenserver:

open a new terminal, run 

    docker exec -it TestServer /bin/bash

in the container:

    java -jar /friedcoke/friedcokemetadata/out/artifacts/friedcokemetadata_jar/friedcokemetadata.jar