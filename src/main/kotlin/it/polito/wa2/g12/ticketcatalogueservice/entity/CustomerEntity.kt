package it.polito.wa2.g12.ticketcatalogueservice.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name ="customer")
class CustomerEntity(
    @Column
    val firstName: String? = null,
    @Column
    val lastName: String? = null,
) {
    @Id
    var id: Int?  = null
}