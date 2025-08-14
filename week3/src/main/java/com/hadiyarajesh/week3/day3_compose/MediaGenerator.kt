package com.hadiyarajesh.week3.day3_compose

import androidx.compose.ui.graphics.Color

object MediaGenerator {
    private val allowedBorderColors =
        listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta)
    private val usernames = listOf("rajesh", "vikas", "aquib", "swapna", "rajat", "suraj")

    fun generateMedia(type: MediaType, count: Int): List<Media> {
        val medias: List<Media> = buildList {
            repeat(count) { index ->
                val id = index + 1
                val thumbnail = "https://picsum.photos/id/${id}/500/500"
                val username = usernames.random()

                when (type) {
                    MediaType.Story -> {
                        val story = Story(
                            id = id,
                            thumbnail = thumbnail,
                            username = username,
                            borderColors = allowedBorderColors.shuffled().take(2)
                        )
                        add(story)
                    }

                    MediaType.Post -> {
                        val post = Post(
                            id = id,
                            thumbnail = thumbnail,
                            username = username,
                            borderColors = allowedBorderColors.shuffled().take(2)
                        )

                        add(post)
                    }
                }

            }
        }

        return medias
    }
}

enum class MediaType {
    Story,
    Post
}

interface Media {
    val id: Int
    val thumbnail: String
    val username: String
    val borderColors: List<Color>
}

data class Story(
    override val id: Int,
    override val thumbnail: String,
    override val username: String,
    override val borderColors: List<Color>
) : Media

data class Post(
    override val id: Int,
    override val thumbnail: String,
    override val username: String,
    override val borderColors: List<Color>
) : Media