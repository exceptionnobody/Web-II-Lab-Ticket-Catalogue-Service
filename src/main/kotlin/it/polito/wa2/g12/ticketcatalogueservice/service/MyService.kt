package it.polito.wa2.g12.ticketcatalogueservice.service

import it.polito.wa2.g12.ticketcatalogueservice.repository.CustomerEntity
import it.polito.wa2.g12.ticketcatalogueservice.repository.MyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MyService {

    @Autowired
    lateinit var myRepository: MyRepository

    fun prova() : String {
        myRepository.save(CustomerEntity(1,"marco","ballario"))
        return "CIAO "
    }
}