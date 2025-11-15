package com.example.quotify.data

data class Quote(
    val id: Int,
    val text: String,
    val author: String,
    val category: QuoteCategory,
    val avatar: String = "👤"
) {
    companion object {
        fun getQuotes(): List<Quote> {
            return listOf(
                Quote(
                    1,
                    "Never forget those that helped before you even had to ask",
                    "ig-glossy_minds",
                    QuoteCategory.LIFE
                ),
                Quote(
                    2,
                    "The price of greatness is responsibility",
                    "ig-glossy_minds",
                    QuoteCategory.LEADERSHIP
                ),
                Quote(
                    3,
                    "Everything you can imagine is real",
                    "Pablo Picasso",
                    QuoteCategory.MOTIVATION
                ),
                Quote(
                    4,
                    "When life gives you monday, dip it in glitter and sparkle all day",
                    "Ella Woodward",
                    QuoteCategory.LIFE
                ),
                Quote(
                    5,
                    "Action without thought is empty. Thought without action is blind.",
                    "Kwame Nkrumah",
                    QuoteCategory.WISDOM
                ),
                Quote(
                    6,
                    "Do what you feel in your heart to be right - for you will be criticized anyway",
                    "Eleanor Roosevelt",
                    QuoteCategory.COURAGE
                ),
                Quote(
                    7,
                    "Do one thing everyday that scares you",
                    "Pablo Picasso",
                    QuoteCategory.COURAGE
                ),
                Quote(
                    8,
                    "The only way to do great work is to love what you do.",
                    "Steve Jobs",
                    QuoteCategory.SUCCESS
                ),
                Quote(
                    9,
                    "Believe you can and you're halfway there.",
                    "Theodore Roosevelt",
                    QuoteCategory.MOTIVATION
                ),
                Quote(
                    10,
                    "Success is not final, failure is not fatal.",
                    "Winston Churchill",
                    QuoteCategory.SUCCESS
                ),
                Quote(
                    11,
                    "The future belongs to those who believe in the beauty of their dreams.",
                    "Eleanor Roosevelt",
                    QuoteCategory.MOTIVATION
                ),
                Quote(
                    12,
                    "It is during our darkest moments that we must focus to see the light.",
                    "Aristotle",
                    QuoteCategory.WISDOM
                ),
                Quote(13, "Everything you can imagine is real", "Unknown", QuoteCategory.LIFE),
                Quote(
                    14,
                    "Love is the most beautiful thing in the world.",
                    "Unknown",
                    QuoteCategory.LOVE
                ),
                Quote(
                    15,
                    "A leader is one who knows the way, goes the way.",
                    "John C. Maxwell",
                    QuoteCategory.LEADERSHIP
                ),
                Quote(
                    16,
                    "The journey of a thousand miles begins with a single step.",
                    "Lao Tzu",
                    QuoteCategory.WISDOM
                ),
                Quote(
                    17,
                    "Innovation distinguishes between a leader and a follower.",
                    "Steve Jobs",
                    QuoteCategory.LEADERSHIP
                ),
                Quote(
                    18,
                    "Your time is limited, don't waste it.",
                    "Steve Jobs",
                    QuoteCategory.LIFE
                ),
                Quote(
                    19,
                    "The only impossible journey is the one you never begin.",
                    "Tony Robbins",
                    QuoteCategory.MOTIVATION
                ),
                Quote(
                    20,
                    "Life is what happens when you're busy making other plans.",
                    "John Lennon",
                    QuoteCategory.LIFE
                )
            )
        }
    }
}