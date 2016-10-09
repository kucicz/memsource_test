package cz.kucicz.memsource

import grails.converters.JSON
import org.apache.juli.logging.LogFactory

import javax.security.auth.login.LoginException

class ProjectsController {
    private static final log = LogFactory.getLog(this)
    def loginService
    def projectsService

    def index() {}

    def getprojects = {
        try {
            log.debug("getProjects called [userName=${params.get("userName")}]")
            def token = loginService.login(params.get("userName") as String, params.get("password") as String)
            def projects = projectsService.getProjects(token)
            log.debug(projects)
            render projects as JSON
        } catch (LoginException e) {
            log.debug(e.getMessage())
            response.status = 403 // caught exception
            render([error: e.getMessage()] as JSON)
        }
        catch (Exception e) {
            log.debug(e.getMessage())
            response.status = 500 // caught exception
            render([error: e.getMessage()] as JSON)
        }
    }
}
