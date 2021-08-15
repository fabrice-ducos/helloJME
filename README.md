# helloJME
a standalone JMonkeyEngine hello world App

This is a simple starter application that does not depend on the great but heavyweight JMonkeyEngine SDK.
It can be used for quick demonstration purposes without the need for the SDK, or for portability testing of the JME (it was developed for this purpose initially, after the author met some issues with testing a JME-based application on MacOSX).

You will just need `maven` (and optionally `make`) to build and run it (they can now also be made available on Windows thanks to the [chocolatey](https://chocolatey.org/) package manager)

For building the project:
`mvn build` or `make comp` or `make compile`

For packaging the project (building a jar file):
`mvn package` or `make`

For launching the project:
`mvn exec:java@run` or `make run`

For launching tests:
`mvn test` or `make test`

For cleaning:
`mvn clean` or `make clean`

## Structure of the project (kept intentionnally as simple as possible):
```
.
├── Makefile
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── org
    │           └── mydomain
    │               └── myproject
    │                   └── JMonkeyApp.java
    └── test
        └── java
            └── org
                └── mydomain
                    └── myproject
                        └── JMonkeyAppTest.java
```
