package entities.search

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SearchInnerArtist(
    @JsonProperty("id") var id: Int,
    @JsonProperty("name") var name: String = "",
    @JsonProperty("link") var link: String = "",
    @JsonProperty("picture") var picture: String = "",
    @JsonProperty("picture_small") var pictureSmall: String = "",
    @JsonProperty("picture_medium") var pictureMedium: String = "",
    @JsonProperty("picture_big") var pictureBig: String = "",
    @JsonProperty("picture_xl") var pictureXl: String = "",
    @JsonProperty("tracklist") var tracklist: String = "",
    @JsonProperty("radio") var radio: Boolean,
    @JsonProperty("type") var type: String = ""
)
