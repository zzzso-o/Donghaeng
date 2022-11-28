# 프로젝트 소개

### 목차

- [동행](#동행)
- [기능 소개](#기능-소개)
- [아키텍쳐 구조](#아키텍쳐-구조)
- [기술스택](#기술스택)
- [추천 알고리즘](#추천-알고리즘)
- [팀원소개](#팀원소개)



### 동행

---------------------------------

![d_main](https://user-images.githubusercontent.com/97646028/204224125-0dd0a486-5b5c-4626-9c55-fb425ad8a5ec.png)

> ### <span style="background-color:#f1f8ff">"부모와 자녀 세대 사이 취향의 거리를 좁히고, <br>가족간 화합을 도모할 수 있는 여행지 추천 서비스"</span>

💞 "동행"은 부모님과 자녀들이 여행지에서 함께 걷는다는 의미입니다. 

👨‍👩‍👧 설문 조사를 통해 부모님의 취향을 파악하고, 그에 따른 여행지 추천을 해줍니다.

📸 여행지에서 할 수 있는 미션을 제공합니다. 사진을 업로드 하여 미션을 달성하고, 앨범에서 업로드한 사진을 모아 볼 수 있습니다.



### 기능 소개

--------------------------------------

<details>
<summary>메인화면</summary>
<div markdown="1">       
<img src=https://user-images.githubusercontent.com/97646028/204214846-4911a7e1-2c9e-4dc3-8112-a48db047ac52.gif>
<div>
맨 처음 들어가면 보이는 메인 화면입니다.<br/>
상단 네비게이션 바를 통해 여행 일정 생성, 마이페이지, 로그아웃 페이지로 이동합니다.
</div>
<img src=https://user-images.githubusercontent.com/97646028/204218501-61088254-19a3-4f1e-b684-c32a52798c12.gif>
    <div>
    스크롤을 내리면 현재 진행중인 여행 일정과 미션을 확인할 수 있습니다.<br>
        미션은 랜덤으로 3개가 제공됩니다. 마음에 들지 않을 시 새로고침 버튼을 눌러 바꿀 수 있습니다.<br>
        부모님과 즐기고 싶은 미션이 있을 경우 직접 추가할 수 있습니다.<br>
        가장 하단의 CLICK 버튼을 누르면 부모님에게 설문조사를 보낼 수 있는 페이지로 이동합니다.<br>
    </div>
</div>
</details>



<details>
<summary>설문조사</summary>
<div markdown="1">       
<img src=https://user-images.githubusercontent.com/97646028/204218626-dc13bf94-387e-498c-8fc4-6685e8baa4e2.gif>
    부모님의 여행 취향에 대한 설문조사입니다.<br>
    설문 링크 복사하기를 누르면 랜덤으로 생성된 링크가 복사됩니다.<br>
    이 링크를 카카오톡으로 공유하면 부모님이 직접 설문을 합니다.<br>
<img src=https://user-images.githubusercontent.com/97646028/204218554-dd1126b6-67b6-4499-a7e7-5cb041435680.gif>
    설문은 총 12개의 질문으로 이루어져있고 부모님을 배려하여 최대한 간단하게 답변할 수 있습니다.<br>
    이 결과를 바탕으로 여행 일정 생성 시 부모님이 선호할 만한 여행지를 먼저 띄워줍니다.
</div>
</details>



<details>
<summary>마이페이지</summary>
<div markdown="1">       
<img src=https://user-images.githubusercontent.com/97646028/204218791-02619d2b-c177-407f-ae12-44d1260d95f8.gif>
    마이페이지에서는 크게 내 정보와 지난 여행을 확인할 수 있습니다.<br>
    내 정보에서는 회원 정보를 수정하거나 탈퇴합니다.<br>
<img src=https://user-images.githubusercontent.com/97646028/204218880-a351ddc0-a480-4b3e-8df8-337a68927c2d.gif>
    지난 여행에서는 미션을 위해 업로드한 사진들을 여행 일정별로 모아 앨범처럼 볼 수 있습니다.<br>
    또한 지난 여행 일정과 방문했던 여행지 열람 기능을 구현했습니다.<br>
</div>
</details>



<details>
<summary>여행 일정 생성</summary>
<div markdown="1">       
<img src=https://user-images.githubusercontent.com/97646028/204221814-acfdb1e7-720a-430a-9667-2016ad1c0887.gif>
   여행 일정 생성을 위해 날짜를 선택합니다. <br>
   여행 가고 싶은 지역을 확대한 뒤 추천 받기를 누르면 추천 여행지와 음식점이 뜹니다.<br>
   원하는 곳을 골라 추가하거나 순서를 바꿔서 나만의 여행일정을 생성합니다.<br>
</div>
</details>



### 아키텍쳐 구조

------------------------------

![d_archi](https://user-images.githubusercontent.com/97646028/204222931-1a1a7b21-ff2c-4309-b4ef-fd89cf399988.png)



### 기술 스택

--------------------------

#### FRONTEND

- Javascript
- React

- Redux Toolkit

#### BACKEND

- Springboot
- MariaDB
- redis
- S3
- JPA

#### CI/CD

- Jenkins
- docker
- AWS



### 추천 알고리즘

-------------------------------

![image](https://user-images.githubusercontent.com/97646028/204227503-0bfee243-7ced-439f-96b2-069e308516fb.png)

동행의 여행지 추천은 사용자 기반 협업 필터링을 바탕으로 합니다. <br>

협업 필터링이란 사용자와 비슷한 취향의 사람들을 기반으로 추천해주는 시스템입니다.<br> 동행에서는 더미 데이터를 넣어 가상의 사용자들을 만들고,  많은 사용자로부터 얻은 취향 정보를 활용하였습니다. <br>축적된 사용자들의 집단 지성을 사용자 기반 협업 필터링에서는 SVD를 활용했습니다. <br>SVD란 Singular Value Decomposition의 약자로, 행렬을 특정한 구조로 분해하는 방식입니다.



### 팀원소개

------------------------

![image](https://user-images.githubusercontent.com/97646028/204227315-c802036c-c103-4040-9791-5740e10aa473.png)

<p align="right">(<a href="#">맨 위로</a>)</p>
