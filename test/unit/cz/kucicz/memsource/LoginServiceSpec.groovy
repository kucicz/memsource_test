package cz.kucicz.memsource

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(LoginService)
@Mock(Token)
class LoginServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test login with success"() {
        setup:
        GroovyMock(HttpUtil, global: true)
        //success
        HttpUtil.sendPost(_ as String, _ as Map) >> '{"user":{"deleted":false,"active":true,"userName":"kuci.cz","firstName":"Vaclav","id":165856,"lastName":"Kucera","role":"ADMIN","email":"kucicz@gmail.com"},"token":"0rxnRNXQ0BpPcebOYLeX1Fekmt1rRBVUGI1KXapaCZaq9dysv5aaicvy87Y0vpDBq","expires":"2016-10-10T13:44:31+0000"}'
        // fail
        when:
        def token = null;
        try {
            token = service.login("userName", "password");
        } catch (Exception e) {

        }
        then:
        token != null
    }

    void "test login with fail"() {
        setup:
        GroovyMock(HttpUtil, global: true)
        //success
        HttpUtil.sendPost(_ as String, _ as Map) >> '{"errorCode":"AuthInvalidCredentials","errorDescription":null}]'
        // fail
        when:
        def response = null;
        try {
            response = service.login("userName", "password");
        } catch (Exception e) {
            response = e.getMessage()
        }
        then:
        response == 'AuthInvalidCredentials: null'
    }
}
