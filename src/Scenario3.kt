import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Connection
import pool.Pool

/**
 * Object Pool, which has 3 pre-prepared objects, works in 4 different threads.
 * The Object Pool object defined outside the threads receives or leaves
 * objects with 50% probability for each thread.
 **/
private val instance = Pool.getInstance(3)
suspend fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        interact("1", 2F)

    }

    CoroutineScope(Dispatchers.IO).launch {
        interact("2", 0.5F)

    }

    CoroutineScope(Dispatchers.IO).launch {
        interact("3", 1F)

    }

    CoroutineScope(Dispatchers.IO).launch {
        interact("4", 3F)
    }

    delay(600_000)
}

private suspend fun interact(scopeName: String, delayInSec: Float) {
    val usingInstances = mutableListOf<Connection>()
    while (true) {
        delay((delayInSec * 1000L).toLong())
        println("Pool Size --> ${instance.poolSize} *** Available Size --> ${instance.availableSize()} *** Using Size --> ${instance.usingSize()}")
        if ((0..1).random() == 1) {
            instance.getConnection()?.also {
                println("---> Borrowed one Object From Scope $scopeName <---")
                usingInstances.add(it)
            } ?: println("---- Available List is Empty From Scope $scopeName ----")
        } else {
            if (usingInstances.isNotEmpty()) {
                val remove = usingInstances[0]
                usingInstances.removeAt(0)
                instance.releaseConnection(remove)
                println("---> Released one Object From Scope $scopeName <---")
            }
        }
    }
}