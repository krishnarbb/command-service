cmd-service              → Project root
 └ cmd-api               → cmd api project
 └ cmd-impl              → cmd implementation project
 └ cmd-proxy-api         → cmd proxy api project
 └ cmd-proxy-impl        → cmd proxy implementation project
 └ integration-tests     → Integration tests
 └ load-test             → Load tests
 └ docs                  → documentation
 └ pom.xml               → Project root build file

Project Structure :
The API project contains the service interface. The API project can be depended on and consumed by other services.

The Implementation project depends on the API project, in order to implement it.

Command Service :
This system has two services, one called cmd-proxy, and one called cmd. 

Each service has two maven projects defined, 
  an API project :  cmd-proxy-api and cmd-api, and
  an implementation project : cmd-proxy-impl and cmd-impl. 

Additionally, cmd-proxy-impl depends on cmd-api, and uses that to invoke calls on cmd.

A separate domain-model project containing the domain logic and domain messages.  

Services depend on this domain-model.

