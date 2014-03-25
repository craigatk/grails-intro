package grails.intro

import grails.test.spock.IntegrationSpec

class PersonServiceIntegrationSpec extends IntegrationSpec {
    PersonService personService

    Person smithInCity
    Person notSmithInCity
    Person personNotInCity

    def setup() {
        Address addressInCity = new Address(street: '123 Main St', city: 'Minneapolis', state: 'MN', postalCode: '55434').save()
        smithInCity = new Person(firstName: 'Jim', lastName: 'Smith', address: addressInCity).save()

        notSmithInCity = new Person(firstName: 'John', lastName: 'Smythe', address: addressInCity).save()

        Address addressNotInCity = new Address(street: '234 Upper St', city: 'St. Paul', state: 'MN', postalCode: '55401').save()
        personNotInCity = new Person(firstName: 'Jane', lastName: 'Smythe', address: addressNotInCity).save()
    }

    def 'should find people by city with criteria query'() {
        when:
        List<Person> peopleInCity = personService.findInCityWithLastNameCriteria('Minneapolis', 'Smith')

        then:
        assert peopleInCity?.contains(smithInCity)
        assert !peopleInCity?.contains(notSmithInCity)
        assert !peopleInCity?.contains(personNotInCity)
    }

    def 'should find people by city with where query'() {
        when:
        List<Person> peopleInCity = personService.findInCityWithLastNameWhere('Minneapolis', 'Smith')

        then:
        assert peopleInCity?.contains(smithInCity)
        assert !peopleInCity?.contains(notSmithInCity)
        assert !peopleInCity?.contains(personNotInCity)
    }
}
