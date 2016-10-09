package cz.kucicz.memsource

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    def getConfiguration() {
        def configurationList = Configuration.getAll()
        if (configurationList.isEmpty()) {
            return new Configuration()
        }
        return configurationList.get(0)
    }

    def saveConfiguration(String username, String password) {
        def configurationList = Configuration.getAll()
        if (configurationList.isEmpty()) {
            def configuration = new Configuration()
        } else {
            def configuration = configurationList.get(0)
        }
        configuration.username = username
        configuration.password = password
        configuration.save()
        return configuration
    }
}
