package it.polito.wa2.g12.ticketcatalogueservice.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface MyRepository : ReactiveCrudRepository<CustomerEntity?, Long?> {
    @Query("SELECT * FROM customer WHERE last_name = :lastname")
    fun findByLastName(lastName: String?): Flux<CustomerEntity?>?
}