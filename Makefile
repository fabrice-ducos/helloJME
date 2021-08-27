tld=org
domainBase=mydomain
artifactId=myproject
jarfile=target/$(artifactId)-$(version).jar

JAVA=java

# this is required on MacOSX
MAVEN_OPTS=-XstartOnFirstThread

MVN=mvn

.PHONY: test run clean

all: $(jarfile)

$(jarfile): src/main/java/$(tld)/$(domainBase)/$(artifactId)/*.java
	$(MVN) package

comp compile:
	$(MVN) compile

# there are some shortcomings with exec:java, in some cases exec:exec may be better;
# see: https://stackoverflow.com/questions/15013651/using-maven-execexec-with-arguments
run: $(jarfile)
	$(MVN) exec:java@run

test: $(jarfile)
	$(MVN) test

clean:
	mvn clean
