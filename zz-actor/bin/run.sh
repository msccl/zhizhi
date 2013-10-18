JAVA_HOME=/usr/local/java
LIB_HOME=/data/webserver/qradmin_quickpai_mobi/app/check/lib

CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$LIB_HOME/aopalliance-1.0.jar:$LIB_HOME/commons-collections-3.2.1.jar:$LIB_HOME/commons-fileupload-1.2.1.jar:$LIB_HOME/commons-io-1.3.2.jar:$LIB_HOME/commons-logging-1.0.4.jar:$LIB_HOME/log4j-1.2.14.jar:$LIB_HOME/mysql-connector-java-5.1.12-bin.jar:$LIB_HOME/commons-dbcp-1.4.jar:$LIB_HOME/commons-pool-1.5.4.jar:$LIB_HOME/log4jdbc4-1.2.1.jar:$LIB_HOME/slf4j-api-1.6.0.jar:$LIB_HOME/slf4j-log4j12-1.6.1.jar:$LIB_HOME/httpclient-4.0.1.jar:$LIB_HOME/httpcore-4.0.1.jar:$LIB_HOME/httpcore-nio-4.0.1.jar:$LIB_HOME/httpmime-4.0.1.jar:$LIB_HOME/org.springframework.aop-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.asm-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.beans-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.context.support-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.context-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.core-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.expression-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.jdbc-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.transaction-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.web.servlet-3.0.3.RELEASE.jar:$LIB_HOME/org.springframework.web-3.0.3.RELEASE.jar



/usr/local/java/bin/java -classpath $CLASSPATH:/data/webserver/qradmin_quickpai_mobi/app/check/bin com.mxuu.client.ClientSpider &
