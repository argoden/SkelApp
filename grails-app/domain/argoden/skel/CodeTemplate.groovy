package argoden.skel

class CodeTemplate {

	SkelUser owner
	String template
	String name
	String category
	Boolean active = true

	static constraints = {
		owner(nullable:true)
		template(maxSize: 4000,  blank: false)
		category(nullable: true)
		name(unique:'owner', blank: false)
	}
}
