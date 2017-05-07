package com.gadomski.akkadb

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

/**
  * Created by Krzysiek on 07.05.2017.
  */
class PongActorTest extends FunSpecLike with Matchers {

  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val pongActor = system.actorOf(Props(classOf[PongActor]))

  describe("Pong Actor") {
    it("should respond with Pong") {
      val future = pongActor ask "Ping"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "Pong")
    }

    it("should fail on unknown message") {
      val future = pongActor ? "unknown"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }

  describe("FutureExamples") {
    import scala.concurrent.ExecutionContext.Implicits.global
    it("should print to the console") {
      askPong("Ping").onSuccess({
        case x: String => println("replied with: " + x)
      })
      Thread.sleep(100)
    }

    it("should print to the console 2 ") {
      askPong("Ping").onComplete(x => println("2replied with: " + x.get))
      Thread.sleep(100)
    }

    it("should use on Complete") {
      val future: Future[String] = askPong("Ping").flatMap(x => askPong(x))
      future.onComplete {
        case y@Success(x) => println("future completed successfully " + y)
        case Failure(e) => println("future failed")
      }
      Thread.sleep(100)
    }

    it("should recover") {
      askPong("Dupa").recover {
        case t: Exception => "Jedziemy"
      }.onComplete {
        case Success(x) => println("future completed successfully " + x)
        case Failure(e) => println("future failed" + e)
      }
      Thread.sleep(100)
    }

    it("should recover with") {
      askPong("unknown").recoverWith {
        case t: Exception => askPong("Ping")
      }.onComplete {
        case Success(x) => println("future completed successfully " + x)
        case Failure(e) => println("future failed" + e)
      }
      Thread.sleep(100)
    }

    it("should combine futures") {
      val f1 = askPong("Pings")
      val f2 = askPong("Pong")
      val f25 = askPong("Pong")

      val f3 = for (
        res1 <- f1;
        res2 <- f2;
        res3 <- f25
      ) yield res1 + " _x_ " + res2 + " " + res3

      f3.onComplete {
        case Success(s) => println("Udało sie, a wynik to : " + s)
        case Failure(e) => println("Nie udało sie" + e.getMessage)
      }
    }

    it("should work with lists of futures") {
      val listOfFutures: List[Future[String]] = List("Pong", "Ping",
        "failed").map(x => askPong(x))
      val futureOfList: Future[List[String]] = Future.sequence(listOfFutures.map(f => f.recover({ case Exception => "exc" })))
    }
  }

  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]

}
