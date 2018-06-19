#test
DELIMITER $$
DROP PROCEDURE IF EXISTS test;
CREATE PROCEDURE test(OUT result VARCHAR(120))
  BEGIN
    DECLARE end INT DEFAULT 0;
    DECLARE cursor_name CURSOR FOR SELECT data_txt FROM query_log_his;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET end = 1;
    OPEN cursor_name;
    FETCH cursor_name INTO result;
    WHILE end <> 1 DO
      FETCH cursor_name INTO result;
    END WHILE;
    #   SELECT DISTINCT q.data_txt FROM service, query_log_his q WHERE service.base_service_id = serviceId AND service.app_id = q.appid AND q.data_txt NOT REGEXP '(\"([^,^\"]+)\":\"([^:^\"]+)\")|(\"([^,^\"]+)\":([\\d]+))' INTO result;
    CLOSE cursor_name;
  END;
$$
DELIMITER ;
CALL test(@result);
SELECT @result;
DROP PROCEDURE IF EXISTS test;

#存储过程输出结果集
DELIMITER $$
DROP PROCEDURE IF EXISTS testResultSet;
CREATE PROCEDURE testResultSet()
  BEGIN
#      SELECT DISTINCT data_txt FROM query_log_his;
    SELECT DISTINCT q.data_txt FROM service, query_log_his q WHERE service.base_service_id = serviceId AND service.app_id = q.appid AND q.data_txt NOT REGEXP '(\"([^,^\"]+)\":\"([^:^\"]+)\")|(\"([^,^\"]+)\":([\\d]+))' INTO result;
  END;
$$
DELIMITER ;
CALL testResultSet();
DROP PROCEDURE IF EXISTS testResultSet;