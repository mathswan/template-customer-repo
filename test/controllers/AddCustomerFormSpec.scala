package controllers

import org.scalatest.{Matchers, WordSpec}
import helpers.AddCustomer.{firstNameBlank, firstNameValid, middleNameBlank, middleNameValid, lastNameBlank, lastNameValid}
import mappings.FirstName.{firstNameMinLength, firstNameMaxLength}
import mappings.LastName.{lastNameMinLength, lastNameMaxLength}

class AddCustomerFormSpec extends WordSpec with Matchers {

  "customer form" should {
    "reject if all fields blank" in {
      nameFiller(firstNameBlank, middleNameBlank, lastNameBlank).fold(
        formWithErrors => formWithErrors.errors.length should equal(4),
        f => fail("An error should occur")
      )
    }

    "reject if first name is less than min length" in {
      nameFiller(firstName = "a" * (firstNameMinLength -1), middleNameValid, lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if first name is greater than max length" in {
      nameFiller(firstName = "a" * (firstNameMaxLength +1), middleNameValid, lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if first name contains special characters" in {
      nameFiller(firstName = "$%^", middleNameValid, lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if first name contains numbers" in {
      nameFiller(firstName = "1234", middleNameValid, lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if middle name contains special characters" in {
      nameFiller(firstNameValid, middleName = "&*£", lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if middle name contains numbers" in {
      nameFiller(firstNameValid, middleName = "1234", lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if last name is less than min length" in {
      nameFiller(firstNameValid, middleNameValid, lastName = "a" * (lastNameMinLength -1)).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if last name is more than max length" in {
      nameFiller(firstNameValid, middleNameValid, lastName = "a" * (lastNameMaxLength +1)).fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if last name contains special characters" in {
      nameFiller(firstNameValid, middleNameValid, lastName = "*$£").fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if last name contains numbers" in {
      nameFiller(firstNameValid, middleNameValid, lastName = "1234").fold(
        formWithErrors => formWithErrors.errors.length should equal(1),
        f => fail("An error should occur")
      )
    }

    "reject if middle name and last name is valid and first name is blank" in {
      nameFiller(firstName = firstNameBlank, middleName = middleNameValid, lastName = lastNameValid).fold(
        formWithErrors => formWithErrors.errors.length should equal(2),
        f => fail("An error should occur")
      )
    }

    "accept if last name contains allowed characters -" in {
      nameFiller(firstNameValid, middleNameValid, lastName = "Smith-Jones").fold(
        formWithErrors => fail("An error should occur"),
        f => {
          f.firstName should equal(firstNameValid)
          f.middleName should equal(Some(middleNameValid))
          f.lastName should equal("Smith-Jones")
        }
      )
    }


    "accept if valid first name and surname only are entered" in {
      nameFiller(firstNameValid, middleNameBlank, lastNameValid).fold(
        formWithErrors => fail("An error should occur"),
        f => {
          f.firstName should equal(firstNameValid)
          f.lastName should equal(lastNameValid)
        }
      )
    }

    "accept if valid first, middle and last names are entered" in {
      nameFiller(firstName = firstNameValid, middleName = middleNameValid, lastName = lastNameValid).fold(
        formWithErrors => fail("An error should occur"),
        f => {
          f.firstName should equal(firstNameValid)
          f.middleName should equal(Some(middleNameValid))
          f.lastName should equal(lastNameValid)
        }
      )
    }
  }

  def nameFiller(firstName: String, middleName: String, lastName: String) = {
    val addCustomer = new AddCustomer

    addCustomer.customerForm.bind(
      Map(
        "firstName" -> firstName,
        "middleName" -> middleName,
        "lastName" -> lastName
      )
    )
  }
}