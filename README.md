# weight-training
내가 쓸려고 만드는 웹페이지!!


<details>
<summary>기능 정의서</summary>
<div markdown="1">

### # 개요

본 문서는 오로지 개인을 위한 “운동기록을 통해 자신의 운동을 전반적으로 돌아보는 웹을 만들어 보기 위한 시스템”을 만들기 위한 요구사항을 정의한 문서이다.

그렇다 첫 문장만 보더라도 필자는 운동을 정말 좋아하는 사람이다. 해당 사람으로서 기록을 통해 보다 편리한 기록 웹을 만들어 보기 위해서 해당 개발을 선택했으며,

본 문서는 가능한 구체적이며 간결하게 표현되어야 하고 추후 시험이 가능해야 한다. 또한 본 문서를 사용하는 대상은 본 과제를 기획하는 오로지 나 혼자만 하는 개발입니다.

### # 요구사항 명세서는 다음과 같은 구성을 되어 있다.

### 로그인 페이지

- 로그인
    - 메인페이지 이동
- 회원 가입
    - Google , Kakao , Naver 버튼 별 연동 이 가능해야한다. 또한 그에 따른 정보를 팝업창을 통해서 연결이 가능 해야한다.
- 비밀번호 찾기
    - 비밀번호 찾기 페이지 단 해당 서비스 회원가입을 통해서 가입된 회원만 가능합니다.

### 메인 페이지

- 간단한 닉네임을 통해 프로필
- 나의 루틴 목록
    - 각 목록중 하나의 버튼을 누른다면 해당 운동들이 나열되면서 해당 페이지로 이동한다.
- 루틴 추가
    - 해당 운동은 루틴 추가 페이지로 이동한다.
- 만약 운동을 시작후 메인페이지로 이동시 끝나지 않은 상태이므로 루틴추가 버튼은 → 운동 종료 버튼으로 바뀐다.

### 운동 시작전 페이지

- 간단히 어느 운동을 하는지에 대한 정보를 담고 있다.
- 해당 운동의 순서는 순서대로 흐르기 때문에 순서를 조정할 수 있다.
- 시작하기 버튼을 클릭한다면 해당 시작중인 페이지로 이동한다.
- 시작전에 운동을 즉시 운동 종목을 바꿀 수 있다.

### 운동 시작 페이지

- 운동에 대한 기본적인 세트 수 는 4세트 이다.
- 운동 세트 수는 추가 삭제가 가능해야한다.
- 사용자의 편의성을 위해 모든 세트 완료기능을 추가
    - 어떤 상태에서도 세트 수 갯 수는 그 즉시 기록이 된다.
- 모든 세트 수 를 완료 했다면 그 즉시 다음 종목으로 이동한다.
- 모든 운동을 마쳤다면 통해서 간단히  느낀점을 적을 수 있다. 그런 다음 모든 운동은 종료된다.

### 나만의 루틴 페이지

- 운동 종목들이 나열된다.
- 운동 종목을 선택해서 추가 삭제가 언제든 가능해야한다.
- 운동 종목은 순서대로 작성 되어야하기 때문에 각 운동 종목의 순서를 조정할 수 있다.
- 운동 종목을 대체할 수 있는 버튼
- 운동 시작 버튼
- 운동 저장 버튼 : 운동 현황이 반드시 저장되어야 하며, 운동을 시작중에도 저장이 되어 있어야 한다.
- 나만의 루틴을 공유할지의 여부도 정할 수 있다.

### 운동 기록 페이지

- 그날 같은 루틴에 있어서 저번과 이번을 비교하여 세트 수 및  갯 수를 비교하여 조금이라도 증량 및 세트 수 가 증가 했다면 간단한 칭찬 멘트를 날려준다.
- 달력에는 운동한 날짜에 간단히 했다는 체크가 되어있다.
    - 해당 달력클릭시 팝업창을 통해 그날 운동했던 내용을 볼 수 있다.

 

<aside>
💡 아직까지 생각나는 것이 여기까지이다. 하지만 나는 이러한 4가지의 종목으로 지속적인 발전사항으로 계속해서 추가할 것이다. 그렇기에 지금 현재는 두서가 없다. 하지만 되는데까지 지속적으로 추가적인 상세 기능들을 추가할 것이다.
</aside>
</div>
</details>
<details>
<summary>사용자 흐름</summary>
<div markdown="1">

![image](https://user-images.githubusercontent.com/48014869/236188161-adfce986-c0cc-4a15-ab9e-3f0c62d467a8.png)

</div>
</details>
</div>
</details>
<details>
<summary>ERD Diamgram</summary>
<div markdown="1">

![image](https://user-images.githubusercontent.com/48014869/236673218-8afdeddb-397b-4859-bb14-533e760963ab.png)

</div>
</details>

<details>
<summary>UI / UX (진행중 기본적인 뼈대 구성)</summary>
<div markdown="1">

![image](https://user-images.githubusercontent.com/48014869/236675979-87ce4746-e193-4d4b-8888-415a2e092714.png)

</div>
</details>
