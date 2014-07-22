package views

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.Common.localHost
import helpers.Success.successUrl
import helpers.{Success,Start}

class SuccessIntegrationSpec extends Specification {

  "go to page" should {
    "display the page" in new WithBrowser{
      browser.goTo(localHost + port + successUrl)

      browser.pageSource must contain(Success.title)
    }
  }
}