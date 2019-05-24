package argoden.skel

import argoden.encrypt.EncryptionUtility

@SuppressWarnings('CatchException')
class SkelUser {

    static transients = ['newPassword', 'passwordChanged', 'rolesFlattened']

    String username
    String firstName
    String lastName
    String email
    String password
    String newPassword
    Boolean active = true
    Boolean isAdmin = false
    Date lastLogin
    Boolean isLoginEnabled = true
    Date dateCreated
    Date lastUpdated
    String createdBy = 'system'
    String updatedBy = 'system'
    String lang = 'en'

    static constraints = {
        username(unique: true)
        firstName(nullable: true)
        lastName(blank: false)
        lastLogin(nullable: true)
        lastUpdated(nullable: true)
        lang(maxSize: 3, nullable: true)
    }

    static mapping = {
        cache true
        active index: 'idxUserActive'
        email index: 'idxUserEmail'
    }

    String toString() {
       "$username $lastName, $firstName"
    }

    def beforeUpdate() {
        lastUpdated = new Date()
        try {
            EncryptionUtility.decryptText(password)
        } catch (Exception e) {
            password = EncryptionUtility.encryptText(password)
        }
    }

    def beforeInsert() {
        dateCreated = new Date()
        lastUpdated = new Date()
        try {
            EncryptionUtility.decryptText(password)
        } catch (Exception e) {
            password = EncryptionUtility.encryptText(password)
        }
    }
}
