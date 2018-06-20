seed = {
    def category = 'makefile'
/*
codeTemplate(meta: [key: 'name'],
	name: 'templateName',
	template:'''

	\n''',
	category: category,
	active: true
)
*/

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-php',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
INSTALLDIR=\\$(BUILDROOT)/www/office

JS_TARGET =\\$(INSTALLDIR)/jscript
CSS_TARGET=\\$(INSTALLDIR)/css
PHP_TARGET=\\$(INSTALLDIR)/APPNAME

CSS     = APPNAME.css
JSCRIPT = APPNAME.js
PHP     = index.php            


all: \\$(JSCRIPT) \\$(CSS) \\$(PHP)
install: \\$(JSCRIPT) \\$(CSS) \\$(PHP)

\\$(JSCRIPT): dummy
\tmkdir -p \\$(JS_TARGET)
\tcp \\$@ \\$(JS_TARGET)
\tppp \\$(JS_TARGET)/\\$@
\tchmod 777 \\$(JS_TARGET)/\\$@

\\$(CSS): dummy
\tmkdir -p \\$(CSS_TARGET)
\tcp \\$@ \\$(CSS_TARGET)
\tppp \\$(CSS_TARGET)/\\$@
\tchmod 777 \\$(CSS_TARGET)/\\$@

\\$(PHP): dummy
\tmkdir -p \\$(PHP_TARGET)
\tcp \\$@ \\$(PHP_TARGET)
\tppp \\$(PHP_TARGET)/\\$@
\tchmod 777 \\$(PHP_TARGET)/\\$@

clean:
\tfor i in \\$(CSS); do \\
\t(echo rm -f \\$(CSS_TARGET)/\\$\\$i); \\
\t(rm -f \\$(CSS_TARGET)/\\$\\$i); \\
\tdone;
\tfor i in \\$(JSCRIPT); do \\
\t(echo rm -f \\$(JS_TARGET)/\\$\\$i); \\
\t(rm -f \\$(JS_TARGET)/\\$\\$i); \\
\tdone;
\tfor i in \\$(PHP); do \\
\t(echo rm -f \\$(PHP_TARGET)/\\$\\$i); \\
\t(rm -f \\$(PHP_TARGET)/\\$\\$i); \\
\tdone;

clobber: clean

dummy:
install:
	\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-c',
            template: '''
#HEADER

BUILDROOT=\\$(HOME)
INSTALL_DIR=\\$(BUILDROOT)/bin 

INC= ../include
LIB= ../libs
COPY= cp

CFLAGS=  -Wreturn-type -Wunused -Wswitch -Wcomment -O3 -I\\$(INC) 
CC = c++  -DDEBUG_LOG -c
STRIP=strip
LINKER = c++ 

#EXECUTABLES= multiIO_server toms
#OBJECTS= multiIO_server.o toms.o

EXECUTABLES= toms 
OBJECTS= toms.o 


INSTALL_DIR=\\$(HOME)/bin

INCLUDES= 
USUALLIB= 


all: \\$(EXECUTABLES)


install: \\$(EXECUTABLES) 
\t\\$(COPY) \\$(EXECUTABLES)  \\$(INSTALL_DIR)


toms: \\$(OBJECTS)
\t\\$(LINKER) \\$(CFLAGS) -o \\$(@) toms.o \\$(USUALLIB)
\t\\$(STRIP) \\$(@)
\t\\$(COPY) \\$(@) \\$(INSTALL_DIR)


toms.o: toms.c \\$(INCLUDES)
\t\\$(CC) \\$(CFLAGS) \\$(*).c



clean:
\trm -f \\$(EXECUTABLES) \\$(OBJECTS) 


clobber: clean
\t( cd \\$(INSTALL_DIR); rm -f \\$(EXECUTABLES) )
	\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-daem',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
OBJ_DIR=\\$(BUILDROOT)/mod
UNIT_DIR=\\$(BUILDROOT)/tbin
PROG_DIR=\\$(BUILDROOT)/bin

OBJS= OBJ.php

UNITS= unit_01.php    \
       unit_02.php    \

PROGS=  daemon.php  \

all: \\$(OBJS) \\$(UNITS)
install: \\$(OBJS) \\$(UNITS)

\\$(OBJS): dummy
	mkdir -p \\$(OBJ_DIR)
	cp \\$@ \\$(OBJ_DIR)
	ppp \\$(OBJ_DIR)/\\$@
	chmod 775 \\$(OBJ_DIR)/\\$@

\\$(UNITS): dummy
	mkdir -p \\$(UNIT_DIR)
	cp \\$@ \\$(UNIT_DIR)
	ppp \\$(UNIT_DIR)/\\$@
	chmod 775 \\$(UNIT_DIR)/\\$@

\\$(DAEMON): dummy
	mkdir -p \\$(PROG_DIR)
	cp \\$@ \\$(PROG_DIR)
	ppp \\$(PROG_DIR)/\\$@
	chmod 775 \\$(PROG_DIR)/\\$@

clean:
dummy:
install:
clobber:
	for i in \\$(OBJS); do \
	(echo rm -f \\$(OBJ_DIR)/\\$\\$i); \
	(rm -f \\$(OBJ_DIR)/\\$\\$i); \
	done;
	for i in \\$(UNITS); do \
	(echo rm -f \\$(UNIT_DIR)/\\$\\$i); \
	(rm -f \\$(UNIT_DIR)/\\$\\$i); \
	done;
	for i in \\$(PROGS); do \
	(echo rm -f \\$(PROG_DIR)/\\$\\$i); \
	(rm -f \\$(PROG_DIR)/\\$\\$i); \
	done;
	\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-dir',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
MODULES := \\${shell ls -d */}

all: \\$(MODULES)
install: \\$(MODULES)

\\$(MODULES): dummy
	cd \\$@; make BUILDROOT=\\$(BUILDROOT)

clean clobber:
	for i in \\$(MODULES); do \
	(cd \\$\\$i; make clobber BUILDROOT=\\$(BUILDROOT) \\$@); \
	done

dummy: 
install:
	\n''',
            category: category,
            active: true
    )


    codeTemplate(meta: [key: 'name'],
            name: 'makefile-dir-file',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
TARGET_DIR=\\$(BUILDROOT)/www/example

DIRS := \\${shell ls -d */}

PROGS= unit_01.php    \
       unit_02.php    \

all: \\$(PROGS) \\$(DIRS) 
install: \\$(PROGS) \\$(DIRS)

\\$(PROGS): dummy
	mkdir -p \\$(TARGET_DIR)
	cp \\$@ \\$(TARGET_DIR)
	ppp \\$(TARGET_DIR)/\\$@
	chmod 775 \\$(TARGET_DIR)/\\$@

\\$(DIRS): dummy
	cd \\$@; make BUILDROOT=\\$(BUILDROOT)

clean:
dummy:
install:

clobber:
	for i in \\$(PROGS); do \
	(echo rm -f \\$(TARGET_DIR)/\\$\\$i); \
	(rm -f \\$(TARGET_DIR)/\\$\\$i); \
	done;
	for i in \\$(DIRS); do \
        (echo rm -f \\$(TARGET_DIR)/\\$\\$i); \
        (rm -f \\$(TARGET_DIR)/\\$\\$i); \
        done;

	\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-nothing-to-do',
            template: '''
#HEADER
all:  
	echo 'Nothing to do'

clobber:
	echo 'Nothing to do'
	\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-simple',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
TARGET_DIR=\\$(BUILDROOT)/example

PROGS= unit_01.php    \
       unit_02.php    \

all: \\$(PROGS) 
install: \\$(PROGS)

\\$(PROGS): dummy
	mkdir -p \\$(TARGET_DIR)
	cp \\$@ \\$(TARGET_DIR)
	ppp \\$(TARGET_DIR)/\\$@
	chmod 755 \\$(TARGET_DIR)/\\$@
clean:
dummy:
install:

clobber:
	for i in \\$(PROGS); do \
	(echo rm -f \\$(TARGET_DIR)/\\$\\$i); \
	(rm -f \\$(TARGET_DIR)/\\$\\$i); \
	done;
	\n''',
            category: category,
            active: true
    )

}
