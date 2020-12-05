
create table category_list (
	category_id uuid PRIMARY KEY,
	category_description varchar(100)
);

create table auction (
	id   uuid PRIMARY KEY,
	item_name   varchar(100) ,
	description   varchar(100),
	category   uuid REFERENCES category_list (category_id),
	start_price   NUMERIC(10,3),
	curr_price   NUMERIC(10,3),
	buynow_price   NUMERIC(10,3),
	startTime   varchar(20),
	endTime   varchar(20),
	highest_bidder   varchar(50),
	seller   varchar(50),
	flag   boolean,
    notification integer DEFAULT 87000,
	status   char(36)
);
