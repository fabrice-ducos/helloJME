# helloJME
a standalone jMonkeyEngine hello world App

This is a simple starter application that does not depend on the great but heavyweight jMonkeyEngine SDK.
It can be used for quick demonstration purposes without the need for the SDK, or for portability and context testing of the JME (it was developed for this purpose initially, after the author met some issues with testing a JME-based application on MacOSX).

It does almost nothing (just rendering a blue cube like in the [JME classic tutorial](https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_simpleapplication.html)).

You will just need `maven` (and optionally `make`) to build and run it (they can now also be made available on Windows thanks to the [chocolatey](https://chocolatey.org/) package manager).

An advice for Maven users on the Windows command line: do not install your JDK under `C:\Program Files` (or any path containing spaces). From the author's experience, even recent versions of Maven may have trouble with paths containing spaces.

For building the project:
`mvn compile` or `make comp` or `make compile`

For packaging the project (building a jar file):
`mvn package` or `make`

For launching the project:
`mvn exec:java@run` or `make run` (this will build the project if it was not done first)

For launching the project within an explicit context:
`mvn exec:java@run -Djme-context=display # valid contexts include: display (default), canvas, headless, offscreen`

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
- MacOSX 11.5.2 Big Sur with AdoptOpenJDK 11.0.4: failure at runtime (not build): 
  dependencies (openal and lwjgl) retrieved by Maven are not signed and OSX will refuse to load them
  ```
  code signature not valid ... mapped file has no cdhash, completely unsigned? Code has to be at least ad-hoc signed
  ```
  This is actually an issue caused by AdoptOpenJDK 11.0.4 (see [Issue #1211](https://github.com/adoptium/temurin-build/issues/1211) in the [temurin-build](https://github.com/adoptium/temurin-build) repo).
    
- the canvas context fails with lwjgl3 (on all systems). This has been a known issue of the JME with lwjgl3 for years (https://github.com/jMonkeyEngine/jmonkeyengine/issues/1192)
  Replace `jme3-lwjgl3` by `jme3-lwjgl` in the dependencies of the `pom.xml` file for testing with canvas.
