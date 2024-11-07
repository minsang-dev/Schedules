// 일정 테이블 생성
CREATE TABLE Schedules
(
    id          INT         NOT NULL AUTO_INCREMENT,
    username   VARCHAR(25) NOT NULL,
    password    VARCHAR(25) NOT NULL,
    title       VARCHAR(25) NOT NULL,
    content     VARCHAR(50) NULL,
    createDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updateDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
);

// 일정 생성
INSERT INTO Schedules (user_name, title, content)
VALUES ('짱구', '주말 할 일 목록', '들숨날숨 쉬기');

// 전체 일정 조회
SELECT *
FROM Schedules
ORDER BY update_date DESC;

// 선택 일정 조회
SELECT *
FROM Schedules
WHERE id = 1;

// 선택한 일정 수정
UPDATE Schedules
SET content = '들숨날숨 쉬기'
WHERE id = 1;

// 선택한 일정 삭제
DELETE
from Schedules
WHERE id = 1;
