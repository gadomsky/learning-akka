package com.gadomski.akkadb

import akka.actor.{Actor, Status}

/**
  * Created by Krzysiek on 07.05.2017.
  */
class PongActor extends Actor {

  override def receive(): Receive = {
    case "Ping" => sender() ! "Pong"
    case "Pong" => sender() ! "Dupa"
    case _ => sender() ! Status.Failure(new Exception("Unknown message"))
  }
}
