package grails.intro

import grails.transaction.Transactional

@Transactional
class PersonService {

    List<Person> findInCityWithLastNameCriteria(String cityName, String personLastName) {
        Person.withCriteria {
            eq('lastName', personLastName)

            address {
                eq('city', cityName)
            }
        }
    }

    List<Person> findInCityWithLastNameWhere(String cityName, String personLastName) {
        Person.where { lastName == personLastName && address.city == cityName }.list()
    }
}
