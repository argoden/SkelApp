import argoden.skel.*

import groovy.io.FileType

def dir = new File("/tmp/templates/")
dir.eachFile(FileType.FILES) { fyle ->
	if (fyle.getName().endsWith(fileExtension) && !fyle.isHidden()) {
		def tmp = fyle.toString().tokenize("/")
		new CodeTemplate([
				template: fyle.getText(),
				name: tmp.last(),
				category: 'fileInsert',
		]).save()
	}
}




