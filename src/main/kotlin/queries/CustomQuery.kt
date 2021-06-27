package queries

import com.elbekD.bot.types.InlineQueryResult
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import entities.QueryData
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
import utils.ViewClassConverter

class CustomQuery {
    private val converter = ViewClassConverter()
    private val url = "https://api.deezer.com"

    fun executeQuery(command: String, getResult: (MutableList<InlineQueryResult>) -> Unit) {
        val params: Pair<String, String> = constructQuery(command)
        val mutableResult: MutableList<InlineQueryResult> = mutableListOf()

        println(params)
        params.second.httpGet()
            .response { _, _, result ->
                var id = 0

                val (bytes, error) = result
                println(bytes?.let { String(it) } ?: println(error))

                if (bytes != null) {
                    when (params.first) {
                        "SearchTrack" -> {
                            val data: QueryData<SearchTrack> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { track ->
                                mutableResult.add(
                                    converter.searchTrackToInlineQuery(id, track)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "SearchArtist" -> {
                            val data: QueryData<SearchArtist> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { searchArtist ->
                                mutableResult.add(
                                    converter.searchArtistToInlineQuery(id, searchArtist)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "SearchAlbum" -> {
                            val data: QueryData<SearchAlbum> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { searchAlbum ->
                                mutableResult.add(
                                    converter.searchAlbumToInlineQuery(id, searchAlbum)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "Artist" -> {
                            val data: Artist = jacksonObjectMapper().readValue(String(bytes))
                            mutableResult.add(converter.artistToInlineQuery(id, data))
                            getResult(mutableResult)
                        }
                        "ArtistTopTrack" -> {
                            val data: QueryData<ArtistsTopTrack> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { topTrack ->
                                mutableResult.add(
                                    converter.artistTopTrackToInlineQuery(id, topTrack)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "ArtistAlbums" -> {
                            val data: QueryData<ArtistAlbum> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { album ->
                                mutableResult.add(
                                    converter.artistAlbumToInlineQuery(id, album)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "Genre" -> {
                            val data: QueryData<Genre> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { genre ->
                                mutableResult.add(
                                    converter.genreToInlineQuery(id, genre)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "ArtistByGenre" -> {
                            val data: QueryData<GenreArtist> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { genreArtist ->
                                mutableResult.add(
                                    converter.genreArtistToInlineQuery(id, genreArtist)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "ChartTracks" -> {
                            val data: QueryData<ChartTrack> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { genreArtist ->
                                mutableResult.add(
                                    converter.chartTrackToInlineQuery(id, genreArtist)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "ChartArtists" -> {
                            val data: QueryData<ChartArtist> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { genreArtist ->
                                mutableResult.add(
                                    converter.chartArtistToInlineQuery(id, genreArtist)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                        "ChartAlbums" -> {
                            val data: QueryData<ChartAlbum> = jacksonObjectMapper().readValue(String(bytes))
                            data.data?.forEach { genreArtist ->
                                mutableResult.add(
                                    converter.chartAlbumToInlineQuery(id, genreArtist)
                                )
                                id++
                            }
                            getResult(mutableResult)
                        }
                    }
                }
            }
    }

    private fun constructQuery(command: String) : Pair<String, String> {
        val params = command.split(" ")

        return when (params[0]) {
            "search-track" -> checkSearchTrack(params)
            "search-artists" -> checkSearchArtist(params)
            "search-albums" -> checkSearchAlbum(params)
            "genres" -> checkGenre(params)
            "artist" -> checkArtist(params)
            "chart" -> checkChart(params)
            else -> Pair("","")
        }
    }

    private fun checkSearchTrack(params: List<String>) : Pair<String, String> {
        val url = if (params.size == 2) "$url/search/track?q=${params[1]}" else ""
        return Pair("SearchTrack", url)
    }

    private fun checkSearchArtist(params: List<String>) : Pair<String, String> {
        return if (params.size == 2) {
            Pair("SearchArtist", "$url/search/artist?q=${params[1]}")
        }
        else if (params.size == 4 && params[2] == "track") {
            Pair("SearchTrack",
                "$url/search?q=artist:\"${params[1]}\" track:\"${params[3]}\""
            )
        }
        else Pair("", "")
    }

    private fun checkSearchAlbum(params: List<String>) : Pair<String, String> {
        return if (params.size == 2) Pair("SearchAlbum", "$url/search/album?q=${params[1]}") else Pair("", "")
    }

    private fun checkGenre(params: List<String>) : Pair<String, String> {
        return if (params.size == 1) Pair("Genre", "$url/genre")
        else if (params.size == 3 && params[2] == "artists"
            && params[1].matches("\\d+".toRegex())) {
            Pair("ArtistByGenre", "$url/genre/${params[1]}/artists")
        }
        else Pair("", "")
    }

    private fun checkArtist(params: List<String>) : Pair<String, String> {
        return if (params.size == 2 && params[1].matches("\\d+".toRegex())) {
            Pair("Artist", "$url/artist/${params[1]}")
        }
        else if (params.size == 3 && params[2] == "top" && params[1].matches("\\d+".toRegex())) {
            Pair("ArtistTopTrack", "$url/artist/${params[1]}/${params[2]}")
        }
        else if (params.size == 3 && params[2] == "albums" && params[1].matches("\\d+".toRegex())) {
            Pair("ArtistAlbums", "$url/artist/${params[1]}/${params[2]}")
        }
        else Pair("", "")
    }

    private fun checkChart(params: List<String>) : Pair<String, String> {
        return if (params.size == 3 && params[1].matches("\\d+".toRegex())) {
            when (params[2]) {
                "tracks" -> Pair("ChartTracks", "$url/chart/${params[1]}/${params[2]}")
                "artists" -> Pair("ChartArtists", "$url/chart/${params[1]}/${params[2]}")
                "albums" -> Pair("ChartAlbums", "$url/chart/${params[1]}/${params[2]}")
                else -> Pair("","")
            }
        }
        else Pair("", "")
    }
}