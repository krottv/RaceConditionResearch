package coroutines

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor

class CoroutinedDataActor(coroutineRunner: CoroutineRunner) :
    CoroutinedData(coroutineRunner) {

    private lateinit var channel: SendChannel<ActorMessage>

    override fun CoroutineScope.started() {

        channel = actor(capacity = 100, context = Dispatchers.IO) {
            var actorState = 0
            for (message in this) {
                when (message) {
                    is ActorMessage.Increment -> {
                        actorState ++
                    }
                    is ActorMessage.GetData -> message.deferred.complete(actorState)
                }
            }
        }
    }

    override suspend fun finished() {
        val completable = CompletableDeferred<Int>()
        channel.send(ActorMessage.GetData(completable))
        this.mutableState = completable.await()
        channel.close()
    }

    override suspend fun incrementState() {
        channel.send(ActorMessage.Increment)
    }
}


private sealed class ActorMessage {
    object Increment: ActorMessage()
    class GetData(val deferred: CompletableDeferred<Int>): ActorMessage()
}