package grails.intro

import grails.rest.Resource

@Resource(uri='/person', formats=['json'])
class Person {
    String firstName
    String middleName
    String lastName
    Integer age

    Address address

    static constraints = {
        firstName(nullable: false, blank: false)
        middleName(nullable: true)
        lastName(nullable: false, blank: false)
        age(nullable: true)
        address(nullable: true)
    }
}
