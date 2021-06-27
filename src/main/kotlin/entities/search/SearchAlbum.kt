package entities.search

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchAlbum (
    @JsonProperty("id") var id: Int,
    @JsonProperty("title") var title: String = "",
    @JsonProperty("link") var link: String = "",
    @JsonProperty("cover") var cover: String = "",
    @JsonProperty("cover_small") var coverSmall: String = "",
    @JsonProperty("cover_medium") var coverMedium: String = "",
    @JsonProperty("cover_big") var coverBig: String = "",
    @JsonProperty("cover_xl") var coverXl: String = "",
    @JsonProperty("md5_image") var md5Image: String = "",
    @JsonProperty("genre_id") var genreId: Int,
    @JsonProperty("nb_tracks") var nbTracks: Int,
    @JsonProperty("record_type") var recordType: String = "",
    @JsonProperty("tracklist") var trackList: String = "",
    @JsonProperty("explicit_lyrics") var explicitLyrics: Boolean,
    @JsonProperty("artist") var artist: SearchInnerArtist,
    @JsonProperty("type") var type: String = ""
) {
    override fun toString(): String {
        return "Title: $title (Deezer ID: $id) \n" +
                "Author: ${artist.name} \n" +
                "Tracks: $nbTracks \n" +
                "Genre ID: $genreId \n\n" +
                "Link: $link"
    }
}
