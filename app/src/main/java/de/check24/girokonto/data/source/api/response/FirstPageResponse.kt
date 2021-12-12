package de.check24.girokonto.data.source.api.response

data class FirstPageResponse(
    val slogan: Slogan,
    val recommends: List<Recommend>
)

data class Recommend(
    val title: String,
    val text: String,
    val short: String,
    val image: String,
    val fee: Double,
    @NullToZeroDouble
    val bonus: Double?
)

data class Slogan(
    val titles: List<String>,
    val logo: String
)

