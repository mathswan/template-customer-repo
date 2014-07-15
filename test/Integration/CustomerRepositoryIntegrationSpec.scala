package Integration

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._

@RunWith(classOf[JUnitRunner])
class CustomerRepositoryIntegrationSpec extends Specification {

  "Customer Repository" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Welcome to the customer repository")
    }
  }
}