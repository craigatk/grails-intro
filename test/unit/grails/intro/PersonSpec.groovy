package grails.intro

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Person)
class PersonSpec extends Specification {
    void "should save full object"() {
        when:
        new Person(firstName: 'Jim', middleName: 'Samson', lastName: 'Smith').save()

        then:
        assert Person.findByFirstName('Jim')
    }

    void "when saving Person without first name should fail"() {
        when:
        new Person(lastName: 'Smith').save()

        then:
        assert !Person.findByLastName('Smith')
    }
}
