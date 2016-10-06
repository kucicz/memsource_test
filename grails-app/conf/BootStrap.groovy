import cz.kucicz.memsource.Configuration
import cz.kucicz.memsource.Project

class BootStrap {

    def init = { servletContext ->
        if (Configuration.count() == 0) {
            new Configuration(username: 'John', password: 'secr3t').save()
        }
        if (Project.count() == 0) {
            new Project(name: 'name', status: 'new', sourceLanguage: 'cs', targetLanguages: ['en', 'de', 'fr', 'at']).save()
            new Project(name: 'name2', status: 'new', sourceLanguage: 'cs', targetLanguages: ['en', 'de', 'fr', 'at']).save()
            new Project(name: 'name3', status: 'new', sourceLanguage: 'cs', targetLanguages: ['en', 'de', 'fr', 'at']).save()
            new Project(name: 'name4', status: 'new', sourceLanguage: 'cs', targetLanguages: ['en', 'de', 'fr', 'at']).save()
        }
    }
    def destroy = {
    }
}
