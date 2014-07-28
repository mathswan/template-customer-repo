package views

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.Common.localHost
import helpers.Success.successUrl
import helpers.AddCustomer._
import helpers.{Success}

class SuccessIntegrationSpec extends Specification {

  "go to page" should {
    "display the page" in new WithBrowser{
      browser.goTo(localHost + port + successUrl)

      browser.pageSource must contain(Success.title)
    }

    "display the users input of all fields from the previous page" in new WithBrowser{
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameValid
      browser.fill(middleNameTextBox) `with` middleNameValid
      browser.fill(lastNameTextBox) `with` lastNameValid

      browser.click(addCustomerButton)

      browser.pageSource must contain(firstNameValid)
      browser.pageSource must contain(middleNameValid)
      browser.pageSource must contain(lastNameValid)
    }

    "display the users input of mandatory fields only from the previous page" in new WithBrowser{
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameValid
      browser.fill(lastNameTextBox) `with` lastNameValid

      browser.click(addCustomerButton)

      browser.pageSource must contain(firstNameValid)
      browser.pageSource must contain(lastNameValid)
    }
  }
}