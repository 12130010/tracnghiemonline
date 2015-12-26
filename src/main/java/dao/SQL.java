package dao;

import model.Account;
import model.CauHoi;

public class SQL {
	public static String ACCOUNT = Account.class.getName();
	public static String CAUHOI = CauHoi.class.getName();
	
	public static String SELECT_ONLY_INFO_KHOA = "select * from khoa where id =? ";
	public static String SELECT_ONLY_INFO_NGANH = "select * from nganh where id =? ";
	public static String CHECK_USER_PASS = "from " + ACCOUNT + " where username = :user and password = :pass";
	public static String CHECK_USERNAME = "from " + ACCOUNT + " where username = :user";
	public static String LIST_CAUHOI_OF_MONHOC = "from " + CAUHOI + " where monhoc_id = :idMonHoc";
	public static String LIST_CAUHOI_OF_MONHOC_SQL = "select * from cauhoi where monhoc_id = :idMonHoc";
	/**LIST_CAUHOI_OF_MONHOC_WITH_DOKHO_SQL
	 * 1: idMonHoc
	 * 2: doKho
	 */
	public static String LIST_CAUHOI_OF_MONHOC_WITH_DOKHO_SQL = "select id from cauhoi where monhoc_id = ? and doKho = ?";
	/** RANK_THEO_ACCOUNT_AND_MON_SQL
	 * 1: idMonHoc
	 * 2: doKho
	 * 3: idAccount
	 * 4: idMonHoc
	 * 5: doKho
	 */

//	public static String RANK_THEO_ACCOUNT_AND_MON_SQL = "select idMonHoc, tenMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem), ( SELECT GROUP_CONCAT(DISTINCT diem ORDER BY diem DESC )  FROM xephang  where  idMonHoc = ? and doKho = ? ) ) as hang from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? and idMonHoc = ? and doKho = ? group by idMonHoc, doKho, tenMonHoc;";
	/** RANK_THEO_ACCOUNT_AND_MON_SQL
	 * 1: idAccount
	 * 2: idMonHoc
	 * 3: doKho
	 */
	public static String RANK_THEO_ACCOUNT_AND_MON_SQL = "select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),   dsxephangDIEM(xh.idMonHoc, doKho) )as hang, FIND_IN_SET( idXepHang,   dsxephangIDXEPHANG(idMonHoc, doKho) ) as viTri from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? and xh.idMonHoc = ? and doKho = ? and FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho)) > 0 group by idMonHoc, doKho, tenMonHoc; ";
	
	/**
	 * LIST_DAUBAN_THEO_MONHOC
	 * 1: idMonHoc
	 * 2: doKho
	 */
	public static String LIST_DAUBAN_THEO_MONHOC = "SELECT idXepHang, idMonHoc, idAccount, tenAcc, doKho,MAX(diem) diem,  FIND_IN_SET( MAX(diem),  dsxephangDIEM(idMonHoc, doKho) )as hang , FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho))as viTri from xephang where idMonHoc = ? and doKho = ? and FIND_IN_SET( idXepHang,  dsxephangIDXEPHANG(idMonHoc, doKho)) > 0 GROUP BY idMonHoc, doKho, idAccount, tenAcc ORDER BY diem DESC limit 0,100;";
	
	/**RANK_THEO_ACCOUNT_SQL
	 * 1: idAccount
	 */
	public static String LIST_RANK_THEO_ACCOUNT_SQL = "select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, xh.doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  dsxephangDIEM(idMonHoc,doKho)) as hang from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? group by idMonHoc, doKho, tenMonHoc ORDER BY hang ASC, doKho ASC;";
	/**GET_TEN_MONHOC
	 * 1: idMonHoc
	 */
	public static String GET_TEN_MONHOC = "select id as idMonHoc, tenMonHoc from monhoc where id = ?";
	/**SAVE_MESSAGE
	 * :message : message
	 */
	public static String SAVE_MESSAGE = "insert into message (message) values (:message);" ;
	/**GET_MESSAGE
	 * 1 : limit
	 */
	public static String GET_MESSAGE = "select * from message order by  id DESC limit 0,?;" ;
	/**INSERT_MONHOC_REF_NGANH
	 * 1 : Nganh_id
	 * 2 : dsMonHoc_id
	 */
	public static String INSERT_MONHOC_REF_NGANH = "insert into nganh_monhoc (Nganh_id, dsMonHoc_id) values (:Nganh_id,:dsMonHoc_id)";
	/**
	 * monhoc_id
	 * id
	 */
	public static String UPDATE_REF_MONHOC_AFTER_INSERT_CAUHOI = "update cauhoi set monhoc_id = :monhoc_id where id = :id";
}
