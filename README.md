Description

MyBnB is a travelling tool that allows renters to find Home away from Home by booking listings managed by Hosts.
It is implemented using React as its frontend, Java as its backend and MySQL as the database, connected using JDBC driver.


Assumptions:
- fixed base price for listings but editable special prices, this decision is to satisfy the condition that the price change can only change on days that the listing is available
- unable to browse unless logged in
- only renters can book listings
- types are restricted to just 4 (apartment, full house, private room, shared room)
- amenities are restricted to 10 (

Challenges:
- sample data was often tweaked to provide more variety in filtering so database was often deleted and reinitialized
- initially attempted to use Spring Boot however due to time constraint and lack of technical knowledge, we opted to using a HttpServer in Java after 2-3 days attempting of learning Spring Boot
- connecting react requests to the Java HttpServer Endpoints for the first time


User Manual:

- Install mySQL and set up root account with username: root and password: 12345

- run initalize ddl files to set up the database with its tables then run the sample data file to fill the database

- Run the Backend Java folder with a Java IDE such as Eclipse which will start the server at port 8080

- Run the frontend React Server by initially running npm install to handle dependencies and npm start to boot up the frontend

- Start browsing through MyBnB by either signup up or logging in



How to get Reports (All Requests are POST requests and tested using Postman):

Report 1: We would like to run a report and provide the total number of bookings in a specific date range by city. 
- found by sending a request to http://localhost:8080/mybnb/reportA with body of "start" for start date, "end" for end date, "city" for city
- note that date is in YYYY-MM-DD format

Report 2: We would like to run a report and provide the total number of bookings in a specific date range by a Zip code within a city. 
- found by sending a request to http://localhost:8080/mybnb/reportB with body of "start" for start date, "end" for end date, "city" for city
- note that date is in YYYY-MM-DD format

Report 3: We would like to run a report and provide the total number of listings per country. 
- found by sending a request to http://localhost:8080/mybnb/reportC with body of "country" for country

Report 4: We would like to run a report and provide the total number of listings per country and city. 
- found by sending a request to http://localhost:8080/mybnb/reportD with body of "country" for country, "city" for city

Report 5: We would like to run a report and provide the total number of listings per country and city, zip. 
- found by sending a request to http://localhost:8080/mybnb/reportE with body of "country" for country, "city" for city, "postal_code" for zip

Report 6: We would like to rank the hosts by the total number of listings they have overall per country 
- found by sending a request to http://localhost:8080/mybnb/reportF with body of "country" for country

Report 7: We would like to rank the hosts by the total number of listings they have overall per city 
- found by sending a request to http://localhost:8080/mybnb/reportG with body of "city" for city

Report 8: For every city and country a report should provide the hosts that have a
number of listings that is more than 10% of the number of listings in that
city and country. This is a query that identifies the possible commercial
hosts, something that the system should flag and prohibit.
- found by sending a request to http://localhost:8080/mybnb/reportH with body of "city" for city, "country" for country


Report 9: We would also like to rank the renters by the number of bookings in a specific time period 
- found by sending a request to http://localhost:8080/mybnb/reportI with body of "start" for start date, "end" for end date


Report 10: We would also like to rank the renters by the number of bookings in a specific time period per city
- found by sending a request to http://localhost:8080/mybnb/reportJ with body of "start" for start date, "end" for end date, "city" for city

Report 11: We would also like to report renter involved in the most number of cancellatons
- found by sending a request to http://localhost:8080/mybnb/reportK with empty body

Report 12: We would also like to report hosts involved in the most number of cancellatons
- found by sending a request to http://localhost:8080/mybnb/reportL with empty body

System Limitations:

- Frequently crashes when adding new special price or new unavailability as a Host for their respective listing, but running refresh immediately fixes it (temporarily)

- No Input validation therefore entering NULL values or misformatted values may cause web app to crash due to failed database operations

- Not all reports are fully implemented due to time constraint

- No real way to update 'upcoming' listings to completed real time



Future Improvement:

- Encryption of password for better privacy and protection

- Image file system implemented so listings are more attractive especially to first time Renters

- Proper Input validation in forms to prevent web app crashing due to failed database operations

- Full implementation of reports specified

- Implement a method to update 'upcoming' listings to completed real time
