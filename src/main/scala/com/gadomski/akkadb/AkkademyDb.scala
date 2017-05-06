package com.gadomski.akkadb

import akka.actor.Actor
import akka.event.Logging
import com.gadomski.akkadb.messages.SetRequest

import scala.collection.mutable.HashMap

/**
  * Created by Krzysiek on 06.05.2017.
  */
class AkkademyDb extends Actor {
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest [ key: {}, value: {}]", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }
}
