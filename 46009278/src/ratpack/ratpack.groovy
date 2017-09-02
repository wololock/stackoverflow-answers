import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {

        get("blocking") {
            println "${new Date()} - received blocking request"
            Thread.sleep(10000)
            println "${new Date()} - replying blocking request"
            render ""
        }

        get("non-blocking") {
            println "${new Date()} - received non-blocking request"
            blocking {
                Thread.sleep(10000)
            } then{
                println "${new Date()} - replying non-blocking request"
                render ""
            }
        }

    }
}
