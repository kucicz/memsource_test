package cz.kucicz.memsource

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    def getConfiguration() {
        def configurationList = Configuration.getAll()
        return configurationList.get(0)
    }

    def saveConfiguration(String username, String password) {
        def configurationList = Configuration.getAll()
        def configuration = configurationList.get(0)
        if (!username.empty) {
            configuration.username = username
        }
        if (!password.empty) {
            configuration.password = password
        }
        configuration.save()
        return configuration
    }
}
