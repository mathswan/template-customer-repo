package constraints

import play.api.data.validation.{ValidationError, Invalid, Valid, Constraint}
import mappings.LastName.lastNameRegex

object LastName {

  def validLastName: Constraint[String] = Constraint[String]("constraint.validLastName") { input =>
    lastNameRegex.pattern.matcher(input).matches match {
      case true => Valid
      case false => Invalid(ValidationError("error.validLastName"))
    }
  }
}