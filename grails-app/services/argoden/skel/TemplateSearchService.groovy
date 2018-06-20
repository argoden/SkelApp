package argoden.skel

class TemplateSearchService {

    static transactional = false

    def loadSingleTemplate(owner, templateName) {
        if (owner && templateName) {
            def rtn = loadTemplateByOwner(owner, templateName)
            if (!rtn) {
                rtn = loadSharedTemplate(templateName)
            }
            return rtn ?: "Error: template not found\n"
        }
    }

    def loadSharedTemplate(templateName) {
        def tmp = CodeTemplate.findByOwnerAndNameAndActive(null, templateName, true)
        return tmp?.template
    }

    def loadTemplateByOwner(owner, templateName) {
        def tmp = CodeTemplate.findByOwnerAndNameAndActive(owner, templateName, true)
        return tmp?.template
    }

    def listAllTemplatesWithDetails(owner) {
        def sharedTemplates = CodeTemplate.findAllByOwner(null)
        def templates = CodeTemplate.findAllByOwner(owner)
        templates += sharedTemplates
        def rtn = ''
        templates.each { template ->
            rtn += "${template.category}\t${template.name}\n"
        }
        return rtn
    }

    def listAllTemplates(owner) {
        def sharedTemplates = CodeTemplate.findAllByOwner(null)
        def templates = CodeTemplate.findAllByOwner(owner)
        templates += sharedTemplates
        def templateNames = templates.collect { it.name }
        def rtn = ''
        templateNames.each { name ->
            rtn += name + '\n'
        }
        return rtn
    }

    def listTemplatesByCategory(category, owner) {
        def sharedTemplates = CodeTemplate.findAllByCategoryAndOwner(category, null)
        def templates = CodeTemplate.findAllByCategoryAndOwner(category, owner)
        templates += sharedTemplates

        def templateNames = templates.collect { it.name }
        def rtn = ''
        templateNames.each { name ->
            rtn += name + '\n'
        }
        return rtn
    }

}
