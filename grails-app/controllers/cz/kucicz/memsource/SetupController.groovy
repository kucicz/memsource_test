package cz.kucicz.memsource

import org.apache.juli.logging.LogFactory

class SetupController {

    private static final log = LogFactory.getLog(this)

    def configurationService;

    def index() {
        log.("index [setup called]")
        def configuration = configurationService.getConfiguration();
        [configuration: configuration]
    }

    def save() {
        log.debug("save called");
        def configuration = new Configuration(params)
        try {
            configurationService.saveConfiguration(configuration.getUsername(), configuration.getPassword())
            flash.message = 'Success!'
        } catch (Exception e) {
            flash.message = 'Fail!'
        }
        redirect(action: 'index')
    }
}
