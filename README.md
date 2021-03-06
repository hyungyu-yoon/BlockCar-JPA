# 블록카

#### Demo
http://13.125.112.95/#/

### 블록체인 기반 중고차 거래 플랫폼
기존의 중고차 거래 플랫폼의 신뢰성 문제를 블록체인을 이용하여 해결하기위한 프로젝트
![블록카](https://user-images.githubusercontent.com/54695488/79346219-11803880-7f6d-11ea-9eea-6da09f32500d.png)

## 개발 환경
* 프론트 엔드 - React, Redux, React Hooks, webpack 4
* 백 엔드 - Spring boot, JPA, Mysql, AWS
* 블록체인 - Hyperledger fabric, docker AWS

### 맡은 역할
백 엔드 개발 REST API 구현, 인증을 위한 메시지 전송 기능

![개발환경](https://user-images.githubusercontent.com/54695488/79346737-abe07c00-7f6d-11ea-9b2d-0646880c66a1.PNG)



## 블록카 개요

블록카 서비스의 모든 차량 데이터는 하이퍼레저 패브릭 네트워크에 기록이 되어 있습니다. 차량의 정보, 사고이력, 점검이력의 데이터를 패브릭 네트워크에서 관리합니다. 차량을 제외한 회원, 게시물, 판매 정보에 관한 데이터는 Mysql DB를 사용하여 관리합니다. 
(모든 차량의 데이터는 이미 패브릭 네트워크에 등록되어 관리하고 있는 점을 가정하였습니다.)

### 블록카의 프로세스

##### 판매자
1. 판매자는 회원가입을 하고 로그인을 합니다.
2. 내차팔기 페이지에 들어가서 이름과 휴대폰 번호 차량 번호를 입력하고 인증요청을 합니다.
3. 받은 인증번호를 입력하고 차량이 확인이 되면 차량의 정보를 보여줍니다.
4. 차량 정보를 확인하고 픽업날짜, 장소, 가격을 입력합니다. (블록카 서비스는 판매자의 차량을 직접 픽업합니다.)
5. 정보 입력이 완료되면 신청하기를 누르면 판매신청이 완료됩니다.

##### 블록카
1. 블록카는 관리 페이지에서 판매차량이 있는지 확인합니다.
2. 판매차량을 확인 후 판매자의 위치로 찾아가 차량을 픽업합니다.
3. 판매차량을 점검 후 사진을 찍어 판매 게시물로 등록합니다.


##### 구매자
1. 구매자는 회원가입을 하고 로그인을 합니다.
2. 홈 화면에서 최근 등록된 차량을 확인 할 수 있고 셀렉트 박스를 선택하여 원하는 차량 모델을 검색할 수 있습니다.
3. 차량을 검색하고 사이드 바에서 가격, 연식, 주행거리, 연료 타입의 필터를 설정합니다.
4. 원하는 차량이 있다면 선택을 하고 상세 페이지를 확인합니다. 
5. 블록체인 기반의 신뢰성있는 차량의 정보, 약관을 확인하고 구매신청을 클릭합니다.
6. 본인 인증을 위해 이름, 휴대폰 번호를 입력하고 배송 날짜와 주소를 입력합니다.
7. 차량의 예약금을 결제하면 신청이 완료됩니다.

##### 블록카
1. 블록카는 관리 페이지에서 구매차량이 있는지 확인합니다.
2. 구매자를 확인하고 구매자의 위치로 찾아가 차량을 배송합니다.
3. 구매자와 함께 차량을 확인하고 서류를 작성하고 남은 금액을 결제합니다.


