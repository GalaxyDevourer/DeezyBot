import com.elbekD.bot.Bot
import com.elbekD.bot.types.*
import queries.CustomQuery
import utils.ViewClassConverter

const val BOT_TOKEN = ""
const val BOT_USERNAME = ""

fun main() {
    val bot = Bot.createPolling(BOT_USERNAME, BOT_TOKEN)
    val converter = ViewClassConverter()
    val customQuery = CustomQuery()

    bot.onCommand("/start") { msg, _ ->
        val buttons = mutableListOf<InlineKeyboardButton>()
        buttons.add(InlineKeyboardButton("Search", callback_data = "search ${msg.message_id.plus(1)}"))
        buttons.add(InlineKeyboardButton("Genres", callback_data = "genres ${msg.message_id.plus(1)}"))
        buttons.add(InlineKeyboardButton("Artists", callback_data = "artists ${msg.message_id.plus(1)}"))
        buttons.add(InlineKeyboardButton("Charts", callback_data = "charts ${msg.message_id.plus(1)}"))
        val keyboard: List<InlineKeyboardButton> = buttons
        val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
        bot.sendMessage(msg.chat.id, markup = InlineKeyboardMarkup(keyboardList), text = "Hi! What are you waiting for? Use the buttons below to choose whatever you want!")
    }

    bot.onCallbackQuery { query ->
        val params = query.data?.split(" ")
        val msgId = params?.get(1)?.toInt()

        val buttons = mutableListOf<InlineKeyboardButton>()
        when (params?.get(0)) {
            "search" -> {
                buttons.add(InlineKeyboardButton("Track", switch_inline_query_current_chat = "search-track Not_Afraid"))
                buttons.add(InlineKeyboardButton("Artists", switch_inline_query_current_chat = "search-artists Starset"))
                buttons.add(InlineKeyboardButton("Artist`s Track", switch_inline_query_current_chat = "search-artists Eminem track Mockingbird"))
                buttons.add(InlineKeyboardButton("Albums", switch_inline_query_current_chat = "search-albums Transmissions"))
                buttons.add(InlineKeyboardButton("Back to Menu", callback_data = "menu $msgId"))
                val keyboard: List<InlineKeyboardButton> = buttons
                val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
                bot.editMessageReplyMarkup(query.from.id, msgId, markup = InlineKeyboardMarkup(keyboardList))
            }
            "genres" -> {
                buttons.add(InlineKeyboardButton("Genres", switch_inline_query_current_chat = "genres"))
                buttons.add(InlineKeyboardButton("Artists by Genre", switch_inline_query_current_chat = "genres 111 artists"))
                buttons.add(InlineKeyboardButton("Back to Menu", callback_data = "menu $msgId"))
                val keyboard: List<InlineKeyboardButton> = buttons
                val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
                bot.editMessageReplyMarkup(query.from.id, msgId, markup = InlineKeyboardMarkup(keyboardList))
            }
            "artists" -> {
                buttons.add(InlineKeyboardButton("Search Artist", switch_inline_query_current_chat = "artist 111"))
                buttons.add(InlineKeyboardButton("Top Tracks", switch_inline_query_current_chat = "artist 111 top"))
                buttons.add(InlineKeyboardButton("Albums", switch_inline_query_current_chat = "artist 111 albums"))
                buttons.add(InlineKeyboardButton("Back to Menu", callback_data = "menu $msgId"))
                val keyboard: List<InlineKeyboardButton> = buttons
                val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
                bot.editMessageReplyMarkup(query.from.id, msgId, markup = InlineKeyboardMarkup(keyboardList))
            }
            "charts" -> {
                buttons.add(InlineKeyboardButton("Tracks", switch_inline_query_current_chat = "chart 111 tracks"))
                buttons.add(InlineKeyboardButton("Albums", switch_inline_query_current_chat = "chart 111 albums"))
                buttons.add(InlineKeyboardButton("Artists", switch_inline_query_current_chat = "chart 111 artists"))
                buttons.add(InlineKeyboardButton("Back to Menu", callback_data = "menu $msgId"))
                val keyboard: List<InlineKeyboardButton> = buttons
                val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
                bot.editMessageReplyMarkup(query.from.id, msgId, markup = InlineKeyboardMarkup(keyboardList))
            }
            "menu" -> {
                buttons.add(InlineKeyboardButton("Search", callback_data = "search $msgId"))
                buttons.add(InlineKeyboardButton("Genres", callback_data = "genres $msgId"))
                buttons.add(InlineKeyboardButton("Artists", callback_data = "artists $msgId"))
                buttons.add(InlineKeyboardButton("Charts", callback_data = "charts $msgId"))
                val keyboard: List<InlineKeyboardButton> = buttons
                val keyboardList: List<List<InlineKeyboardButton>> = listOf(keyboard)
                bot.editMessageReplyMarkup(query.from.id, msgId, markup = InlineKeyboardMarkup(keyboardList))
            }
        }

        bot.answerCallbackQuery(query.id)
    }

    bot.onInlineQuery { query ->
        val command = query.query
        if (command != "") {
            customQuery.executeQuery(command) { list ->
                bot.answerInlineQuery(query.id,
                    if (list.isNotEmpty()) list
                    else mutableListOf(
                        converter.getErrorInlineQuery(1,
                            "Nothing found by request :(",
                            "Please try again with a different request!")
                    )
                )
            }
        }
        else  {
            bot.answerInlineQuery(query.id,
                listOf(converter.getErrorInlineQuery(1,
                    "Your request is empty!",
                    "Please try again with a different request!"
                ))
            )
        }
    }

    bot.start()
}
