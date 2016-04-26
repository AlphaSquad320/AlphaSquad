# Makefile for AlphaSquad database project
# for CSCI-320-01 with Scott Johnson.
#
# by Tommy Bohde
#
# last update: 041016 3:20 pm

CLASSPATH = .:bin/
BUILDDIR  = bin/

JAVA = java -cp $(CLASSPATH)
JAVAC = javac -d $(BUILDDIR) -cp $(CLASSPATH)

#default target
all: setup objects tables gui

#ensures existence of bin dir - not intented to be called directly
setup:
	mkdir -p $(BUILDDIR)

#use command 'make objects' to compile only the objects package code
objects:
	$(JAVAC) src/as/project/objects/*.java

#use command 'make tables' to compile only the tables package code
tables:
	$(JAVAC) src/as/project/tables/*.java

#use command 'make gui' to compile only the GUI package code
gui:
	$(JAVAC) src/as/project/gui/*.java

#use command 'make run' to execute
#recompiles all classes to ensure JVM compatibility
run: all	
	$(JAVA) as.project.gui.MainGUI

#use command 'make clean' to remove precompiled code
clean:
	rm -r -f $(BUILDDIR) 
