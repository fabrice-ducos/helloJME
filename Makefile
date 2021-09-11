include detect_os.mk
tld=org
domainBase=mydomain
artifactId=myproject
jarfile=target/$(artifactId)-$(version).jar

JAVA=java

MVN=mvn

#for some reason to be clarified, MAVEN_OPTS has no effect with exec:java@run
#export MAVEN_OPTS=-XstartOnFirstThread
#
# one therefore uses another goal for launching on osx systems

ifeq ($(detected_OS),Darwin)
MAIN_RUN_EXEC_GOAL=exec:exec@run-osx
RESIZ_EXEC_GOAL=exec:exec@resizable-osx
else
MAIN_RUN_EXEC_GOAL=exec:java@run
RESIZ_EXEC_GOAL=exec:java@resizable
endif

.PHONY: test run start
.PHONY: clean clean-m2

all: $(jarfile)

$(jarfile): src/main/java/$(tld)/$(domainBase)/$(artifactId)/*.java
	$(MVN) package

comp compile:
	$(MVN) compile

run start: $(jarfile)
	$(MVN) $(MAIN_RUN_EXEC_GOAL)

run-resizable run-resiz resizable resiz: $(jarfile)
	$(MVN) $(RESIZ_EXEC_GOAL)

test: $(jarfile)
	$(MVN) test

clean:
	mvn clean

clean-m2:
	-rm -rf ~/.m2
