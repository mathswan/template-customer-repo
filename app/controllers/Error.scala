package controllers

import play.api.mvc.{Action, Controller}

class Error extends Controller {

  def present = Action {
    Ok(views.html.error())
  }
}

