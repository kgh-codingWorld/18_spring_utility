CREATE DATABASE SPRING_UTILITY;
USE SPRING_UTILITY;

# chapter01_sqlLogging , chapter04_scheduler 데이터
CREATE TABLE PRODUCT (
    PRODUCT_ID 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NM 		VARCHAR(100),
	PRICE 			INT,
	DELIVERY_PRICE 	INT,
	ENROLL_AT		TIMESTAMP,
	BRAND_ID		BIGINT
);

INSERT INTO 
		PRODUCT 
VALUES
	(1 , '삼성전자 2021 노트북 플러스2 15.6' , 598000 , 0 , '2021-01-07' , 1),  
	(2 , '삼성전자 2021 갤럭시북 15.6' , 1208000 , 0 , '2021-02-28' , 1),
	(3 , 'LG전자 10세대 코어i7 윈10탑재 17형 LG 그램 2020년형 17Z90N'  , 2149000 , 0 , '2021-03-07' , 2),
	(4 , 'LG전자 2021그램 360 14' , 1740000 , 0  , '2021-07-08' , 2),
	(5 , 'LG전자 2020 울트라 PC 14' , 477000 , 0 , '2021-10-10' , 2),
	(6 , '2020 맥북 프로 13' , 2129650 , 3000 , '2022-01-01' , 3),
	(7 , 'Apple 2020 맥북 에어 13' , 1489800 , 3000 , '2022-03-03' , 3),
	(8 , '레노버 2021 IdeaPad Slim3 15.6' , 2129650 , 2500 , '2022-07-07' , 4),
	(9 , '기가바이트 2021 Gaming G5 15.6' , 1499000 , 2500 , '2022-11-11' , 5),
	(10 , 'HP 2021 노트북 15s' , 768840 , 2500 , '2022-12-31' , 6);

	
CREATE TABLE BRAND (
    BRAND_ID	BIGINT PRIMARY KEY,
    BRAND_NM	VARCHAR(200),
    ENTERED_AT 	TIMESTAMP,
    ACTIVE_YN	CHAR
);

INSERT INTO 
		BRAND 
VALUES
	(1 , 'samsung' , '2021-01-01' , 'Y'), 
	(2 , 'lg' , '2021-01-01' , 'Y'),  
	(3 , 'apple' , '2021-01-01' , 'Y'),  
	(4 , 'lenovo' , '2021-01-01' , 'N'),  
	(5 , 'gigabyte' , '2021-01-01' , 'N'),  
	(6 , 'hp' , '2021-01-01' , 'N'); 


# chapter03_transaction 데이터
CREATE TABLE ACCOUNT (
	ACCOUNT_ID VARCHAR(5),
	BALANCE    LONG
);
INSERT INTO ACCOUNT VALUES('111' , 300000);
INSERT INTO ACCOUNT VALUES('222' , 300000);


CREATE TABLE MEMBER(
	MEMBER_ID VARCHAR(10),
	POINT     INT
);
INSERT INTO MEMBER VALUES('user1' , 10000);

CREATE TABLE CART(
	CART_CNT INT,
	MEMBER_ID VARCHAR(10)
);
INSERT INTO CART VALUES(10 , 'user1');

CREATE TABLE `ORDER`(
	ORDER_CNT INT,
	MEMBER_ID VARCHAR(10)
);
INSERT INTO `ORDER` VALUES(0 , 'user1');

