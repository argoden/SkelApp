seed = {
    dependsOn(['userSeed'])
    def owner = [domainClass: 'skelUser', username: 'argoden']
    def dt = new Date()
    userToken(meta: [key: 'token'],
            token: '160824010520170621103356',
            owner: owner,
            dateCreated: dt
    )

}