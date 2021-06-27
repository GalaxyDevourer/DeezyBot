package entities.artist

import com.fasterxml.jackson.annotation.JsonProperty

data class Artist (
    @JsonProperty("id") var id: Int,
    @JsonProperty("name") var name: String = "",
    @JsonProperty("link") var link: String = "",
    @JsonProperty("share") var share: String = "",
    @JsonProperty("picture") var picture: String = "",
    @JsonProperty("picture_small") var pictureSmall: String = "",
    @JsonProperty("picture_medium") var pictureMedium: String = "",
    @JsonProperty("picture_big") var pictureBig: String = "",
    @JsonProperty("picture_xl") var pictureXl: String = "",
    @JsonProperty("nb_album") var nbAlbum: Int,
    @JsonProperty("nb_fan") var nbFan: Int,
    @JsonProperty("radio") var radio: Boolean,
    @JsonProperty("tracklist") var trackList: String = "",
    @JsonProperty("type") var type: String = ""
) {
    override fun toString(): String {
        return "Name: $name (Deezer ID: $id) \n" +
                "Albums: $nbAlbum \n" +
                "Fans: $nbFan \n\n" +
                "Link: $link"
    }
}