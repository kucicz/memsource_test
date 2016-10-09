package cz.kucicz.memsource

class Token {

    static constraints = {
    }

    String username
    String token
    String expires

    @Override
    public String toString() {
        return "Token{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }
}
