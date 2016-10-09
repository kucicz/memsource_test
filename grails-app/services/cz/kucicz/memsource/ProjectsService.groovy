package cz.kucicz.memsource

import grails.converters.JSON
import grails.transaction.Transactional
import org.apache.juli.logging.LogFactory

@Transactional
class ProjectsService {
    private static final log = LogFactory.getLog(this);
    // will be moved to some property file
    private static final String ACTION = 'api/v3/project/list';
    private static final String URL = 'https://cloud.memsource.com/web/';

    def getProjects(String token) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", token);
        String responseJson = HttpUtil.sendPost(URL + ACTION, parameters);
        def jsonObject = JSON.parse(responseJson)
        log.debug(jsonObject)
        // save new token
        def projects = []
        jsonObject.each { json -> projects << new Project(name: json.name, status: json.status, sourceLanguage: json.sourceLang, targetLanguages: json.targetLangs) }
        return projects
    }
}
