package cz.kucicz.memsource

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    /**
     * Get Configuration from database (there is always stored just one record)
     * @return Configuration object
     */
    def getConfiguration() {
        def configurationList = Configuration.getAll()
        if (configurationList.isEmpty()) {
            return new Configuration()
        }
        return configurationList.get(0)
    }

    /**
     * Store new configuration to db
     * @param username - username to be stored
     * @param password - password to be saved
     */
    def saveConfiguration(String username, String password) {
        def configurationList = Configuration.getAll()
        def configuration = new Configuration();
        if (!configurationList.isEmpty()) {
            configuration = configurationList.get(0)
        }

        configuration.username = username.trim()
        configuration.password = password.trim()
        configuration.save()
        return configuration
    }
}
