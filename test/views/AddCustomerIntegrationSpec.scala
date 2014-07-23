package Integration

import org.specs2.mutable.Specification
import play.api.test.WithBrowser
import helpers.AddCustomer.addCustomerUrl
import helpers.Common.localHost
import helpers.AddCustomer.{firstNameBlank, firstNameValid, firstNameTextBox, addCustomerButton, backButton, lastNameValid, lastNameTextBox, clearButton}
import helpers.{AddCustomer, Start, Success}

class AddCustomerIntegrationSpec extends Specification {

  "go to page" should {
    "display the page" in new WithBrowser {
      browser.goTo(localHost + port + addCustomerUrl)

      browser.pageSource must contain(AddCustomer.title)
    }
  }

  "back button" should {
    "display the start page" in new WithBrowser {
      browser.goTo(localHost + port + addCustomerUrl)

      browser.click(backButton)

      browser.pageSource must contain(Start.title)
    }
  }

  "submit button" should {
    "redirect to success on correct input" in new WithBrowser {
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameValid
      browser.fill(lastNameTextBox) `with` lastNameValid

      browser.click(addCustomerButton)

      browser.pageSource must contain(Success.title)
    }

    "stay on customer repository on incorrect input" in new WithBrowser {
      browser.goTo(localHost + port + addCustomerUrl)
      browser.fill(firstNameTextBox) `with` firstNameBlank

      browser.click(addCustomerButton)

      browser.pageSource must contain(AddCustomer.title)
    }
  }

  "clear button" should {
    "redirect to add customer page" in new WithBrowser {
      browser.goTo(localHost + port + addCustomerUrl)

      browser.click(clearButton)

      browser.pageSource must contain(AddCustomer.title)
    }
  }
}