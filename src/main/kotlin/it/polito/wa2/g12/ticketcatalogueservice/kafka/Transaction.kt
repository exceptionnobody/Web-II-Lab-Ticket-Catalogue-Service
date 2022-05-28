package it.polito.wa2.g12.ticketcatalogueservice.kafka

import com.fasterxml.jackson.annotation.JsonProperty

data class Transaction(
    @JsonProperty("order_id")
    val order_id: Long,
    @JsonProperty("transaction_id")
    val transaction_id: Long?,
    @JsonProperty("status")
    val status: String,
)
