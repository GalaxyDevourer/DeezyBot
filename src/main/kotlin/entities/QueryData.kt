package entities

import com.fasterxml.jackson.annotation.JsonProperty

class QueryData<T>(
    @JsonProperty("data") var data: List<T>?,
    @JsonProperty("total") var total: Int,
    @JsonProperty("next") var next: String = ""
)