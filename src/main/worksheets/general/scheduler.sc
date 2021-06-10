import java.util.concurrent.TimeUnit
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

val actorSystem = ActorSystem("Test", ConfigFactory.load("/resources/application.conf"))
  def myjob(id:String):Unit = {
  System.out.println(s"printing id = $id")
}
actorSystem.scheduler.scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS))(myjob("32424"))
