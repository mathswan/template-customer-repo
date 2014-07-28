package controllers

import play.api.test.{FakeRequest, WithApplication}
import play.api.test.Helpers._
import org.scalatest.{Matchers, WordSpec}

class ErrorUnitSpec extends WordSpec with Matchers {

  "present" should {
    "present the page" in new WithApplication{
      val error = new Error
      val result = error.present(FakeRequest())

      status(result) should equal(OK)
    }
  }
}
