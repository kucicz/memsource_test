package cz.kucicz.memsource

import org.apache.juli.logging.LogFactory
import org.springframework.util.StringUtils

class SetupController {

    private static final log = LogFactory.getLog(this)

    def configurationService;

    def index() {
        log.debug("index called")
        def configuration = configurationService.getConfiguration();
        [configuration: configuration]
    }

    def save() {
        log.debug("save called");
        try {
            if (!StringUtils.hasText(params.get("username") as String)) {
                throw new Exception("Missing username")
            }
            if (!StringUtils.hasText(params.get("password") as String)) {
                throw new Exception("Missing password")
            }
            def configuration = new Configuration(username: params.get("username"), password: params.get("password"))
            configurationService.saveConfiguration(configuration.getUsername(), configuration.getPassword())
            flash.message = 'Success!'
        } catch (Exception e) {
            flash.message = e.getMessage()
        }
        redirect(action: 'index')
    }
}
