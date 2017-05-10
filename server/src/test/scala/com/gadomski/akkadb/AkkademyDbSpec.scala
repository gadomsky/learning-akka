package com.gadomski.akkadb

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.gadomski.akkadb.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

/**
  * Created by Krzysiek on 06.05.2017.
  */
class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value") //tell (!) is synchronous only here in TestActorRef. Normally is not.
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }

}
