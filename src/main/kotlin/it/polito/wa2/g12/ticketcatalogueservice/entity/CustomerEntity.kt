package it.polito.wa2.g12.ticketcatalogueservice.repository

import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.relational.core.mapping.Table

@Table(name ="customer")
class CustomerEntity(
    @Id
    var id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
)