package chap09

object ObserverMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val feed: Feed = Feed()
        feed.registerObserver(NYTimes())
        feed.registerObserver(Guardian())
        feed.registerObserver(LeMonde())
        feed.notifyObservers("The queen said her favourite book is Lambdas in action!")

        val feedLambda = Feed()
        feedLambda.registerObserver { tweet: String ->
            if (tweet.isNotEmpty() && tweet.contains("money")) {
                println("Breaking news in NY! $tweet")
            }
        }
        feedLambda.registerObserver { tweet: String ->
            if (tweet.isNotEmpty() && tweet.contains("queen")) {
                println("Yet another news in London... $tweet")
            }
        }

        feed.notifyObservers("Money money money, show me the money!")
    }

    @FunctionalInterface
    interface Observer {
        fun notify(tweet: String)
    }

    interface Subject {
        fun registerObserver(o: Observer)
        fun registerObserver(f: (String) -> Unit) = Unit
        fun notifyObservers(tweet: String)
    }

    private class NYTimes : Observer {
        override fun notify(tweet: String) {
            if (tweet.isNotEmpty() && tweet.contains("money")) {
                println("Breaking news in NY! $tweet")
            }
        }
    }

    private class Guardian : Observer {
        override fun notify(tweet: String) {
            if (tweet.isNotEmpty() && tweet.contains("queen")) {
                println("Yet another news in London... $tweet")
            }
        }
    }

    private class LeMonde : Observer {
        override fun notify(tweet: String) {
            if (tweet.isNotEmpty() && tweet.contains("wine")) {
                println("Today, cheese, wine and news! $tweet")
            }
        }
    }

    private class Feed(val observers: MutableList<Observer> = mutableListOf()) : Subject {
        override fun registerObserver(o: Observer) {
            observers.add(o)
        }

        override fun notifyObservers(tweet: String) {
            observers.forEach { it.notify(tweet) }
        }
    }
}