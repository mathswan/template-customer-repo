package controllers

import play.api.test.{FakeRequest, WithApplication}
import play.api.test.Helpers._
import org.scalatest.{Matchers, WordSpec}
import models.Customer

class SuccessUnitSpec extends WordSpec with Matchers {

  "present" should {
    "present the page" in new WithApplication {
      val customer: Customer = Customer("Mike", Some("Arthur"), "Jones")

      val success = new Success
      val result = success.present("Matthew")(FakeRequest())

      status(result) should equal(OK)
      //ToDo this test is currently causing an internal server error (but is passing) needs investigation
    }
  }
}
