package it.polito.wa2.g12.ticketcatalogueservice.repository

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface OrderRepository: ReactiveCrudRepository<Order?, Long?>