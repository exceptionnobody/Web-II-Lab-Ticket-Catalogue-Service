package it.polito.wa2.g12.ticketcatalogueservice.service

import it.polito.wa2.g12.ticketcatalogueservice.repository.CustomerEntity
import it.polito.wa2.g12.ticketcatalogueservice.repository.MyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MyService {

    @Autowired
    lateinit var myRepository: MyRepository

    fun newCustomer() : Mono<CustomerEntity> {
        return myRepository.save(CustomerEntity("Pietro", "Ubertini"))
    }

    fun findCustomers() : Flux<CustomerEntity?> {
        return myRepository.findAll()
    }
}