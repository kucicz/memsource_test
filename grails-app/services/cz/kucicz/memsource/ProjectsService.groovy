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

    /**
     * Get list of projects from memsource API
     * @param token - autorization token
     * @return list of projects
     * @throws RuntimeException in case of failure
     */
    def getProjects(String token) {
        log.debug("getProjects")
        Map<String, String> parameters = new HashMap<>();
        parameters.put("token", token);
        String responseJson = HttpUtil.sendPost(URL + ACTION, parameters);
        def jsonObject = JSON.parse(responseJson)
        if (responseJson.contains('\"errorCode\":') && responseJson.contains('\"errorDescription\":')) {
            // check response for error
            log.warn(" [getProjects failed: ${responseJson}]")
            String errorCode = jsonObject.errorCode as String
            String errorDescription = jsonObject.errorDescription as String
            throw new RuntimeException(errorCode + ": " + errorDescription)
        }
        log.debug(jsonObject)
        // save new token
        def projects = []
        jsonObject.each { json ->
            projects << new Project(name: json.name, status: json.status,
                    sourceLanguage: json.sourceLang, targetLanguages: json.targetLangs)
        }
        return projects
    }
}
