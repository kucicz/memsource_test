package cz.kucicz.memsource

class Project {

    String name
    String status
    String sourceLanguage
    List targetLanguages
    static hasMany = [targetLanguages: String]


    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", sourceLanguage='" + sourceLanguage + '\'' +
                ", targetLanguages=" + targetLanguages +
                '}';
    }
}
