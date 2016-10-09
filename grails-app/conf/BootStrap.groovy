import cz.kucicz.memsource.Configuration

class BootStrap {

    def init = { servletContext ->
        if (Configuration.count() == 0) {
            new Configuration(username: 'John', password: 'secr3t').save()
        }
    }
    def destroy = {
    }
}
