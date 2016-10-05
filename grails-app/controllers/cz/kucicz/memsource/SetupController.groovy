package cz.kucicz.memsource

class SetupController {

    def configurationService;

    def index() {
        def configuration = configurationService.getConfiguration();
        [configuration: configuration]
    }

    def save() {
        def configuration = new Configuration(params)
        configuration = configurationService.saveConfiguration(configuration.getUsername(), configuration.getPassword())
        render(view: 'index.gsp', model: [configuration: configuration])
    }
}
