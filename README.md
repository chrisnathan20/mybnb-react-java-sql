Description

MyBnB is a travelling tool that allows renters to find Home away from Home by booking listings managed by Hosts.
It is implemented using React as its frontend, Java as its backend and MySQL as the database, connected using JDBC driver.


Assumptions:
- fixed base price for listings but editable special prices
- unable to browse unless logged in
- only renters can book listings
- types are restricted to just 4 (apartment, full house, private room, shared room)


User Manual:

- Install mySQL and set up root account with username: root and password: 12345

- run initalize ddl files to set up the database with its tables then run the sample data file to fill the database

- Run the Backend Java folder with a Java IDE such as Eclipse which will start the server at port 8080

- Run the frontend React Server by initially running npm install to handle dependencies and npm start to boot up the frontend

- Start browsing through MyBnB by either signup up or logging in


System Limitations:

- Frequently crashes when adding new special price or new unavailability as a Host for their respective listing, but running refresh immediately fixes it (temporarily)

- No Input validation therefore entering NULL values or misformatted values may cause web app to crash due to failed database operations

- Not all reports are fully implemented due to time constraint



Future Improvement:

- Encryption of password for better privacy and protection

- Image file system implemented so listings are more attractive especially to first time Renters

- Proper Input validation in forms to prevent web app crashing due to failed database operations

- Full implementation of reports specified
