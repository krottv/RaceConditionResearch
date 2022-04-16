import ConcurrentTestUtils.TEST_TIMEOUT
import ConcurrentTestUtils.testClass
import coroutines.*
import data.IntSharedMutableData
import data.VolatileIntMutableData
import kotlin.test.Test

class SuspendWorkTest {
    private val runner: SuspendRunner = SuspendRunnerImpl()

    @Test(timeout = TEST_TIMEOUT)
    fun testActor() {
        testClass(SuspendWorkActor(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testChannel() {
        testClass(SuspendWorkChannel(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testConfinement() {
        testClass(SuspendWorkConfinement(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testMutex() {
        testClass(SuspendWorkMutex(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testVolatile() {
        testClass(SuspendWork(runner, VolatileIntMutableData()), canBeLong = false, expectedToBeUnstable = true)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testBasic() {
        testClass(SuspendWork(runner, IntSharedMutableData()), canBeLong = false, expectedToBeUnstable = true)
    }
}
