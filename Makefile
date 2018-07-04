KOTLINC=kotlinc/bin/kotlinc
CLASSPATH=lib/junit-4.12.jar:lib/hamcrest-all-1.3.jar:lib/http-20070405.jar

all: kotlin-install lib target test-compile 

run: main-run

test: test-run

kotlin-install: kotlin-compiler-1.2.51.zip kotlinc

kotlin-compiler-1.2.51.zip:
	wget https://github.com/JetBrains/kotlin/releases/download/v1.2.51/kotlin-compiler-1.2.51.zip

kotlinc:
	unzip kotlin-compiler-1.2.51.zip

target:
	mkdir target

lib:
	mkdir lib
	wget http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar
	mv junit-4.12.jar lib/junit-4.12.jar
	wget http://central.maven.org/maven2/org/hamcrest/hamcrest-all/1.3/hamcrest-all-1.3.jar
	mv hamcrest-all-1.3.jar lib/hamcrest-all-1.3.jar
	wget http://central.maven.org/maven2/com/sun/net/httpserver/http/20070405/http-20070405.jar
	mv http-20070405.jar lib/http-20070405.jar


test-compile:
	${KOTLINC} -include-runtime -classpath "${CLASSPATH}" src/http-server.kt -d target/http-server.jar
	${KOTLINC} -include-runtime -classpath "${CLASSPATH}:target/http-server.jar" test/http-server-test.kt -d target/http-server-test.jar 

test-run:
	java -classpath "${CLASSPATH}:target/http-server-test.jar:target/http-server.jar" org.junit.runner.JUnitCore brymlee.Tests

main-run:
	java -classpath "${CLASSPATH}:target/http-server.jar" brymlee.Http_serverKt

kotlinc-help:
	${KOTLINC} -help
