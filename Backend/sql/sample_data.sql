/*
8 users

10 hosts
            -> host A has 1 listing (Scarb)
            -> host B has 3 listing but all in USA instead of Canada
            -> host C has 2 listing, this host is used to show that if there is a booking, we cannot delete a listing (Scarb, Vancouver)
            -> host D has 2 listing, this host we will use to succesfully delete a listing (Scarb, Scarb)
            -> host E has 1 listing (Scarb)
            -> host F has 1 listing (Scarb)
            -> host G has 1 listing (Scarb)
            -> host H has 2 listing (Scarb, Vancouver)
            -> host I has 3 listing (Scarb, Scarb, Scarb)
	        -> host J has 2 listing (Vancouver, Vancouver) <- lives in Vancouver


4 renters
            -> renter A has 8 bookings (2 cancelled, 5 completed, 1 upcoming)
            -> renter B has 6 bookings (1 cancelled, 3 completed, 2 upcoming)
            -> renter C has 4 bookings (1 cancelled, 3 completed)
            -> renter D has 4 bookings (4 cancelled)


-4 types
            -> full house, apartment, private room, shared room

18 listings
            -> host B's 2 listing in USA is to show that if we filter by country Canada in the search page, 
               then USA listings should not appear (1 of each type)
            -> The rest of the listings are all in Canada
                
                - 2 full house
                - 3 apartment
                - 3 private room
                - 3 shared room

            -> host A has 1 full house 
            -> host B has 1 private room, 1 shared room
            -> host C has 1 full house, 2 apartment, 1 private room
            -> host D has 1 apartment, 1 private room, 2 shared room

-20 amenities
            -> Pool Access
            -> Gym Access
            -> Wifi
            -> Kitchen
            -> Free parking
            -> Washer
            -> Dryer
            -> Air conditioning 
            -> Heating
            -> Self check-in
            -> Laptop-friendly workspace
            -> Pets allowed
            -> Crib
            -> High chair
            -> Bathtub
            -> Carbon Monoxide Alarm
            -> Smoke Alarm
            -> Fire Extinguisher
            -> First-aid kit
            -> Step-free entryway
            -> Accessible bathroom

*/   
        
-- hosts
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostA', 'Host Alex', '60 Town Centre Court', 'Canada', 'Scarborough', 'M1P 0B1', '2000-07-28', '612 459 834', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostB', 'Host Ben', '190 Borough Drive', 'Canada', 'Scarborough', 'M1P 0B6', '1990-07-20', '700 459 834', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostC', 'Host Charles', '740 Midland Avenue', 'Canada', 'Scarborough', 'M1K 4E1', '1992-08-15', '756 459 980', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostD', 'Host David', '1763 Comox St', 'Canada', 'Vancouver', 'V6G 1P6', '1989-09-20', '980 769 675', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostE', 'Host Evan', '833 Kennedy Rd', 'Canada', 'Scarborough', 'M1K 2E3', '1990-08-15', '342 459 233', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostF', 'Host Frank', '908 Eglinton Rd', 'Canada', 'Scarborough', 'M1K 2U8', '2000-11-20', '234 543 564', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostG', 'Host Gary', '1065 Eglinton Ave', 'Canada', 'Scarborough', 'M6C 2E1', '1985-09-20', '453 459 234', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostH', 'Host Harry', '20 Toronto St', 'Canada', 'Scarborough', 'M1P 2E1', '2000-09-20', '890 930 898', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostI', 'Host Ian', '90 Java St', 'Canada', 'Toronto', 'M1K 3E3', '2000-01-10', '940 930 393', '12345678');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('hostJ', 'Host Jack', '45 Brook Rd', 'Canada', 'Toronto', 'M1P 2K0', '1990-09-20', '234 102 343', '12345678');

-- renters
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('renterA', 'Renter Anne', '25 Town Centre Court', 'Canada', 'Scarborough', 'M1P 0B4', '2002-04-09', '902 459 945', '12345678');
insert into renter(username, payment_info) values ('renterA', '4929039985978715');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('renterB', 'Renter Becky', '15 Dundonald St', 'Canada', 'Toronto', 'M4Y 1K4', '2001-06-20', '920 239 374', '12345678');
insert into renter(username, payment_info) values ('renterB', '4234830212302930');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('renterC', 'Renter Cindy', '939 Beatty St', 'Canada', 'Vancouver', 'V6Z 3C1', '2003-01-20', '192 342 900', '12345678');
insert into renter(username, payment_info) values ('renterC', '9203283939402839');
insert into user(username, name, address, country, city, postal_code, dob, sin, password) values ('renterD', 'Renter Daisy', '60 Brian Harrison Way', 'Canada', 'Scarborough', 'M1P 5J5', '2001-01-01', '567 896 432', '12345678');
insert into renter(username, payment_info) values ('renterD', '9304829301922930');

-- type
insert into type(name) values ('full house');
insert into type(name) values ('apartment');
insert into type(name) values ('private room');
insert into type(name) values ('shared room');

-- amenities
insert into amenities(name) values ('Pool Access');
insert into amenities(name) values ('Gym Access');
insert into amenities(name) values ('Wifi');
insert into amenities(name) values ('Kitchen');
insert into amenities(name) values ('Free parking');
insert into amenities(name) values ('Washer');
insert into amenities(name) values ('Dryer');
insert into amenities(name) values ('Air conditioning');
insert into amenities(name) values ('Heating');
insert into amenities(name) values ('Self check-in');
insert into amenities(name) values ('Laptop-friendly workspace');
insert into amenities(name) values ('Pets allowed');
insert into amenities(name) values ('Crib');
insert into amenities(name) values ('High chair');
insert into amenities(name) values ('Bathtub');
insert into amenities(name) values ('Carbon Monoxide Alarm');
insert into amenities(name) values ('Smoke Alarm');
insert into amenities(name) values ('Fire Extinguisher');
insert into amenities(name) values ('First-aid kit');
insert into amenities(name) values ('Step-free entryway');
insert into amenities(name) values ('Accessible bathroom');

-- listing
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.773331, -79.254481, '25 Town Centre Court 2904', 'Canada', 'Scarborough', 'M1P 0B4', 80.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.773331, -79.254481, '25 Town Centre Court 2503', 'Canada', 'Scarborough', 'M1P 0B4', 80.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.710704, -79.393944, '2570 Kingston Road', 'Canada', 'Scarborough', 'M4P 0E6', 90.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.713618, -79.246665, '570 Birchmount Road', 'Canada', 'Scarborough', 'M1M 1L9', 90.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.742103, -79.221055, '3161 Eglinton Avenue East 2103', 'Canada', 'Scarborough', 'M1J 2G7', 150.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.742103, -79.221055, '3161 Eglinton Avenue East 1509', 'Canada', 'Scarborough', 'M1J 2G7', 150.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.773331, -79.254481, '61 Town Centre Court 1402', 'Canada', 'Scarborough', 'M1P 5C5', 79.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.773331, -79.254481, '61 Town Centre Court 1103', 'Canada', 'Scarborough', 'M1P 5C5', 79.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.774404, -79.250126, '88 Grangeway Ave 1302', 'Canada', 'Scarborough', 'M1H 0A2', 84.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.774404, -79.250126, '88 Grangeway Ave 1104', 'Canada', 'Scarborough', 'M1H 0A2', 84.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (43.781626, -79.247753, '36 Lee Centre Dr', 'Canada', 'Scarborough', 'M1H 3K2', 94.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (40.758709, -73.999575, '555 W 38th St 2815', 'USA', 'New York', '10018', 105.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (34.035854, -118.435191, '2431 S Sepulveda Blvd S1', 'USA', 'Los Angeles', '90064', 120.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (29.788282, -95.569913, '21095 Brittmoore Rd A1', 'USA', 'Houston', '77043', 105.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (49.279411, -123.120807, '930 Seymour Street 504', 'Canada', 'Vancouver', 'V6B 1B4', 90.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (49.316862, -122.984668, '1120 Strathaven Dr', 'Canada', 'Vancouver', 'V7H 2Z6', 97.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (49.286046, -123.139464, '1661 Davie St', 'Canada', 'Vancouver', 'V6G 0E1', 95.00);
insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (49.279411, -123.120807, '4188 Yew St', 'Canada', 'Vancouver', 'V6J 0G3', 87.00);

-- host-listing
insert into host_listing(username, listing_id) values('hostA', 1);
insert into host_listing(username, listing_id) values('hostB', 12);
insert into host_listing(username, listing_id) values('hostB', 13);
insert into host_listing(username, listing_id) values('hostB', 14);
insert into host_listing(username, listing_id) values('hostC', 2);
insert into host_listing(username, listing_id) values('hostC', 15);
insert into host_listing(username, listing_id) values('hostD', 3);
insert into host_listing(username, listing_id) values('hostD', 4);
insert into host_listing(username, listing_id) values('hostE', 5);
insert into host_listing(username, listing_id) values('hostF', 6);
insert into host_listing(username, listing_id) values('hostG', 7);
insert into host_listing(username, listing_id) values('hostH', 16);
insert into host_listing(username, listing_id) values('hostI', 8);
insert into host_listing(username, listing_id) values('hostI', 9);
insert into host_listing(username, listing_id) values('hostI', 10);
insert into host_listing(username, listing_id) values('hostJ', 17);
insert into host_listing(username, listing_id) values('hostJ', 18);

-- full house, apartment, private room, shared room
-- listing-type
insert into listing_type(listing_id, type_name) values(1, 'apartment');
insert into listing_type(listing_id, type_name) values(2, 'apartment');
insert into listing_type(listing_id, type_name) values(3, 'full house');
insert into listing_type(listing_id, type_name) values(4, 'full house');
insert into listing_type(listing_id, type_name) values(5, 'private room');
insert into listing_type(listing_id, type_name) values(6, 'shared room');
insert into listing_type(listing_id, type_name) values(7, 'private room');
insert into listing_type(listing_id, type_name) values(8, 'shared room');
insert into listing_type(listing_id, type_name) values(9, 'apartment');
insert into listing_type(listing_id, type_name) values(10, 'apartment');
insert into listing_type(listing_id, type_name) values(11, 'full house');
insert into listing_type(listing_id, type_name) values(12, 'apartment');
insert into listing_type(listing_id, type_name) values(13, 'full house');
insert into listing_type(listing_id, type_name) values(14, 'shared room');
insert into listing_type(listing_id, type_name) values(15, 'private room');
insert into listing_type(listing_id, type_name) values(16, 'apartment');
insert into listing_type(listing_id, type_name) values(17, 'shared room');
insert into listing_type(listing_id, type_name) values(18, 'private room');

-- listing_amenity
insert into listing_amenity(listing_id, amenity_name) values(1, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(3, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(5, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(6, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(8, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(14, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(15, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(17, 'Wifi');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Wifi');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Gym Access');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Gym Access');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Gym Access');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Gym Access');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Gym Access');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Pool Access');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Pool Access');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Pool Access');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Pool Access');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Pool Access');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Kitchen');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Kitchen');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Kitchen');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Kitchen');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Kitchen');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Free parking');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Free parking');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Washer');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Washer');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Washer');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Washer');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Dryer');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Dryer');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Dryer');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Dryer');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(3, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(5, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(15, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Air conditioning');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Air conditioning');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(3, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(5, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(15, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Heating');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Heating');

insert into listing_amenity(listing_id, amenity_name) values(3, 'Self check-in');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Self check-in');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Self check-in');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Self check-in');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(3, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(5, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(15, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Laptop-friendly workspace');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Laptop-friendly workspace');

insert into listing_amenity(listing_id, amenity_name) values(10, 'Bathtub');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Bathtub');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Bathtub');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Pets allowed');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Pets allowed');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Pets allowed');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Pets allowed');

insert into listing_amenity(listing_id, amenity_name) values(3, 'Crib');
insert into listing_amenity(listing_id, amenity_name) values(3, 'High Chair');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Carbon Monoxide Alarm');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Carbon Monoxide Alarm');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Carbon Monoxide Alarm');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Carbon Monoxide Alarm');

insert into listing_amenity(listing_id, amenity_name) values(1, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(2, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(3, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(5, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(6, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(7, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(8, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(9, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(10, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(12, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(14, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(15, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(16, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(17, 'Smoke Alarm');
insert into listing_amenity(listing_id, amenity_name) values(18, 'Smoke Alarm');

insert into listing_amenity(listing_id, amenity_name) values(3, 'Fire Extinguisher');
insert into listing_amenity(listing_id, amenity_name) values(4, 'Fire Extinguisher');
insert into listing_amenity(listing_id, amenity_name) values(11, 'Fire Extinguisher');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Fire Extinguisher');

insert into listing_amenity(listing_id, amenity_name) values(3, 'First-aid kit');
insert into listing_amenity(listing_id, amenity_name) values(4, 'First-aid kit');
insert into listing_amenity(listing_id, amenity_name) values(11, 'First-aid kit');
insert into listing_amenity(listing_id, amenity_name) values(13, 'First-aid kit');

insert into listing_amenity(listing_id, amenity_name) values(13, 'Step-free entryway');
insert into listing_amenity(listing_id, amenity_name) values(13, 'Accessible bathroom');

-- special prices
insert into Special_prices(listing_id, initial_date, end_date, price) values(1, '2022-08-14', '2022-08-20', 100.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(1, '2022-09-04', '2022-09-10', 120.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(4, '2022-09-11', '2022-09-15', 150.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(4, '2022-09-20', '2022-09-27', 130.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(4, '2022-10-01', '2022-10-04', 140.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(9, '2022-08-14', '2022-08-18', 110.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(9, '2022-09-01', '2022-09-05', 100.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(9, '2022-10-01', '2022-10-04', 80.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(9, '2022-10-10', '2022-10-15', 90.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(17, '2022-08-21', '2022-08-25', 105.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(17, '2022-09-01', '2022-09-07', 90.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(17, '2022-09-17', '2022-09-20', 85.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(17, '2022-09-25', '2022-09-30', 100.00);
insert into Special_prices(listing_id, initial_date, end_date, price) values(17, '2022-10-01', '2022-10-05', 80.00);

-- booking (listing unavailability)
insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-08-15', '2022-08-18', 1);
insert into booking(booking_id, total_cost, status) values(1, 300.00, "upcoming");
insert into booking_renter(booking_id, username) values(1, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-06-15', '2022-06-17', 10);
insert into booking(booking_id, total_cost, status) values(2, 168.00, "completed");
insert into booking_renter(booking_id, username) values(2, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-05-05', '2022-05-09', 1);
insert into booking(booking_id, total_cost, status) values(3, 320.00, "completed");
insert into booking_renter(booking_id, username) values(3, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-04-25', '2022-04-29', 1);
insert into booking(booking_id, total_cost, status) values(4, 320.00, "completed");
insert into booking_renter(booking_id, username) values(4, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-04-02', '2022-04-07', 15);
insert into booking(booking_id, total_cost, status) values(5, 450.00, "completed");
insert into booking_renter(booking_id, username) values(5, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-03-12', '2022-03-17', 15);
insert into booking(booking_id, total_cost, status) values(6, 450.00, "completed");
insert into booking_renter(booking_id, username) values(6, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 8);
insert into booking(booking_id, total_cost, status) values(7, 250.00, "cancelled");
insert into booking_renter(booking_id, username) values(7, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 17);
insert into booking(booking_id, total_cost, status) values(8, 190.00, "cancelled");
insert into booking_renter(booking_id, username) values(8, 'renterA');

insert into listing_unavailability(initial_date, end_date, listing_id) values("2022-08-25", "2022-08-27", 12);
insert into booking(booking_id, total_cost, status) values(9, 210.00, "upcoming");
insert into booking_renter(booking_id, username) values(9, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values("2022-09-27", "2022-09-29", 12);
insert into booking(booking_id, total_cost, status) values(10, 210.00, "upcoming");
insert into booking_renter(booking_id, username) values(10, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values("2022-04-27", "2022-04-29", 12);
insert into booking(booking_id, total_cost, status) values(11, 210.00, "completed");
insert into booking_renter(booking_id, username) values(11, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values("2022-04-17", "2022-04-19", 13);
insert into booking(booking_id, total_cost, status) values(12, 240.00, "completed");
insert into booking_renter(booking_id, username) values(12, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-04-10', '2022-04-15', 15);
insert into booking(booking_id, total_cost, status) values(13, 450.00, "completed");
insert into booking_renter(booking_id, username) values(13, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 2);
insert into booking(booking_id, total_cost, status) values(14, 240.00, "cancelled");
insert into booking_renter(booking_id, username) values(14, 'renterB');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-03-25', '2022-03-29', 1);
insert into booking(booking_id, total_cost, status) values(15, 320.00, "completed");
insert into booking_renter(booking_id, username) values(15, 'renterC');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-03-02', '2022-03-07', 15);
insert into booking(booking_id, total_cost, status) values(16, 450.00, "completed");
insert into booking_renter(booking_id, username) values(16, 'renterC');

insert into listing_unavailability(initial_date, end_date, listing_id) values('2022-02-12', '2022-02-17', 15);
insert into booking(booking_id, total_cost, status) values(17, 450.00, "completed");
insert into booking_renter(booking_id, username) values(17, 'renterC');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 2);
insert into booking(booking_id, total_cost, status) values(18, 320.00, "cancelled");
insert into booking_renter(booking_id, username) values(18, 'renterC');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 2);
insert into booking(booking_id, total_cost, status) values(19, 320.00, "cancelled");
insert into booking_renter(booking_id, username) values(19, 'renterC');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 2);
insert into booking(booking_id, total_cost, status) values(20, 320.00, "cancelled");
insert into booking_renter(booking_id, username) values(20, 'renterD');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 2);
insert into booking(booking_id, total_cost, status) values(21, 400.00, "cancelled");
insert into booking_renter(booking_id, username) values(21, 'renterD');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 18);
insert into booking(booking_id, total_cost, status) values(22, 174.00, "cancelled");
insert into booking_renter(booking_id, username) values(22, 'renterD');

insert into listing_unavailability(initial_date, end_date, listing_id) values(NULL, NULL, 18);
insert into booking(booking_id, total_cost, status) values(23, 348.00, "cancelled");
insert into booking_renter(booking_id, username) values(23, 'renterD');