package grails.intro

class Address {
    String street
    String street2
    String city
    String state
    String postalCode

    static constraints = {
        street(nullable: false)
        street2(nullable: true)
        city(nullable: false)
        state(nullable: false)
        postalCode(nullable: false)
    }
}
