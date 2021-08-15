# helloJME
a standalone JMonkeyEngine hello world App

This is a simple starter application that does not depend on the great but heavyweight JMonkeyEngine SDK.
It can be used for quick demonstration purposes without the need for the SDK, or for portability testing of the JME (it was developed for this purpose initially, after the author met some issues with testing a JME-based application on MacOSX).

You will just need `maven` (and optionally `make`) to build and run it (they can now also be made available on Windows thanks to the [chocolatey](https://chocolatey.org/) package manager).

An advice for Maven users on the Windows command line: do not install your JDK under `C:\Program Files` (or any path containing spaces). From the author's experience, even recent versions of Maven may have trouble with paths containing spaces.

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

The POM file refers to LWJGL3. If one wants to use LWJGL2, one just needs to replace lwjgl3 with lwjgl in the dependency section for jme3-lwjgl.

## Known successful builds and execution:
- Windows 10 with AdoptOpenJDK 11.0.12 (Eclipse Temurin)
- Linux Mint 19 Cinnamon with AdoptOpenJDK 11.0.12

## Known failures:
- Linux Ubuntu 20.04 image on Windows 10 (WSL2) with AdoptOpenJDK 11.0.6 and VCXSRV X-server (Xming doesn't support GLX 1.3+ anyway): failure at runtime (not build)
  ```
  libGL error: No matching fbConfigs or visuals found
  libGL error: failed to load driver: swrast
  ```
- MacOSX 11.4 Big Sur with AdoptOpenJDK 11.0.4: failure at runtime (not build): 
  dependencies (openal and lwjgl) retrieved by Maven are not signed and OSX will refuse to load them
  ```
  code signature not valid ... mapped file has no cdhash, completely unsigned? Code has to be at least ad-hoc signed
  ```
