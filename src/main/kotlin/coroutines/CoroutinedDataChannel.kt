package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class CoroutinedDataChannel(coroutineRunner: CoroutineRunner) :
    CoroutinedData(coroutineRunner) {

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
                    mutableState += i
                else
                    break
            }
        }
    }

    override suspend fun incrementState() {
        channel.send(1)
    }
}