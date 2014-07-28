require 'simplecov'
require 'coveralls'

SimpleCov.formatter = Coveralls::SimpleCov::Formatter
SimpleCov.start do
   /target/scala-2.10/src_managed/main/routes_reverseRouting.scala
end