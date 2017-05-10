import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * Created by Krzysiek on 10.05.2017.
  */
class ClientIntegrationSpec extends FunSpecLike with Matchers{
  val client = new Client("127.0.0.1:2552")
  describe("akkademyDbClient") {
    it("should set a value") {
      client.set("12345", new Integer(123))

      val futureResult: Future[Any] = client.get("12345")
      val result = Await.result(futureResult, 10 seconds)
      result should equal(123)
    }
  }
}
