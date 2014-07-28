package controllers

import play.api.mvc.{Action, Controller}
import models.Customer

class Success extends Controller {

  def present(customer: String) = Action {
    Ok(views.html.success(customer))
  }
}
