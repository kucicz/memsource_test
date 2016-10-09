package cz.kucicz.memsource

import grails.converters.JSON
import org.apache.juli.logging.LogFactory
import org.springframework.util.StringUtils

import javax.security.auth.login.LoginException

class ProjectsController {
    private static final log = LogFactory.getLog(this)
    def loginService
    def projectsService
    def configurationService

    def index() {}

    def getProjectList = {
        try {
            log.info("getProjects called [userName=${params.get("userName")}]")
            // get username and password
            def credentials = configurationService.getConfiguration()
            if (!StringUtils.hasText(credentials.username) || !StringUtils.hasText(credentials.password)) {
                throw new Exception("Missing username or password")
            }
            // get authorization token
            def token = loginService.login(credentials.username, credentials.password)
            // get projects
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
