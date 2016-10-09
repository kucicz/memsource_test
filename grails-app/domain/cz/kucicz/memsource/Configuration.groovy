package cz.kucicz.memsource

class Configuration {

    static constraints = {
        username blank: false
        password blank: false
    }

    String username;
    String password;


    @Override
    public String toString() {
        return "Configuration{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
