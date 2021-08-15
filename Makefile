tld=org
domainBase=mydomain
artifactId=myproject
jarfile=target/$(artifactId)-$(version).jar

JAVA=java
#MAVEN_FLAGS=-X
MAVEN_FLAGS=
MVN=mvn $(MAVEN_FLAGS)

.PHONY: test run clean

all: $(jarfile)

$(jarfile): src/main/java/$(tld)/$(domainBase)/$(artifactId)/*.java
	$(MVN) package

comp compile:
	$(MVN) compile

run: $(jarfile)
	$(MVN) exec:java@run

test: $(jarfile)
	$(MVN) test

clean:
	mvn clean
