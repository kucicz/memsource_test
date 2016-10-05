package cz.kucicz.memsource

class SetupController {

    def configurationService;

    def index() {
        def configuration = configurationService.getConfiguration();
        [configuration: configuration]
    }

    def save() {
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
