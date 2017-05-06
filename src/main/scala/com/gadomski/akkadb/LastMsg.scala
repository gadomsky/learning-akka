package com.gadomski.akkadb

import akka.actor.Actor
import akka.event.Logging
import com.gadomski.akkadb.messages.TextMsg

/**
  * Created by Krzysiek on 06.05.2017.
  */
class LastMsg extends Actor {
  val log = Logging(context.system, this)
  var last: String = null

  override def receive: Receive = {
    case TextMsg(value) => {
      log.info("Received msg: {}", value)
      last = value
    }
    case o => log.info("Unknown msg {}", o)
  }
}
