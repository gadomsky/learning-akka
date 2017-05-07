package com.gadomski.akkadb.messages

/**
  * Created by Krzysiek on 06.05.2017.
  */
case class SetRequest(key: String, value: Object){}
case class GetRequest(key: String){}
case class KeyNotFoundException(key: String) extends Exception{}
