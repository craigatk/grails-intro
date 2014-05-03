package grails.intro

import grails.test.spock.IntegrationSpec

class PersonServiceIntegrationSpec extends IntegrationSpec {
    PersonService personService

    Person smithInCity
    Person notSmithInCity
    Person personNotInCity

    Address addressInMinneapolis

    def setup() {
        addressInMinneapolis = new Address(street: '123 Main St', city: 'Minneapolis', state: 'MN', postalCode: '55434').save()
        smithInCity = new Person(firstName: 'Jim', lastName: 'Smith', address: addressInMinneapolis).save()

        notSmithInCity = new Person(firstName: 'John', lastName: 'Smythe', address: addressInMinneapolis).save()

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

    def 'should find person in Minneapolis over 35 using where query'() {
        given:
        Person person = new Person(firstName: 'Jane', lastName: 'Smith', age: 36, address: addressInMinneapolis).save()

        when:
        List<Person> searchResults = personService.findInMinneapolisOver35Where()

        then:
        assert searchResults == [person]
    }

    def 'should find person in Minneapolis over 35 using criteria query'() {
        given:
        Person person = new Person(firstName: 'Jane', lastName: 'Smith', age: 36, address: addressInMinneapolis).save()

        when:
        List<Person> searchResults = personService.findInMinneapolisOver35Criteria()

        then:
        assert searchResults == [person]
    }

    def 'should find person in Minneapolis over 35 using HQL query'() {
        given:
        Person person = new Person(firstName: 'Jane', lastName: 'Smith', age: 36, address: addressInMinneapolis).save()

        when:
        List<Person> searchResults = personService.findInMinneapolisOver35HQL()

        then:
        assert searchResults == [person]
    }
}
