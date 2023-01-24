--DROP TABLE users;
CREATE TABLE users(
	ssn varchar(10) not null primary key,
	fname varchar(15) not null,
	lname varchar(15) not null,
	nickname varchar(15) not null,
	mail varchar(30) not null,
	psswd varchar(20) not null,
	bdate date,
	balance int default 0
);

--DROP TABLE books;
CREATE TABLE books(
	id varchar(10) not null primary key,
	name varchar(30) not null,
	author varchar(30) not null,
	dailyPrice int not null,
	time int not null,
	category varchar(20) not null,
	check(time != 0)
);

--DROP TABLE narrator;
CREATE TABLE narrator(
	ssn varchar(10) not null primary key,
	fname varchar(15) not null,
	lname varchar(15) not null,
	audience int default 0
);



--DROP TABLE narrates;
CREATE TABLE narrates(
	id varchar(10) not null primary key,
	nssn varchar(10) not null,
	bookid varchar(10) not null,
	foreign key (nssn) references narrator(ssn),
	foreign key (bookid) references books(id)
);



--DROP TABLE rental;
CREATE TABLE rental(
	id varchar(10) not null primary key,
	ussn varchar(10) not null,
	bookid varchar(10) not null,
	rentDay date not null,
	dayLimit int not null,
	price int,
	isExpired int default 0,
	foreign key (ussn) references users(ssn) on delete cascade,
	foreign key (bookid) references books(id) on delete cascade
	
);


CREATE OR REPLACE FUNCTION IncreaseNumberListeners( ) 
RETURNS TRIGGER AS $$
DECLARE
 	x INTEGER;
BEGIN 
    UPDATE narrator
    SET audience = audience + 1
    WHERE ssn = (SELECT ssn AS x
                  FROM narrator,narrates n
                  WHERE n.bookid = new.bookid AND n.nssn = ssn);
				  
    RETURN NEW;
END; 
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER trigger1 AFTER INSERT ON rental FOR EACH ROW EXECUTE PROCEDURE IncreaseNumberListeners();



INSERT INTO books VALUES('1000','İcatlarım','Nikola Tesla',8,179,'Biography');
INSERT INTO books VALUES('1001','Denemeler','Montaigne',13,447,'Nonfiction');
INSERT INTO books VALUES('1002','Bir Aşk Masalı','Ahmet Ümit',15,528,'Novel');
INSERT INTO books VALUES('1003','Babamın Bavulu','Orhan Pamuk',7,103,'Biography');
INSERT INTO books VALUES('1004','Gece Yarısı Kütüphanesi','Matt Haig',14,491,'Novel');
INSERT INTO books VALUES('1005','Huzursuzluk','Zülfü Livaneli',10,242,'Novel'); 
INSERT INTO books VALUES('1006','Suç ve Ceza','Fyodor Dostoyevski',20,1363,'Classic');
INSERT INTO books VALUES('1007','Bir İdam Mahkumunun Son Günü','Victor Hugo',8,166,'Classic');
INSERT INTO books VALUES('1008','Doğu Ekspresinde Cinayet','Agatha Cristie',14,392,'Detective');
INSERT INTO books VALUES('1009','Morgue Sokağı Cinayeti','Edgar Allan Poe',10,188,'Detective');
INSERT INTO books VALUES('1010','Sultanı Öldürmek','Ahmet Ümit',19,1139,'Detective');
INSERT INTO books VALUES('1011','Dahi Diktatör','Celal Şengör',8,252,'History');
INSERT INTO books VALUES('1012','Bunu Herkes Bilir','Emrah Safa Gürkan',15,486,'History');
INSERT INTO books VALUES('1013','Uğultulu Tepeler','Emily Bronte',16,746,'History');
INSERT INTO books VALUES('1014','Fatih Harbiye','Peyami Safa',7,236,'Novel');
INSERT INTO books VALUES('1015','Aylak Adam','Yusuf Atılgan',6,362,'Novel');
INSERT INTO books VALUES('1016','Dönüşüm','Franz Kafka',5,133,'Classic');
INSERT INTO books VALUES('1017','Milena''ya Mektuplar','Franz Kafka',10,300,'Nonfiction');
INSERT INTO books VALUES('1018','Yer Altından Notlar','Fyodor Dostoyevski',4,77,'Drama');
INSERT INTO books VALUES('1019','Gazi Mustafa Kemal Atatürk','İlber Ortaylı',9,777,'Biography');

INSERT INTO narrator(ssn,fname,lname) VALUES('2000','Hasan','Tanılmış');
INSERT INTO narrator(ssn,fname,lname) VALUES('2001','Barış','Özcan');
INSERT INTO narrator(ssn,fname,lname) VALUES('2002','Toprak','Sergen');
INSERT INTO narrator(ssn,fname,lname) VALUES('2003','Orhan','Pamuk');
INSERT INTO narrator(ssn,fname,lname) VALUES('2004','Sezin','Akbaşoğulları');
INSERT INTO narrator(ssn,fname,lname) VALUES('2005','Murat','Sarı');
INSERT INTO narrator(ssn,fname,lname) VALUES('2006','Şerif','Erol');
INSERT INTO narrator(ssn,fname,lname) VALUES('2007','Burak','Sergen');
INSERT INTO narrator(ssn,fname,lname) VALUES('2008','Murat','Eken');
INSERT INTO narrator(ssn,fname,lname) VALUES('2009','Kerim','Öztürk');
INSERT INTO narrator(ssn,fname,lname) VALUES('2010','Tolga','Korkut');
INSERT INTO narrator(ssn,fname,lname) VALUES('2011','Serhat','Yiğit');
INSERT INTO narrator(ssn,fname,lname) VALUES('2012','Cüneyt','Kıran');
INSERT INTO narrator(ssn,fname,lname) VALUES('2013','Özlem','Dinsel');
INSERT INTO narrator(ssn,fname,lname) VALUES('2014','Mazlum','Kiper');
INSERT INTO narrator(ssn,fname,lname) VALUES('2015','Göker','Ersivri');
INSERT INTO narrator(ssn,fname,lname) VALUES('2016','Okan','Bayülgen');
INSERT INTO narrator(ssn,fname,lname) VALUES('2017','Mehmet','Konu');


INSERT INTO narrates VALUES('3000','2000','1000');
INSERT INTO narrates VALUES('3001','2001','1001');
INSERT INTO narrates VALUES('3002','2002','1002');
INSERT INTO narrates VALUES('3003','2003','1003');
INSERT INTO narrates VALUES('3004','2004','1004');
INSERT INTO narrates VALUES('3005','2005','1005');
INSERT INTO narrates VALUES('3006','2006','1006');
INSERT INTO narrates VALUES('3007','2007','1007');
INSERT INTO narrates VALUES('3008','2008','1008');
INSERT INTO narrates VALUES('3009','2009','1009');
INSERT INTO narrates VALUES('3010','2010','1010');
INSERT INTO narrates VALUES('3011','2011','1011');
INSERT INTO narrates VALUES('3012','2012','1012');
INSERT INTO narrates VALUES('3013','2013','1013');
INSERT INTO narrates VALUES('3014','2014','1014');
INSERT INTO narrates VALUES('3015','2015','1015');
INSERT INTO narrates VALUES('3016','2016','1016');
INSERT INTO narrates VALUES('3017','2016','1017');
INSERT INTO narrates VALUES('3018','2017','1018');
INSERT INTO narrates VALUES('3019','2014','1019');

--DROP SEQUENCE userseq;
CREATE SEQUENCE userseq
INCREMENT 1
START 4000;

INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Beyda','Güler','beydak','bg@gmail.com','bg123','12-DEC-2001');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Elif Sena','Yılmaz','elifsena','elylmz@gmail.com','12345','1-FEB-2001');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Şeymanur','Korkmaz','xseymal','skorkmaz@gmail.com','seyma123','4-OCT-2002');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Serkan','Ayvaz','serqan','sayvaz@gmail.com','1sayvaz1','7-OCT-1975');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Ali','Çetin','thisisceto','ceto@gmail.com','bela123','15-JUL-1986');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Merve','Öz','mervedeyim','merveler@gmail.com','gurlll','7-NOV-1999');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Gupse','Cici','cicikiz','cici@gmail.com','g6h7','28-NOV-2000');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Halis','Kaya','halis1mi','kaya@gmail.com','sym33','8-JUN-2000');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Fatma','Yılmaz','fatmahnimm06','fatmylm@gmail.com','kizim','19-OCT-1970');
INSERT INTO users(ssn,fname,lname,nickname,mail,psswd,bdate) VALUES(nextval('userseq'),'Özlem','Korkmaz','catlover','seyma@gmail.com','remix','30-NOV-1975');

--DROP SEQUENCE rentalseq;
CREATE SEQUENCE rentalseq
INCREMENT 1
START 5000;

INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4000','1000','17-JAN-2023',1,8);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4000','1003','17-JAN-2023',8,56);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4000','1001','17-JAN-2023',6,78);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4000','1011','17-JAN-2023',6,48);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1006','17-JAN-2023',5,100);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1007','17-JAN-2023',5,40);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1008','17-JAN-2023',3,52);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1009','17-JAN-2023',7,70);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1010','17-JAN-2023',4,76);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4001','1011','17-JAN-2023',2,16);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4002','1012','17-JAN-2023',7,105);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4002','1013','17-JAN-2023',5,80);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4002','1014','17-JAN-2023',4,28);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4002','1015','17-JAN-2023',2,12);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4002','1016','17-JAN-2023',2,10);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1000','17-JAN-2023',3,24);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1001','17-JAN-2023',4,42);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1002','17-JAN-2023',5,75);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1003','17-JAN-2023',4,28);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1004','17-JAN-2023',5,70);
INSERT INTO rental(id,ussn,bookid,rentDay,dayLimit,price) VALUES(nextval('rentalseq'),'4003','1005','17-JAN-2023',7,70);


CREATE OR REPLACE FUNCTION ReduceBalance( ) 
RETURNS TRIGGER AS $$ 
DECLARE 
	b INTEGER;
BEGIN 

	SELECT balance INTO b
	FROM users
	WHERE ssn = new.ussn;
	
	IF b >= new.price THEN
		UPDATE users
		SET balance = balance - new.price
		WHERE ssn = new.ussn;
		RETURN NEW;
	ELSE
		RAISE WARNING 'Your balance is not enough to rent this book';
		RETURN OLD;
	END IF;
END; 
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER trigger2 BEFORE INSERT ON rental FOR EACH ROW EXECUTE PROCEDURE ReduceBalance();

CREATE OR REPLACE FUNCTION controlRent( ) 
RETURNS TRIGGER AS $$ 
DECLARE
 	cur CURSOR FOR SELECT bookid
 				FROM rental
				WHERE ussn = new.ussn;
BEGIN 
	FOR c in cur LOOP
		IF c.bookid = new.bookid THEN
			RAISE WARNING 'You have already rented this book';
			RETURN OLD;
		END IF;
	END LOOP;
	RETURN NEW;
END; 
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER trigger3 BEFORE INSERT ON rental FOR EACH ROW EXECUTE PROCEDURE controlRent();

CREATE OR REPLACE VIEW userLibrary AS
SELECT name,author,n.fname,n.lname,category,time,u.ssn,books.id
FROM books,users u,rental,narrates r,narrator n
WHERE books.id = rental.bookid AND rental.ussn = u.ssn AND books.id = r.bookid AND nssn=n.ssn;


CREATE TYPE book AS (bookName VARCHAR(30), author VARCHAR(30), narratorFname VARCHAR(15), 
					 narratorLname VARCHAR(15), time INTEGER, category VARCHAR(30));
--DROP FUNCTION searchBook(VARCHAR)
CREATE OR REPLACE FUNCTION SearchBook(bookName VARCHAR(30))
RETURNS book[] AS $$
DECLARE 
	books book[];
	book_cursor CURSOR FOR  SELECT name, author, fname, lname, time, category
							FROM books b, narrates n, narrator
							WHERE b.id = bookid AND nssn = ssn;
	i INTEGER;
BEGIN
	i := 1; 
	FOR b IN book_cursor LOOP
		IF b.name LIKE Concat('%',bookName,'%') THEN
			books[i] = b;
			i = i+1;
		END IF;
	END LOOP; 
	RETURN books;
END;
$$LANGUAGE 'plpgsql';

--Select SearchBook('Dahi Dik');


--DROP FUNCTION favCategory(VARCHAR(15));
CREATE OR REPLACE FUNCTION favCategory(user_ssn varchar(15),OUT num int,OUT outCat varchar(15))
AS $$
DECLARE
i int := 0;
BEGIN
	SELECT count(category),category INTO num,outCat
	FROM users,books,rental
	WHERE users.ssn = rental.ussn AND rental.bookid = books.id
	GROUP BY category,ssn
	HAVING ssn = user_ssn
	ORDER BY count(category) DESC
	LIMIT 1;
END;
$$LANGUAGE 'plpgsql';
--SELECT favCategory('4000');

SELECT name, author, fname, lname, time, category, audience 
FROM books b, narrates n, narrator 
WHERE b.id = bookid AND nssn = ssn;


--DROP FUNCTION increaseBalance(INTEGER,VARCHAR(10));
CREATE OR REPLACE FUNCTION increaseBalance(amount INTEGER, userssn VARCHAR(10))
RETURNS void AS $$
BEGIN
	UPDATE users
	SET balance = balance + amount
	WHERE ssn = userssn;
END;
$$LANGUAGE 'plpgsql';
--SELECT increaseBalance(50, '4001')


--DROP FUNCTION calculatePrice(INTEGER,VARCHAR);
CREATE OR REPLACE FUNCTION calculatePrice(dayLimit INTEGER, book_id VARCHAR)
RETURNS INTEGER AS $$
DECLARE 
	total_price INTEGER;
	book_price INTEGER;
BEGIN

	SELECT dailyPrice INTO book_price
	FROM books
	WHERE id = book_id;
	
	total_price = book_price * dayLimit;
	RETURN total_price;
END;
$$LANGUAGE 'plpgsql';
--Select calculatePrice(5, '1003');



--DROP FUNCTION filterbooks(VARCHAR, INTEGER);

CREATE OR REPLACE FUNCTION filterBooks(Category VARCHAR, hour INTEGER)
RETURNS book[] AS $$
DECLARE 
	books book[];
	i INTEGER;
	book_cursor CURSOR FOR  SELECT name, author, fname, lname, time, b.category 
						FROM books b, narrates n, narrator
						WHERE b.id = bookid AND nssn = ssn;
BEGIN
	i := 1;
	IF Category = 'All' AND hour  != 0 THEN 
		FOR b IN book_cursor LOOP
			IF b.time>=(hour-1)*120 AND b.time <= hour*120 THEN
				books[i] = b;
				i = i+1;
			END IF;
		END LOOP; 
	ELSIF Category != 'All' AND hour = 0 THEN 
		FOR b IN book_cursor LOOP
			IF b.category = Category THEN
				books[i] = b;
				i = i+1;
			END IF;
		END LOOP; 
	ELSIF Category != 'All' AND hour != 0 THEN 
		FOR b IN book_cursor LOOP
			IF b.time>=(hour-1)*120 AND b.time <= hour*120 AND b.category = Category THEN
				books[i] = b;
				i = i+1;
			END IF;
		END LOOP; 
	ELSIF Category = 'All' AND hour = 0 THEN 
		FOR b IN book_cursor LOOP
				books[i] = b;
				i = i+1;
		END LOOP; 
	END IF;	
	RETURN books;
END;
$$LANGUAGE 'plpgsql';
