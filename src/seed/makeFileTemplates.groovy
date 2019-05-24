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
            name: 'makefile-dir',
            template: '''
BUILDROOT=\\$(HOME)
MODULES := \\${shell ls -d */}

all: \\$(MODULES)
install: \\$(MODULES)

\\$(MODULES): dummy
\tcd \\$@; make BUILDROOT=\\$(BUILDROOT)

clean clobber:
    for i in \\$(MODULES); do \\\n
    (cd \\$\\$i; make clobber BUILDROOT=\\$(BUILDROOT) \\$@); \\\n
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

PROGS= file_one file_two

all: \\$(PROGS) \\$(DIRS)
install: \\$(PROGS) \\$(DIRS)

\\$(PROGS): dummy
\tmkdir -p \\$(TARGET_DIR)
\tcp \\$@ \\$(TARGET_DIR)
\tppp \\$(TARGET_DIR)/\\$@
\tchmod 775 \\$(TARGET_DIR)/\\$@

\\$(DIRS): dummy
    cd \\$@; make BUILDROOT=\\$(BUILDROOT)

clean:
dummy:
install:

clobber:
\tfor i in \\$(PROGS); do \\\n
\t(echo rm -f \\$(TARGET_DIR)/\\$\\$i); \\\n
\t(rm -f \\$(TARGET_DIR)/\\$\\$i); \\\n
\tdone;
\tfor i in \\$(DIRS); do \\\n
\t\t(echo rm -f \\$(TARGET_DIR)/\\$\\$i); \\\n
\t\t(rm -f \\$(TARGET_DIR)/\\$\\$i); \\\n
\t\tdone;

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
\techo 'Nothing to do'
\t\n''',
            category: category,
            active: true
    )

    codeTemplate(meta: [key: 'name'],
            name: 'makefile-simple',
            template: '''
#HEADER
BUILDROOT=\\$(HOME)
TARGET_DIR=\\$(BUILDROOT)/example

PROGS= file_one file_two

all: \\$(PROGS) 
install: \\$(PROGS)

\\$(PROGS): dummy
\tmkdir -p \\$(TARGET_DIR)
\tcp \\$@ \\$(TARGET_DIR)
\tppp \\$(TARGET_DIR)/\\$@
\tchmod 755 \\$(TARGET_DIR)/\\$@
clean:
dummy:
install:

clobber:
\tfor i in \\$(PROGS); do \
\t(echo rm -f \\$(TARGET_DIR)/\\$\\$i); \
\t(rm -f \\$(TARGET_DIR)/\\$\\$i); \
\tdone;
\t\n''',
            category: category,
            active: true
    )

}
