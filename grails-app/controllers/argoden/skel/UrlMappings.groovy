package argoden.skel

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/auth/$username/"(controller:"auth", action: "index")
        "/auth/$username/$template"(controller:"auth", action: "loadTemplates")
    }
}
