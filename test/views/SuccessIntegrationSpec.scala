package views

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.Common.localHost
import helpers.Success.successUrl
import helpers.AddCustomer._
import helpers.{Success}

class SuccessIntegrationSpec extends Specification {

  "go to page" should {
    "return bad request if the user does not access a previous page" in new WithBrowser{
      browser.goTo(localHost + port + successUrl)

      browser.pageSource must contain("Bad request")
    }

    "display the page when user inputs of all fields from the previous page" in new WithBrowser{
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameValid
      browser.fill(middleNameTextBox) `with` middleNameValid
      browser.fill(lastNameTextBox) `with` lastNameValid

      browser.click(addCustomerButton)

      browser.pageSource must contain(Success.title)
      browser.pageSource must contain(firstNameValid)
      browser.pageSource must contain(middleNameValid)
      browser.pageSource must contain(lastNameValid)
    }

    "display the page when the user inputs mandatory fields only from the previous page" in new WithBrowser{
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameValid
      browser.fill(lastNameTextBox) `with` lastNameValid

      browser.click(addCustomerButton)

      browser.pageSource must contain(Success.title)
      browser.pageSource must contain(firstNameValid)
      browser.pageSource must contain(lastNameValid)
    }
  }
}