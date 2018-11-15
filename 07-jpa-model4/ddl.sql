create table CATEGORY_ITEM (
	CATEGORY_ID bigint not null,
	ITEM_ID bigint not null
);

create table Category (
	CATEGORY_ID bigint not null,
	name varchar(255),
	PARENT_ID bigint,
	primary key (CATEGORY_ID)
);

create table Delivery (
	DELIVERY_ID bigint not null,
	city varchar(255),
	status varchar(255),
	street varchar(255),
	zipcode varchar(255),
	primary key (DELIVERY_ID)
);

create table Item (
	DTYPE varchar(31) not null,
	ITEM_ID bigint not null,
	name varchar(255),
	price integer not null,
	stockQuantity integer not null,
	actor varchar(255),
	director varchar(255),
	author varchar(255),
	isbn varchar(255),
	artist varchar(255),
	etc varchar(255),
	primary key (ITEM_ID)
);

create table Member (
	MEMBER_ID bigint not null,
	createdDate timestamp,
	lastModifiedDate timestamp,
	city varchar(255),
	name varchar(255),
	street varchar(255),
	zipcode varchar(255),
	primary key (MEMBER_ID)
);

create table ORDERS (
	ORDER_ID bigint not null,
	createdDate timestamp,
	lastModifiedDate timestamp,
	orderDate timestamp,
	status varchar(255),
	DELIVERY_ID bigint,
	MEMBER_ID bigint,
	primary key (ORDER_ID)
);

create table ORDER_ITEM (
	ORDER_ITEM_ID bigint not null,
	count integer not null,
	orderPrice integer not null,
	ITEM_ID bigint,
	ORDER_ID bigint,
	primary key (ORDER_ITEM_ID)
);

alter table CATEGORY_ITEM 
	add constraint FK_CATEGORY_ITEM_ITEM
	foreign key (ITEM_ID) 
	references Item;

alter table CATEGORY_ITEM 
	add constraint FK_CATEGORY_ITEM_CATEGORY 
	foreign key (CATEGORY_ID) 
	references Category;

alter table Category 
	add constraint FK_CATEGORY_PARENT 
	foreign key (PARENT_ID) 
	references Category;

alter table ORDERS 
	add constraint FK_ORDERS_DELIVERY 
	foreign key (DELIVERY_ID) 
	references Delivery;

alter table ORDERS 
	add constraint FK_ORDERS_MEMBER 
	foreign key (MEMBER_ID) 
	references Member;

alter table ORDER_ITEM 
	add constraint FK_ORDER_ITEM_ITEM 
	foreign key (ITEM_ID) 
	references Item;

alter table ORDER_ITEM 
	add constraint FK_ORDER_ITEM_ORDER 
	foreign key (ORDER_ID) 
	references ORDERS;