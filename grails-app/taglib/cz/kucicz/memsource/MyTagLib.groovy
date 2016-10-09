package cz.kucicz.memsource

class MyTagLib {

    ProjectsController projectService

    static namespace = "memsource"

    def renderProjects = {
        def projects = projectService.get()
        out << "<table>"
        out << "<tr><th>Name</th><th>Status</th><th>Source language</th><th>Target languages</th></tr>"
        projects.each { project ->
            out << "<tr><td>${project.name}</td><td>${project.status}" +
                    "</td><td>${project.sourceLanguage}</td><td>${project.targetLanguages.each { string -> '${string}' }}</td></tr>"
        }
        out << "</table>"
    }
}
