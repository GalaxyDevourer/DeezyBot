package entities.search

import com.fasterxml.jackson.annotation.JsonProperty
import utils.DurationFormatter

data class SearchTrack(
    @JsonProperty("id") var id: Int,
    @JsonProperty("readable") var readable: Boolean,
    @JsonProperty("title") var title: String = "",
    @JsonProperty("title_short") var titleShort: String = "",
    @JsonProperty("title_version") var titleVersion: String = "",
    @JsonProperty("link") var link: String = "",
    @JsonProperty("duration") var duration: Int,
    @JsonProperty("rank") var rank: Int,
    @JsonProperty("explicit_lyrics") var explicitLyrics: Boolean,
    @JsonProperty("explicit_content_lyrics") var explicitContentLyrics: Int,
    @JsonProperty("explicit_content_cover") var explicitContentCover: Int,
    @JsonProperty("preview") var preview: String = "",
    @JsonProperty("md5_image") var md5Image: String = "",
    @JsonProperty("artist") var artist: SearchInnerArtist,
    @JsonProperty("album") var album: SearchInnerAlbum,
    @JsonProperty("type") var type: String = ""
) {
    override fun toString(): String {
        return "Title: $title (Deezer ID: $id) \n" +
                "Album: ${album.name}\n" +
                "Author: ${artist.name} \n\n" +
                "Duration: ${DurationFormatter().duration(duration)} \n" +
                "Rank: $rank\n\n" +
                "Link: $link"
    }
}
