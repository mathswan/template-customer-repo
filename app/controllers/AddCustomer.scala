package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Customer
import mappings.LastName.{lastNameMinLength, lastNameMaxLength}
import mappings.FirstName.{firstNameMinLength, firstNameMaxLength}
import constraints.FirstName.validFirstName
import constraints.LastName.validLastName
import constraints.MiddleName.validMiddleName

class AddCustomer extends Controller {

  val customerForm = Form(
    mapping(
      "firstName" -> (nonEmptyText(firstNameMinLength, firstNameMaxLength) verifying validFirstName),
      "middleName" -> optional(nonEmptyText(firstNameMinLength, firstNameMaxLength) verifying validMiddleName),
      "lastName" -> (nonEmptyText(lastNameMinLength, lastNameMaxLength) verifying validLastName)
    )(Customer.apply)(Customer.unapply)
  )

  def present = Action {
    Ok(views.html.addCustomer(customerForm))
  }

  def submit = Action {
    implicit request => customerForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.addCustomer(formWithErrors)),
      formValid => {
        val customer = if (formValid.middleName.isEmpty) formValid.firstName + " " + formValid.lastName
                       else formValid.firstName + " " + formValid.middleName.getOrElse("") + " " + formValid.lastName
        Redirect(routes.Success.present(customer))
      }
    )
  }
}