package pool

import model.Connection
import kotlin.math.abs

class Pool private constructor(
    initialPoolSize: Int = 10
) {

    var poolSize: Int = initialPoolSize
        private set(value) {
            // value is new value
            // field is old value
            if (field != value) {
                val diff = value - field
                field = value
                updatePoolObjectCount(diff)
            }
        }

    @Synchronized
    fun availableSize() = _available.count()

    @Synchronized
    fun usingSize() = _inUse.count()

    private val _available = mutableListOf<Connection>()
    private val _inUse = mutableListOf<Connection>()

    init {
        createInitialObjects()
    }

    private fun createInitialObjects() {
        for (i in 0 until poolSize) _available.add(Connection(title = "$i"))
    }

    @Synchronized
    private fun updatePoolObjectCount(difference: Int) {
        if (difference < 0) {
            // decrease pool size by difference
            if (availableSize() > abs(difference)) {
                for (i in 0 until abs(difference)) _available.removeAt(i)
            } else _available.clear()

        } else {
            // increase pool size by difference
            for (i in 0 until difference) _available.add(Connection(title = "$i / $difference"))
        }
    }

    @Synchronized
    fun getConnection(): Connection? {
        return if (availableSize() > 0) {
            val item = _available[0]
            _available.removeAt(0)
            _inUse.add(item)
            item
        } else null
    }

    @Synchronized
    fun releaseConnection(item: Connection) {
        // do not add released item if pool size decreased.
        if (availableSize() + 1 <= poolSize) _available.add(item)
        _inUse.remove(item)
    }

    companion object {
        @Volatile
        private var instance: Pool? = null

        @Synchronized
        fun getInstance(initialPoolSize: Int = 10): Pool {
            val result = instance ?: Pool(initialPoolSize).also { instance = it }
            result.poolSize = initialPoolSize
            return result
        }
    }
}