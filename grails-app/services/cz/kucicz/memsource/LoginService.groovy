package cz.kucicz.memsource

import grails.converters.JSON
import grails.transaction.Transactional
import org.apache.juli.logging.LogFactory

import javax.security.auth.login.LoginException
import java.time.Clock
import java.time.Instant

@Transactional
class LoginService {
    private static final log = LogFactory.getLog(this);
    // will be moved to property file
    private static final String ACTION = 'api/v3/auth/login';
    private static final String URL = 'https://cloud.memsource.com/web/';

    /**
     * Get authorization token from memsource API
     * @param username username
     * @param password password
     * @return authorization token
     * @throws LoginException in case of wrong credentials
     * @throws IOException in case of unexpected response
     */
    def login(String username, String password) {
        log.debug("login [username=$username]")
        // check existing tokenJson
        Token token = Token.findByUsername(username)
        if (token != null) {
            // check stored token expiration
            long now = Instant.now(Clock.systemUTC()).toEpochMilli()

            if (JSONDateParser.parse(token.expires).getTime() > now) {
                log.debug("login [using old token]")
                // check token credentials
                if (token.username == username) {
                    return token.token;
                } else {
                    throw new LoginException("Invalid username or password")
                }
            }
            token.delete()
        }

        // call login
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", username);
        parameters.put("password", password);
        String responseJson = HttpUtil.sendPost(URL + ACTION, parameters);
        def tokenJson = JSON.parse(responseJson)
        if (tokenJson.errorCode != null) {
            log.warn("login [Login failed: ${responseJson}]")
            String errorCode = tokenJson.errorCode as String
            String errorDescription = tokenJson.errorDescription as String
            throw new LoginException(errorCode + ": " + errorDescription)
        }
        if (tokenJson.token == null || tokenJson.expires == null) {
            log.warn("login [Invalid response: ${responseJson}]")
            throw new IOException("Unexpected login response")
        }
        Token newToken = new Token(username: username, token: tokenJson.token, expires: tokenJson.expires)
        log.debug("login [token: ${newToken}]")
        // save new tokenJson
        newToken.save()
        return newToken.token
    }
}
