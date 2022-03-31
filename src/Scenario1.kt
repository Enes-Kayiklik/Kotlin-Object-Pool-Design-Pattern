import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pool.Pool

/**
 * 10 different thread with waiting list.
 **/
suspend fun main() {
    val instance = Pool.getInstance(10)
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 1 <---")
        delay(10_000)
        instance.releaseConnection(connectionObject)
        println("---> Released one Object From Scope 1 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 2 <---")
        delay(7_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 2 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 3 <---")
        delay(8_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 3 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 4 <---")
        delay(11_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 4 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 5 <---")
        delay(7_100)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 5 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 6 <---")
        delay(8_100)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 6 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 7 <---")
        delay(8_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 7 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 8 <---")
        delay(9_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 8 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 9 <---")
        delay(6_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 9 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 10 <---")
        delay(5_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 10 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 11 <---")
        delay(13_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 11 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 12 <---")
        delay(12_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 12 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 13 <---")
        delay(10_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 13 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 14 <---")
        delay(9_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 14 <---")
    }
    CoroutineScope(Dispatchers.IO).launch {
        var connectionObject = instance.getConnection()
        while (connectionObject == null) {
            delay(10)
            connectionObject = instance.getConnection()
        }
        println("---> Borrowed one Object From Scope 15 <---")
        delay(15_000)
        instance.releaseConnection(connectionObject!!)
        println("---> Released one Object From Scope 15 <---")
    }

    delay(600_000)
}