package entities.genres

import com.fasterxml.jackson.annotation.JsonProperty

data class GenreArtist (
    @JsonProperty("id") var id: Int,
    @JsonProperty("name") var name: String = "",
    @JsonProperty("picture") var picture: String = "",
    @JsonProperty("picture_small") var pictureSmall: String = "",
    @JsonProperty("picture_medium") var pictureMedium: String = "",
    @JsonProperty("picture_big") var pictureBig: String = "",
    @JsonProperty("picture_xl") var pictureXl: String = "",
    @JsonProperty("radio") var radio: Boolean,
    @JsonProperty("tracklist") var trackList: String = "",
    @JsonProperty("type") var type: String = ""
) {
    override fun toString(): String {
        return "Name: $name (Deezer ID: $id) \n" +
                "Tracklist Link: $trackList"
    }
}
