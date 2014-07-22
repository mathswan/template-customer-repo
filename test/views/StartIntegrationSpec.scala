package views

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.{Start, AddCustomer}
import helpers.Common.localHost

class StartIntegrationSpec extends Specification {

  "go to page" should {
    "display the page" in new WithBrowser{
      browser.goTo(localHost + port)

      browser.pageSource must contain(Start.title)
    }
  }

  "next button" should {
    "display the customer repository page" in new WithBrowser {
      browser.goTo(localHost + port)

      browser.click(Start.next)

      browser.pageSource must contain(AddCustomer.title)
    }
  }
}