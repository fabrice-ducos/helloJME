ifeq ($(OS),Windows_NT)
    detected_OS := Windows
    sep := \\
else
    detected_OS := $(shell sh -c 'uname 2> /dev/null || echo Unknown')
    sep := /
endif
