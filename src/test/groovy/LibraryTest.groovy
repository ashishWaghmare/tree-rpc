/*
 * This Spock specification was auto generated by running 'gradle init --type groovy-library'
 * by 'ashishw' at '28/2/16 12:46 AM' with Gradle 2.2.1
 *
 * @author ashishw, @date 28/2/16 12:46 AM
 */

import spock.lang.Specification

class LibraryTest extends Specification {
    def "someLibraryMethod returns true"() {
        setup

        when:
        def result = lib.someLibraryMethod()
        then:
        result == true
    }
}
