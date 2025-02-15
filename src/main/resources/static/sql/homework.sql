//kerkesa 1
select c.customerName, count(*) as orders_nr
from customers c
         inner join orders o on
        c.customerNumber = o.customerNumber
group by c.customerNumber, c.customerName
having count(*) = (
    select count(*) from orders
    group by customerNumber
    order by count(*) desc
    limit 1
    );

// kerkesa 2
select * from customers c
                  left join orders o
                            on c.customerNumber = o.customerNumber
                  inner join orderdetails od
                             on o.orderNumber = od.orderNumber
where c.country = 'Germany';

//kerkesa 3
select customerName, sum(amount) as revenue
from customers c inner join payments p
                            on c.customerNumber = p.customerNumber
group by c.customerNumber

//kerkesa 4
select distinct p.productCode, p.productName
from products p
         inner join orderdetails od on p.productCode = od.productCode
         inner join orders o on od.orderNumber = o.orderNumber
where o.orderDate between '2004-12-01' and '2004-12-31'

//kerkesa 5
create table employeedetails (
    bankAccount varchar(50),
    address varchar(100),
    phoneNumber varchar(15),
    personalEmail varchar(50),
    employeeNumber int,
    foreign key (employeeNumber) references employees(employeeNumber)
);