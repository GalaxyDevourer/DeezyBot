package entities.charts

import com.fasterxml.jackson.annotation.JsonProperty
import entities.search.SearchInnerArtist

data class ChartAlbum (
    @JsonProperty("id") var id: Int,
    @JsonProperty("title") var title: String = "",
    @JsonProperty("link") var link: String = "",
    @JsonProperty("cover") var cover: String = "",
    @JsonProperty("cover_small") var coverSmall: String = "",
    @JsonProperty("cover_medium") var coverMedium: String = "",
    @JsonProperty("cover_big") var coverBig: String = "",
    @JsonProperty("cover_xl") var coverXl: String = "",
    @JsonProperty("md5_image") var md5Image: String = "",
    @JsonProperty("record_type") var recordType: String = "",
    @JsonProperty("tracklist") var trackList: String = "",
    @JsonProperty("explicit_lyrics") var explicitLyrics: Boolean,
    @JsonProperty("position") var position: Int,
    @JsonProperty("artist") var artist: SearchInnerArtist,
    @JsonProperty("type") var type: String = ""
) {
    override fun toString(): String {
        return "Title: $title (Deezer ID: $id) \n" +
                "Author: ${artist.name} \n" +
                "Position: $position \n\n" +
                "Link: $link"
    }
}
