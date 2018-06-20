package argoden.skel

class AuthController {

    def authService
    def templateSearchService

    def index() {
        // displays list of templates
        def username = params.username
        def tokenStr = params.t_
        def category = params.category
        def isVerbose = params.isVerbose

        def token  = authService.validateUserByToken(username, tokenStr)
        if (token){
            if (category){
                render templateSearchService.listTemplatesByCategory(category, token.owner)
            } else if (isVerbose){
                render templateSearchService.listAllTemplatesWithDetails(token.owner)
            }
            else {
                render templateSearchService.listAllTemplates(token.owner)
            }
        } else {
            render "Error happened"
        }
    }

    def loadTemplates() {
        def username     = params.username
        def templateName = params.template
        def tokenStr     =  params.t_

        def token  = authService.validateUserByToken(username, tokenStr)
        if (token) {
            render templateSearchService.loadSingleTemplate(token.owner, templateName)
        } else {
            render "Error happened"
        }
    }

}
