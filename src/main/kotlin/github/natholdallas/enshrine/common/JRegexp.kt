package github.natholdallas.enshrine.common

class JRegexp private constructor() {

    companion object {
        const val EMAIL: String = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
        const val PASSWORD: String = "^(?=.*[a-zA-Z])(?=.*\\d).{4,20}$"
    }

}
