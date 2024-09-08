package dev.voidvoxel.betterbitter.api

object TextHelper {
    @JvmStatic
    fun randomBridge(): String {
        if (Math.random() <= 0.2)
            return "is going to"
        if (Math.random() <= 0.2)
            return "can"
        else if (Math.random() <= 0.2)
            return "could"
        else if (Math.random() <= 0.2)
            return "may"
        else if (Math.random() <= 0.2)
            return "will"
        else if (Math.random() <= 0.2)
            return "might"
        else if (Math.random() <= 0.2)
            return "feels"
        else if (Math.random() <= 0.2)
            return "feels like"
        else if (Math.random() <= 0.2)
            return "likes"
        else if (Math.random() <= 0.2)
            return "likes to"

        return "is"
    }
    @JvmStatic
    fun randomVerb(): String {
        if (Math.random() <= 0.1)
            return "jump"
        else if (Math.random() <= 0.1)
            return "walk"
        else if (Math.random() <= 0.1)
            return "swim"
        else if (Math.random() <= 0.1)
            return "cook"
        else if (Math.random() <= 0.1)
            return "create"
        else if (Math.random() <= 0.1)
            return "craft"
        else if (Math.random() <= 0.1)
            return "destroy"
        else if (Math.random() <= 0.1)
            return "bake"
        else if (Math.random() <= 0.1)
            return "melt"
        else if (Math.random() <= 0.1)
            return "become"
        else if (Math.random() <= 0.1)
            return "avoid"
        else if (Math.random() <= 0.1)
            return "smell"
        else if (Math.random() <= 0.1)
            return "taste"
        else if (Math.random() <= 0.1)
            return "see"
        else if (Math.random() <= 0.1)
            return "feel"
        else if (Math.random() <= 0.1)
            return "hear"
        else if (Math.random() <= 0.1)
            return "wish"
        else if (Math.random() <= 0.1)
            return "try"
        else if (Math.random() <= 0.1)
            return "climb"
        else if (Math.random() <= 0.1)
            return "fly"
        else if (Math.random() <= 0.1)
            return "plant"
        else if (Math.random() <= 0.1)
            return "approach"

        return "exist"
    }

    @JvmStatic
    fun randomInsanityMessage(): String {
        if (Math.random() <= 0.5) {
            return randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord()
        } else if (Math.random() <= 0.5) {
            return "The " + randomInsanityWord() + randomBridge() + " " + randomVerb() + " inside of " + randomInsanityWord() + "."
        } else if (Math.random() <= 0.5) {
            return "Can you see " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "?"
        } else if (Math.random() <= 0.5) {
            return "Turn around. " + randomInsanityWord() + " will " + randomVerb() + " " + randomInsanityWord() + "!"
        } else if (Math.random() <= 0.5) {
            return "What if " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "?"
        } else if (Math.random() <= 0.4) {
            return "Why are you " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "?"
        } else if (Math.random() <= 0.4) {
            return "Why won't you " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "?"
        } else if (Math.random() <= 0.4) {
            return "What if " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "?"
        } else if (Math.random() <= 0.4) {
            return "Sometimes " + randomInsanityWord() + " " + randomBridge() + " " + randomInsanityWord() + " " + randomVerb() + " " + randomInsanityWord() + "."
        } else {
            return randomInsanityWord() + "..."
        }
    }

    @JvmStatic
    fun randomInsanityWord(): String {
        var word = "§k" + "42".repeat(Math.round(Math.random() * 6).toInt() + 1)

        if (Math.random() <= 0.35)
            word += " " + randomInsanityWord()

        return "$word§r"
    }
}