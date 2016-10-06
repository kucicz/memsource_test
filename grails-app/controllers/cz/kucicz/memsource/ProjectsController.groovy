package cz.kucicz.memsource

import grails.converters.JSON

class ProjectsController {

    def index() {}

    def getprojects = {
        def projects = Project.list()
        //response.contentType = "application/json"
        render projects as JSON
    }
}
