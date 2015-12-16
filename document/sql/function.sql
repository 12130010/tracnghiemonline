-- function return String list Diem
create FUNCTION dsxephangDIEM(idMH BIGINT, dk INT) returns text
BEGIN
DECLARE s text;
set s = (SELECT GROUP_CONCAT(DISTINCT diem ORDER BY diem DESC)
	from ( SELECT min(idXepHang) as idXepHang, idMonHoc,  idAccount, tenAcc, doKho , max(diem ) as diem
from xephang as xh
where diem = (select MAX(diem) from xephang as xh3 where xh.idAccount = xh3.idAccount and xh.tenAcc = xh3.tenAcc and xh.idMonHoc = xh3.idMonHoc and xh.doKho = xh3.doKho)
and idMonHoc = idMH and doKho = dk
GROUP BY idMonHoc,  idAccount, tenAcc, doKho 
ORDER BY diem Desc , idXepHang Asc)as tb ) 
;
return s;
END;
-- test
SELECT dsxephangDIEM(9,1,2);

SELECT * from xephang LIMIT 0,8

-- function return String list idXepHang
create FUNCTION dsxephangIDXEPHANG(idMH BIGINT, dk INT) returns text
BEGIN
DECLARE s text;
set s = (SELECT GROUP_CONCAT(DISTINCT idXepHang)
	from ( SELECT min(idXepHang) as idXepHang, idMonHoc,  idAccount, tenAcc, doKho , max(diem ) as diem
from xephang as xh
where diem = (select MAX(diem) from xephang as xh3 where xh.idAccount = xh3.idAccount and xh.tenAcc = xh3.tenAcc and xh.idMonHoc = xh3.idMonHoc and xh.doKho = xh3.doKho)
and idMonHoc = idMH and doKho = dk
GROUP BY idMonHoc,  idAccount, tenAcc, doKho 
ORDER BY diem Desc , idXepHang Asc)as tb ) 
;
return s;
END;
--
SELECT  dsxephangIDXEPHANG(9,1);

 SELECT min(idXepHang) as idXepHang, idMonHoc,  idAccount, tenAcc, doKho , max(diem ) as diem
from xephang as xh
where diem = (select MAX(diem) from xephang as xh3 where xh.idAccount = xh3.idAccount and xh.tenAcc = xh3.tenAcc and xh.idMonHoc = xh3.idMonHoc and xh.doKho = xh3.doKho)
and idMonHoc = 9 and doKho = 1
GROUP BY idMonHoc,  idAccount, tenAcc, doKho 
ORDER BY diem Desc , idXepHang Asc