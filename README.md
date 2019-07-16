# api-automation-retrofit2
This is an API automation test which use retrofit 2 as a networking library

Set up:
----------------------
1. Setup with IntelliJ
* IntelliJ IDE: https://www.jetbrains.com/idea/
* IntelliJ cucumber plugin : https://plugins.jetbrains.com/plugin/7212-cucumber-for-java

To run and create report:
* Ensure there is a local.properties with suitable values
* mvn -Pacceptance-test clean verify
* In IntelliJ, run tests by right-click & run AllTests.java or individual test classes

# References:
Retrofit2
* http://square.github.io/retrofit/
* Getting both headers and body from response: http://stackoverflow.com/questions/23896149/retrofit-robospice-get-response-headers-from-successful-request
* Might not be able to mix hosts in a single test, as adapter is set up with a single url?
* http://www.vmerror.com/2014/10/use-retrofit-with-jackson.html
* http://stackoverflow.com/questions/27230418/is-there-a-responseinterceptor-as-its-counterpart-requestinterceptor-compone
* http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
* http://www.tagwith.com/question_521494_how-to-specify-a-default-user-agent-for-okhttp-2-x-requests
* https://github.com/square/retrofit/issues/668

Cucumber
* https://www.tutorialspoint.com/cucumber/index.htm
* https://www.guru99.com/cucumber-tutorials.html
* http://toolsqa.com/cucumber/cucumber-tutorial/