# xserver-test-artemis
Testing the starting and stopping of an embedded artemis server using the xserver-maven-plugin within a maven test cycle 


## Usage

Checkout the project and run mvn verify.
~~~~~
mvn verify
~~~~~

## Steps

1. The plugin starts an embedded artemis server (from this project).
2. The test case is then run
3. The plugin stops the embedded artemis server 

[xserver-maven-plugin][https://github.com/gogit/xserver-maven-plugin]


##trace
~~~~~
Oct 01, 2016 5:07:48 PM org.apache.activemq.artemis.core.server.impl.LiveOnlyActivation run
INFO: AMQ221007: Server is now live
Oct 01, 2016 5:07:48 PM org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl start
INFO: AMQ221001: Apache ActiveMQ Artemis Message Broker version 1.3.0 [localhost, nodeID=9f568676-87ef-11e6-8085-0800278178c4] 
Starting message test
Sending message: Hello sent at Sat Oct 01 17:07:50 BST 2016
Received message:Hello sent at Sat Oct 01 17:07:50 BST 2016
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.17 sec - in uk.co.thinktag.embedded.EmbeddedServerTest

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
~~~~~
