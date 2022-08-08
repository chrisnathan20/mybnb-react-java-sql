/*
User (username, name, address, country, city, postal code, dob, sin, occupation)
Renter (username, payment_info)
Listing (listing_id, longitude, latitude, address, country, city, postal code, base_price)
Special-prices (listing_id, start_date, end_date, price)
Booking (booking_id, total_cost) // note that booking_id is synonymous with unavail_id
Comment (comment_id, title, content, rating)
host-listing (username, listing_id)
listing-amenity (listing_id,amenity_name)
listing-type (listing_id, type)
listing-unavailability (unavail_id, start_date, end_date, listing_id)
booking-comment (booking_id, comment_id)
booking-renter (booking_id, username) 
*/

CREATE DATABASE MyBnB;
USE MyBnB;

CREATE TABLE User(
    username varchar(20) PRIMARY KEY, 
    name varchar(50), 
    address varchar(30), 
    country varchar(20), 
    city varchar(20), 
    postal_code varchar(7), 
    dob DATE, 
    sin varchar(11),
    password varchar(30));

CREATE TABLE Renter(
    username varchar(20) PRIMARY KEY, 
    payment_info varchar(16), 
    foreign key(username) references User(username));

CREATE TABLE Listing(
    listing_id integer AUTO_INCREMENT PRIMARY KEY, 
    longitude DECIMAL(9,6), 
    latitude DECIMAL(9,6), 
    address varchar(30), 
    country varchar(20), 
    city varchar(20), 
    postal_code varchar(7), 
    base_price DECIMAL(10,2));
    
CREATE TABLE amenities(
	name varchar(30) PRIMARY KEY
);

CREATE TABLE type(
	name varchar(30) PRIMARY KEY
);

CREATE TABLE Special_prices(
    listing_id integer, 
    initial_date DATE, 
    end_date DATE, 
    price DECIMAL(10,2), 
    primary key(listing_id, initial_date, end_date),
    foreign key(listing_id) references Listing(listing_id));
    
CREATE TABLE listing_unavailability(
    unavail_id integer AUTO_INCREMENT PRIMARY KEY,
    initial_date DATE,
    end_date DATE,
    listing_id integer,
    foreign key(listing_id) references Listing(listing_id));
    
CREATE TABLE Booking(
    booking_id integer PRIMARY KEY,
    total_cost DECIMAL(10,2),
    status varchar(20),
    foreign key(booking_id) references listing_unavailability(unavail_id));

CREATE TABLE Comment(
    comment_id integer AUTO_INCREMENT PRIMARY KEY,
    title varchar(20),
    content varchar(255),
    rating integer,
    forUsername varchar(20),
    fromUsername varchar(20),
    foreign key(forUsername) references User(username),
    foreign key(fromUsername) references User(username));

CREATE TABLE host_listing(
    username varchar(20),
    listing_id integer,
    primary key(username, listing_id),
    foreign key(username) references User(username),
    foreign key(listing_id) references Listing(listing_id));

CREATE TABLE listing_amenity(
    listing_id integer,
    amenity_name varchar(30),
    primary key(listing_id, amenity_name),
    foreign key(listing_id) references Listing(listing_id),
    foreign key(amenity_name) references Amenities(name));

CREATE TABLE listing_type(
    listing_id integer PRIMARY KEY,
    type_name varchar(30),
    foreign key(listing_id) references Listing(listing_id),
    foreign key(type_name) references Type(name));

CREATE TABLE booking_comment(
    booking_id integer,
    comment_id integer PRIMARY KEY,
    foreign key(booking_id) references Booking(booking_id),
    foreign key(comment_id) references Comment(comment_id));

CREATE TABLE booking_renter(
    booking_id integer PRIMARY KEY,
    username varchar(20),
    foreign key(username) references User(username),
    foreign key(booking_id) references Booking(booking_id));
