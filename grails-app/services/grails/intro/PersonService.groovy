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

    List<Person> findInMinneapolisOver35Where() {
        Person.where { address.city == 'Minneapolis' && age > 35 }.list()
    }

    List<Person> findInMinneapolisOver35Criteria() {
        Person.withCriteria {
            address {
                eq('city', 'Minneapolis')
            }
            gt('age', 35)
        }
    }

    List<Person> findInMinneapolisOver35HQL() {
        Person.executeQuery("select person from Person person inner join person.address as address \
                             where person.age > 35 and address.city = 'Minneapolis'")
    }
}
