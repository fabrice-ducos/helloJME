tld=org
domainBase=mydomain
artifactId=myproject
jarfile=target/$(artifactId)-$(version).jar

JAVA=java

MVN=mvn

.PHONY: test run start
.PHONY: test-osx run-osx start-osx
.PHONY: clean clean-m2

all: $(jarfile)

$(jarfile): src/main/java/$(tld)/$(domainBase)/$(artifactId)/*.java
	$(MVN) package

comp compile:
	$(MVN) compile

# there are some shortcomings with exec:java, in some cases exec:exec may be better;
# see: https://stackoverflow.com/questions/15013651/using-maven-execexec-with-arguments
run start: $(jarfile)
	$(MVN) exec:java@run

# for some reason, MAVEN_OPTS has no effect with exec:java@run
#export MAVEN_OPTS=-XstartOnFirstThread
#
# one therefore uses another goal for launching on osx systems

run-osx start-osx:
	$(MVN) exec:exec@run-osx

test: $(jarfile)
	$(MVN) test

clean:
	mvn clean

clean-m2:
	-rm -rf ~/.m2
