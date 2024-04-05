Book-My-Cab

BookMyCab Project App

BookMyCab is a Spring Boot application that simplifies the cab booking process. Users can add drivers, find available rides, choose a ride, and much more.

Features:
- Add users to the system
- Add drivers to the system
- Find available rides based on user location
- Choose a ride from the list of available drivers

Getting Started:
Prerequisites: Java JDK, Spring Boot, use in-memory data-structure for now, Maven

Installation:
Clone this repository to your local machine.

```bash
git clone https://github.com//BookMyCab.git
```

Usage:
API Documentation:

Example API endpoints:
- POST /api/addDriver: Add a new driver to the system.
- POST /api/addUser: Register a new user.
- POST /api/findRide: Find available rides based on user location.
- POST /api/chooseRide: Choose a ride from the list of available drivers.

Database Schema:
A brief overview of the database schema:
Did not use any database or NoSQL store, used in-memory data-structure for now as Requested.

Note: Also added PostMan endopoint refer/import Thikify assignment.postman_collection.json 