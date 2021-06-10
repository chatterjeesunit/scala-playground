package com.test.json

/**
 * Created by sunitc on 27/9/15.
 */
class SlackMessage (val message:String, val channel:String, val username:String) {


  override def toString = s"SlackMessage($message, $channel, $username)"
}
