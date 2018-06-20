package argoden.skel

import argoden.encrypt.EncryptionUtility

import java.security.SecureRandom
import java.text.SimpleDateFormat


class AuthService {

    static transactional = false

    def random = new SecureRandom()
    def tokenFormat = new SimpleDateFormat("yyyyMMddHHmmss")

    def authenticateUser(username, password) {
        def user = getActiveUser(username)
        if (EncryptionUtility.decryptText(user?.password) == password) {
            return user
        }
        return false
    }

    def getActiveUser(username) {
        SkelUser.findByUsernameAndActive(username, true)
    }

    def generateToken() {
        random.nextInt() + tokenFormat.format(new Date())
    }

    def getUserToken(skelUser) {
        def tokens = UserToken.findAllByOwner(skelUser, [order: 'desc', sort: 'id'])
        def token
        if (tokens) {
            token = tokens.first()
        } else {
            token = new UserToken(owner: skelUser, token: generateToken(), lastUsed: new Date())
            token.save()
        }
        return token
    }

    // todo: why not validate by instance comparison?
    private validateUserByToken(username, tokenStr) {
        if (tokenStr && tokenStr.size() == 24) {
            def token = UserToken.findByToken(tokenStr)
            if (token?.owner?.username == username) {
                return token
            }
        }
    }
}
