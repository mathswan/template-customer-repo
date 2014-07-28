package views

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.{Error, Start}
import helpers.Common.localHost
import helpers.Error.errorUrl

class ErrorIntegrationSpec extends Specification {

  "go to page" should {
    "display the page" in new WithBrowser{
      browser.goTo(localHost + port + errorUrl)

      browser.pageSource must contain(Error.title)
    }
  }

  "next button" should {
    "display the customer repository page" in new WithBrowser {
      browser.goTo(localHost + port + errorUrl)

      browser.click(Error.exit)

      browser.pageSource must contain(Start.title)
    }
  }
}