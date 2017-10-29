## Java REST example

### API endpoints

make sure your tomcat server is pointed to port 8082

List of CityDistances objects
http://localhost:8082/DistanceREST/rest/distances

Show me a list of distances less than {x} x is an integer representing miles
http://localhost:8082/DistanceREST/rest/distances/x

Update a distance with PUT
http://localhost:8082/DistanceREST/rest/distances?distance=1111&origin=Atlanta
this example will update object with origin city name Atlanta to a new distance of 1111 miles



