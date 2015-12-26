-- xep hang của 1 acc theo monhoc và do kho
select idMonHoc, tenMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  (
SELECT GROUP_CONCAT(DISTINCT diem 
ORDER BY diem DESC ) 
FROM xephang 
where  idMonHoc = 9 and doKho = 1
) )as hang,
FIND_IN_SET( idAccount,  (
SELECT GROUP_CONCAT(DISTINCT idAccount 
ORDER BY diem DESC , idXepHang ASC) 
FROM xephang 
where  idMonHoc = 9 and doKho = 1
) )

as viTri

-- xep hang của 1 acc theo monhoc và do kho ver2 - RUN 
select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  
 dsxephangDIEM(xh.idMonHoc, doKho) )as hang,
FIND_IN_SET( idXepHang,  
 dsxephangIDXEPHANG(idMonHoc, doKho) )
as viTri
from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id
where idAccount = 4 and xh.idMonHoc = 9 and doKho = 1 and FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho)) > 0
group by idMonHoc, doKho, tenMonHoc;

-- danh sach xep hang theo 1 mon hoc và độ khó ver2 (bo sung vi tri) - RUN

SELECT idXepHang, idMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem,  FIND_IN_SET( MAX(diem),  dsxephangDIEM(idMonHoc, doKho)
)as hang , FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho))as viTri 
from xephang 
where idMonHoc = 9 and doKho = 1 and FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho)) > 0
GROUP BY idMonHoc, doKho, idAccount, tenAcc
ORDER BY diem DESC
limit 0,100;

-- xep hang các môn học của 1 acc ver2 - RUN
select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, xh.doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  dsxephangDIEM(idMonHoc,doKho)) as hang
from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id
where idAccount = 3
group by idMonHoc, doKho, tenMonHoc
ORDER BY hang ASC, doKho ASC;

-- test
SELECT dsxephangDIEM(9, 1);
SELECT ds

-- chinh sua xep hang account
SELECT min(idXepHang) as idXepHang, idMonHoc,  idAccount, tenAcc, doKho , max(diem ) as diem
from xephang as xh
where diem = (select MAX(diem) from xephang as xh3 where xh.idAccount = xh3.idAccount and xh.tenAcc = xh3.tenAcc and xh.idMonHoc = xh3.idMonHoc and xh.doKho = xh3.doKho)
and idMonHoc = 9 and doKho = 1 
GROUP BY idMonHoc,  idAccount, tenAcc, doKho 
ORDER BY diem Desc , idXepHang Asc

-- test
select GROUP_CONCAT(DISTINCT diem)
from (
SELECT diem
from xephang as xh
where diem = (select MAX(diem) from xephang as xh2 where xh.idAccount = xh2.idAccount and xh.tenAcc = xh2.tenAcc and xh.idMonHoc = xh2.idMonHoc and xh.doKho = xh2.doKho)
and idMonHoc = 9 and doKho = 1
GROUP BY idMonHoc,  idAccount, tenAcc, doKho 
ORDER BY diem Desc , idXepHang Asc
) as total

-- danh sach xep hang theo 1 mon hoc và độ khó.

SELECT idMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem,  FIND_IN_SET( MAX(diem),  (
	SELECT GROUP_CONCAT(DISTINCT diem
	ORDER BY diem DESC ) 
	FROM xephang 
	where  idMonHoc = 9 and doKho = 1
) 
)as hang 
from xephang 
where idMonHoc = 9 and doKho = 1 
GROUP BY idMonHoc, doKho, idAccount, tenAcc
ORDER BY diem DESC
limit 0,15;


-- xep hang các môn học của 1 acc
select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, xh.doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  (
SELECT GROUP_CONCAT(DISTINCT diem
ORDER BY diem DESC ) 
FROM xephang 
where  idMonHoc = xh.idMonHoc and doKho = xh.doKho
)
) as hang
from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id
where idAccount = 5
group by idMonHoc, doKho, tenMonHoc
ORDER BY hang ASC;

-- message
select *
from message
ORDER BY id Desc 
limit 0,1;

-- xoa cac mon hoc bi mồ côi.
delete from monhoc
where id not in (SELECT DISTINCT dsMonhoc_id from nganh_monhoc)

-- xoa cac dapan bi mồ coi
delete from dapan
where cauhoi_id is null;