package cz.kucicz.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProjectsService)
class ProjectsServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test getProjects with success"() {
        setup:
        GroovyMock(HttpUtil, global: true)
        // Mock memsource API
        HttpUtil.sendPost(_ as String, _ as Map) >> '[{"id":1435507,"uid":"951dhkNaZGh44qD9qDg8K6","internalId":2,"name":"asdf","status":"NEW","sourceLang":"en","targetLangs":["ku","la","ru_lv"],"dateDue":null,"dateCreated":"2016-10-07T21:50:09+0000","note":"","machineTranslateSettings":{"id":38600,"name":"Microsoft with Feedback","type":"MicrosoftTranslatorWithFeedback","default":true,"args":{"category":null}},"workflowSteps":[],"client":null,"businessUnit":null,"domain":null,"subDomain":null,"langSettings":[],"createdBy":{"deleted":false,"active":true,"userName":"kuci.cz","firstName":"Vaclav","id":165856,"lastName":"Kucera","role":"ADMIN","email":"kucicz@gmail.com"},"qualityAssuranceSettings":{"emptyTranslation":true,"inconsistentTranslation":true,"joinTags":true,"missingNumbers":true,"segmentNotConfirmed":true,"terminology":true,"multipleSpaces":true,"trailingSpace":true,"trailingPunctuation":false,"targetLength":{"enabled":false,"max":1000},"formatting":true,"unresolvedComment":true,"emptyPairTags":true,"strictJobStatus":false,"forbiddenStrings":{"enabled":false,"list":[]},"excludeLockedSegments":false,"ignoreNotApprovedTerms":false,"spellCheck":true,"repeatedWords":true,"inconsistentTagContent":true,"emptyTagContent":true,"xliffTags":true,"forbiddenTerms":false,"targetLengthPercent":{"enabled":false,"max":130.0},"targetLengthPerSegment":true,"newerAtPrecedingWorkflowStep":true,"leadingAndTrailingSpaces":true,"ignoreInAllWorkflowSteps":false,"regexp":null},"analyseSettings":{"type":"PreAnalyse","includeFuzzyRepetitions":true,"includeConfirmedSegments":true,"includeNumbers":true,"includeLockedSegments":true,"countSourceUnits":true,"includeTransMemory":true,"compareWorkflowLevel":null},"owner":{"deleted":false,"active":true,"userName":"kuci.cz","firstName":"Vaclav","id":165856,"lastName":"Kucera","role":"ADMIN","email":"kucicz@gmail.com"}},{"id":1435506,"uid":"f4d8TkOqAS9m29tV2oZo93","internalId":1,"name":"test","status":"NEW","sourceLang":"cs","targetLangs":["ab","af","am","sq"],"dateDue":null,"dateCreated":"2016-10-07T21:49:19+0000","note":"","machineTranslateSettings":{"id":38600,"name":"Microsoft with Feedback","type":"MicrosoftTranslatorWithFeedback","default":true,"args":{"category":null}},"workflowSteps":[],"client":null,"businessUnit":null,"domain":null,"subDomain":null,"langSettings":[],"createdBy":{"deleted":false,"active":true,"userName":"kuci.cz","firstName":"Vaclav","id":165856,"lastName":"Kucera","role":"ADMIN","email":"kucicz@gmail.com"},"qualityAssuranceSettings":{"emptyTranslation":true,"inconsistentTranslation":true,"joinTags":true,"missingNumbers":true,"segmentNotConfirmed":true,"terminology":true,"multipleSpaces":true,"trailingSpace":true,"trailingPunctuation":false,"targetLength":{"enabled":false,"max":1000},"formatting":true,"unresolvedComment":true,"emptyPairTags":true,"strictJobStatus":false,"forbiddenStrings":{"enabled":false,"list":[]},"excludeLockedSegments":false,"ignoreNotApprovedTerms":false,"spellCheck":true,"repeatedWords":true,"inconsistentTagContent":true,"emptyTagContent":true,"xliffTags":true,"forbiddenTerms":false,"targetLengthPercent":{"enabled":false,"max":130.0},"targetLengthPerSegment":true,"newerAtPrecedingWorkflowStep":true,"leadingAndTrailingSpaces":true,"ignoreInAllWorkflowSteps":false,"regexp":null},"analyseSettings":{"type":"PreAnalyse","includeFuzzyRepetitions":true,"includeConfirmedSegments":true,"includeNumbers":true,"includeLockedSegments":true,"countSourceUnits":true,"includeTransMemory":true,"compareWorkflowLevel":null},"owner":{"deleted":false,"active":true,"userName":"kuci.cz","firstName":"Vaclav","id":165856,"lastName":"Kucera","role":"ADMIN","email":"kucicz@gmail.com"}}]'
        when:
        def projects = null
        try {
            projects = service.getProjects("token")
        } catch (Exception e) {
            println e.getMessage()
        }
        then:
        projects != null
    }

    void "test getProjects with fail"() {
        setup:
        GroovyMock(HttpUtil, global: true)
        // Mock memsource API
        HttpUtil.sendPost(_ as String, _ as Map) >> '{"errorCode":"AuthUnauthorized","errorDescription":"Unauthorized access."}'
        when:
        def response = null
        try {
            response = service.getProjects("token")
        } catch (Exception e) {
            response = e.getMessage()
        }
        then:
        response == "AuthUnauthorized: Unauthorized access."
    }
}
