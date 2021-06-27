package utils

import com.elbekD.bot.types.InlineQueryResultArticle
import com.elbekD.bot.types.InputTextMessageContent
import entities.artist.Artist
import entities.artist.ArtistAlbum
import entities.artist.ArtistsTopTrack
import entities.charts.ChartAlbum
import entities.charts.ChartArtist
import entities.charts.ChartTrack
import entities.genres.Genre
import entities.genres.GenreArtist
import entities.search.SearchAlbum
import entities.search.SearchArtist
import entities.search.SearchTrack

class ViewClassConverter() {
    fun getErrorInlineQuery(id: Int, title: String, msg: String): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), title, InputTextMessageContent(msg), description = msg,
            thumb_url = "https://blog.vverh.digital/wp-content/uploads/2020/06/oblojka-404.png", thumb_width=48, thumb_height=48
        )
    }

    fun searchTrackToInlineQuery(id: Int, track: SearchTrack): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), "${track.artist.name} - ${track.title}",
            InputTextMessageContent(track.toString()),
            description = "Album: ${cutString(track.album.name)} \nDuration: ${DurationFormatter().duration(track.duration)}",
            thumb_url = track.album.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun searchArtistToInlineQuery(id: Int, searchArtist: SearchArtist): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), searchArtist.name,
            InputTextMessageContent(searchArtist.toString()),
            description = "Deezer ID: ${searchArtist.id} \nAlbums: ${searchArtist.nbAlbum}",
            thumb_url = searchArtist.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun searchAlbumToInlineQuery(id: Int, album: SearchAlbum): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), cutString(album.title),
            InputTextMessageContent(album.toString()),
            description = "Artist: ${album.artist.name} \nTracks: ${album.nbTracks}",
            thumb_url = album.coverMedium, thumb_width=48, thumb_height=48
        )
    }

    fun artistToInlineQuery(id: Int, artist: Artist): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), artist.name,
            InputTextMessageContent(artist.toString()),
            description = "Deezer ID: ${artist.id} \nAlbums: ${artist.nbAlbum}",
            thumb_url = artist.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun artistTopTrackToInlineQuery(id: Int, topTrack: ArtistsTopTrack): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), "${topTrack.artist.name} - ${topTrack.title}",
            InputTextMessageContent(topTrack.toString()),
            description = "Album: ${cutString(topTrack.album.name)} \nDuration: ${DurationFormatter().duration(topTrack.duration)}",
            thumb_url = topTrack.album.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun artistAlbumToInlineQuery(id: Int, album: ArtistAlbum): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), cutString(album.title),
            InputTextMessageContent(album.toString()),
            description = "Release date: ${album.releaseDate}",
            thumb_url = album.coverMedium, thumb_width=48, thumb_height=48
        )
    }

    fun genreToInlineQuery(id: Int, genre: Genre): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), genre.name,
            InputTextMessageContent(genre.toString()),
            description = "Genre ID: ${genre.id}",
            thumb_url = genre.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun genreArtistToInlineQuery(id: Int, genreArtist: GenreArtist): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), genreArtist.name,
            InputTextMessageContent(genreArtist.toString()),
            description = "Deezer ID: ${genreArtist.id}",
            thumb_url = genreArtist.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun chartTrackToInlineQuery(id: Int, chartTrack: ChartTrack): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), "${chartTrack.artist.name} - ${chartTrack.title}",
            InputTextMessageContent(chartTrack.toString()),
            description = "Album: ${cutString(chartTrack.album.name)} \nPosition: ${chartTrack.position}",
            thumb_url = chartTrack.album.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    fun chartAlbumToInlineQuery(id: Int, chartAlbum: ChartAlbum): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), cutString(chartAlbum.title),
            InputTextMessageContent(chartAlbum.toString()),
            description = "Artist: ${chartAlbum.artist.name} \nPosition: ${chartAlbum.position}",
            thumb_url = chartAlbum.coverMedium, thumb_width=48, thumb_height=48
        )
    }

    fun chartArtistToInlineQuery(id: Int, chartArtist: ChartArtist): InlineQueryResultArticle {
        return InlineQueryResultArticle(
            id.toString(), chartArtist.name,
            InputTextMessageContent(chartArtist.toString()),
            description = "Deezer ID: ${chartArtist.id} \nPosition: ${chartArtist.position}",
            thumb_url = chartArtist.pictureMedium, thumb_width=48, thumb_height=48
        )
    }

    private fun cutString(name: String): String {
        return if (name.length > 30) "${name.subSequence(0, 20)}..." else name
    }
}