package seed
println "Running User seed"
seed = {

	def dt = new Date()
	def createdBy = 'seedMe'
	skelUser(meta: [key: 'username'],
			username: 'argoden', password: '123456',
			firstName: 'Automatic', lastName: 'Process',
			email: 'tester@img.com',
			active: true, isLoginEnabled: true,
			createdBy: createdBy, updatedBy: createdBy,
			dateCreated: dt
	)

}
