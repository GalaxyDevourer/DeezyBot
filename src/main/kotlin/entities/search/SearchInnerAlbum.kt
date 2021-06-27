package entities.search

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SearchInnerAlbum(
    @JsonProperty("id") var id: Int,
    @JsonProperty("title") var name: String = "",
    @JsonProperty("cover") var link: String = "",
    @JsonProperty("cover_small") var picture: String = "",
    @JsonProperty("cover_medium") var pictureSmall: String = "",
    @JsonProperty("cover_big") var pictureMedium: String = "",
    @JsonProperty("cover_xl") var pictureBig: String = "",
    @JsonProperty("md5_image") var pictureXl: String = "",
    @JsonProperty("tracklist") var tracklist: String = "",
    @JsonProperty("type") var type: String = ""
)
