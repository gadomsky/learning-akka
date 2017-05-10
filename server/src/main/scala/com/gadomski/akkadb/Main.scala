package com.gadomski.akkadb

import akka.actor.{ActorSystem, Props}

/**
  * Created by Krzysiek on 08.05.2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("akkademy")
    system.actorOf(Props[AkkademyDb], name = "akkademy-db")

  }
}
