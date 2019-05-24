package argoden.skel

class TemplateSearchService {

    static transactional = false

    def loadSingleTemplate(owner, templateName) {
        if (owner && templateName) {
            def rtn = loadTemplateByOwner(owner, templateName)
            rtn = rtn ?: this.loadSharedTemplate(templateName)
            rtn ?: 'Error: template not found\n'
        }
    }

    def loadSharedTemplate(templateName) {
        def tmp = CodeTemplate.findByOwnerAndNameAndActive(null, templateName, true)
        tmp?.template
    }

    def loadTemplateByOwner(owner, templateName) {
        def tmp = CodeTemplate.findByOwnerAndNameAndActive(owner, templateName, true)
        tmp?.template
    }

    def listAllTemplatesWithDetails(owner) {
        def sharedTemplates = CodeTemplate.findAllByOwner(null)
        def templates = CodeTemplate.findAllByOwner(owner)
        templates += sharedTemplates

        templates*.name.join(System.lineSeparator())
    }

    def listAllTemplates(owner) {
        def sharedTemplates = CodeTemplate.findAllByOwner(null)
        def templates = CodeTemplate.findAllByOwner(owner)
        templates += sharedTemplates

        templates*.name.join(System.lineSeparator())
    }

    def listTemplatesByCategory(category, owner) {
        def sharedTemplates = CodeTemplate.findAllByCategoryAndOwner(category, null)
        def templates = CodeTemplate.findAllByCategoryAndOwner(category, owner)
        templates += sharedTemplates

        templates*.name.join(System.lineSeparator())
    }
}
