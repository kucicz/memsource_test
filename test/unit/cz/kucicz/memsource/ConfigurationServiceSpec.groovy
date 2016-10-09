package cz.kucicz.memsource

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ConfigurationService)
@Mock(Configuration)
class ConfigurationServiceSpec extends Specification {

    def setup() {
        new Configuration(username: "username", password: "password").save()
    }

    def cleanup() {
    }

    def "test getConfiguration"() {
        when:
        def configuration = service.getConfiguration()
        then:
        configuration.password == "password"
        configuration.username == "username"
    }

    def "test saveConfiguration"() {
        when:
        service.saveConfiguration("username", "password1")
        then:
        service.getConfiguration().password == "password1"
    }
}
