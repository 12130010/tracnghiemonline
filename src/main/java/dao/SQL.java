package dao;

import model.Account;
import model.CauHoi;

public class SQL {
	public static String ACCOUNT = Account.class.getName();
	public static String CAUHOI = CauHoi.class.getName();
	
	public static String SELECT_ONLY_INFO_KHOA = "select * from khoa where id =? ";
	public static String SELECT_ONLY_INFO_NGANH = "select * from nganh where id =? ";
	public static String CHECK_USER_PASS = "from " + ACCOUNT + " where username = :user and password = :pass";
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
//	public static String RANK_THEO_ACCOUNT_AND_MON_SQL = "select idMonHoc,tenMonHoc, idAccount, tenAcc, doKho, MAX(diem) from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? and idMonHoc = ? and doKho = ? group by idMonHoc, doKho, tenMonHoc;";
	public static String RANK_THEO_ACCOUNT_AND_MON_SQL = "select idMonHoc, tenMonHoc, idAccount, tenAcc, doKho,MAX(diem), FIND_IN_SET( MAX(diem), ( SELECT GROUP_CONCAT( diem ORDER BY diem DESC )  FROM xephang  where  idMonHoc = ? and doKho = ? ) ) as hang from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? and idMonHoc = ? and doKho = ? group by idMonHoc, doKho, tenMonHoc;";
	/**
	 * LIST_DAUBAN_THEO_MONHOC
	 * 1: idMonHoc
	 * 2: doKho
	 * 3: idMonHoc
	 * 4: doKho
	 */
	public static String LIST_DAUBAN_THEO_MONHOC = "SELECT idMonHoc, idAccount, tenAcc, doKho, MAX(diem) diem,  FIND_IN_SET( MAX(diem),  ( SELECT GROUP_CONCAT( DISTINCT diem	ORDER BY diem DESC ) 	FROM xephang 	where  idMonHoc = ? and doKho = ?) ) as hang from xephang where idMonHoc = ? and doKho = ? GROUP BY idMonHoc, doKho, idAccount, tenAcc ORDER BY diem DESC limit 0,100;";
	/**RANK_THEO_ACCOUNT_SQL
	 * 1: idAccount
	 */
	public static String RANK_THEO_ACCOUNT_SQL = "select xh.idMonHoc, tenMonHoc, idAccount, tenAcc, xh.doKho,MAX(diem) diem, FIND_IN_SET( MAX(diem),  ( SELECT GROUP_CONCAT(DISTINCT diem ORDER BY diem DESC )  FROM xephang where  idMonHoc = xh.idMonHoc and doKho = xh.doKho ) ) as hang from xephang as xh INNER JOIN monhoc as mh on  xh.idMonHoc = mh.id where idAccount = ? group by idMonHoc, doKho, tenMonHoc ORDER BY hang ASC;";
	/**GET_TEN_MONHOC
	 * 1: idMonHoc
	 */
	public static String GET_TEN_MONHOC = "select id as idMonHoc, tenMonHoc from monhoc where id = ?";
}
