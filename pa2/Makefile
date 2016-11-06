#Another Makefile with macros

JAVASRC = Search.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Search.java
CLASSES = Search.class
JARFILE = Search

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

