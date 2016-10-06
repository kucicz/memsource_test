package cz.kucicz.memsource

import grails.transaction.Transactional

@Transactional
class ProjectService {

    def getProjectsList() {
        return Project.getAll()

    }
}
