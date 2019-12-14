## 
This folder contains instructions for Installing and running load tests.

##
Load Testing Tool : Apache Benchmark


Install instructions on linux :
$apt-get update
$apt-get install apache2-utils
$ab -V

To run load test tools
$ab -p input.txt -T application/json -H "Content-Type: application/json" -c 3 -n 1000 http://localhost:9000/proxy/rest-cmd/

In the above command : input.txt file contains the Json content to be sent as POST request.

Test Results :
loadTestResult.txt file contains the load test results.




