package com.gadomski.akkadb

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.gadomski.akkadb.messages.{SetRequest, TextMsg}
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

/**
  * Created by Krzysiek on 06.05.2017.
  */
class LastMsgSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("lastMsg") {
    describe("given TextMsg") {
      it("should save value") {
        val actorRef = TestActorRef(new LastMsg)
        actorRef ! TextMsg("msg1") //tell (!) is synchronous only here in TestActorRef. Normally is not.
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.last should equal("msg1")
      }
    }
    describe("given two TextMsg") {
      it("should save last value") {
        val actorRef = TestActorRef(new LastMsg)
        actorRef ! TextMsg("msg1") //tell (!) is synchronous only here in TestActorRef. Normally is not.
        actorRef ! TextMsg("msg2") //tell (!) is synchronous only here in TestActorRef. Normally is not.
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.last should equal("msg2")
      }
    }
  }
}
