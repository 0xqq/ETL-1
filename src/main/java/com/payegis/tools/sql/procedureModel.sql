#procedure syntax:输入userId，输出userName
#MySql支持 IN (传递给存储过程), OUT (从存储过程传出) 和 INOUT (对存储过程传入和传出)类型的参数, 存储过程的代码位于 BEGIN 和 END 语句内, 它们是一系列 SQL 语句 , 用来检索值, 然后保存到相应的变量 (通过指定INTO关键字);
CREATE PROCEDURE GetNameByID(
  IN userID INT,
  OUT userName VARCHAR(200)
)
  BEGIN
    # 定义局部变量
    DECLARE a INT;
    DECLARE c VARCHAR(12);
    DECLARE flag BOOLEAN;

    # 局部变量初始化
    SET flag = TRUE;
    SET a = 1;
    SET c = "initialize";

    # 查询
    SELECT name FROM user
      WHERE id = userID
    INTO c;

    # 将局部变量c的值赋给全局变量userName
    SELECT c INTO userName;

    # 条件语句
    IF flag THEN
      SELECT name FROM user
        WHERE id = userID
      INTO c;
    END IF;

    # 循环语句1
    WHILE a < 6 DO
      SET a = a + 1;
    END WHILE;

    # 循环语句2
    REPEAT
      INSERT INTO user VALUES (1, "chenzuoli");
      SET a = a + 1;
    UNTIL a = 10 END REPEAT;

    # 循环语句3
    label: LOOP
      INSERT INTO user VALUES (1, a);
      SET a = a+1;
      IF (a = 30) THEN
        LEAVE label;
      END IF;
    END LOOP;

  END ;
CALL GetNameByID(1, @userName);
SELECT @userName;
DROP PROCEDURE IF EXISTS GetNameByID;