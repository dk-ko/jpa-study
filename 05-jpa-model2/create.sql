create table member(
member_id bigint primary key,
name varchar(100),
city varchar(100),
street varchar(100),
zipcode varchar(100)
);

create table orders(
order_id bigint primary key,
member_id bigint,
orderdate timestamp,
status enum('order','cancle')
);

alter table orders
add constraint FK_orders_member
foreign key (member_id)
references member(member_id);

create table order_item(
order_item_id bigint primary key,
order_id bigint,
item_id bigint,
orderprice int,
count int
);

create table item(
item_id bigint primary key,
name varchar(100),
price int,
stockquantity int
);

alter table order_item
add constraint FK_order_item_orders
foreign key (order_id)
references orders(order_id);

alter table order_item
add constraint FK_order_item_item
foreign key (item_id)
references item(item_id);

select * from information_schema.table_constraints 
where constraint_schema = 'jpa-study';