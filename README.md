## Schedule 🌵
### 일정 관리 앱 서버 만들기
---
## API
| 기능         | Method | API PATH             | Request | Response   | 상태코드 : 성공 | 상태코드 : 실패 |
| ------------- | ------ | -------------------- | ------- | --------- | ------------- | ------------ |
| 일정 등록      | POST   | /api/schedules       | Body    | 등록 정보 | 201 | 400 |
| 일정 조회      | GET    | /api/schedules       | Param   | 다건 정보 | 200 | 404  |
| 선택 일정 조회 | GET    | /api/schedules/{id}  | Param   | 단건 정보 | 200 | 404 |
| 선택 일정 수정 | PUT    | /api/schedules/ {id} | Body    | 수정 정보 | 200 | 400 |
| 선택 일정 삭제 | DELETE | /api/schedules/ {id} | Param	  | -        | 200 | 400 or 404 |

---
<details>
  <summary>일정 등록</summary>
	
### RequestBody
 ``` json
{
    "username" : "작성자 명",
    "password" : "비밀번호",
    "title": "제목",
    "content": "내용"
}
```
### ResponseBody
Success - 201 CREATED
 ``` json
{
    "id" : 1,
    "user_name" : "작성자 명",
    "title" : "할 일 제목",
    "content" : "할 일 내용",
    "create_date" : "작성일자"
}
```
Fail - 400 BAD REQUEST
 ``` json
{
    "msg" : "`title`은 필수값입니다."
 }
```
</details>

<details>
  <summary>일정 조회</summary>
	
### ResponseBody
Success - 200 OK
``` json
{
 {
      "id" : 1,
      "user_name" : "작성자 명",
      "title" : "할 일 제목",
      "content" : "할 일 내용",
      "create_date" : "작성일자",
      "updae_date" : "수정일자"
  }
   {
      "id" : 2,
      "user_name" : "작성자 명",
      "title" : "할 일 제목2",
      "content" : "할 일 내용2",
      "create_date" : "작성일자",
      "updae_date" : "수정일자"
  }
   {
      "id" : 3,
      "user_name" : "작성자 명",
      "title" : "할 일 제목3",
      "content" : "할 일 내용3",
      "create_date" : "작성일자",
      "updae_date" : "수정일자"
  }
}
```
Fail - 400 404 NOT FOUND
``` json
{
    "msg" : "조회 실패 || 해당 ID를 가진 일정이 존재하지 않습니다."
}
```
</details>

<details>
  <summary>선택 일정 조회</summary>
	
### ResponseBody
Success - 200 OK
``` json
{
    "id" : 1,
    "user_name" : "작성자 명",
    "title" : "할 일 제목",
    "content" : "할 일 내용",
    "create_date" : "작성일자"
}
```
Fail - 404 NOT FOUND
```json
{
    "msg" : "조회 실패 || 해당 ID를 가진 일정이 존재하지 않습니다."
}
```
</details>

<details>
  <summary>선택 일정 수정</summary>
	
### ResponseBody
Success - 200 OK
``` json
{
    "id" : 1,
    "user_name" : "작성자 명",
    "title" : "할 일 제목",
    "content" : "할 일 내용",
    "create_date" : "작성일자",
    "updae_date" : "수정일자"
}
```	
Fail - 400 BAD REQUEST
```json
{
    "msg" : "`title`은 필수값입니다."
}
```
</details>

<details>
  <summary>선택 일정 삭제</summary>
	
### ResponseBody
Success - 200 OK
``` json
{
    "msg" : "삭제 완료"
}
```
Fail
ex) Fail - 400 BAD REQUEST
``` json
{
    "msg" : "지정일이 오늘인 일정은 삭제할 수 없습니다."
}
```
ex) Fail - 404 NOT FOUND
``` json
{
    "msg" : "존재하지 않는 ID 입니다."
}
```
</details>
