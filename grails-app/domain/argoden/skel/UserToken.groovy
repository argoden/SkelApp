package argoden.skel

@SuppressWarnings('GrailsDuplicateConstraint')
class UserToken {
    SkelUser owner
    String token
    String tokenSecret
    Date lastUsed
    Date dateCreated

    static constraints = {
        token(blank: false)
        lastUsed(nullable: true)
        tokenSecret(nullable: true)
        token(unique: true)
    }

    static mapping = {
        cache true
    }

}
