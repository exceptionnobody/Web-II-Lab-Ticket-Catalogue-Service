package it.polito.wa2.g12.ticketcatalogueservice.kafka

import com.fasterxml.jackson.annotation.JsonProperty

data class Billing(
    @JsonProperty("order_id")
    val order_id: Long,
    @JsonProperty("price")
    val price: Long,
    @JsonProperty("ccn")
    val ccn: String,
    @JsonProperty("exp")
    val exp: String,
    @JsonProperty("cvv")
    val cvv: String,
    @JsonProperty("card_holder")
    val card_holder: String,
)
