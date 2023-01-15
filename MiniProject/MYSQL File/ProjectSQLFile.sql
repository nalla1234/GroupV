/* 1.	Store the 10 products into database with product id, description, price, name, quantity.*/
create table productlist(ProductId int not null auto_increment primary key, Description varchar(255), Price int, Name varchar(255),Quantity int);
select * from productlist;
create table userdetails(UserId int not null auto_increment primary key,Username varchar(255),Email varchar(255), Password varchar(255)); 
select * from userdetails;
update productlist set Name = "Keyboard" where ProductId = 3;
update productlist set Name = "Smart Phone" where ProductId = 6;
create table purchasedetails(UserId int,
Username varchar(255),
ProductId varchar(255), 
purchasequantity int, Foreign key(UserId) references userdetails(UserId)); 

select * from  purchasedetails;
Alter table purchasedetails add Amount int;