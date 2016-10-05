package cz.kucicz.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Configuration)
class ConfigurationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test save"() {
        def configuration = new Configuration()
        configuration.setUsername("dummy")
        configuration.setPassword("secret")
        configuration.save()

        assert Configuration.count() == 1
    }

    void "test update"() {
        def configuration = Configuration.get(1)
        configuration.setPassword("secret1")
        configuration.save()
        assert configuration.get(1).password == "secret1"
    }

    void "test delete"() {
        Configuration.deleteAll()
        assert Configuration.count() == 0
    }
}
