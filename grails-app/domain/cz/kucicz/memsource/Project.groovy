package cz.kucicz.memsource

class Project {

    String name
    String status
    String sourceLanguage
    List targetLanguages
    static hasMany = [targetLanguages: String]
}
