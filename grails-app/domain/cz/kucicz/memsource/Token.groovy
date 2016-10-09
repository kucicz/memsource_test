package cz.kucicz.memsource

class Token {

    static constraints = {
    }
    String username
    int passwordHash
    String token
    String expires


    @Override
    public String toString() {
        return "Token{" +
                "username='" + username + '\'' +
                ", passwordHash=" + passwordHash +
                ", token='" + token + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }
}
