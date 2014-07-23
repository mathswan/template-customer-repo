Template Customer Repo
====

Build status for master branch [![Build Status](https://travis-ci.org/mathswan/template-customer-repo.svg?branch=master)](https://travis-ci.org/mathswan/template-customer-repo)

Code coverage for master branch [![Coverage Status](https://coveralls.io/repos/mathswan/template-customer-repo/badge.png)](https://coveralls.io/r/mathswan/template-customer-repo)


This is a Scala sample project. It will consist of:

* one newly created Play project with a start page, application/form page and a summary page
* Unit and Integration Tests
* Twitter Bootstrap

The point of this application is to show how to quickly setup a simple Play application which uses Git with GitHub to store it's code

Web framework
-------------
I am using [Play framework](http://www.playframework.com/documentation/2.3.x/Home) because:

* It is a framework I have used for Vehicles Online at the DVLA.

Twitter Bootstrap
-------------
I am using [Twitter Bootstrap](http://www.http://getbootstrap.com/) because:

* Bootstrap is the most popular HTML, CSS, and JS framework for developing responsive, mobile first projects on the web.

Version control
---------------

I am using Git with Github because:

* I am familiar with Github from using it on several projects.
* I prefer using Git for version control as it works well with TDD's short iterations of coding.

Build server
------------
I am using [Travis CI](https://travis-ci.org/mathswan) as my build server because:

* It offers incredibly fast setup with a Github account.
* It is hosted in the cloud (unlike a Jenkins server that runs as a local instance).
* It automatically builds branches.
* It automatically builds forks!
* It is free.

Code coverage
-------------
When the Continuous Integration build runs, the tests will gather coverage statistics and post the results online to [Coveralls](https://coveralls.io).

Offline I run the sbt plugin for [Scoverage](https://github.com/scoverage/sbt-scoverage). From the sbt console run 'scoverage:test' to make it run the tests and output statistics to html files.