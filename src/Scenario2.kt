import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Connection
import pool.Pool

/**
 * There is a reference to the object pool object defined as Singleton in each thread.
 * All threads can change the object pool size within themselves.
 * Since the Object pool is singleton, other instances are also affected by this situation.
 * This process was carried out using 4 different threads.
 **/
suspend fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        val usingInstances = mutableListOf<Connection>()
        while (true) {
            val instance = Pool.getInstance((3..5).random())
            delay(2000L)
            println("Pool Size --> ${instance.poolSize} *** Available Size --> ${instance.availableSize()} *** Using Size --> ${instance.usingSize()}")
            if ((0..1).random() == 1) {
                instance.getConnection()?.also {
                    println("---> Borrowed one Object From Scope 1 <---")
                    usingInstances.add(it)
                } ?: println("---- Available List is Empty From Scope 1 ----")
            } else {
                if (usingInstances.isNotEmpty()) {
                    val remove = usingInstances[0]
                    usingInstances.removeAt(0)
                    instance.releaseConnection(remove)
                    println("---> Released one Object From Scope 1 <---")
                }
            }
        }
    }

    CoroutineScope(Dispatchers.IO).launch {
        val usingInstances = mutableListOf<Connection>()
        while (true) {
            val instance = Pool.getInstance((3..5).random())
            delay(500L)
            println("Pool Size --> ${instance.poolSize} *** Available Size --> ${instance.availableSize()} *** Using Size --> ${instance.usingSize()}")
            if ((0..1).random() == 1) {
                instance.getConnection()?.also {
                    println("---> Borrowed one Object From Scope 2 <---")
                    usingInstances.add(it)
                } ?: println("---- Available List is Empty From Scope 2 ----")
            } else {
                if (usingInstances.isNotEmpty()) {
                    val remove = usingInstances[0]
                    usingInstances.removeAt(0)
                    instance.releaseConnection(remove)
                    println("---> Released one Object From Scope 2 <---")
                }
            }
        }
    }

    CoroutineScope(Dispatchers.IO).launch {
        val usingInstances = mutableListOf<Connection>()
        while (true) {
            val instance = Pool.getInstance((3..5).random())
            delay(1000L)
            println("Pool Size --> ${instance.poolSize} *** Available Size --> ${instance.availableSize()} *** Using Size --> ${instance.usingSize()}")
            if ((0..1).random() == 1) {
                instance.getConnection()?.also {
                    println("---> Borrowed one Object From Scope 3 <---")
                    usingInstances.add(it)
                } ?: println("---- Available List is Empty From Scope 3 ----")
            } else {
                if (usingInstances.isNotEmpty()) {
                    val remove = usingInstances[0]
                    usingInstances.removeAt(0)
                    instance.releaseConnection(remove)
                    println("---> Released one Object From Scope 3 <---")
                }
            }
        }
    }

    CoroutineScope(Dispatchers.IO).launch {
        val usingInstances = mutableListOf<Connection>()
        while (true) {
            val instance = Pool.getInstance((3..5).random())
            delay(3000L)
            println("Pool Size --> ${instance.poolSize} *** Available Size --> ${instance.availableSize()} *** Using Size --> ${instance.usingSize()}")
            if ((0..1).random() == 1) {
                instance.getConnection()?.also {
                    println("---> Borrowed one Object From Scope 4 <---")
                    usingInstances.add(it)
                } ?: println("---- Available List is Empty From Scope 4 ----")
            } else {
                if (usingInstances.isNotEmpty()) {
                    val remove = usingInstances[0]
                    usingInstances.removeAt(0)
                    instance.releaseConnection(remove)
                    println("---> Released one Object From Scope 4 <---")
                }
            }
        }
    }

    delay(600_000)
}