package it.polito.wa2.g12.ticketcatalogueservice.kafka
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import kotlin.text.Charsets.UTF_8

class TransactionDeserializer : Deserializer<Transaction> {
    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): Transaction? {
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to Product"), UTF_8
            ), Transaction::class.java
        )
    }

    override fun close() {}

}
