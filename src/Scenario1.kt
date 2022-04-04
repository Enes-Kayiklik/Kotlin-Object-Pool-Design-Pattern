import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pool.Pool

/**
 * 10 pre-prepared objects are kept in the Object Pool. But no more than 10 objects are allowed.
 * If 10 objects are in use, it is ensured that the requesters are waited. A request was made by
 * starting 15 separate threads. Only 10 of these 15 threads were able to retrieve objects from the
 * object pool and left the object pool back after use. Out of 15 threads, 5 of which
 * could not receive an object were made to wait and continued to run when the object
 * pool responded to the object request.
 **/
private val instance = Pool.getInstance(10)
suspend fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        interact("1", 10F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("2", 7F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("3", 8F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("4", 11F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("5", 7.1F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("6", 8.1F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("7", 8F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("8", 9F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("9", 6F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("10", 5F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("11", 13F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("12", 12F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("13", 10F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("14", 14F)
    }
    CoroutineScope(Dispatchers.IO).launch {
        interact("15", 15F)
    }
    delay(600_000)
}

private suspend fun interact(scopeName: String, delayInSec: Float) {
    var connectionObject = instance.getConnection()
    while (connectionObject == null) {
        delay(10)
        connectionObject = instance.getConnection()
    }
    println("---> Borrowed one Object From Scope $scopeName <---")
    delay((delayInSec * 1000).toLong())
    instance.releaseConnection(connectionObject)
    println("---> Released one Object From Scope $scopeName <---")
}