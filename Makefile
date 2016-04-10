# Makefile for AlphaSquad database project
# for CSCI-320-01 with Scott Johnson.
#
# by Tommy Bohde
#
# last update: 041016 3:20 pm

#default target
all: gui

#use command 'make gui' to compile only the GUI package code
gui:
	mkdir -p bin
	javac -d bin/ src/as/project/gui/*.java

#use command 'make run' to execute
#recompiles all classes to ensure JVM compatibility
run: all	
	java -cp .:bin/ as.project.gui.MainGUI

#use command 'make clean' to remove precompiled code
clean:
	rm bin/as/project/gui/*.class
