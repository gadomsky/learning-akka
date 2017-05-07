package com.gadomski.akkadb

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.gadomski.akkadb.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable.HashMap

/**
  * Created by Krzysiek on 06.05.2017.
  */
class AkkademyDb extends Actor {
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest [ key: {}, value: {}]", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest [key: {}]", key)
      val response: Option[Object] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    case o =>
      log.info("received unknown message: {}", o)
      Status.Failure(new ClassNotFoundException)
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("akkademy")
    system.actorOf(Props[AkkademyDb], name = "akkademy-db")
  }
}
