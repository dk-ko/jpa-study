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

-- orders 테이블 foreign key 추가
-- orders : member = N : 1
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

-- order_item 테이블 foreign key 추가
-- order_item : orders = N : 1
alter table order_item
add constraint FK_order_item_orders
foreign key (order_id)
references orders(order_id);

-- order_item : item = N : 1
alter table order_item
add constraint FK_order_item_item
foreign key (item_id)
references item(item_id);

-- 생성된 제약 조건 출력
select * from information_schema.table_constraints
-- where constraint_schema = 'db_name';

-- 요구사항에 맞춰 추가된 테이블 생성 (category, delivery, category_item)
create table category(
category_id bigint primary key,
parent_id bigint,
name varchar(100)
);

create table delivery(
delivery_id bigint primary key,
city varchar(100),
street varchar(100),
status enum('READY','COMP')
);

create table category_item(
category_id bigint,
item_id bigint
);

-- category_item 테이블 foreign key 추가
-- category_item : category = N : 1
alter table category_item
add constraint FK_category_item_category
foreign key (category_id)
references category(category_id);

-- category_item : item = N : 1
alter table category_item
add constraint FK_category_item_item
foreign key (item_id)
references item(item_id);

-- 맵핑 관계에 맞는 컬럼 추가
alter table orders
add (delivery_id varchar(100) unique);

-- orders 테이블 foreign key 추가
-- orders : delivery = 1 : 1
alter table orders
add constraint FK_orders_delivery
foreign key (delivery_id)
references delivery(delivery_id);
