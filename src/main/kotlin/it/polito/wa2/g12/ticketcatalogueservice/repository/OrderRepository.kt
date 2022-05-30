package it.polito.wa2.g12.ticketcatalogueservice.repository

import it.polito.wa2.g12.ticketcatalogueservice.entity.Order
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository



interface OrderRepository: CoroutineCrudRepository<Order, Long> {
    @Query("""SELECT * FROM orders""")
    fun findAllOrders(): Flow<Order>
}