package coroutines

import data.SharedMutableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class SuspendWorkChannel(suspendRunner: SuspendRunner, sharedMutableData: SharedMutableData) :
    SuspendWork(suspendRunner, sharedMutableData) {

    private val channel = Channel<Int>(capacity = 100)

    override suspend fun finished() {
        super.finished()
        channel.close()
    }

    override fun CoroutineScope.started() {
        launch(Dispatchers.IO) {
            while (!channel.isClosedForReceive) {
                val i = channel.receiveCatching().getOrNull()
                if (i != null)
                    sharedMutableData.increment()
                else
                    break
            }
        }
    }

    override suspend fun incrementState() {
        channel.send(1)
    }
}