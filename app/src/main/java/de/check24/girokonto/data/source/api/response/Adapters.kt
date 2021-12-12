package de.check24.girokonto.data.source.api.response

import com.squareup.moshi.*


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToZeroDouble


object NullToZeroDoubleAdapter : JsonAdapter<Double>() {
    @FromJson
    @NullToZeroDouble
    override fun fromJson(reader: JsonReader): Double {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextDouble()
        }
        reader.nextNull<Unit>()
        return 0.0
    }

    @ToJson
    override fun toJson(writer: JsonWriter, @NullToZeroDouble value: Double?) {
        TODO("Not implemented")
    }
}

